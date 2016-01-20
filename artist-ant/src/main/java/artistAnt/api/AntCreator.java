package artistAnt.api;

import artistAnt.model.Ant;

/**
 * Repersents classes that can configure an return an initialized and configured
 * {@link Ant}.
 * 
 * @author calcacuervo
 * 
 */
public interface AntCreator {

	/**
	 * This method will return an initialized {@link Ant}.
	 * @return an instance of {@link Ant}.
	 */
	public Ant createAnt();

}
