package config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import resource.ResourceUtils;

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
		addSprite("house");
	
		addValue("screenWidth");
		addValue("screenHeight");
		
		addValue("tileWidth");
		addValue("tileHeight");
		
		addValue("withEnemyIA?");
		
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
    
}