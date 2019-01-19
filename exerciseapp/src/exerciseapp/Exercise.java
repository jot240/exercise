package exerciseapp;

public class Exercise {
	private int duration;
	private int reps;
	private String title;
	public int GetDuration() {
		return duration;
	}
	public int GetReps() {
		return reps;
	}
	public String GetTitle() {
		return title;
	}
	public void SetTitle(String exname) {
		title = exname;
	}
	public void SetReps(int n) {
		reps = n;
	}
	public void SetDuration(int n) {
		duration = n;
	}
}
