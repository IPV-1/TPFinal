package components.text;


import scenes.FieldScene;

import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Appearance;

public class NewGameText extends SelectableText {

    public NewGameText(String text, double x, double y, boolean selected) {
        super(text, x, y, selected);
    }
    
    public NewGameText(Appearance appearance, double x, double y, boolean selected) {
        super(appearance, x, y, selected);
    }

    @Override
    public GameScene getNewScene() {
        return new FieldScene(this.getGame());
    }
}
