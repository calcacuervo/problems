package artistAnt.integration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import artistAnt.actions.OrientationMovement;
import artistAnt.actions.impl.LeftOrientationMovement;
import artistAnt.actions.impl.RightOrientationMovement;
import artistAnt.api.HistoryService;
import artistAnt.history.HistoryServiceRegistry;
import artistAnt.history.HistoryServiceRegistry.HistoryServiceKey;
import artistAnt.input.InputParserImpl;
import artistAnt.model.Ant;
import artistAnt.model.Orientation;
import artistAnt.model.Position;
import artistAnt.output.AntPositionOutputParser;
import artistAnt.output.ImageOutputParser;
import artistAnt.runtime.AntMovementExecutor;
import artistAnt.runtime.DefaultAntCreator;

/**
 * This test shows how to generate an image by parsing the output.
 * @author calcacuervo
 *
 */
public class ImageGenerationTest {


	@Test
	public void exportedImage() {
		InputParserImpl parser = new InputParserImpl();
		String input = "0 0 GYBRCL LRRRLR 100000";
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());

		Orientation defaultOrientation = Orientation.N;
		Map<String, OrientationMovement> orientationMovements = new HashMap<String, OrientationMovement>();
		orientationMovements.put("R", new RightOrientationMovement());
		orientationMovements.put("L", new LeftOrientationMovement());
		DefaultAntCreator prov = new DefaultAntCreator(
				parser.parseInput(bais), defaultOrientation,
				orientationMovements);
		Ant ant = prov.createAnt();
		AntMovementExecutor executor = new AntMovementExecutor(
				prov.getAntLifetimeStrategy());
		executor.moveAnt(ant);
		HistoryService<List<Position>> history = HistoryServiceRegistry
				.getService(HistoryServiceKey.POSITION_HISTORY_SERVICE);
		String parsedOutput = new AntPositionOutputParser(ant)
				.generateOutput(history);
		System.out.println(parsedOutput);

		ImageOutputParser exportImage;
		try {
			exportImage = new ImageOutputParser(ant, File.createTempFile(
					"testFile", ".bmp"));
			exportImage.setSquareSize(2);
			String fileName = exportImage.generateOutput(history);
			System.out.println("Created image in file: " + fileName);
		} catch (IOException e) {
			Assert.fail("Could not create file!");
		}
	}
}
