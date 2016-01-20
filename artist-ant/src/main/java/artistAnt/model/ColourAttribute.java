package artistAnt.model;

/**
 * This attribute represents the colout of a cell.
 * @author calcacuervo
 *
 */
public class ColourAttribute implements CellAttribute {

	/**
	 * The current colour.
	 */
	private Colour colour;

	/**
	 * Creates a new {@link ColourAttribute} instance.
	 * @param initialColour the initial colour.
	 */
	public ColourAttribute(Colour initialColour) {
		this.colour = initialColour;
	}

	@Override
	public Object getValue() {
		return colour;
	}

	@Override
	public void setValue(Object newValue) {
		if (!(newValue instanceof Colour)) {
			throw new IllegalArgumentException();
		}
		this.colour = (Colour) newValue;
	}

	@Override
	public CellAttributeType getAttributeType() {
		return CellAttributeType.COLOUR_ATTRIBUTE;
	}
	
	@Override
	public String toString() {
		return this.colour.toString();
	}
}