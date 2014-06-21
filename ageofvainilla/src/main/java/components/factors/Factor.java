package components.factors;

public abstract class Factor {
	
	public static final Factor RED = new RedFactor();
	public static final Factor BLUE = new RedFactor();
	public static final Factor RES = new RedFactor();
	public static final Factor NEU = new NeutralFactor();
	
	protected Factor() {
	}
	
	public boolean isEnemy(Factor factor){
		return false;
	}
	
	protected boolean isEnemyOfRedFactor() {
		return false;
	}
	
	protected boolean isEnemyOfBlueFactor() {
		return false;
	}
	
	public boolean isResource() {
		return false;
	}

}
