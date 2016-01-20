package artistAnt.actions.impl;

import java.util.Map;

import artistAnt.actions.OrientationMovement;
import artistAnt.api.AntAction;
import artistAnt.model.Ant;
import artistAnt.model.CellAttributeType;
import artistAnt.model.Colour;
import artistAnt.model.ColourAttribute;
import artistAnt.model.Orientation;

/**
 * This action changes the ant orientation. It bases its decision in the current
 * colour and a decision map.
 * 
 * @author calcacuervo
 * 
 */
public class ColourRuleBasedOrientationAction extends AntAction {

	/**
	 * This maps indicated where to orientate from a colour.
	 */
	private Map<Colour, OrientationMovement> movements;

	/**
	 * Creates a new {@link ColourRuleBasedOrientationAction}.
	 * 
	 * @param movements
	 *            The movements map. It cannot be null.
	 */
	public ColourRuleBasedOrientationAction(
			final Map<Colour, OrientationMovement> movements) {
		this.movements = movements;
	}

	/**
	 * Gets the current ant position, and then its colour, and uses the movement
	 * map to see how to orientate.
	 */
	@Override
	protected void executeActionIfNeeded(Ant ant) {
		ColourAttribute coordColour = (ColourAttribute) ant.getGrid()
				.getAttributeAtPosition(ant.getCurrentCoord(),
						CellAttributeType.COLOUR_ATTRIBUTE);
		if (coordColour != null) {
			OrientationMovement movement = this.movements.get(coordColour
					.getValue());
			Orientation newOrientation = movement.getNextOrientation(ant
					.getOrientation());
			ant.setNewOrientation(newOrientation);
		}
	}

}
