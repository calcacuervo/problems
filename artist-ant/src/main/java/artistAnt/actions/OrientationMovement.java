package artistAnt.actions;

import artistAnt.model.Orientation;

/**
 * Represents some service that knows where to move from one orientation. There
 * will be two basic implementations, one to move to the right and another to
 * move to the left. But there could be some other implementations, for example,
 * move it according to user input, or move it randomly. Or maybe let the ant
 * decide where to move!.
 * 
 * @author calcacuervo
 * 
 */
public interface OrientationMovement {
	
	/**
	 * Gets the next orientation.
	 * @param currentOrientation the currently orientation. Cannot be null.
	 * @return
	 */
	public Orientation getNextOrientation(final Orientation currentOrientation);
}
