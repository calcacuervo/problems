package artistAnt.runtime;

import artistAnt.api.AntLifetimeStrategy;
import artistAnt.model.Ant;

/**
 * This executor moves an ant during a configured time. It used a
 * {@link AntLifetimeStrategy} to decide when to stop moving it.
 * 
 * @author calcacuervo
 * 
 */
public class AntMovementExecutor {

	private AntLifetimeStrategy lifetime;

	/**
	 * Creates a new {@link AntMovementExecutor}.
	 * @param lifetime cannot be null!.
	 */
	public AntMovementExecutor(AntLifetimeStrategy lifetime) {
		this.lifetime = lifetime;
	}

	public void moveAnt(Ant ant) {
		while (!lifetime.isFinished()) {
			ant.executeActions();
		}
	}

}
