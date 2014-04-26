package components;

import age.AgeOfVainilla;
import com.uqbar.vainilla.GameComponent;
import scenes.FieldScene;


public class BasicAgeComponent extends GameComponent<FieldScene> {

    @Override
    public AgeOfVainilla getGame(){
        return (AgeOfVainilla) super.getGame();
    }
}
