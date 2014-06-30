package components.units.resources;


public class ResourceType {
	
	public static String GOLD = "GOLD";
	public static String LUMBER = "LUMBER";
	public static String FOOD = "FOOD";
	public static String ROCK = "ROCK";
	
	private String label;

	public ResourceType(String label){
		setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	protected void setLabel(String label) {
		this.label = label;
	}
}
