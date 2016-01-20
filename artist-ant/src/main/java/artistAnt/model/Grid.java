package artistAnt.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a grid. This grid basically contains some cells which
 * have attributes. It also have default attributes for attributes not set. As
 * the grid is initialized, it only holds needed cells, and the others are
 * supposed to have default values.
 * 
 * @author calcacuervo
 * 
 */
public class Grid {

	/**
	 * The cells map, which have a position and a map of attributes.
	 */
	private Map<Position, Map<CellAttributeType, CellAttribute>> grid;

	/**
	 * Default attributes used by not initialized cells.
	 */
	private Map<CellAttributeType, CellAttribute> defaultAttributes;

	/**
	 * Creates a new {@link Grid} instance.
	 * 
	 * @param defaultAttributes
	 *            a map with default attributes.
	 */
	public Grid(final Map<CellAttributeType, CellAttribute> defaultAttributes) {
		this.defaultAttributes = defaultAttributes;
		this.grid = new HashMap<Position, Map<CellAttributeType, CellAttribute>>();
	}

	/**
	 * Sets a given attribute in a given position.
	 * 
	 * @param coord
	 *            the position.
	 * @param attribute
	 *            the attribute to set.
	 * @param attributeType
	 *            the attribute type to set.
	 */
	public void setNewAttributeAtPosition(Position coord,
			CellAttribute attribute, CellAttributeType attributeType) {
		Map<CellAttributeType, CellAttribute> cellAttributes = this.grid
				.get(coord);
		if (cellAttributes == null) {
			cellAttributes = new HashMap<CellAttributeType, CellAttribute>();
			this.grid.put(coord, cellAttributes);
		}
		CellAttribute currentAttribute = cellAttributes.get(attributeType);
		if (currentAttribute == null) {
			cellAttributes.put(attributeType, attribute);
		} else {
			currentAttribute.setValue(attribute.getValue());
		}
	}

	/**
	 * Returns an attribute value, given a position and the attribute type.
	 * @param coord
	 * @param attributeType
	 * @return
	 */
	public CellAttribute getAttributeAtPosition(Position coord,
			CellAttributeType attributeType) {
		Map<CellAttributeType, CellAttribute> cellAttributes = this.grid
				.get(coord);
		if (cellAttributes == null) {
			return this.getAttributeFromMap(this.defaultAttributes,
					attributeType);
		}
		return this.getAttributeFromMap(cellAttributes, attributeType);
	}

	private CellAttribute getAttributeFromMap(
			Map<CellAttributeType, CellAttribute> cellAttributes,
			CellAttributeType attributeType) {
		CellAttribute attribute = cellAttributes.get(attributeType);
		if (attribute == null) {
			System.out
					.println("Could not find any attribute for this position and this attribute type");
			return null;
		} else {
			return attribute;
		}
	}
}
