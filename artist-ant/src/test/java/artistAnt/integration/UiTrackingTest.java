package artistAnt.integration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import junit.framework.Assert;

import org.junit.Test;

import artistAnt.actions.OrientationMovement;
import artistAnt.actions.impl.AntMovementAction;
import artistAnt.actions.impl.ColourRuleBasedOrientationAction;
import artistAnt.actions.impl.FirstTimeConditionedAntMovementAction;
import artistAnt.actions.impl.LeftOrientationMovement;
import artistAnt.actions.impl.PaletteBasedPainter;
import artistAnt.actions.impl.RightOrientationMovement;
import artistAnt.api.AntAction;
import artistAnt.api.HistoryService;
import artistAnt.api.InputParser.InputProperty;
import artistAnt.event.EventRegistryFactory;
import artistAnt.event.EventType;
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
import artistAnt.visual.GridFrame;

/**
 * This test shows how to track the ant movements with listeners, showing it
 * into the UI.
 * 
 * @author calcacuervo
 * 
 */
public class UiTrackingTest {

	/**
	 * This test shows interaction with a User Interface Listener which shows
	 * the ant and grid status.
	 */
	@Test
	public void integrationWithUI() {
		InputParserImpl parser = new InputParserImpl();
		String input = "0 0 CRYBL RLRRL 10";
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());

		Orientation defaultOrientation = Orientation.N;
		Map<String, OrientationMovement> orientationMovements = new HashMap<String, OrientationMovement>();
		orientationMovements.put("R", new RightOrientationMovement());
		orientationMovements.put("L", new LeftOrientationMovement());
		List<AntAction> actions = new ArrayList<AntAction>();
		actions.add(new AntAction() {
			@Override
			protected void executeActionIfNeeded(Ant ant) {
				// DELAY ACTION!! Useful for showing the status of the ant in
				// the UI.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		DefaultAntCreator prov = new DefaultAntCreator(
				parser.parseInput(bais), defaultOrientation,
				orientationMovements, actions);
		Ant ant = prov.createAnt();
		AntMovementExecutor executor = new AntMovementExecutor(
				prov.getAntLifetimeStrategy());

		GridFrame frame = new GridFrame(ant.getGrid(), ant.getCurrentCoord());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("The Artist Ant");

		frame.setVisible(true);

		EventRegistryFactory.getRegistryInstance().register(
				EventType.ANT_CHANGED, frame.getGridComponent());
		executor.moveAnt(ant);
		HistoryService<List<Position>> history = HistoryServiceRegistry
				.getService(HistoryServiceKey.POSITION_HISTORY_SERVICE);
		String parsedOutput = new AntPositionOutputParser(ant)
				.generateOutput(history);
		frame.setVisible(false);
		System.out.println(parsedOutput);
		Assert.assertEquals("R|R|C|\nY|B|R|\nC|R|R|\n-1 -1", parsedOutput);
	}

}
