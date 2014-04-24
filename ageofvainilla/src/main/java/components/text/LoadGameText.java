package components.text;


import scenes.FieldScene;

import com.uqbar.vainilla.GameScene;

public class LoadGameText extends SelectableText {

    public LoadGameText(String text, double x, double y, boolean selected) {
        super(text, x, y, selected);
    }

    @Override
    public GameScene getNewScene() {
        return new FieldScene(this.getGame());
    }
}
