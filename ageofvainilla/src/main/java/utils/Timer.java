package utils;


public class Timer {

	private double time;
	private double limit;
	
	public Timer() {
		this.reset();
	}

	public void reset() {
		this.setTime(0d);
	}

	public double getLimit() {
		return this.limit;
	}
	
	public void setLimit(double limit) {
		this.limit = limit;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
	public void addTime(double time) {
		this.setTime(this.getTime() + time);
	}
}
