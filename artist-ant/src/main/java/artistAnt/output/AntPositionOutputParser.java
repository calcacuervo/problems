package artistAnt.output;

import java.util.List;

import artistAnt.api.HistoryService;
import artistAnt.api.OutputParser;
import artistAnt.model.Ant;
import artistAnt.model.CellAttributeType;
import artistAnt.model.Position;

public class AntPositionOutputParser implements
		OutputParser<String, List<Position>> {

	private Ant ant;

	public AntPositionOutputParser(final Ant ant) {
		this.ant = ant;
	}

	/**
	 * This class will generate the output following this approach: Since the
	 * grid is infinite in both dimensions, the output must cover only a region.
	 * The region is defined as follows:
	 * 
	 * Considering the initial position of the ant as the (0,0) position, n
	 * cells up, n cells down, m cells left and m cells right. N is the maximum
	 * absolute value of the y coordinates the ant reached. M is the maximum
	 * absolute value of the x coordinates the ant reached.
	 * 
	 */
	@Override
	public String generateOutput(HistoryService<List<Position>> history) {
		List<Position> positions = history.getAntHistory(this.ant);
		if (positions == null || positions.isEmpty()) {
			System.out.println("There is not information about this ant!");
			return "";
		}
		int maxX= this.getMaxAbsoluteValueAtDimension(positions, 0);
		int maxY= this.getMaxAbsoluteValueAtDimension(positions, 1);
		
		Position initialPosition = positions.get(0);
		StringBuilder output = new StringBuilder();
		for (int y = initialPosition.getCoordAtDimension(1) - maxY; y <= initialPosition.getCoordAtDimension(1) + maxY; y++) {
			for (int x = initialPosition.getCoordAtDimension(0) - maxX; x <= initialPosition.getCoordAtDimension(0) + maxX; x++) {
				output.append(ant
						.getGrid()
						.getAttributeAtPosition(
								new Position(new int[] { x, y }),
								CellAttributeType.COLOUR_ATTRIBUTE).getValue()
						.toString());
				output.append("|");
			}
			output.append("\n");
		}

		// now calculate final movement
		Position finalPosition = positions.get(positions.size() - 1);
		output.append(finalPosition.getCoordAtDimension(0)
				- initialPosition.getCoordAtDimension(0));
		output.append(" ");
		output.append(initialPosition.getCoordAtDimension(1) - finalPosition.getCoordAtDimension(1));
		return output.toString();
	}

	/**
	 * Gets the maximun absolute value in a given dimension from a list of
	 * {@link Position}.
	 * 
	 * @param positions
	 * @param dimension
	 * @return
	 */
	protected int getMaxAbsoluteValueAtDimension(List<Position> positions,
			int dimension) {
		int min = Integer.MIN_VALUE;
		for (Position position : positions) {
			int coord = Math.abs(position.getCoordAtDimension(dimension));
			if (coord > min) {
				min = coord;
			}
		}
		return min;
	}

}
