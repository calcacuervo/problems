package artistAnt.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import artistAnt.api.EventListener;
import artistAnt.api.HistoryService;
import artistAnt.event.EventType;
import artistAnt.model.Ant;
import artistAnt.model.Position;

/**
 * This is a global service that will save the information about the ant.
 * Nowadays it saves the ant positions in a list. This list has an order. The
 * first element is the oldest in the history and the last element is the
 * newest.
 * 
 * @author calcacuervo
 * 
 */
public class AntPositionHistoryService implements EventListener,
		HistoryService<List<Position>> {

	/**
	 * This is the positions map.
	 */
	private Map<Ant, List<Position>> positionsMap;

	/**
	 * At the beginning, the positions map is empty.
	 */
	public AntPositionHistoryService() {
		positionsMap = new HashMap<Ant, List<Position>>();
	}
	
	/**
	 * This constructor will add the first position of the ant;
	 * @param ant
	 */
	public AntPositionHistoryService(final Ant ant) {
		positionsMap = new HashMap<Ant, List<Position>>();
		this.addHistory(ant);
	}

	/**
	 * Adds the current ant position to the ant history.
	 */
	public void addHistory(final Ant ant) {
		List<Position> antHistory = this.positionsMap.get(ant);
		if (antHistory == null) {
			antHistory = new ArrayList<Position>();
			this.positionsMap.put(ant, antHistory);
		}
		positionsMap.get(ant).add(ant.getCurrentCoord());
	}

	/**
	 * If there is an ANT_CHANGED event, we will add it to history.
	 */
	@Override
	public void handleEvent(EventType eventType, Object... data) {
		// this event must be attached to an event that gives a new position as
		// Data
		if (eventType != EventType.ANT_CHANGED) {
			System.out
					.println("WARN: Cannot save ant position history, as received an object that it is not an Ant");
		} else {
			Ant ant = (Ant) data[0];
			this.addHistory(ant);
		}
	}

	@Override
	public List<Position> getAntHistory(Ant ant) {
		return positionsMap.get(ant);
	}
}
