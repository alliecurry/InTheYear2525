import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import processing.core.*;
import wordcram.*;

@SuppressWarnings("serial")
public class main_class extends PApplet{
	
	int frameCounter = 0; // frame counter for noloop function

	public GLOBAL g = new GLOBAL(this);
	public Parser pars = new Parser();
	
	public static Menu menu;
	public static GraphsArea graphArea;
	public static MultistateButton analysisTypeButton;
	public TagCloud tc;
	
	public void setup() {
		
		setupColors();
		
		size(1024,768, JAVA2D);
		frameRate(60);
		
		background(GLOBAL.colorBackgroundLayerTwo);
		
		GLOBAL.gu = new GuiGradient();
//		GLOBAL.tFont = loadFont("LucidaSans-48.vlw");
		GLOBAL.tFont = createFont("Segoe UI", 20);
		GLOBAL.selectedEpisodesList = Parser.LIST_ALL; // Initialize the displayed list to "all episodes"

		//Parse all available transcripts.
		pars.parseCatchphrases();		//Loads cathphrases (& their regular expressions) into corresponding Character objects
		pars.parseBackgroundChars();	//Loads unacceptable character names from file names.txt
		pars.parseAllTranscripts();		//Parse all episode transcripts
		pars.filterCharacters();        //Remove characters in less than 1 episode
		GLOBAL.parseForWordMap = true;	//Flag to enable parsing on .txt files for word mapping [DO NOT REMOVE!!!]
		pars.parseAllTranscripts();		//Parse all word file transcripts.
		
		
		//WORD FREQUENCY TESTING ------------------------
		ArrayList<Word> a1 = Parser.ALL_CHARACTERS.get(0).getWordRange(1, 1, 6, 26);
		int max=50; if(a1.size()<max) { max = a1.size(); }
		
		/*System.out.println(a1.get(0).getWord() + "\t" + a1.get(0).getFreq());
		System.out.println(a1.get(1).getWord() + "\t" + a1.get(1).getFreq());
		System.out.println(a1.get(2).getWord() + "\t" + a1.get(2).getFreq());
		a1 = Parser.ALL_CHARACTERS.get(0).getWordRange(1, 1, 6, 26);
		System.out.println("------\n"+a1.get(0).getWord() + "\t" + a1.get(0).getFreq());
		System.out.println(a1.get(1).getWord() + "\t" + a1.get(1).getFreq());
		System.out.println(a1.get(2).getWord() + "\t" + a1.get(2).getFreq());*/
		
		//for(int z=0; z<max; ++z) {
			//System.out.println(a1.get(z).getWord() + "\t" + a1.get(z).getFreq());
		//}//------------------------------------------------*/
		
		//Sort ALL_CHARACTERS where characters in more episodes are listed first.
		/*Collections.sort(Parser.ALL_CHARACTERS, new Comparator<Object>(){
            public int compare(Object ob1, Object ob2) {
                Character c1 = (Character) ob1;
                Character c2 = (Character) ob2;
               return c2.getTotalEpisodes() - c1.getTotalEpisodes();
            }
 
        });*/
		
		//Sort ALL_CHARACTERS alphabetically
			Collections.sort(Parser.ALL_CHARACTERS, new Comparator<Object>(){
	            public int compare(Object ob1, Object ob2) {
	                Character c1 = (Character) ob1;
	                Character c2 = (Character) ob2;
	               return c1.getName().compareToIgnoreCase(c2.getName());
	            }
	 
	        });
		
		// Set incons of the characters after the parsing end
		for (int i = 0; i< Parser.ALL_CHARACTERS.size(); i++) {
			Parser.ALL_CHARACTERS.get(i).setIcon();
		}

		// TreeMap .tm3 file -> copy & paste on the Characters.tm3 file  
		/*System.out.println("Appearence");
		System.out.println("INTEGER");
		
		for(int x=0; x<Parser.ALL_CHARACTERS.size(); ++x) {
			String name = Parser.ALL_CHARACTERS.get(x).getName();
			int ep = Parser.ALL_CHARACTERS.get(x).getTotalEpisodes();

			//System.out.println(name + ":\t\t" + ep + " episodes.");

			if ( Parser.ALL_CHARACTERS.get(x).getTotalEpisodes() > 5)
				//System.out.println("<leaf>\n<label>"+ Parser.ALL_CHARACTERS.get(x).getName() +"</label>\n<weight>"+ Parser.ALL_CHARACTERS.get(x).getTotalEpisodes() +"</weight>\n<value>"+ Parser.ALL_CHARACTERS.get(x).getTotalEpisodes() +"</value>\n</leaf>");
				System.out.println(Parser.ALL_CHARACTERS.get(x).getTotalEpisodes() + "\tCharacters\t" + Parser.ALL_CHARACTERS.get(x).getName());
		}
		*/
		
		//TESTING... 1 2 3! 
		//Parser.ALL_CHARACTERS.get(30).printPhrases();
		
		// Create the menu
		menu = new Menu();
		menu.width = 200;
		menu.height = height;
		
		// Create the plotting area, composed at most by 3 plots
		graphArea = new GraphsArea();
		graphArea.x = 220;
		graphArea.y = 40;
		graphArea.width = 1000 - 220;
		graphArea.height = 750 - 40;
		graphArea.createScrollBar();
		
		// tristate button for setting type of analysis
		analysisTypeButton = new MultistateButton();
		analysisTypeButton.x = 860;
		analysisTypeButton.y = 10;
		analysisTypeButton.addState("characters");
		analysisTypeButton.addState("episodes");
		analysisTypeButton.addState("seasons");
		analysisTypeButton.active = true;
		
		// Initialization of the first kind of analysis
		GLOBAL.ANALYSIS_TYPE = analysisTypeButton.getState();
		
		GLOBAL.episodeStart = Parser.LIST_ALL.get(0);
		GLOBAL.episodeEnd = Parser.LIST_ALL.get(Parser.LIST_ALL.size() - 1 );
	  
		smooth();
		
	}

	public void draw() {

		frameCounter++;


		drawLayerTwoBackground();
		graphArea.draw();
		menu.draw();
		analysisTypeButton.draw();

		drawLayerTwoText();
		noFill();


		if (frameCounter == 120) { // wait 2 sec before going to sleep
			noLoop();
			frameCount = 0;
		}
	}

	// Draw the background of the second layer
	public void drawLayerTwoBackground() {
		background(GLOBAL.colorBackgroundLayerTwo);
	}
	
	public void drawLayerTwoText() {
		fill(GLOBAL.colorText);
		textFont(GLOBAL.tFont,20);
		textAlign(CENTER);
//		// TODO now only for season
//		if (GLOBAL.SEASON_SELECTED == 0)
//			text("You have selected: all seasons", width/2 - 150, 40);
//		else
//		text("You have selected: season " + GLOBAL.SEASON_SELECTED, width/2 - 150, 40);
		text("In the year 2525", 100, 40);
		textFont(GLOBAL.tFont,16);
		text("Select type of analysis: ",750, 30);
		
	}
	
	// Color setup function, all colors should be set here, so every change will be global
	public void setupColors() {
		GLOBAL.colorToggleOff = color(152);
		GLOBAL.colorText = color(224);
		GLOBAL.colorBackgroundLayerOne = color(36);
		GLOBAL.colorBackgroundLayerTwo = GLOBAL.colorBackgroundLayerOne;
		GLOBAL.colorLinesLabelY = color(128);		
		GLOBAL.colorButtonLabel = color(224);	
		GLOBAL.colorPlotArea = color(0);
		GLOBAL.colorStatsArea = color(28,28,28);
		GLOBAL.colorMenuBackground = color(28,28,28);
		GLOBAL.colorIconBackground = color(224);
		GLOBAL.colorTagCloudBackground = color(224,224,224,0);
		
		int[] temp = {color(136,204,238), color(68,170,153), color(17,119,51), color(221,204,119), 
					color(204,102,119), color(136,34,85), color(146,189,16), color(170,68,153), 
					color(51,34,136), color(255,255,225)
		};
		
		//Randomize colors
		int[] c = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		Random rand = new Random();
		
		for(int x=0; x<temp.length; ++x){
			int n = rand.nextInt(temp.length);
			while(c[n] != -1) {
				n = rand.nextInt(temp.length);
			}
			c[n] = temp[x];
		}

		GLOBAL.COLORS = new ColorSwatch(c);
	}

	// If mouse is pressed, check what has been pressed and activate the action
	public void mousePressed() { 
		frameCounter = 0;
		loop();

		println("x = " + mouseX+ "y =" + mouseY);

		if(menu.mouseOver() || menu.characterPicker.mouseOver() || menu.seasonPicker.mouseOver() || menu.episodePicker.mouseOver())
			menu.doAction();

		// Change type of analysis
		if ( analysisTypeButton.mouseOver() ) {
			analysisTypeButton.doAction();
			GLOBAL.ANALYSIS_TYPE = analysisTypeButton.getState();
			graphArea.clearGraphs();
		}

		if (graphArea.mouseOver())
			graphArea.doAction();

	}

	public void mouseClicked() {
		frameCounter = 0;
		loop();
	}
	public void mouseMoved() {
		frameCounter = 0;
		loop();
	}	

	public void mouseDragged() {
		frameCounter = 0;
		loop();
	}

	public void mouseReleased() {
		// Scrolls must be released 
		menu.episodePicker.scroll.mouseReleased();
		menu.characterPicker.scroll.mouseReleased();
		graphArea.mouseReleased();
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "main_class" });
	}

}