package artistAnt.model;

/**
 * Enumerates the different orientations. Each orientation has information about
 * which orientation is at right and which one is at left, and also know how to
 * move forward in a grid.
 * 
 * @author calcacuervo
 * 
 */
public enum Orientation {

	/**
	 * North.
	 */
	N("E", "W", 0, -1),
	/**
	 * West.
	 */
	W("N", "S", -1, 0),
	/**
	 * East.
	 */
	E("S", "N", 1, 0),
	/**
	 * South.
	 */
	S("W", "E", 0, 1);

	/**
	 * The orientation at right.
	 */
	private String rightOrientation;

	/**
	 * The orientation at left.
	 */
	private String leftOrientation;

	// This class uses simply uses X and Y for the position, as the orientation
	// with N S E W with two dimensional for its nature!.
	/**
	 * This x and y represents the movement that has to be done to move towards
	 * this direction.
	 */
	private int x;

	private int y;

	Orientation(String moveRightOrientation, String moveLeftOrientation, int x,
			int y) {
		this.rightOrientation = moveRightOrientation;
		this.leftOrientation = moveLeftOrientation;
		this.x = x;
		this.y = y;
	}

	/**
	 * Given a position, returns the next position in this orientation.
	 * 
	 * @param originalCoord
	 * @return
	 */
	public Position getNextCoord(Position originalCoord) {
		return new Position(new int[] {
				originalCoord.getCoordAtDimension(0) + this.x,
				originalCoord.getCoordAtDimension(1) + this.y });
	}

	public Orientation getRightOrientation() {
		return Orientation.valueOf(rightOrientation);
	}

	public Orientation getLeftOrientation() {
		return Orientation.valueOf(leftOrientation);
	}

}
