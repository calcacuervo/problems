package artistAnt.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import artistAnt.api.EventListener;
import artistAnt.event.EventType;
import artistAnt.model.Ant;
import artistAnt.model.CellAttributeType;
import artistAnt.model.Colour;
import artistAnt.model.Grid;
import artistAnt.model.Position;
import artistAnt.util.ColorUtils;

/**
 * This component will draw the grid using task information. It is registered as
 * a listener of the ant changes.
 * 
 * @author calcacuervo
 * 
 */
public class GridComponent extends JComponent implements EventListener

{

	private int antPosX = -1;
	private int antPosY = -1;
	private Color antColour = Color.GRAY;
	private int side;
	private GridFrame containingFrame;

	private Image antImage;

	private Grid model;

	public GridComponent(Grid model, Position antPosition, GridFrame frame) {
		side = 10;

		setPreferredSize(new Dimension(1000, 1000));

		this.model = model;
		this.antPosX = antPosition.getCoordAtDimension(0);
		this.antPosY = antPosition.getCoordAtDimension(1);
		this.containingFrame = frame;
		try {
			String fileSeparator = System.getProperty("file.separator");
			antImage = ImageIO.read(new File("src" + fileSeparator + "main"
					+ fileSeparator + "resources" + fileSeparator + "artistAnt"
					+ fileSeparator + "visual" + fileSeparator + "ant.jpg"));
		} catch (IOException e) {
			System.out.println("Could not find ant image.");
		}
	}

	public void paintComponent(Graphics g)

	{
		Graphics2D g2 = (Graphics2D) g;
		int count = side;
		int size = 40;
		for (int x = -4; x < count; x++)

		{
			for (int y = -4; y < count; y++) {
				int i = x + 4;
				int j = y + 4;
				Rectangle grid = new Rectangle(200 + i * size, 20 + j * size,
						size, size);

				g2.draw(grid);
				g2.setColor(ColorUtils.getAwtColour((Colour)this.model
						.getAttributeAtPosition(
								new Position(new int[] { x, y }),
								CellAttributeType.COLOUR_ATTRIBUTE).getValue()));
				g2.fillRect(200 + i * size, 20 + j * size, size, size);
				g2.drawString(x + " " + y, 200 + i * size, 20 + j * size);
			}
		}
		paintAnt(antColour);

	}

	public void paintAnt(Color color) {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		int antSize = 20;
		int size = 40;
		if (this.antImage != null) {
			// show the jpg
			g2.drawImage(this.antImage, 200 + (antPosX + 4) * size + 10, 20
					+ (antPosY + 4) * size + 10, null);
		} else {
			// show a simple square in case there we don't find the image file
			g2.setColor(color);
			g2.fillRect(200 + (antPosX + 4) * size + 10, 20 + (antPosY + 4)
					* size + 10, antSize, antSize);
		}
	}

	@Override
	public void handleEvent(EventType eventType, Object... data) {
		if (eventType == EventType.ANT_CHANGED) {
			Ant ant = (Ant) data[0];
			this.antPosX = ant.getCurrentCoord().getCoordAtDimension(0);
			this.antPosY = ant.getCurrentCoord().getCoordAtDimension(1);
			this.paint(getGraphics());
		}
		this.containingFrame.setNewEventLabel(eventType.name() + ". Position: "
				+ antPosX + " " + antPosY);
		//Just sleep a bit, so the user has time to see what's happening.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
