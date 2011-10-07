import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class BarChart extends Widget{
	
	// Define
	public int CHARACTER_GRAPH = 0;
	public int EPISODE_GRAPH = 1;
	public int SEASON_GRAPH = 2;
	
	public int GRAPH_TYPE;
	
	public Character character;
	public Episode episode;
	public Integer season;
	
	public int plotWidth;
	public int infoWidth;
	
	public float rectWidth;
		
	public BarChart(Character c, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		infoWidth = 100;
		
		plotWidth = width - infoWidth; // Need space for icons and stat
		
		character = c;
		
		GRAPH_TYPE = TYPE;
		
	}
	
	public BarChart(Episode e, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		plotWidth = width;
		
		episode = e;
		
		GRAPH_TYPE = TYPE;
	}
	
	public BarChart(int s, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		plotWidth = width;
		
		season = new Integer(s);
		
		GRAPH_TYPE = TYPE;
		
	}
	
	public void changePosition(int xValue, int yValue, int w, int l) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
	}
	
	public void draw() {
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorPlotArea);
		GLOBAL.processing.rect( x,y, x + plotWidth, y + height);
		GLOBAL.processing.fill(GLOBAL.processing.color(255));
		
		if (GRAPH_TYPE == CHARACTER_GRAPH) {
				
			if (GLOBAL.CATCHPHRASES_ANALYSIS == false)
				createDialogueBarChart();
			else
				createCatchphraseBarChart();
			
		}
		
		else if (GRAPH_TYPE == EPISODE_GRAPH) {
			
			ArrayList<Character> charactersInEpisode = new ArrayList<Character>();
			
			// For each character, add to the charactersInEpisode only the one that appears
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
				if (episode.getNumberOfLinesPerCharacter(Parser.ALL_CHARACTERS.get(i)) > 0)
					charactersInEpisode.add(Parser.ALL_CHARACTERS.get(i));
			}		
			
			// Width of a single bar
			rectWidth = (float)(width)/(2*charactersInEpisode.size());

			float barY = y + height;
			float value;
			
			Widget rolloverRect = new Widget();
			
			// For each character that appears, plot the bar
			for(int i =0; i < charactersInEpisode.size(); i++) {
				
				value = episode.getNumberOfLinesPerCharacter(charactersInEpisode.get(i));
				barY = GLOBAL.processing.map(value, 0, 100, y + height, y);					 // TODO 100 is a default value, must be set as the max	
				float barX = GLOBAL.processing.map(i, 0, charactersInEpisode.size() - 1, x + rectWidth, x + width - rectWidth);
				
				GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
				GLOBAL.processing.fill(GLOBAL.processing.color(255));
				GLOBAL.processing.rect( barX - rectWidth/2, barY, barX+ rectWidth/2, y + height);
				
				// Draw the character name
				String label = charactersInEpisode.get(i).getName();
				GLOBAL.processing.fill(GLOBAL.colorText);
				GLOBAL.processing.textFont(GLOBAL.tFont,8);
				GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
				GLOBAL.processing.text(label, barX , y + height + 15);
				
			}
			
			// Search for rollover functions
			for(int i =0; i < charactersInEpisode.size(); i++) {
				float barX = GLOBAL.processing.map(i, 0, charactersInEpisode.size() - 1, x + rectWidth, x + width - rectWidth);

				// Check for any mouse rollover functionality to be displayed
				if (GLOBAL.processing.mouseX > (barX - rectWidth/2) && GLOBAL.processing.mouseX < (barX + rectWidth) 
						&& GLOBAL.processing.mouseY > y && GLOBAL.processing.mouseY < (y  + height)) {					
					rolloverRect.x = (int)(barX - rectWidth/2);
					rolloverRect.y = (int)(y);
					rolloverRect.width = (int)(rectWidth);
					rolloverRect.height = (int)(height);
					rolloverRect.draw();
					mouseRolloverFunction(charactersInEpisode.get(i));
					
				}
			}
			
		} // end if
		
		else if (GRAPH_TYPE == SEASON_GRAPH) {

			// Width of a single bar
			rectWidth = (float)(width)/(2*Parser.ALL_CHARACTERS.size());

			float barY = y + height;
			float value;
			
			String c = " ";
			
			// For each character
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {

				switch ( season.intValue() ) {
				case 0 : 
					value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodes();
					barY = GLOBAL.processing.map(value, 0, Parser.LIST_ALL.size(), y + height, y);
					break;
				case 1 :
					value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(1);
					barY = GLOBAL.processing.map(value, 0, Parser.LIST_S1.size(), y + height, y);
					break;
				case 2 :
					value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(2);
					barY = GLOBAL.processing.map(value, 0, Parser.LIST_S2.size(), y + height, y);
					break;
				case 3 :
					value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(3);
					barY = GLOBAL.processing.map(value, 0, Parser.LIST_S3.size(), y + height, y);
					break;
				case 4 :
					value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(4);
					barY = GLOBAL.processing.map(value, 0, Parser.LIST_S4.size(), y + height, y);
					break;
				case 5 :
					value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(5);
					barY = GLOBAL.processing.map(value, 0, Parser.LIST_S5.size(), y + height, y);
					break;
				case 6 :
					value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(6);
					barY = GLOBAL.processing.map(value, 0, Parser.LIST_S6.size(), y + height, y);
					break;
				}
								
				float barX = GLOBAL.processing.map(i, 0, Parser.ALL_CHARACTERS.size() - 1, x + rectWidth, x + width - rectWidth);
				GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
				GLOBAL.processing.fill(GLOBAL.processing.color(255));
				GLOBAL.processing.rect( barX - rectWidth/2, barY, barX+ rectWidth/2, y + height);
				
				if (Parser.ALL_CHARACTERS.get(i).getName().toCharArray()[0] != c.toCharArray()[0]) {
					c = Parser.ALL_CHARACTERS.get(i).getName().substring(0, 1);
					GLOBAL.processing.fill(GLOBAL.colorText);
					GLOBAL.processing.textFont(GLOBAL.tFont,10);
					if (!c.equals("e") && !c.equals("q"))
						GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
					else
						GLOBAL.processing.textAlign(GLOBAL.processing.RIGHT);
					GLOBAL.processing.text(c.toUpperCase(), barX , y + height + 15);
				}
				
				
			}
			
			// Rollover
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
				float barX = GLOBAL.processing.map(i, 0, Parser.ALL_CHARACTERS.size() - 1, x + rectWidth, x + width - rectWidth);
				// Check for any mouse rollover functionality to be displayed
				if (GLOBAL.processing.mouseX > (barX - rectWidth/2) && GLOBAL.processing.mouseX < (barX + rectWidth) 
						&& GLOBAL.processing.mouseY > y && GLOBAL.processing.mouseY < (y  + height)) {	
					main_class.graphArea.mouseSeasonRolloverFunction(rectWidth, barX, Parser.ALL_CHARACTERS.get(i));
					mouseRolloverFunction(Parser.ALL_CHARACTERS.get(i));
				}
			}
		} // end if
		
	}

	// Dialogue analysis for character
	public void createDialogueBarChart() {
		float barY = y + height;
		float value;

		int indexStart = 0;
		int indexEnd = Parser.LIST_ALL.size() -1;

		for (int i = 0; i< Parser.LIST_ALL.size() ; i++) {
			if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeStart.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeStart.getEpisode())
				indexStart = i;
			if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeEnd.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeEnd.getEpisode())
				indexEnd = i;
		}

		rectWidth = (float)(width)/(2*(indexEnd - indexStart));

		for ( int i = indexStart; i <= indexEnd; i++ ) {
			value = Parser.LIST_ALL.get(i).getNumberOfLinesPerCharacter(character);
			barY = GLOBAL.processing.map(value, 0, 100, y + height, y);					 // TODO 100 is a default value, must be set as the max	
			float barX = GLOBAL.processing.map(i, indexStart, indexEnd, x + rectWidth, x + plotWidth - rectWidth);
			GLOBAL.processing.rect( barX - rectWidth/2, barY, barX + rectWidth/2, y + height);

			// Check for any mouse rollover functionality to be displayed
			if (GLOBAL.processing.mouseX > (barX - rectWidth/2) && GLOBAL.processing.mouseX < (barX + rectWidth) 
					&& GLOBAL.processing.mouseY > y && GLOBAL.processing.mouseY < (y  + height)) {
				String label = "S"+ Parser.LIST_ALL.get(i).getSeason() + " E" + Parser.LIST_ALL.get(i).getEpisode()+ " " + Parser.LIST_ALL.get(i).getName();
				main_class.graphArea.mouseCharacterRolloverFunction(rectWidth, barX, label);

			}
		}

		// Draw icon and info		
		GLOBAL.processing.fill(GLOBAL.colorText);
		GLOBAL.processing.textFont(GLOBAL.tFont,16);
		GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
		GLOBAL.processing.text(character.getName(), x + width - 40, y + 20);

		if (character.getIcon() != null)
			GLOBAL.processing.image( character.getIcon(), x + width - 80, y + 30, 80, 80);
	}
	

	public void createCatchphraseBarChart() {
		
		float barY = y + height;
		float value;

		int indexStart = 0;
		int indexEnd = Parser.LIST_ALL.size() -1;

		for (int i = 0; i< Parser.LIST_ALL.size() ; i++) {
			if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeStart.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeStart.getEpisode())
				indexStart = i;
			if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeEnd.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeEnd.getEpisode())
				indexEnd = i;
		}

		rectWidth = (float)(width)/(2*(indexEnd - indexStart));
		
		for ( int i = indexStart; i <= indexEnd; i++ ) {
			
			value = 0;
			ArrayList<Catchphrase> phrases = character.getAllPhrases();
			HashMap<String, Integer> stringPhrase  = new HashMap();
			
			// for each phrase
			for (int j=0; j < phrases.size(); j++) {
				int numberOfTime = phrases.get(j).getTotalEpisode(Parser.LIST_ALL.get(i).getSeason(), Parser.LIST_ALL.get(i).getEpisode());
				if ( numberOfTime > 0){
					value += numberOfTime;
				    stringPhrase.put(phrases.get(j).getPhrase(), new Integer((int)numberOfTime));
				}
			}
			
			barY = GLOBAL.processing.map(value, 0, 10, y + height, y);					 // TODO 10 is a default value, must be set as the max	
			float barX = GLOBAL.processing.map(i, indexStart, indexEnd, x + rectWidth, x + plotWidth - rectWidth);
			GLOBAL.processing.rect( barX - rectWidth/2, barY, barX + rectWidth/2, y + height);

			// Check for any mouse rollover functionality to be displayed
			if (GLOBAL.processing.mouseX > (barX - rectWidth/2) && GLOBAL.processing.mouseX < (barX + rectWidth) 
					&& GLOBAL.processing.mouseY > y && GLOBAL.processing.mouseY < (y  + height)) {
				String label = "S"+ Parser.LIST_ALL.get(i).getSeason() + " E" + Parser.LIST_ALL.get(i).getEpisode()+ " " + Parser.LIST_ALL.get(i).getName();
				main_class.graphArea.mouseCharacterRolloverFunction(rectWidth, barX, label);
				
				Object[] stringSet = stringPhrase.keySet().toArray();
				
				int padding = 15; // padding between catchphrases
				
				for (int k = 0; k< stringSet.length; k++) {
					// Draw the rect
					GLOBAL.processing.noStroke();
					GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
					GLOBAL.processing.fill(GLOBAL.colorPlotArea);
					GLOBAL.processing.rect(GLOBAL.processing.mouseX - 15 - GLOBAL.processing.textWidth((String)stringSet[k]) , GLOBAL.processing.mouseY - 8 - padding -10 , GLOBAL.processing.mouseX - 8, GLOBAL.processing.mouseY - 8 - padding);
					GLOBAL.processing.fill(GLOBAL.processing.color(255));
					
					// Draw the catchphrases
					GLOBAL.processing.fill(GLOBAL.colorText);
					GLOBAL.processing.textFont(GLOBAL.tFont,14);
					GLOBAL.processing.textAlign(GLOBAL.processing.RIGHT);
					GLOBAL.processing.text(stringSet[k] + " = " + stringPhrase.get(stringSet[k]) , GLOBAL.processing.mouseX - 8, GLOBAL.processing.mouseY - padding);
					padding += 15;
				}
			}
		}

		// Draw icon and info		
		GLOBAL.processing.fill(GLOBAL.colorText);
		GLOBAL.processing.textFont(GLOBAL.tFont,16);
		GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
		GLOBAL.processing.text(character.getName(), x + width - 40, y + 20);

		if (character.getIcon() != null)
			GLOBAL.processing.image( character.getIcon(), x + width - 80, y + 30, 80, 80);
		
	}
	
	public void mouseRolloverFunction(Character character) {
		
		// Draw a rectangle, the label and an image inside, now the image is set to 100x100
		
		// Rectangle
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNER);
		GLOBAL.processing.fill(GLOBAL.colorIconBackground);
		GLOBAL.processing.rect( GLOBAL.processing.mouseX + 10 , GLOBAL.processing.mouseY - 40 - 100 , 100, 130); //x,y,width,height
		
		// Text
		GLOBAL.processing.fill(GLOBAL.colorBackgroundLayerTwo);
		GLOBAL.processing.textFont(GLOBAL.tFont,14);
		GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
		GLOBAL.processing.text(character.getName(), GLOBAL.processing.mouseX + 60, GLOBAL.processing.mouseY - 18 - 100); // center in the upper side, middle point, of the icon 100x100
		
		// Image
		if (character.getIcon()!= null)
			GLOBAL.processing.image(character.getIcon(), GLOBAL.processing.mouseX + 10, GLOBAL.processing.mouseY - 10 - 100, 100,100);
		
	}

}
