package artistAnt.model;

/**
 * Represents an attribute of a cell.
 * 
 * @author calcacuervo
 * 
 */
public interface CellAttribute {

	/**
	 * Returns the value of this attribute.
	 * 
	 * @return
	 */
	public Object getValue();

	/**
	 * Sets a new value from this attribute.
	 * 
	 * @param newValue
	 *            the new value.
	 */
	public void setValue(Object newValue);

	/**
	 * Gets the attribute type for this attribute. This is to differentiate
	 * among different attributes.
	 * 
	 * @return
	 */
	public CellAttributeType getAttributeType();
}
