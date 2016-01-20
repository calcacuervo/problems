package artistAnt.output;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import artistAnt.api.HistoryService;
import artistAnt.api.OutputParser;
import artistAnt.model.Ant;
import artistAnt.model.CellAttributeType;
import artistAnt.model.Colour;
import artistAnt.model.Position;
import artistAnt.util.ColorUtils;

/**
 * This output parser will generate a bmp image with the representation of the
 * grid.
 * 
 * @author calcacuervo
 * 
 */
public class ImageOutputParser implements OutputParser<String, List<Position>> {

	private Ant ant;

	private File outputFile;
	
	private int squareSize = 20;

	public ImageOutputParser(final Ant ant, final File file) {
		this.ant = ant;

		outputFile = file;
	}

	/**
	 * This will generate a bmp image from the given history, and will save it
	 * in the configured file. The method will return the full path of the file.
	 */
	@Override
	public String generateOutput(HistoryService<List<Position>> history) {
		List<Position> positions = history.getAntHistory(this.ant);
		int maxX = this.getMaxAbsoluteValueAtDimension(positions, 0);
		int maxY = this.getMaxAbsoluteValueAtDimension(positions, 1);
		int sizeX = 2 * maxX + 1;
		int sizeY = 2 * maxY + 1;
		BufferedImage image = new BufferedImage(sizeX * this.squareSize, sizeY * this.squareSize,
				BufferedImage.TYPE_INT_RGB);
		StringBuilder output = new StringBuilder();
		for (int y = 0; y < sizeY * this.squareSize; y++) {
			for (int x = 0; x < sizeX * this.squareSize; x++) {
				int roundedX = Math.round(x / this.squareSize);
				int roundedY = Math.round(y / this.squareSize);
				Color c = ColorUtils.getAwtColour((Colour)this.ant
						.getGrid()
						.getAttributeAtPosition(
								new Position(new int[] { roundedX - maxX,
										roundedY - maxY }),
								CellAttributeType.COLOUR_ATTRIBUTE).getValue());
				image.setRGB(x, y, c.getRGB());
			}
			output.append("\n");
		}

		try {
			ImageIO.write(image, "bmp", this.outputFile);
		} catch (IOException e) {
			throw new RuntimeException(
					"There was an error writing to the file", e);
		}
		return this.outputFile.getAbsolutePath();
	}

	// TODO check if we can reuse it
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

	public void setSquareSize(int squareSize) {
		this.squareSize = squareSize;
	}
}
