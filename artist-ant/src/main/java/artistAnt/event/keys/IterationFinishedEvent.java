package artistAnt.event.keys;

import artistAnt.api.Event;
import artistAnt.event.EventType;

/**
 * An event that indicates the iteration finished.
 * @author calcacuervo
 *
 */
public class IterationFinishedEvent implements Event {

	/**
	 * NO DATA NEEDED!
	 */
	@Override
	public Object getEventData() {
		return null;
	}

	@Override
	public EventType getEventType() {
		return EventType.ITERATION_FINISHED;
	}
}
