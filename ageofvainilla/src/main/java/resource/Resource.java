package resource;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.exceptions.GameException;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

/**
 * Get Resource instances and Sound.
 * See test for usage.
 *
 * Resource#from{Image, Sound} search in "PROJECT_ROOT/src/main/resources/resource/"
 * Resource#get{Image, Sound} search in "PROJECT_ROOT/src/main/resources/resource/{img, sound}" respectively.
 *
 * Version 2.0
 */
public class Resource {

    protected static final String IMG_PACKAGE = "img";
    protected static final String SOUND_PACKAGE = "sound";

    /**
     * Returns the image in path "PROJECT_ROOT/src/main/resources/resource/imageFileName"
     * @param imageFileName String
     * @return Resource
     */
    public static Sprite fromImage(String imageFileName) {
        BufferedImage image;

        try {
            image = ImageIO.read(Resource.class.getResource(imageFileName));
        } catch (Exception e) {
            throw new GameException(String.format("The resource '%s' was not found", imageFileName));
        }

        return new Sprite(image);
    }

    /**
     * Shortcut for fromImage("img/fileName")
     * Will find file in "PROJECT_ROOT/src/main/resources/resource/img/fileName"
     * @param fileName String
     * @return Resource
     */
    public static Sprite getImage(String fileName) {
        return fromImage(String.format("%s/%s", IMG_PACKAGE, fileName));
    }

    /**
     * Returns the sound on path "PROJECT_ROOT/src/main/resources/resource/filePath"
     * @param filePath String
     * @return Sound
     */
    public static Sound fromSound(String filePath) {
        InputStream i =  Resource.class.getResourceAsStream(filePath);
        return SoundBuilder.buildSound(i);
    }

    /**
     * Shortcut for fromSound("sound/fileName").
     * Will find the file on path "PROJECT_ROOT/src/main/resources/resource/sound/filePath"
     * @param fileName String
     * @return Sound
     */
    public static Sound getSound(String fileName) {
        return fromSound(String.format("%s/%s", SOUND_PACKAGE, fileName));
    }
}
