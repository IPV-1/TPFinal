package config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import map.tiles.Tile;

import resource.AnimationGenerator;
import resource.ResourceUtils;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;

public class Configuration {

    private static Properties properties;
    
	private static Map<String, Sprite> sprites = new HashMap<String, Sprite>();
	private static Map<String, Double> values = new HashMap<String, Double>();
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	private static Map<String, String> strings = new HashMap<String, String>();
	
    private static void loadConfiguration() {
		addSprite("pointer");
		addSprite("grass");
		addSprite("plain");
		addSprite("rock");
		addSprite("water");
		
		addSprite("gold_res");
		addSprite("rock_res");
		addSprite("lumber_res");
		addSprite("food_res");
		
		addSprite("house");
		addSprite("wall");
		addSprite("center");
		
		addSprite("delete");
		addSprite("stop");
		
		addSprite("stand");
		addSprite("standE");
		addSprite("walking_unit");
		addSprite("walking_unitE");
	
		addValue("screenWidth");
		addValue("screenHeight");
		
		addValue("tileWidth");
		addValue("tileHeight");
		
		addValue("farmPerMinute_unit");
		
		addValue("withEnemyIA?");
		
		addValue("movingUnitSpeed");
		addValue("unitHP");
		addValue("unitPA");
		
		addValue("enemyDelay");
		addValue("enemySpawnX");
		addValue("enemySpawnY");
		
		addValue("downButtonWidth");
		addValue("downButtonHeight");
		
		addString("map_txt");
		addString("map_img");
		
		addSound("intro_sound");
    }
    
    public static void LOAD(String file) {
    	properties = getConfigurations(file);
    	loadConfiguration();
    }
    
	public static Sprite getSprite(String key) {
		return sprites.get(key);
	}

	public static Double getValue(String key) {
		return values.get(key);
	}
	
	public static Sound getSound(String key) {
		return sounds.get(key);
	}
	
	public static String getString(String key) {
		return strings.get(key);
	}

    private static String FETCH(String key){
        return (String) properties.get(key);
    }

    private static Properties getConfigurations(String file) {
        URL configFile = Configuration.class.getResource(file);
        try {
            InputStream configFileStream = configFile.openStream();
            Properties p = new Properties();
            p.loadFromXML(configFileStream);
            configFileStream.close();
            return p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
	private static void addValue(String key) {
		values.put(key, Double.valueOf(FETCH(key)));
	}

	private static void addSprite(String key) {
		sprites.put(key, ResourceUtils.getSprite(FETCH(key)));
	}
	
	private static void addSound(String key) {
		sounds.put(key, ResourceUtils.getSound(FETCH(key)));
	}
	
	private static void addString(String key) {
		strings.put(key, FETCH(key));
	}

	public static int getDisplayWidth() {
		return getValue("screenWidth").intValue();
	}

	public static int getDisplayHeight() {
		return getValue("screenHeight").intValue();
	}

	public static boolean getBoolean(String string) {
		return getValue(string) > 0;
	}

	public static Appearance getScaledAppearance(Appearance appearance) {
		return getScaledAppearance(appearance, Tile.WIDTH, Tile.HEIGHT);
	}

	public static Appearance getScaledAnimation(String imgID, int widthSprite, int heightSprite) {
//		AnimationGenerator.createAnimation(0.2, 1, Configuration.getSprite("walking_unit"), 29, 29);
		
		Sprite img = Configuration.getSprite(imgID);
		
		return AnimationGenerator.createScaledAnimation(0.2, img, widthSprite, heightSprite, Tile.WIDTH, Tile.HEIGHT);
	}

	public static Appearance getScaledAppearance(Appearance appearance, int width, int height) {
		Appearance app = appearance.copy();
		
		if(app instanceof Sprite) {
			app = ((Sprite)app).scaleTo(width, height);
		}
		
		return app;
	}

	public static Appearance getScaledDownButton(Appearance appearance) {
		int WIDTH = Configuration.getValue("downButtonWidth").intValue();
		int HEIGHT = Configuration.getValue("downButtonHeight").intValue();
		
		return Configuration.getScaledAppearance(appearance, WIDTH, HEIGHT);
	}
    
}