package scenes.statics;


import java.util.ArrayList;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;
import components.text.ExitText;
import components.text.LoadGameText;
import components.text.NewGameText;
import components.text.SelectableText;
import components.text.TextSelector;

public class StartScene extends GameScene {

    public StartScene (Game game) {
        super();
        this.setGame(game);
        ArrayList<SelectableText> selectableTexts = new ArrayList<SelectableText>();
        selectableTexts.add(new NewGameText("New Game", 50, 50, true));
        selectableTexts.add(new LoadGameText("Load Game", 50, 150, false));
        selectableTexts.add(new ExitText("Exit", 50, 250, false));

        this.addComponent(new TextSelector(selectableTexts));
        this.addComponents(selectableTexts);
        
//        Configuration.getSound("intro_sound").play();
    }

}
