package artistAnt.actions;

import artistAnt.api.AntAction;
import artistAnt.api.Event;
import artistAnt.event.EventRegistry;
import artistAnt.model.Ant;

/**
 * Extension of {@link AntAction}, which after finishing the action execution,
 * will register a new event in the {@link EventRegistry} instance it has. Child
 * classes will ahve to implement two methods: <li>
 * internalExecuteAntAction: It is the actual execution</li> <li>
 * getEvent: That will return the generated {@link Event} instance. </ul></li>
 * 
 * @author calcacuervo
 * 
 */
public abstract class AntActionWithCallback extends AntAction {

	/**
	 * The event registry will be notified than an event happened.
	 */
	private EventRegistry eventRegistry;

	/**
	 * Creates a new {@link AntActionWithCallback} instance.
	 * @param registry the vent registry. It must not be null.
	 */
	public AntActionWithCallback(final EventRegistry registry) {
		this.eventRegistry = registry;
	}

	/**
	 * Internally executes the action and informs the {@link EventRegistry} that an event occurred.
	 */
	@Override
	protected void executeActionIfNeeded(Ant ant) {
		this.internalExecuteAntAction(ant);
		this.eventRegistry.eventOccured(getEvent(ant));
	}

	/**
	 * Must be filled with the actual execution action.
	 * @param ant
	 */
	protected abstract void internalExecuteAntAction(final Ant ant);

	/**
	 * Will create an instance of {@link Event} for this action
	 * @param ant
	 * @return the event
	 */
	protected abstract Event getEvent(final Ant ant);
}
