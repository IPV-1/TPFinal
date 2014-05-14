package resource;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.exceptions.GameException;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class ResourceUtils {

    protected static final String IMG_PACKAGE = "img";
    protected static final String SOUND_PACKAGE = "sound";

    public static Sprite getSprite(String fileName) {
        return ResourceUtils.getSprite(IMG_PACKAGE, fileName);
    }

    /**
     * filePackage needs to be inside Resource's package.
     * If there is more than one level of packages, it needs to be written as
     * package/subPackage
     */
    public static Sprite getSprite(String filePackage, String fileName) {
        BufferedImage image;
        String filePath;
        filePath = filePackage + "/" + fileName;
        try {
            image = ImageIO.read(ResourceUtils.class.getResource(filePath));
        } catch (Exception e) {
            throw new GameException("The resource '" + filePath + "' was not found");
        }

        return new Sprite(image);
    }

    public static Sound getSound(String fileName) {
        return ResourceUtils.getSound(SOUND_PACKAGE, fileName);
    }

    public static Sound getSound(String filePackage, String fileName) {
        String filePath;
        filePath = filePackage + "/" + fileName;
        return SoundBuilder.buildSound(ResourceUtils.class.getResourceAsStream(filePath));
    }
}
