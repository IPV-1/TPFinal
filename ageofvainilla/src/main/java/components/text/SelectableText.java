package components.text;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;
import scenes.FieldScene;

import java.awt.*;


public abstract class SelectableText extends GameComponent<FieldScene> {
    private static int defaultFont = Font.PLAIN;
    public int style = defaultFont;
    private boolean selected = false;

    public SelectableText(Appearance appearance, double x, double y, boolean selected) {
        super(x, y);
        this.selected = selected;
        this.setAppearance(appearance);
        if (this.selected) {
            this.select();
        }
    }

    public SelectableText(String text, double x, double y, boolean selected) {
        this(new Label(new Font("SansSerif", defaultFont, 50), Color.RED, text), x, y, selected);
    }

    public void update(DeltaState deltaState) {
        if (this.clicked(deltaState)) {
            this.getGame().setCurrentScene(this.getNewScene());
        }
    }

    public void select() {
        this.style = Font.BOLD;
        this.changeStyle(this.style);
        this.selected = true;
    }

    public void unselect() {
        this.style = Font.PLAIN;
        this.changeStyle(this.style);
        this.selected = false;
    }

    public boolean clicked(DeltaState deltaState) {
        return deltaState.isKeyPressed(this.textKey()) && this.selected;
    }

    protected Key textKey() {
        return Key.ENTER;
    }

    protected void changeStyle(int style) {
        this.style = style;
        Label label = (Label) this.getAppearance();
        Font font = label.getFont().deriveFont(style);
        label.setFont(font);
    }

    public abstract GameScene getNewScene();

}
