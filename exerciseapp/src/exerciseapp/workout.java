package exerciseapp;

import java.util.*;

public class workout {
	private int intensity;
	private List<String> prog = new ArrayList<String>();
	public List<String> createProg(int intensity, String Focus) {
		if (Focus.equals("cardio")){
			for(int i = 0; i <= intensity; i++) {
				prog.add("jumping jacks");
			}
	}
		return prog;
}
}
