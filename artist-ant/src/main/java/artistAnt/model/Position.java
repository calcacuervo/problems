package artistAnt.model;

/**
 * This class will represent a point in the grid. It can have many dimensions.
 * 
 * @author calcacuervo
 * 
 */
public class Position {

	private int[] coords;

	public Position(int[] theCoords) {
		this.coords = theCoords;
	}

	public int getCoordAtDimension(int dimension) {
		return this.coords[dimension];
	}
	
	public int getDimensionsCount() {
		return this.coords.length;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (int coord : this.coords) {
			result = prime * result + coord;	
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (other.getDimensionsCount() != this.getDimensionsCount()) {
			return false;
		}
		for (int i = 0; i < other.getDimensionsCount(); i++) {
			if (other.getCoordAtDimension(i) != this.getCoordAtDimension(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder representation = new StringBuilder();
		for (int i = 0; i < this.getDimensionsCount(); i++) {
			representation.append("Dimension " + i + ": " + this.getCoordAtDimension(i));
		}
		return representation.toString();
	}
}
