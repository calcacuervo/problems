package artistAnt.input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import artistAnt.api.InputParser;
import artistAnt.exception.AntConfigurationParseException;

/**
 * This is an specific parser, which takes a given input stream, and will read
 * the model that will be represented as a map. The model returned is according
 * the the original requirement.
 * 
 * @author calcacuervo
 * 
 */
public class InputParserImpl implements InputParser {

	public Map<InputProperty, String> parseInput(InputStream input) {

		StringBuilder parsedInput = new StringBuilder();
		try {
			if (input != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));
				String strLine;
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					parsedInput.append(strLine);
				}
				// Close the input stream
				input.close();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Could not obtain string from given Input Stream");
		}

		String[] inputParts = parsedInput.toString().split(" ");
		if (inputParts != null && inputParts.length != 5) {
			throw new AntConfigurationParseException("Input " + parsedInput
					+ " is not as expected");
		}

		// TODO I don't know why are these two parameters in examples but not
		// into description.
		// String posX = inputParts[0];
		// String posY = inputParts[1];
		String colours = inputParts[2];
		String orientationRules = inputParts[3];
		String interations = inputParts[4];
		Map<InputProperty, String> parsedMap = new HashMap<InputParser.InputProperty, String>();
		// parsedMap.put(InputProperty.POSX, posX);
		// parsedMap.put(InputProperty.POSY, posY);
		parsedMap.put(InputProperty.ORIENTATION_RULES, orientationRules);
		parsedMap.put(InputProperty.COLOURS, colours);
		parsedMap.put(InputProperty.ITERATIONS, interations);
		return parsedMap;
	}
}
