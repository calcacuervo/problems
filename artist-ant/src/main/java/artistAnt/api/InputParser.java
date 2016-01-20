package artistAnt.api;

import java.io.InputStream;
import java.util.Map;

/**
 * Represents a parser from a given input to a map model.
 * @author calcacuervo
 *
 */
public interface InputParser {

	/**
	 * Keys to be used in the model map.
	 * @author calcacuervo
	 *
	 */
	public static enum InputProperty {
		/**
		 * Position at X.
		 */
		POSX,
		/**
		 * Position at Y.
		 */
		POSY,
		/**
		 * Colours list.
		 */
		COLOURS,
		/**
		 * Rules for orientation.
		 */
		ORIENTATION_RULES,
		/**
		 * Number of iterations.
		 */
		ITERATIONS
	}

	/**
	 * Parses an input given in an input stream to a model represented in a map.
	 * @param input the input. It should not be null.
	 * @return a {@link Map} representing the model.
	 */
	public Map<InputProperty, String> parseInput(InputStream input);
}
