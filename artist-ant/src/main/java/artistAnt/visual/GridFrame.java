package artistAnt.visual;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import artistAnt.model.Grid;
import artistAnt.model.Position;

public class GridFrame extends JFrame

{

	private JLabel lastEventLabel;

	private JLabel countField;

	private JPanel panel;

	private GridComponent gridComponent;

	public GridComponent getGridComponent() {
		return gridComponent;
	}

	private static final int FRAME_WIDTH = 500;

	private static final int FRAME_HEIGHT = 500;

	public GridFrame(Grid grid, Position initialAntPosition) {
		createTextField();

		createPanel();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);

		gridComponent = new GridComponent(grid, initialAntPosition, this);
		panel.add(gridComponent);
		panel.updateUI();
	}

	private void createTextField()

	{

		lastEventLabel = new JLabel("Last Event Processed: ");
		final int FIELD_WIDTH = 10;
		countField = new JLabel("");
	}
	
	private void createPanel() {

		panel = new JPanel();

		panel.add(lastEventLabel);

		panel.add(countField);

		add(panel);

	}

	void setNewEventLabel(String event) {
		this.countField.setText(event);
	}
}
