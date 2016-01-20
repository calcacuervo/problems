package artistAnt.actions.impl;

import java.util.List;

import artistAnt.actions.AntActionWithCallback;
import artistAnt.api.AntAction;
import artistAnt.api.Event;
import artistAnt.event.EventRegistry;
import artistAnt.event.keys.IterationFinishedEvent;
import artistAnt.model.Ant;

/**
 * This is an action that represents an iteration. An iteration is a set of
 * actions that will be executed sequentially and synchronously. This
 * implementation will generate a {@link IterationFinishedEvent}.
 * 
 * @author calcacuervo
 * 
 */
public class IterationAction extends AntActionWithCallback {

	/**
	 * The list of actions to be executed.
	 */
	private List<AntAction> actions;

	public IterationAction(final List<AntAction> actions, EventRegistry registry) {
		super(registry);
		this.actions = actions;
	}

	@Override
	protected Event getEvent(Ant ant) {
		return new IterationFinishedEvent();
	}

	@Override
	protected void internalExecuteAntAction(Ant ant) {
		for (AntAction action : this.actions) {
			action.executeAntAction(ant);
		}
	}

}
