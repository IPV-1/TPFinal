package components.interfaces;

import com.uqbar.vainilla.DeltaState;
import components.MouseHandler;

public interface Selectable {

	public void seleccionate(MouseHandler mouse, DeltaState deltaState);
	
	public void seleccionate(MouseHandler mouse);
	
	public void deseleccionate(MouseHandler mouse);
}
