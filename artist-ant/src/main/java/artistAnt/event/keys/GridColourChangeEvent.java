package artistAnt.event.keys;

import artistAnt.api.Event;
import artistAnt.event.EventType;
import artistAnt.model.Colour;
import artistAnt.model.Position;

/**
 * An event indicating the grid colour changed in some position. The generated
 * event data will be an array with the colour and position.
 * 
 * @author calcacuervo
 * 
 */
public class GridColourChangeEvent implements Event {

	private Colour newColour;

	private Position changedPosition;

	public GridColourChangeEvent(final Colour colour, final Position position) {
		this.newColour = colour;
		this.changedPosition = position;
	}

	@Override
	public Object getEventData() {
		return new Object[] { newColour, changedPosition };
	}

	@Override
	public EventType getEventType() {
		return EventType.GRID_COLOUR_CHANGED;
	}
}
