package components.boards;

import java.awt.Color;
import java.awt.Font;

import scenes.FieldScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public abstract class Board extends GameComponent<FieldScene> {
	
	private int value;
	
	public Board(double x, double y, Color color) {
		super(new Label(new Font("verdana",  Font.BOLD, 34), color, "0"), x, y);
	}
	
    public void changeScore(int score){
        this.getAppearance().setText(this.getLabel() +": "+ Integer.toString(score));
    }
	
	public abstract String getLabel();
	
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
