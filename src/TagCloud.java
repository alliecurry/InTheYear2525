import java.util.ArrayList;

import processing.core.*;
import wordcram.*;

public class TagCloud{
	
	public ArrayList<wordcram.Word> alphabet;
	public wordcram.Word[] w;
	
	public WordCram wc;
	PGraphics buffer;
	
	public TagCloud() {
	
		alphabet = new ArrayList<wordcram.Word>();
		
		for (int i =0; i< Parser.ALL_CHARACTERS.size(); i++) {
			if (Parser.ALL_CHARACTERS.get(i).getTotalEpisodes() > 7) {
			alphabet.add( new wordcram.Word(Parser.ALL_CHARACTERS.get(i).getName(), (int) Parser.ALL_CHARACTERS.get(i).getTotalEpisodes()));
			//System.out.println("found");
			}
		}
		

		
//		 wc = new WordCram(GLOBAL.processing)
//		  .fromTextFile("data/transcripts/SEASON1/S1E1.txt");

		buffer = GLOBAL.processing.createGraphics(600, 300, GLOBAL.processing.JAVA2D);
		buffer.beginDraw();
		buffer.background(0);
		
		w = new wordcram.Word[alphabet.size()];
		
		  wc = new WordCram(GLOBAL.processing)
		  //.fromTextFile("data/names.txt")
		  .fromWords(alphabet.toArray(w))
				//.withColors(color(255,0,0), color(0), color(0,0,255)) // red, black, and blue
		  .withCustomCanvas(buffer)
		 .withColor(GLOBAL.colorText)
				    .sizedByWeight(9, 30).withWordPadding(4).withAngler(Anglers.horiz()).withPlacer(Placers.centerClump());

	}
	
	public void draw() {

		if (wc.hasMore()) {  

			// draw the progress bar
			//float progress = wc.getProgress();
			//drawProgressBar(progress);
			//drawProgressText(progress);

			wc.drawNext();
		}
		else {

			buffer.endDraw();
			GLOBAL.processing.image(buffer, 300, 200);

			//System.out.println("done.");

		}
		//if (!wc.hasMore())
	}

	void drawProgressBar(float progress) {
		int gray = GLOBAL.processing.color(progress * 255);

		// Draw the empty box:
		GLOBAL.processing.noFill();
		GLOBAL.processing.stroke(255);
		GLOBAL.processing.strokeWeight(2);
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNER);
		GLOBAL.processing.rect(100, (300/2)-30, (600-200), 60);
		  
		  // Fill in the portion that's done:
		  GLOBAL.processing.fill(gray);
		  GLOBAL.processing.rect(100, (300/2)-30, (600) * progress, 60);
		}

		void drawProgressText(float progress) {
			GLOBAL.processing.text(GLOBAL.processing.round(progress * 100) + "%", 600/2, (300/2)+50);
		}
	
}

//public class TagCloud extends Widget{
//
//	PFont font;
//	String fontFile = "LucidaSans-48.vlw";
//	float baseline_ratio = (float)0.4;
//
//	String[] tags  = {  "The Office", "The Colbert Report", "Battlestar Galactica", "weeds", "californication", "scrubs", "CSI", "Smallville", "House", "Family Guy", "Daily Show", "US", "Boondocks", "Venture Brothers", "Flight of the Conchords", "Firefly", "Angel", "Grey's Anatomy", "Venture Bros", "King of The Hill", "America's Next Top Model", "Project Runway", "My Name is Earl", "Dirty Jobs", "Eureka", "Friends", "Frasier", "Sex and the City", "Food Network", "South Park", "Seinfeld", "The Daily Show", "Colbert Report", "The Riches", "LOST", "Aqua Teen Hunger Force", "Arrested Development", "Freaks and Geeks", "MythBusters", "Dexter", "Six Feet Under", "Home Movies", "Pete & Pete", "Extras", "tears", "Law and Order", "Pete and Pete", "Invader Zim", "Jericho", "The Boondocks", "The Simpsons", "Futurama", "jeopardy", "King Of Queens", "American Dad", "Dr. Who", "That 70's Show", "Avatar", "Fraggle Rock", "History Channel", "24", "Kids in the Hall", "Strangers With Candy"};
//	int[] tagtally = {  16, 4, 3, 8, 2, 15, 5, 3, 8, 16, 3, 2, 2, 2, 5, 5, 2, 9, 2, 2, 4, 4, 3, 2, 2, 5, 2, 4, 2, 2, 3, 6, 7, 2, 9, 4, 7, 2, 2, 6, 3, 5, 2, 2, 2, 2, 3, 3, 2, 3, 5, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
//	int most = GLOBAL.processing.max(tagtally);
//	int least = GLOBAL.processing.min(tagtally);
//	int large_font = 28;
//	int small_font = 8;
//
//	public Box[] boxes =  new Box[tags.length];
//
//	public TagCloud(){
//	  font = GLOBAL.processing.loadFont(fontFile);
//	  
//	  for (int i = 0; i < tags.length; i++) {
//	    int h_test = (int)GLOBAL.processing.map(tagtally[i], least, most, small_font, large_font);
//	    GLOBAL.processing.textFont(font, h_test);
//	    int w_test = (int)(GLOBAL.processing.textWidth(tags[i]));
//
//	    boxes[i] = new Box(tags[i],w_test,h_test);
//	  }
//	}
//
//	public void draw(){
//		
//	  GLOBAL.processing.rectMode(GLOBAL.processing.CENTER);
//
//	  GLOBAL.processing.pushMatrix();
//	  GLOBAL.processing.scale((float)0.82,(float)0.82);
//	  GLOBAL.processing.translate((float)0.12*width,(float)0.15*height);
//	  for(int i=0; i<boxes.length; i++){
//	    boxes[i].collide(i);
//	    GLOBAL.processing.fill(0);
//	    boxes[i].render(i);
//	  }
//	  GLOBAL.processing.popMatrix();
//	  
//	  //check for stopping
//	  int completed = 0;
//	  for(int i=0; i<boxes.length; i++){
//	    if(boxes[i].frozen)
//		completed++;
//	  }
//	  System.out.println("completed:" + completed);
//	  if(completed == boxes.length){
//		  //GLOBAL.processing.noLoop();
//	  }
//	}
//
//	void mouseReleased(){
//	  boxes[0].x = GLOBAL.processing.random(20,380);
//	  boxes[0].y = GLOBAL.processing.random(20,180);
//	}
//	
//	
//	public class Box extends Widget{
//		public float x, y, w, h, volume, attempts;
//		String word;
//		boolean frozen = true;
//		int c;
//
//		public float angle = 0;
//		public float outwards = 1;
//
//		Box(String word, int w, int h){
//			this.word = word;
//			this.x = GLOBAL.processing.cos(angle) * GLOBAL.processing.log(outwards) * 42 + 500;
//			this.y = GLOBAL.processing.sin(angle) * GLOBAL.processing.log(outwards) * 21 + 500;
//			angle -= 61;
//			outwards += 0.4;
//			this.w = w;
//			this.h = h;
//			this.volume = w*h;
//			this.c = GLOBAL.processing.color(GLOBAL.processing.random(100,255), GLOBAL.processing.random(100,255), GLOBAL.processing.random(100,255));
//		}
//
//		void render(int id){
//			/*    noFill();
//		    stroke(0,20);
//		    rect(x,y,w,h); */
//			GLOBAL.processing.fill(c);
//			GLOBAL.processing.textFont(font, h);
//			GLOBAL.processing.text(tags[id], (int)(x - w/2), (int)(y + h * baseline_ratio));
//		}
//
//		void collide(int i){
//			frozen = true;
//			for(i+=1; i<boxes.length; i++){
//				float dx = boxes[i].x - this.x;
//				float dy = boxes[i].y - this.y;
//				float tx = boxes[i].w/2 + this.w/2;
//				float ty = boxes[i].h/2 + this.h/2;
//				if( (GLOBAL.processing.abs(dx) < tx) && (GLOBAL.processing.abs(dy) < ty) ){
//					float me = (float)0.5 * boxes[i].volume / this.volume;
//					float you = (float)0.5 * this.volume / boxes[i].volume;
//					attempts++;
//
//					System.out.println("for collision "+i+", I am "+me+", & you are "+you);
//					frozen = false;	
//					if(dx > 0){
//						this.x     -= me  * 1.5;
//						boxes[i].x += you * 1.5;
//					}else{
//						this.x     += me  * 1.5;
//						boxes[i].x -= you * 1.5;
//					}
//
//					if(dy > 0){
//						this.y     -= me;
//						boxes[i].y += you;
//					}else{
//						this.y     += me;
//						boxes[i].y -= you;
//					}
//
//					if(this.attempts > 500){
//						this.x = GLOBAL.processing.random(0,width);
//						this.y = GLOBAL.processing.random(0,height);
//						this.attempts = 0;
//					}
//				}
//			}
//		}
//	}
//	
//}
