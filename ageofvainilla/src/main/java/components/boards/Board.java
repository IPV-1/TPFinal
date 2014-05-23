package components.boards;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Label;
import components.BasicAgeComponent;

public abstract class Board extends BasicAgeComponent {
	
	private int value;
	
	public Board(double x, double y, Color color) {
		super(x, y);

		this.setAppearance(new Label(new Font("verdana",  Font.BOLD, getFontSize()), color, "0")); 
	}
	
    public int getFontSize() {
		return 32;
	}

	public void changeScore(int score){
        this.getAppearance().setText(this.getTitle() +": "+ Integer.toString(score));
    }
	
	public abstract String getTitle();
	
	public void add(int value) {
		this.setValue(this.getValue() + value);
	}

	public void reset() {
		this.setValue(0);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		this.changeScore(getValue());
		super.update(deltaState);
	}
	
    @Override
    public Label getAppearance(){
        return (Label) super.getAppearance();
    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
