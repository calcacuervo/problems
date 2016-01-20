package artistAnt.actions.impl;

import artistAnt.actions.AntActionWithCallback;
import artistAnt.api.Event;
import artistAnt.event.EventRegistry;
import artistAnt.event.keys.AntChangeEvent;
import artistAnt.model.Ant;

/**
 * This action simply moves the ant to the next position and creates an
 * {@link AntChangeEvent} after making it.
 * 
 * @author calcacuervo
 * 
 */
public class AntMovementAction extends AntActionWithCallback {

	/**
	 * Creates a new {@link AntMovementAction}.
	 * @param registry the event registry to register a new event.
	 */
	public AntMovementAction(final EventRegistry registry) {
		super(registry);
	}

	@Override
	protected Event getEvent(Ant ant) {
		return new AntChangeEvent(ant);
	}

	@Override
	protected void internalExecuteAntAction(Ant ant) {
		ant.setNewPosition(ant.getOrientation().getNextCoord(
				ant.getCurrentCoord()));
	}

}
