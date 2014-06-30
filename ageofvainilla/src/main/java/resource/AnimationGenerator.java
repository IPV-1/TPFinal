package resource;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;

public class AnimationGenerator {
	public static Animation createAnimation(double time, double scale, Sprite image, int spriteWidth, int spriteHeight){
		Sprite[] sprites = getImageSprites(scale, image, spriteWidth, spriteHeight);
		
		return new Animation(time, sprites);
	}
	
	private static Sprite[] getImageSprites(double scale, Sprite image, int spriteWidth, int spriteHeight) {
		double imgWidth = image.getWidth();
		double imgHeight = image.getHeight();
		
		Sprite[] sprites = new Sprite[(int)(imgWidth/spriteWidth) * (int)(imgHeight/spriteHeight)]; 
		
		int index = 0;
		for (int j = 0; j < imgHeight; j += spriteHeight) {
			for (int i = 0; i < imgWidth; i += spriteWidth) {
				sprites[index] = image.crop(i, j, spriteWidth, spriteHeight);//.scale(scale);
				index++;
			}
		}
		return sprites;
	}

	public static Appearance createScaledAnimation(double time, Sprite img, int widthSprite, int heightSprite, int sizeWidth, int sizeHeight) {
		Animation anima = createAnimation(time, 1, img, widthSprite, heightSprite);
		
		Sprite[] sprites = new Sprite[anima.getSprites().length];
		
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = anima.getSprites()[i].scaleTo(sizeWidth, sizeHeight);
		}
		
		return new Animation(time, sprites);
	}
}
