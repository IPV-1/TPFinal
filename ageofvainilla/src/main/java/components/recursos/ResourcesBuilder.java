package components.recursos;

import scenes.FieldScene;

import components.units.resources.Resource;
import components.units.resources.factory.ResourceFactory;

public class ResourcesBuilder {

	public static void initialBuild(FieldScene scene) {
		effectiveBuildIn(scene, ResourceFactory.createGold(4, 2, 200));
		effectiveBuildIn(scene, ResourceFactory.createGold(4, 4, 200));
		
		effectiveBuildIn(scene, ResourceFactory.createRock(15, 4, 200));
		
		effectiveBuildIn(scene, ResourceFactory.createFood(17, 4, 200));
		
		effectiveBuildIn(scene, ResourceFactory.createLumber(17, 7, 200));
	}
	
	public static void effectiveBuildIn(FieldScene scene, Resource resource) {
		scene.getMap().occupyBuilding(resource);
		scene.addComponent(resource);
	}

}
