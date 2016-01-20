package artistAnt.actions.impl;

import artistAnt.event.EventRegistry;
import artistAnt.event.keys.AntChangeEvent;
import artistAnt.model.Ant;

/**
 * This is an extension of {@link AntMovementAction}, which is has a condition
 * in order to perform the action. This condition indicates that the first time
 * it is executed, the action will not be called. From then on, it will always
 * be executed. It will generate a {@link AntChangeEvent} event after finishing
 * it.
 * 
 * @author calcacuervo
 * 
 */
public class FirstTimeConditionedAntMovementAction extends AntMovementAction {

	/**
	 * Indicates whether it is the first time this instance was called the
	 * method {@link AntMovementAction#executeAntAction(Ant)}
	 */
	private boolean isFirstTime;

	public FirstTimeConditionedAntMovementAction(EventRegistry registry) {
		super(registry);
		this.isFirstTime = false;
	}

	/**
	 * Overrides the default implementation to check if it is the first time is was invoked.
	 */
	@Override
	protected boolean isNeededToExecuteAction(Ant ant) {
		if (!this.isFirstTime) {
			this.isFirstTime = true;
			return false;
		}
		return true;
	}

}
