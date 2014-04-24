package components.text;


import com.uqbar.vainilla.GameScene;

public class ExitText extends SelectableText {

    public ExitText(String text, double x, double y, boolean selected) {
        super(text, x, y, selected);
    }

    @Override
    public GameScene getNewScene() {
    	System.exit(0);
        return null;
    }
}
