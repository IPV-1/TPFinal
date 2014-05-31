package components.units.resources;


public class ResourceType {
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
