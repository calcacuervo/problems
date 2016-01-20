package artistAnt.integration;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import artistAnt.actions.OrientationMovement;
import artistAnt.actions.impl.AntMovementAction;
import artistAnt.actions.impl.FirstTimeConditionedAntMovementAction;
import artistAnt.actions.impl.LeftOrientationMovement;
import artistAnt.actions.impl.RightOrientationMovement;
import artistAnt.api.HistoryService;
import artistAnt.event.EventRegistryFactory;
import artistAnt.exception.AntConfigurationParseException;
import artistAnt.history.HistoryServiceRegistry;
import artistAnt.history.HistoryServiceRegistry.HistoryServiceKey;
import artistAnt.input.InputParserImpl;
import artistAnt.model.Ant;
import artistAnt.model.Orientation;
import artistAnt.model.Position;
import artistAnt.output.AntPositionOutputParser;
import artistAnt.runtime.AntMovementExecutor;
import artistAnt.runtime.DefaultAntCreator;

/**
 * This test corresponds to case 1 presented in requirements: Input 3 3 RGY LRL
 * 4
 * 
 * Output R|R|R| G|G|R| G|G|R| 0 -1
 * 
 * @author calcacuervo
 * 
 */
public class Example1Test {

	@Test
	public void testIntegration1Test() {
		InputParserImpl parser = new InputParserImpl();
		String input = "0 0 WYB LRR 7";
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

		Assert.assertEquals("W|W|Y|\nY|B|Y|\nY|Y|W|\n0 1", parsedOutput);
	}

}
