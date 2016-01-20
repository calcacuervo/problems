package artistAnt.event;

/**
 * The vent registry will be global to the application or to a part of the
 * application, so this class will be responsible to manage the instances. This
 * implementation returns always the same instance, as a singleton.
 * 
 * @author calcacuervo
 * 
 */
public class EventRegistryFactory {

	private static EventRegistry instance;

	// VER SI HABRIA QUE HACER UNA INTERFAZ, y esta seria una implementacion
	// singleton.
	public static EventRegistry getRegistryInstance() {
		if (instance == null) {
			instance = new EventRegistry();
		}
		return instance;
	}

}
