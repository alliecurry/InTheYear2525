import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import processing.core.*;

public class main_class extends PApplet{
	
	public GLOBAL g = new GLOBAL(this);
	public Parser pars = new Parser();
	
	public Menu menu;
	public GraphsArea graphArea;
	
	// test
	public ScrollBar scroll;

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
		for(int x=0; x<Parser.ALL_CHARACTERS.size(); ++x) {
			String name = Parser.ALL_CHARACTERS.get(x).getName();
			int ep = Parser.ALL_CHARACTERS.get(x).getTotalEpisodes();

			System.out.println(name + ":\t" + ep + " episodes.");
		}     
		//
		
		
//		setupCharacterButtons();
//		setupSeasonsButtons();
//		setupFilterButtons();
//		setupEpisodeButtons();
		
		// testing
		menu = new Menu();
		menu.width = 400;
		menu.height = 700;
		graphArea = new GraphsArea();
		// tristate button
								
		smooth();
		
	}
	
    @SuppressWarnings("unchecked")
	public void draw() {

		// layer 1
		if(GLOBAL.LAYER == 1){
			
			drawLayerOneBackground();
			drawLayerOneText();
			
			drawCharacterButtons();
			drawSeasonsButtons();
			drawFilterButtons();
			drawEpisodeButtons();
			
		}
		// layer 2
		else if(GLOBAL.LAYER == 2){
			drawLayerTwoBackground();
			drawLayerTwoText();
			//drawLayerTwoStats();
			//drawGraph();
			graphArea.draw();
			menu.draw();
			
		}
		// layer 3
		else if(GLOBAL.LAYER == 3){


		}

	}

	///
	/// Layer One functions
	///
	public void drawLayerOneText() {
		fill(GLOBAL.colorText);
		textFont(GLOBAL.tFont,24);
		text("In the year 2525", width/2 - 100, 40);
		text("Choose a character :",50,100);
		text("Choose among series and episodes :",50 + width/2,100);
	}

	// Draw the background of the first layer, i.e. the layer where you choose a character, a season or an episode (or all episodes)
	public void drawLayerOneBackground() {

		background(GLOBAL.colorBackgroundLayerOne);
		
		stroke(this.color(GLOBAL.colorLinesLayerOne));
		strokeWeight(2);
		line(width/2, 60, width/2, height - 30);
		
	}
	
	// SEASON BUTTONS
	public void setupSeasonsButtons(){
		SeasonButton b1 = new SeasonButton(1);
		b1.setLabel("Season 1");
		b1.x = 565;
		b1.y = 190;
		b1.active = true;
		SeasonButton b2 = new SeasonButton(2);
		b2.setLabel("Season 2");
		b2.x = 715;
		b2.y = 190;
		b2.active = true;
		SeasonButton b3 = new SeasonButton(3);
		b3.setLabel("Season 3");
		b3.x = 865;
		b3.y = 190;
		b3.active = true;
		SeasonButton b4 = new SeasonButton(4);
		b4.setLabel("Season 4");
		b4.x = 565;
		b4.y = 230;
		b4.active = true;
		SeasonButton b5 = new SeasonButton(5);
		b5.setLabel("Season 5");
		b5.x = 715;
		b5.y = 230;
		b5.active = true;
		SeasonButton b6 = new SeasonButton(6);
		b6.setLabel("Season 6");
		b6.x = 865;
		b6.y = 230;
		b6.active = true;
		SeasonButton bAll = new SeasonButton(0);
		bAll.setLabel("All seasons");
		bAll.x = 715;
		bAll.y = 270;
		bAll.active = true;
		GLOBAL.allSeasonsButtons.add(b1);
		GLOBAL.allSeasonsButtons.add(b2);
		GLOBAL.allSeasonsButtons.add(b3);
		GLOBAL.allSeasonsButtons.add(b4);
		GLOBAL.allSeasonsButtons.add(b5);
		GLOBAL.allSeasonsButtons.add(b6);
		GLOBAL.allSeasonsButtons.add(bAll);
	}
	
	
    // draw the seasons buttons
	public void drawSeasonsButtons() {
		// Draw all the series button
		for(int i = 0; i < GLOBAL.allSeasonsButtons.size(); i++) {
			GLOBAL.allSeasonsButtons.get(i).draw();	
		}
	}
	
	// EPISODE BUTTONS
	public void setupEpisodeButtons() {
		// Create a new scrollbar
		scroll = new ScrollBar();
		scroll.x = 560;
		scroll.y = 490;	
		scroll.width = 15;
		scroll.height = 250;	
		// Draw all the series button
		scroll.size = (float)4 / GLOBAL.selectedEpisodesList.size();
		if (scroll.size > 1)
			scroll.size = 1;
	}
	
	public void drawEpisodeButtons(){

		float val = 0;
		// elementNumber: var for offset calculation
		int elementNumber = 0;	
		
		// Draw the scrollBar
		if (GLOBAL.selectedEpisodesList.size() != 0)
			scroll.size = (float)4 / GLOBAL.selectedEpisodesList.size();
		if (scroll.size > 1)
			scroll.size = 1;
		if (GLOBAL.selectedEpisodesListChanged) {
			scroll.val = 0;
			GLOBAL.selectedEpisodesListChanged = false;
		}
		scroll.draw();
		
		// First, create and deactivate all buttons	
		GLOBAL.allEpisodesButtons.clear();
		for(int i =0; i < GLOBAL.selectedEpisodesList.size(); i++) {
			Button b = new Button();
			b.setLabel("S"+GLOBAL.selectedEpisodesList.get(i).getSeason()+"E"+GLOBAL.selectedEpisodesList.get(i).getEpisode()+": "+ GLOBAL.selectedEpisodesList.get(i).getName());
			b.active = false;
			GLOBAL.allEpisodesButtons.add(b);
			
		}
		// Find from what button we have to print, based on the value of the scrollbar in this moment
		val = map(scroll.val,0, 1,0,GLOBAL.allEpisodesButtons.size()-5);
		// Draw all the visible series buttons
		for(int j = (int)val; j <= val + 5 && j < GLOBAL.allEpisodesButtons.size(); j++) {
			GLOBAL.allEpisodesButtons.get(j).x = 600;
			GLOBAL.allEpisodesButtons.get(j).y = 500 + elementNumber*40;
			GLOBAL.allEpisodesButtons.get(j).active = true;
			GLOBAL.allEpisodesButtons.get(j).draw();
			elementNumber++;
		}
	}
	
	// FILTER BUTTONS
	public void setupFilterButtons() {
		FilterButton b1 = new FilterButton(1);
		b1.setLabel("S1");
		b1.x = 560;
		b1.y = 400;
		b1.active = true;
		FilterButton b2 = new FilterButton(2);
		b2.setLabel("S2");
		b2.x = 600;
		b2.y = 400;
		b2.active = true;
		FilterButton b3 = new FilterButton(3);
		b3.setLabel("S3");
		b3.x = 640;
		b3.y = 400;
		b3.active = true;
		FilterButton b4 = new FilterButton(4);
		b4.setLabel("S4");
		b4.x = 680;
		b4.y = 400;
		b4.active = true;
		FilterButton b5 = new FilterButton(5);
		b5.setLabel("S5");
		b5.x = 720;
		b5.y = 400;
		b5.active = true;
		FilterButton b6 = new FilterButton(6);
		b6.setLabel("S6");
		b6.x = 760;
		b6.y = 400;
		b6.active = true;
		FilterButton bAll = new FilterButton(0);
		bAll.setLabel("ALL");
		bAll.x = 800;
		bAll.y = 400;
		bAll.active = true;
		GLOBAL.allFilterButtons.add(b1);
		GLOBAL.allFilterButtons.add(b2);
		GLOBAL.allFilterButtons.add(b3);
		GLOBAL.allFilterButtons.add(b4);
		GLOBAL.allFilterButtons.add(b5);
		GLOBAL.allFilterButtons.add(b6);
		GLOBAL.allFilterButtons.add(bAll);	
	}
	
	public void drawFilterButtons() {
		// Draw all the series button
		for(int i = 0; i < GLOBAL.allFilterButtons.size(); i++) {
			GLOBAL.allFilterButtons.get(i).draw();	
		}
	}
	
	public void setupCharacterButtons() {
		int offsetX = 0, offsetY= 0;
		// TODO make all buttons after having all the occurrences. for all in ALL_CHARACTER, create a button with getName and getOccurrences
		for(int i = 0; i < Parser.ALL_CHARACTERS.size(); i++) {	
			// TODO eliminate this if when all the character images will be available
			if(Parser.ALL_CHARACTERS.get(i).getTotalEpisodes() > 4) {
				CharacterButton cb = new CharacterButton(Parser.ALL_CHARACTERS.get(i).getName(), Parser.ALL_CHARACTERS.get(i).getTotalEpisodes(), true);
				cb.x = 40 + offsetX;
				cb.y = 150 + offsetY;
				cb.active = true;
				
				if (offsetX == 200) {
					offsetX = 0;
					offsetY += 100;
				}
				else
					offsetX += 100;
				
				//cb.setLabel(Parser.ALL_CHARACTERS.get(i).getName());
				GLOBAL.allCharacterButtons.add(cb);
			}
		}
	}
	
	public void drawCharacterButtons() {
		for(int i = 0; i < GLOBAL.allCharacterButtons.size(); i++) {	
			GLOBAL.allCharacterButtons.get(i).draw();
		}
	}
	
	
	///
	/// End Layer One functions
	///
	
	///
	/// Layer two functions
	///
	
	// Draw the background of the second layer
	public void drawLayerTwoBackground() {

		background(GLOBAL.colorBackgroundLayerTwo);
		
	}
	
	public void drawLayerTwoText() {
		fill(GLOBAL.colorText);
		textFont(GLOBAL.tFont,20);
		// TODO now only for season
		if (GLOBAL.SEASON_SELECTED == 0)
			text("You have selected: all seasons", width/2 - 150, 40);
		else
		text("You have selected: season " + GLOBAL.SEASON_SELECTED, width/2 - 150, 40);
	}
	
	public void drawLayerTwoStats(){
		  noStroke();
		  rectMode(CORNERS);
		  fill(GLOBAL.colorStatsArea);
		  rect(GLOBAL.statX1,GLOBAL.statY1, GLOBAL.statX2, GLOBAL.statY2);
		  fill(GLOBAL.colorText);
		  text("Statistics:",24,90);
	}
	
	// testing the bar chart for all season button
	public void drawGraph() {
		noStroke();
		rectMode(CORNERS);
		fill(GLOBAL.colorPlotArea);
		rect(300,300, 1000,700);
		fill(color(255));

		float rectWidth = (float)(1000 - 300)/(2*pars.ALL_CHARACTERS.size());
		//float x = 300 + rectWidth; //starting point for plot
		for(int i =0; i < pars.ALL_CHARACTERS.size(); i++) {
			float value = pars.ALL_CHARACTERS.get(i).getTotalEpisodes();
			float x = map(i, 0, pars.ALL_CHARACTERS.size(), 300,1000);
			float y = map(value, 0, pars.LIST_ALL.size(), 700,300);
			rect( x - rectWidth/2, y, x+ rectWidth/2, 700);
		}

		//		  for (int row = 0; row < rowCount; row++) {
		//		    if (data.isValid(row, col)) {
		//		      float value = data.getFloat(row, col);
		//		      float x = map(years[row], yearMin, yearMax, plotX1, plotX2);
		//		      float y = map(value, dataMin, dataMax, plotY2, plotY1);
		//		      rect(x-barWidth/2, y, x+barWidth/2, plotY2);
		//		    }
		//		  }
	}
	
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
	}
	
	// If mouse is pressed, check what has been pressed and activate the action
	public void mousePressed() { 
		  println("x = " + mouseX+ "y =" + mouseY);
		  if (GLOBAL.LAYER == 1 && !scroll.dragging) {
			  for(int i = 0; i < GLOBAL.allSeasonsButtons.size(); i++) {
				  if( GLOBAL.allSeasonsButtons.get(i).mouseOver() && GLOBAL.allSeasonsButtons.get(i).active) {
					  GLOBAL.allSeasonsButtons.get(i).doAction();
				  }
			  }
			  for(int i = 0; i < GLOBAL.allEpisodesButtons.size(); i++) {
				  if( GLOBAL.allEpisodesButtons.get(i).mouseOver() && GLOBAL.allEpisodesButtons.get(i).active) {
					  GLOBAL.allEpisodesButtons.get(i).doAction();
				  }
			  }
			  for(int i = 0; i < GLOBAL.allFilterButtons.size(); i++) {
				  if( GLOBAL.allFilterButtons.get(i).mouseOver() && GLOBAL.allFilterButtons.get(i).active) {
					  GLOBAL.allFilterButtons.get(i).doAction();
				  }
			  }
			  for(int i = 0; i < GLOBAL.allCharacterButtons.size(); i++) {
				  if( GLOBAL.allCharacterButtons.get(i).mouseOver() && GLOBAL.allCharacterButtons.get(i).active) {
					  GLOBAL.allCharacterButtons.get(i).doAction();
				  }
			  }

			  if(scroll.mouseOver())
				  scroll.mousePressed();
		  }
		  if(GLOBAL.LAYER ==2 && (menu.mouseOver() || menu.characterPicker.mouseOver() || menu.seasonPicker.mouseOver() || menu.episodePicker.mouseOver()))
			  menu.doAction();
	}
	
	public void mouseReleased() {
		// Scrolls must be released 
		menu.episodePicker.scroll.mouseReleased();
		menu.characterPicker.scroll.mouseReleased();
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "main_class" });
	}

}