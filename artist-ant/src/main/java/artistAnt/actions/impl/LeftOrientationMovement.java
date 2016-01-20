package artistAnt.actions.impl;

import artistAnt.actions.OrientationMovement;
import artistAnt.model.Orientation;

/**
 * This class knows of to move left from an orientation.
 * 
 * @author calcacuervo
 * 
 */
public class LeftOrientationMovement implements OrientationMovement {

	@Override
	public Orientation getNextOrientation(Orientation currentOrientation) {
		return currentOrientation.getLeftOrientation();
	}

}
