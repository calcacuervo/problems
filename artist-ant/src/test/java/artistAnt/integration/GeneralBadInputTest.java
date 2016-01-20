package artistAnt.integration;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import artistAnt.actions.OrientationMovement;
import artistAnt.actions.impl.LeftOrientationMovement;
import artistAnt.actions.impl.RightOrientationMovement;
import artistAnt.exception.AntConfigurationParseException;
import artistAnt.input.InputParserImpl;
import artistAnt.model.Ant;
import artistAnt.model.Orientation;
import artistAnt.runtime.DefaultAntCreator;

public class GeneralBadInputTest {

	@Test(expected = AntConfigurationParseException.class)
	public void inexistingColour() {
		InputParserImpl parser = new InputParserImpl();
		String input = "0 0 WXB LRR 7";
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());

		Orientation defaultOrientation = Orientation.N;
		Map<String, OrientationMovement> orientationMovements = new HashMap<String, OrientationMovement>();
		orientationMovements.put("R", new RightOrientationMovement());
		orientationMovements.put("L", new LeftOrientationMovement());
		DefaultAntCreator prov = new DefaultAntCreator(
				parser.parseInput(bais), defaultOrientation,
				orientationMovements);
		prov.createAnt();
	}

	@Test(expected = AntConfigurationParseException.class)
	public void inexistingRule() {
		InputParserImpl parser = new InputParserImpl();
		String input = "0 0 WYB PRR 7";
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());

		Orientation defaultOrientation = Orientation.N;
		Map<String, OrientationMovement> orientationMovements = new HashMap<String, OrientationMovement>();
		orientationMovements.put("R", new RightOrientationMovement());
		orientationMovements.put("L", new LeftOrientationMovement());
		DefaultAntCreator prov = new DefaultAntCreator(
				parser.parseInput(bais), defaultOrientation,
				orientationMovements);
		prov.createAnt();
	}

	@Test(expected = AntConfigurationParseException.class)
	public void incorrectInput() {
		InputParserImpl parser = new InputParserImpl();
		String input = "0 0 WYB";
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
		parser.parseInput(bais);
	}
}
