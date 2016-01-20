package artistAnt.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import artistAnt.model.Colour;

/**
 * This class will help to map colours from the model implementation to AWT
 * colours.
 * 
 * @author calcacuervo
 * 
 */
public class ColorUtils {


	/**
	 * An static map. This could be replaces by a properties file.
	 */
	private static Map<Colour, Color> colourMapping = new HashMap<Colour, Color>();

	static {
		colourMapping.put(Colour.B, Color.BLACK);
		colourMapping.put(Colour.R, Color.RED);
		colourMapping.put(Colour.Y, Color.YELLOW);
		colourMapping.put(Colour.W, Color.WHITE);
		colourMapping.put(Colour.G, Color.GREEN);
		colourMapping.put(Colour.L, Color.BLACK);
		colourMapping.put(Colour.C, Color.CYAN);
	}
	
	public static Color getAwtColour(Colour colour) {
		return colourMapping.get(colour);
	}
}
