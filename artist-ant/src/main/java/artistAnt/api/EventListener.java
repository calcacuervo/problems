package artistAnt.api;

import artistAnt.event.EventType;

/**
 * Represents classes that will be listening for some special {@link EventType}.
 * 
 * @author calcacuervo
 * 
 */
public interface EventListener {

	/**
	 * Handles an event that is from an specific {@link EventType} and has some
	 * produced data.
	 * 
	 * @param eventType
	 * @param data
	 */
	public void handleEvent(EventType eventType, Object... data);
}
