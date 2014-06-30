package components.buttons;

import components.MouseHandler;

import config.Configuration;

public class DeleteButton extends BuildingButton {
	
	public DeleteButton() {
		super(null, Configuration.getSprite("delete"), 0, 1);
	}
	
	@Override
	public void clickedBy(MouseHandler mouse) {
		// Delete selected units
		if(mouse.shouldInteractSeleted()) {
			mouse.deleteSelected();
		}
	}

}
