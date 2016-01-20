package artistAnt.actions.impl;

import artistAnt.actions.OrientationMovement;
import artistAnt.model.Orientation;

/**
 * This class knows of to move right from an orientation.
 * @author calcacuervo
 *
 */
public class RightOrientationMovement implements OrientationMovement {
	
	@Override
	public Orientation getNextOrientation(Orientation currentOrientation) {
		return currentOrientation.getRightOrientation();
	}
	
}
