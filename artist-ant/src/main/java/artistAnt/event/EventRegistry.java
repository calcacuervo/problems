package artistAnt.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import artistAnt.api.Event;
import artistAnt.api.EventListener;

/**
 * This is the event registry. It will hold listeners for different event types.
 * When an event occurs, it will call the listener sequentially and
 * synchronously. There could be some extension to create a new thread for each
 * listener so that the main thread does not have to wait for it.
 * 
 * @author calcacuervo
 * 
 */
public class EventRegistry {
	
	/**
	 * The event type listener mapping.
	 */
	private Map<EventType, List<EventListener>> keys;

	public EventRegistry() {
		keys = new HashMap<EventType, List<EventListener>>();
	}

	/**
	 * Registers a new listener for an {@link EventType}.
	 * @param key
	 * @param target
	 */
	public void register(EventType key, EventListener target) {
		List<EventListener> list = keys.get(key);
		if (list == null) {
			list = new ArrayList<EventListener>();
			keys.put(key, list);
		}
		list.add(target);
	}

	/**
	 * Unregisters a listener for an {@link EventType}.
	 * @param key
	 * @param target
	 */
	public void unregister(EventType key, EventListener target) {
		List<EventListener> list = keys.get(key);
		if (list != null) {
			list.remove(target);
		}
	}

	/**
	 * Calls the listened registered to the event type.
	 * @param event
	 */
	public void eventOccured(Event event) {
		List<EventListener> list = keys.get(event.getEventType());
		if (list != null) {
			for (EventListener responseHandler : list) {
				// This is a simple implementation. It could be possible to make
				// the listeners asyncronous!
				responseHandler.handleEvent(event.getEventType(),
						event.getEventData());
			}
		}
	}

}
