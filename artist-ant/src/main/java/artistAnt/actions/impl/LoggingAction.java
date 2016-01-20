package artistAnt.actions.impl;

import artistAnt.api.AntAction;
import artistAnt.model.Ant;
import artistAnt.model.CellAttributeType;

/**
 * Action with logging purposes, that just logs the current position and colour.
 * 
 * @author calcacuervo
 * 
 */
public class LoggingAction extends AntAction {

	@Override
	protected void executeActionIfNeeded(Ant ant){
		System.out.println(ant.getGrid().getAttributeAtPosition(
				ant.getCurrentCoord(), CellAttributeType.COLOUR_ATTRIBUTE));
		System.out.println(ant.getCurrentCoord());

	}
}
