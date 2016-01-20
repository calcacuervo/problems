package artistAnt.api;

import artistAnt.event.EventType;

/**
 * Generic interface for an Event. The event will produce some Data, and also
 * has an {@link EventType}. To create a new event, it is needed to implement
 * this class, and add a new {@link EventType} enumeration.
 * 
 * @author calcacuervo
 * 
 */
public interface Event {

	/**
	 * Returns some event data.
	 * @return
	 */
	public Object getEventData();

	/**
	 * Returns the event type.
	 * @return
	 */
	public EventType getEventType();
}
