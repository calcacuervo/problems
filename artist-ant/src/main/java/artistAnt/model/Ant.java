package artistAnt.model;

import artistAnt.api.AntAction;

/**
 * This is the ant of the application. It lives in a {@link Grid}, and has an
 * actualPosition and orientation (North, South, East, West). It has an action
 * to execute.
 * 
 * @author calcacuervo
 * 
 */
public class Ant {

	/**
	 * The ant is located in a grid.
	 */
	private Grid grid;

	/**
	 * The ant has a current position.
	 */
	private Position actualPosition;

	/**
	 * The ant has a current orientation.
	 */
	private Orientation orientation;

	/**
	 * Action to be executed by the ant.
	 */
	private AntAction antAction;

	/**
	 * Creates a new {@link Ant} instance.
	 * @param initialOrientation cannot be null.
	 * @param initialPosition cannot be null.
	 * @param grid cannot be null.
	 * @param antAction cannot be null.
	 */
	public Ant(final Orientation initialOrientation,
			final Position initialPosition, final Grid grid,
			final AntAction antAction) {
		this.orientation = initialOrientation;
		this.antAction = antAction;
		this.actualPosition = initialPosition;
		this.grid = grid;
	}

	/**
	 * Execute the configured actions.
	 */
	public void executeActions() {
		this.antAction.executeAntAction(this);
	}

	public Grid getGrid() {
		return this.grid;
	}

	public Position getCurrentCoord() {
		return this.actualPosition;
	}

	public Orientation getOrientation() {
		return this.orientation;
	}

	public void setNewPosition(final Position newPosition) {
		this.actualPosition = newPosition;
	}

	public void setNewOrientation(final Orientation newOrentation) {
		this.orientation = newOrentation;
	}
}
