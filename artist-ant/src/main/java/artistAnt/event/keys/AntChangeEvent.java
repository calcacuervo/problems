package artistAnt.event.keys;

import artistAnt.api.Event;
import artistAnt.event.EventType;
import artistAnt.model.Ant;

/**
 * Event indicating the ant changed.
 * @author calcacuervo
 *
 */
public class AntChangeEvent implements Event {

	/**
	 * The ant that changed.
	 */
	private Ant ant;

	public AntChangeEvent(Ant ant) {
		this.ant = ant;
	}

	@Override
	public Object getEventData() {
		return ant;
	}

	@Override
	public EventType getEventType() {
		return EventType.ANT_CHANGED;
	}
}
