package components.buttons;

import components.MouseHandler;

import config.Configuration;

public class DeleteButton extends BuildingButton {
	
	public DeleteButton() {
		super(null, Configuration.getSprite("delete").scaleTo(40, 40), 0, 1);
	}
	
	@Override
	public void clickedBy(MouseHandler mouse) {
		// Delete selected units
		mouse.deleteSelected();
	}

}
