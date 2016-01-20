package artistAnt.integration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import junit.framework.Assert;

import org.junit.Test;

import artistAnt.actions.OrientationMovement;
import artistAnt.actions.impl.AntMovementAction;
import artistAnt.actions.impl.ColourRuleBasedOrientationAction;
import artistAnt.actions.impl.FirstTimeConditionedAntMovementAction;
import artistAnt.actions.impl.LeftOrientationMovement;
import artistAnt.actions.impl.PaletteBasedPainter;
import artistAnt.actions.impl.RightOrientationMovement;
import artistAnt.api.AntAction;
import artistAnt.api.HistoryService;
import artistAnt.api.InputParser.InputProperty;
import artistAnt.event.EventRegistryFactory;
import artistAnt.event.EventType;
import artistAnt.history.HistoryServiceRegistry;
import artistAnt.history.HistoryServiceRegistry.HistoryServiceKey;
import artistAnt.input.InputParserImpl;
import artistAnt.model.Ant;
import artistAnt.model.Orientation;
import artistAnt.model.Position;
import artistAnt.output.AntPositionOutputParser;
import artistAnt.output.ImageOutputParser;
import artistAnt.runtime.AntMovementExecutor;
import artistAnt.runtime.DefaultAntCreator;
import artistAnt.visual.GridFrame;

/**
 * This test corresponds to case 2 presented in requirements:
 * Input 3 3 WYB LRR 7
 * 
 * Output
 * W|W|Y|
 * Y|B|Y|
 * Y|Y|W|
 * 0 1
 * 
 * @author calcacuervo
 * 
 */
public class Example2Test {

	/**
	 * This is one of the tests provided in the example.
	 */

	/**
	 * This is one of the tests provided in the example.
	 */
	@Test
	public void integration2() {
		InputParserImpl parser = new InputParserImpl();
		String input = "0 0 RGY LRL 4";
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());

		Orientation defaultOrientation = Orientation.N;
		Map<String, OrientationMovement> orientationMovements = new HashMap<String, OrientationMovement>();
		orientationMovements.put("R", new RightOrientationMovement());
		orientationMovements.put("L", new LeftOrientationMovement());
		final class AnotherAntCreator extends
				DefaultAntCreator {
			public AnotherAntCreator(
					Map<InputProperty, String> inputMap,
					Orientation defaultOrientation,
					Map<String, OrientationMovement> orientationMovements) {
				super(inputMap, defaultOrientation, orientationMovements);
			}

			public AnotherAntCreator(
					Map<InputProperty, String> inputMap,
					Orientation defaultOrientation,
					Map<String, OrientationMovement> orientationMovements,
					List<AntAction> actions) {
				super(inputMap, defaultOrientation, orientationMovements);
			}

			@Override
			protected List<AntAction> generateActions(String colours,
					String orientationRules) {
				ColourRuleBasedOrientationAction orientationAction = new ColourRuleBasedOrientationAction(
						this.buildMovementsMap(colours, orientationRules));
				PaletteBasedPainter painter = new PaletteBasedPainter(
						this.buildPallete(colours),
						EventRegistryFactory.getRegistryInstance());
				AntMovementAction movementAction = new FirstTimeConditionedAntMovementAction(
						EventRegistryFactory.getRegistryInstance());
				List<AntAction> actions = new ArrayList<AntAction>();
				actions.add(movementAction);
				actions.add(orientationAction);
				actions.add(painter);
				return actions;
			}
		}
		DefaultAntCreator prov = new AnotherAntCreator(
				parser.parseInput(bais), defaultOrientation,
				orientationMovements);
		Ant ant = prov.createAnt();
		AntMovementExecutor executor = new AntMovementExecutor(
				prov.getAntLifetimeStrategy());
		executor.moveAnt(ant);
		HistoryService<List<Position>> history = HistoryServiceRegistry
				.getService(HistoryServiceKey.POSITION_HISTORY_SERVICE);
		String parsedOutput = new AntPositionOutputParser(ant)
				.generateOutput(history);
		System.out.println(parsedOutput);

		Assert.assertEquals("R|R|R|\nG|G|R|\nG|G|R|\n0 -1", parsedOutput);
	}

}
