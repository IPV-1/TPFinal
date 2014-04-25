import junit.framework.TestCase;
import resource.Resource;

public class ResourceTest extends TestCase {


    public void testGetResourceFromImagePath() {
        Resource.fromImage("img/ball.png");
    }


    public void testGetImage() {
        Resource.getImage("ball.png");
    }

    public void testGetImageFromSubFolder() {
        Resource.getImage("scenes/title.png");
    }


    public void testGetSoundFromFilePath() {
        Resource.fromSound("sound/ball_hit.wav");
    }


    public void testGetSound() {
        Resource.getSound("ball_hit.wav");
    }

}
