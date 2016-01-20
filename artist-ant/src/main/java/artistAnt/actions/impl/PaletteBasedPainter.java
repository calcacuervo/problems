package artistAnt.actions.impl;

import java.util.List;

import artistAnt.actions.AntActionWithCallback;
import artistAnt.api.Event;
import artistAnt.event.EventRegistry;
import artistAnt.event.keys.GridColourChangeEvent;
import artistAnt.model.Ant;
import artistAnt.model.CellAttributeType;
import artistAnt.model.Colour;
import artistAnt.model.ColourAttribute;

/**
 * This painter will pain a position in the ant's grid. It will base the colour
 * to pain decision, with a given colour palette. It will generate a
 * 
 * @author calcacuervo
 * 
 */
public class PaletteBasedPainter extends AntActionWithCallback {

	/**
	 * The palette. It is a list as we need to have it ordered in the same order
	 * than was created.
	 */
	private List<Colour> palette;

	/**
	 * Creates a new {@link PaletteBasedPainter}.
	 * 
	 * @param pallete
	 *            the pallete. Cannot be null.
	 */
	public PaletteBasedPainter(List<Colour> pallete, EventRegistry eventRegistry) {
		super(eventRegistry);
		this.palette = pallete;
	}

	/**
	 * Paints the current ant position with a colour based on the current
	 * position colour and also in the palette. It will paint it with the next
	 * colour in the palette (from the current colour).
	 */
	@Override
	protected void internalExecuteAntAction(Ant ant) {
		CellAttributeType attributeType = CellAttributeType.COLOUR_ATTRIBUTE;
		ColourAttribute colourAttributeAtPosition = (ColourAttribute) ant
				.getGrid().getAttributeAtPosition(ant.getCurrentCoord(),
						attributeType);
		int colourPosition = this
				.getColourPosition((Colour) colourAttributeAtPosition
						.getValue());
		if (colourPosition != -1) {
			Colour newColour = this.palette.get((colourPosition + 1)
					% this.palette.size());
			ColourAttribute newColourAttribute = new ColourAttribute(newColour);
			ant.getGrid().setNewAttributeAtPosition(ant.getCurrentCoord(),
					newColourAttribute, CellAttributeType.COLOUR_ATTRIBUTE);
		}
	}

	@Override
	protected Event getEvent(Ant ant) {
		return new GridColourChangeEvent((Colour) ant
				.getGrid()
				.getAttributeAtPosition(ant.getCurrentCoord(),
						CellAttributeType.COLOUR_ATTRIBUTE).getValue(),
				ant.getCurrentCoord());
	}

	private int getColourPosition(final Colour originalColour) {
		if (originalColour == null) {
			return -1;
		}
		int pos = 0;
		for (Colour colour : this.palette) {
			// we can compare instances as it is an enum.
			if (colour == originalColour) {
				return pos;
			}
			pos++;
		}
		// No element found.
		System.out.println("Could not found colour " + originalColour
				+ " in the pallete.");
		return -1;
	}

}
