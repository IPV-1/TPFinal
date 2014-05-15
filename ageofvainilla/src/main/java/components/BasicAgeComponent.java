package components;

import scenes.FieldScene;
import age.AgeOfVainilla;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;


public class BasicAgeComponent extends GameComponent<FieldScene> {

    public BasicAgeComponent(Appearance appearance, int x, int y) {
    	super(appearance, x, y);
	}
    
    public BasicAgeComponent(double x, double y) {
    	super(x, y);
	}
    
    public BasicAgeComponent() {
    	super();
	}

	@Override
    public AgeOfVainilla getGame(){
        return (AgeOfVainilla) super.getGame();
    }



}
