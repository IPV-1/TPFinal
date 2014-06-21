package components.factors;

public class RedFactor extends Factor {
	
	protected RedFactor(){
	}
	
	@Override
	public boolean isEnemy(Factor factor) {
		return factor.isEnemyOfRedFactor();
	}
	
	@Override
	protected boolean isEnemyOfBlueFactor() {
		return true;
	}

}
