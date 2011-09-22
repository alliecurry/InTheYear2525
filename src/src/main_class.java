import processing.core.*;

public class main_class extends PApplet {
	
	public void setup() {

		size(1023,720);
		
		smooth();
		
		// Load and initialize the data structures here..
		
	}

	public void draw() {

		background(0);
		
		// layer 1
		if(GLOBAL.LAYER == 1){
			
			
		}
		// layer 2
		else if(GLOBAL.LAYER == 2){
			
			
		}
		// layer 3
		else if(GLOBAL.LAYER == 3){


		}

	}


	// Starting point of the application
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "main_class" });
	}

}