package components.recursos;

import scenes.FieldScene;

import components.units.resources.Resource;
import components.units.resources.factory.ResourceFactory;

public class ResourcesBuilder {

	public static void initialBuild(FieldScene scene) {
		effectiveBuildIn(scene, ResourceFactory.createGold(4,2, 200));
	}
	
	public static void effectiveBuildIn(FieldScene scene, Resource resource) {
		scene.getMap().occupy(resource, (int)resource.getTileX(), (int)resource.getTileY());
		scene.addComponent(resource);
	}

}
