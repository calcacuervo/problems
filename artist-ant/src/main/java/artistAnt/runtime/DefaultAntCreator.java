package artistAnt.runtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import artistAnt.actions.OrientationMovement;
import artistAnt.actions.impl.AntMovementAction;
import artistAnt.actions.impl.ColourRuleBasedOrientationAction;
import artistAnt.actions.impl.IterationAction;
import artistAnt.actions.impl.PaletteBasedPainter;
import artistAnt.api.AntAction;
import artistAnt.api.AntLifetimeStrategy;
import artistAnt.api.AntCreator;
import artistAnt.api.InputParser.InputProperty;
import artistAnt.event.EventRegistryFactory;
import artistAnt.event.EventType;
import artistAnt.exception.AntConfigurationParseException;
import artistAnt.history.AntPositionHistoryService;
import artistAnt.history.HistoryServiceRegistry;
import artistAnt.history.HistoryServiceRegistry.HistoryServiceKey;
import artistAnt.model.Ant;
import artistAnt.model.CellAttribute;
import artistAnt.model.CellAttributeType;
import artistAnt.model.Colour;
import artistAnt.model.ColourAttribute;
import artistAnt.model.Grid;
import artistAnt.model.Orientation;
import artistAnt.model.Position;

/**
 * This is a default implementation ant creator, that will create an ant, grid,
 * action, palette as described in the original problem. It will receive an
 * inputMap, the initial Orientation. It will create three default actions in
 * the following order: - Change Orientation. - Paint Grid - Move forward.
 * 
 * This actions list and order can be changed by overriding the method
 * {@link DefaultAntCreator#generateActions(String, String)}
 * 
 * @author calcacuervo
 * 
 */
public class DefaultAntCreator implements AntCreator {

	/**
	 * This is the input model map.
	 */
	private Map<InputProperty, String> inputMap;

	/**
	 * The initial Orientation of the ant.
	 */
	private Orientation defaultOrientation;

	/**
	 * It is a map, where the key is an String that will represent a orientation
	 * change (Left/Right), and the value, the actual
	 * {@link OrientationMovement}.
	 */
	private Map<String, OrientationMovement> orientationMovements;

	/**
	 * This is the {@link AntLifetimeStrategy} implementation to manage the
	 * lifecycle of this executions.
	 */
	private AntLifetimeStrategy lifetimeStrategy;

	/**
	 * Extra actions to be configured.
	 */
	private List<AntAction> extraActions;
	
	/**
	 * Creates a new {@link DefaultAntCreator}.
	 * 
	 * @param inputMap
	 *            must not be null.
	 * @param defaultOrientation
	 *            must not be null.
	 * @param orientationMovements
	 *            must not be null.
	 */
	public DefaultAntCreator(Map<InputProperty, String> inputMap,
			Orientation defaultOrientation,
			Map<String, OrientationMovement> orientationMovements) {
		this(inputMap, defaultOrientation, orientationMovements, Collections.EMPTY_LIST);
	}
	
	/**
	 * Creates a new {@link DefaultAntCreator}.
	 * 
	 * @param inputMap
	 *            must not be null.
	 * @param defaultOrientation
	 *            must not be null.
	 * @param orientationMovements
	 *            must not be null.
	 */
	public DefaultAntCreator(Map<InputProperty, String> inputMap,
			Orientation defaultOrientation,
			Map<String, OrientationMovement> orientationMovements,
			List<AntAction> extraActions) {
		this.orientationMovements = orientationMovements;
		this.defaultOrientation = defaultOrientation;
		this.inputMap = inputMap;
		this.extraActions = extraActions;
	}

	/**
	 * Creates a new ant with the given configuration, following the original
	 * requirements given as default. It will throw
	 * {@link AntConfigurationParseException} if there is some error when
	 * parsing the configuration.
	 */
	@Override
	public Ant createAnt() {
		// int posX = Integer.parseInt(inputMap.get(InputProperty.POSX));
		// int posY = Integer.parseInt(inputMap.get(InputProperty.POSY));
		String colours = inputMap.get(InputProperty.COLOURS);
		String orientationRules = inputMap.get(InputProperty.ORIENTATION_RULES);
		Colour initialColour = Colour.valueOf(colours.substring(0, 1));
		ColourAttribute coloutAttribute = new ColourAttribute(initialColour);
		Map<CellAttributeType, CellAttribute> defaults = new HashMap<CellAttributeType, CellAttribute>();
		defaults.put(CellAttributeType.COLOUR_ATTRIBUTE, coloutAttribute);
		Grid grid = new Grid(defaults);

		List<AntAction> actions = generateActions(colours, orientationRules);

		//now add some extra actions
		for (AntAction antAction : this.extraActions) {
			actions.add(antAction);
		}
		int initialPosX = 0;
		int initialPosY = 0;
		// this ant will have multiple actions, so we have to create the
		// composite action.
		Ant ant = new Ant(defaultOrientation, new Position(new int[] {
				initialPosX, initialPosY }), grid, new IterationAction(actions,
				EventRegistryFactory.getRegistryInstance()));
		this.attachListeners(inputMap, ant);
		return ant;
	}

	/**
	 * This method generate default actions and can be overriden for anyone that
	 * needs different implementation.
	 * 
	 * @param colours
	 * @param orientationRules
	 * @return MUST NOT RETURN NULL!
	 */
	protected List<AntAction> generateActions(String colours,
			String orientationRules) {
		AntMovementAction movementAction = new AntMovementAction(
				EventRegistryFactory.getRegistryInstance());
		ColourRuleBasedOrientationAction orientationAction = new ColourRuleBasedOrientationAction(
				this.buildMovementsMap(colours, orientationRules));
		PaletteBasedPainter painter = new PaletteBasedPainter(
				this.buildPallete(colours),
				EventRegistryFactory.getRegistryInstance());

		List<AntAction> actions = new ArrayList<AntAction>();
		actions.add(orientationAction);
		actions.add(painter);
		actions.add(movementAction);
		return actions;
	}

	/**
	 * Builds the colour palette from an string. It simply takes every char from
	 * the string and tries to get it from {@link Colour} enum. It will throw an
	 * {@link AntConfigurationParseException} if it cannot find one of the given
	 * colours.
	 * 
	 * @param stringPallete
	 * @return
	 */
	protected List<Colour> buildPallete(String stringPallete) {
		List<Colour> pallete = new ArrayList<Colour>();
		for (int i = 0; i < stringPallete.length(); i++) {
			String stringColour = stringPallete.substring(i, i + 1);
			Colour colour = Colour.valueOf(stringColour);
			if (colour != null) {
				pallete.add(colour);
			} else {
				throw new AntConfigurationParseException(
						"Could not find color " + stringColour
								+ " from given paletter.");
			}
		}
		return pallete;
	}

	/**
	 * It will parse the movements map. For a colour, it will move right or left
	 * depending in the rules string. This method will throw an
	 * {@link AntConfigurationParseException} if colours length does not match
	 * rules length, or if there is some rules char that it is not configured in
	 * orientatioMovements map.
	 * 
	 * @param colours
	 * @param rules
	 * @return
	 */
	protected Map<Colour, OrientationMovement> buildMovementsMap(
			String colours, String rules) {
		if (colours.length() != rules.length()) {
			throw new AntConfigurationParseException(
					"Malformed input: Colors and rules count does not match");
		}
		Map<Colour, OrientationMovement> movementMap = new HashMap<Colour, OrientationMovement>();
		for (int i = 0; i < colours.length(); i++) {
			String colourString = colours.substring(i, i + 1);
			Colour colour = null; 
			try {
				colour = Colour.valueOf(colourString);
			} catch (IllegalArgumentException iae) {
				throw new AntConfigurationParseException(iae);
			}
			String rule = rules.substring(i, i + 1);
			OrientationMovement om = this.orientationMovements.get(rule);
			if (om != null && colour != null) {
				movementMap.put(colour, om);
			} else {
				throw new AntConfigurationParseException(
						"Malformed input: One of the movements (Left/Right) strings is not configured for this configuration.");
			}
		}
		return movementMap;
	}

	/**
	 * This will attach needed listeners
	 */
	protected void attachListeners(Map<InputProperty, String> input, Ant ant) {
		this.lifetimeStrategy = new IterationCountLifetimeStrategy(
				Integer.parseInt(input.get(InputProperty.ITERATIONS)));
		EventRegistryFactory.getRegistryInstance().register(
				EventType.ITERATION_FINISHED,
				(IterationCountLifetimeStrategy) lifetimeStrategy);
		AntPositionHistoryService historyService = new AntPositionHistoryService(
				ant);
		HistoryServiceRegistry.registerNewHistoryService(
				HistoryServiceKey.POSITION_HISTORY_SERVICE, historyService);
		EventRegistryFactory.getRegistryInstance().register(
				EventType.ANT_CHANGED, historyService);
	}

	public AntLifetimeStrategy getAntLifetimeStrategy() {
		return this.lifetimeStrategy;
	}

}
