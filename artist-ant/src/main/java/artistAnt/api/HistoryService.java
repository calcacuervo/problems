package artistAnt.api;

import artistAnt.model.Ant;

/**
 * Represents history for an ant. It provides method to add something to the
 * history, and to get the history of an ant.
 * 
 * @author calcacuervo
 * 
 * @param <T> this represents the format in which the history will be returned.
 */
public interface HistoryService<T> {

	/**
	 * Adds in the history some attributes of the current state of the ant.
	 * 
	 * @param ant
	 */
	public void addHistory(final Ant ant);

	/**
	 * Returns the whole history of the ant.
	 * 
	 * @param ant
	 * @return
	 */
	public T getAntHistory(final Ant ant);
}
