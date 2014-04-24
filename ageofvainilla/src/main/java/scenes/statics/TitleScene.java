package scenes.statics;

import resource.Resource;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.events.constants.Key;

public class TitleScene extends GameScene {
	
	public TitleScene(Game game) {
		this.addComponent(new GameComponent<GameScene>(Resource.getSprite(
				"img/scenes", "title.png"), 0, 0) {
			@Override
			public void update(DeltaState deltaState) {
				if (deltaState.isKeyPressed(Key.ENTER)) {
					this.getGame().setCurrentScene(
							new StartScene(this.getGame()));
				}
				super.update(deltaState);
			}
		});
	}

}
