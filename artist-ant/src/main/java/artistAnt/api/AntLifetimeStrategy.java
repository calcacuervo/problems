package artistAnt.api;

/**
 * Interface to identify classes which will decide if the ant finished its
 * lifecycle.
 * 
 * @author calcacuervo
 * 
 */
public interface AntLifetimeStrategy {

	/**
	 * Decides whether the ant finished its lifecycle.
	 * 
	 * @return true if it finished. Otherwise, returns false.
	 */
	public boolean isFinished();
}
