package components.factors;

public class BlueFactor extends Factor {
	
	protected BlueFactor(){
	}
	
	@Override
	public boolean isAlly() {
		return true;
	}
	
	@Override
	public boolean isEnemy(Factor factor) {
		return factor.isEnemyOfBlueFactor();
	}
	
	@Override
	protected boolean isEnemyOfRedFactor() {
		return true;
	}
}
