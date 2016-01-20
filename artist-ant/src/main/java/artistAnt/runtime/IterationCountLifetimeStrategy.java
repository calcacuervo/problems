package artistAnt.runtime;

import java.util.Observer;

import artistAnt.api.AntLifetimeStrategy;
import artistAnt.api.EventListener;
import artistAnt.event.EventType;

/**
 * Implementation of {@link AntLifetimeStrategy}, which takes count of the
 * number of iterations made by the ant. It also implements {@link Observer}, so
 * that it will "watch" the events that happens to update the count when an
 * iteration was finished.
 * 
 * @author calcacuervo
 * 
 */
public class IterationCountLifetimeStrategy implements AntLifetimeStrategy,
		EventListener {

	/**
	 * The max count of iterations.
	 */
	private int maxIterations;

	/**
	 * The actual iterations.
	 */
	private int actualCount;

	/**
	 * Creates a new {@link IterationCountLifetimeStrategy} instance.
	 * Initializes actualCount to zero.
	 * 
	 * @param maxIterations
	 */
	public IterationCountLifetimeStrategy(int maxIterations) {
		this.maxIterations = maxIterations;
		this.actualCount = 0;
	}

	/**
	 * @see AntLifetimeStrategy#isFinished()
	 */
	@Override
	public boolean isFinished() {
		return this.actualCount >= this.maxIterations;
	}

	@Override
	public void handleEvent(EventType eventType, Object... data) {
		if (eventType == EventType.ITERATION_FINISHED) {
			this.actualCount++;
		}
	}
}
