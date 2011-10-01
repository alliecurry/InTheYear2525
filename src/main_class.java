import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import processing.core.*;

public class main_class extends PApplet{
	
	public GLOBAL g = new GLOBAL(this);
	public Parser pars = new Parser();
	
	public static Menu menu;
	public static GraphsArea graphArea;
	public static MultistateButton analysisTypeButton;
	
	public void setup() {
		
		setupColors();
		
		size(1024,768);
		frameRate(15);
		
		GLOBAL.gu = new GradientUtils();
		GLOBAL.tFont = loadFont("LucidaSans-48.vlw");
		GLOBAL.selectedEpisodesList = Parser.LIST_ALL; // Initialize the displayed list to "all episodes"

		//Parse all available transcripts.
		pars.parseBackgroundChars();
		pars.parseAllTranscripts();
		pars.filterCharacters();        //Remove characters in less than 1 episode
		

		//Sort ALL_CHARACTERS where characters in more episodes are listed first.
		Collections.sort(Parser.ALL_CHARACTERS, new Comparator<Object>(){
            public int compare(Object ob1, Object ob2) {
                Character c1 = (Character) ob1;
                Character c2 = (Character) ob2;
               return c2.getTotalEpisodes() - c1.getTotalEpisodes();
            }
 
        });
		
		
		//Prints all characters currently added. 
		//For testing only.
		/*
		for(int x=0; x<Parser.ALL_CHARACTERS.size(); ++x) {
			String name = Parser.ALL_CHARACTERS.get(x).getName();
			int ep = Parser.ALL_CHARACTERS.get(x).getTotalEpisodes();

			System.out.println(name + ":\t" + ep + " episodes.");
		}   
		*/  
		//
		
		// Create the menu
		menu = new Menu();
		menu.width = 200;
		menu.height = height;
		
		// Create the plotting area, composed at most by 3 plots
		graphArea = new GraphsArea();
		graphArea.x = 220;
		graphArea.y = 90;
		graphArea.width = 1000 - 220;
		graphArea.height = 750 - 90;
		graphArea.createScrollBar();
		
		// tristate button for setting type of analysis
		analysisTypeButton = new MultistateButton();
		analysisTypeButton.x = 860;
		analysisTypeButton.y = 50;
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
	
    @SuppressWarnings("unchecked")
	public void draw() {

			drawLayerTwoBackground();
			graphArea.draw();
			menu.draw();
			analysisTypeButton.draw();
//			
//			int dateSelectorX = (width - Parser.LIST_ALL.size()*2)/2;
//			
//			strokeWeight(2);
//			stroke(GLOBAL.colorText);
//			for (int i = 0; i< Parser.LIST_ALL.size() ; i++) {
//				int x = dateSelectorX + i*5;
//				line(x,height,x,height - 7);
//			}
			
			drawLayerTwoText();
			noFill();

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
		text("Select type of analysis: ",750, 70);
		
	}
	
//	public void drawLayerTwoStats(){
//		  noStroke();
//		  rectMode(CORNERS);
//		  fill(GLOBAL.colorStatsArea);
//		  rect(GLOBAL.statX1,GLOBAL.statY1, GLOBAL.statX2, GLOBAL.statY2);
//		  fill(GLOBAL.colorText);
//		  text("Statistics:",24,90);
//	}
	
	// Color setup function, all colors should be set here, so every change will be global
	public void setupColors() {
		GLOBAL.colorToggleOff = color(152);
		GLOBAL.colorText = color(224);
		GLOBAL.colorBackgroundLayerOne = color(36);
		GLOBAL.colorBackgroundLayerTwo = GLOBAL.colorBackgroundLayerOne;
		GLOBAL.colorLinesLayerOne = color(128);		
		GLOBAL.colorButtonLabel = color(224);	
		GLOBAL.colorPlotArea = color(0);
		GLOBAL.colorStatsArea = color(28,28,28);
		GLOBAL.colorMenuBackground = color(28,28,28);
	}
	
	// If mouse is pressed, check what has been pressed and activate the action
	public void mousePressed() { 
		  println("x = " + mouseX+ "y =" + mouseY);
		  
		  if(menu.mouseOver() || menu.characterPicker.mouseOver() || menu.seasonPicker.mouseOver() || menu.episodePicker.mouseOver())
			  menu.doAction();
		  
		  // Change type of analysis
		  if ( analysisTypeButton.mouseOver() ) 
			  analysisTypeButton.doAction();
		  
		  if (graphArea.mouseOver())
			  graphArea.doAction();
			  
	}
	
	public void mouseReleased() {
		// Scrolls must be released 
		menu.episodePicker.scroll.mouseReleased();
		menu.characterPicker.scroll.mouseReleased();
		graphArea.scroll.mouseReleased();
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "main_class" });
	}

}