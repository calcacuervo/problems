package artistAnt.api;

import artistAnt.model.Ant;

/**
 * Represents an abstract ant action. It executes some ant action if it is
 * needed. Default implementation is that it is always needed to execute the
 * action, but children can override
 * {@link AntAction#isNeededToExecuteAction(Ant)} method in order to do some
 * check.
 * 
 * @author calcacuervo
 * 
 */
public abstract class AntAction {

	/**
	 * Returns true if it is needed to execute the action at this moment.
	 * Default implementation returns always true.
	 * 
	 * @param ant
	 * @return always true.
	 */
	protected boolean isNeededToExecuteAction(Ant ant) {
		return true;
	}

	/**
	 * Checks if it is needed to execute the action and then executes it.
	 * 
	 * @param ant
	 */
	public void executeAntAction(Ant ant) {
		if (this.isNeededToExecuteAction(ant)) {
			executeActionIfNeeded(ant);
		}
	}

	/**
	 * This method will be implemented with the actual action code of the
	 * concrete class.
	 * 
	 * @param ant
	 */
	protected abstract void executeActionIfNeeded(Ant ant);

}
