package artistAnt.history;

import java.util.HashMap;
import java.util.Map;

import artistAnt.api.HistoryService;

/**
 * Registry with different history services in the application. History services
 * usually are global to the application and this class will help to get it from
 * may parts in the application.
 * 
 * @author calcacuervo
 * 
 */
public class HistoryServiceRegistry {

	public static enum HistoryServiceKey {
		POSITION_HISTORY_SERVICE;
	}

	private static Map<HistoryServiceKey, HistoryService> historyServices = new HashMap<HistoryServiceRegistry.HistoryServiceKey, HistoryService>();

	public static void registerNewHistoryService(HistoryServiceKey key,
			HistoryService service) {
		historyServices.put(key, service);
	}
	
	public static HistoryService getService(HistoryServiceKey key) {
		return historyServices.get(key);
	}
}
