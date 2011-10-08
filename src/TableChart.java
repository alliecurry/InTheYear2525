import java.util.ArrayList;
import java.util.HashMap;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class TableChart extends Widget {

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
	
	public ScrollBar scroll; // scrollbar for the table
	
	public ArrayList<TableEntry> allTableEntries;
	
	public float[] averagesToBePlot;
	public int[] countersForAverage;
		
	public TableChart(Character c, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		infoWidth = 100;
		
		plotWidth = width - infoWidth; // Need space for icons and stat
		
		character = c;
		
		GRAPH_TYPE = TYPE;
		
		allTableEntries = new ArrayList<TableEntry>();
		
		createScrollBar();
		
	}
	
	public TableChart(Episode e, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		plotWidth = width;
		
		episode = e;
		
		GRAPH_TYPE = TYPE;
		
		allTableEntries = new ArrayList<TableEntry>();
		
		createScrollBar();
		
	}
	
	public TableChart(Integer s, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		plotWidth = width;
		
		season = s;
		
		GRAPH_TYPE = TYPE;
		
		allTableEntries = new ArrayList<TableEntry>();

		createScrollBar();
		
	}

	public void draw() {

		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorPlotArea);
		GLOBAL.processing.rect( x ,y, x + plotWidth, y + height);
		GLOBAL.processing.fill(GLOBAL.processing.color(255));			

		if (GRAPH_TYPE == CHARACTER_GRAPH) {
			
			if (GLOBAL.CATCHPHRASES_ANALYSIS == false)
				createDialogueTableChart();
			else {
				createCatchphraseTableChart();
			}
			
		}
		else if (GRAPH_TYPE == EPISODE_GRAPH) {		
			createEpisodeTableChart();
		} 
		else if (GRAPH_TYPE == SEASON_GRAPH) {
			createSeasonTableChart();
		}
		
		int numberOfElements = (int) (height - 100)/27;
		
		// Resize based on number of graphs and number of episodes
		scroll.height = (numberOfElements + 1) * 25;
		scroll.size = (float)4 / allTableEntries.size();
		
		if(allTableEntries.size() >= numberOfElements)
			scroll.draw();
		else
			scroll.size = 1;
		
		// elementNumber: var for offset calculation
		int elementNumber = 0;	
					
		// Find from what button we have to print, based on the value of the scrollbar in this moment
		float val = GLOBAL.processing.map(scroll.val,0, 1,0,allTableEntries.size() - 5);
		// Draw all the visible series buttons
		for(int j = (int)val; j <= val + numberOfElements && j < allTableEntries.size(); j++) {
			allTableEntries.get(j).x = x + 60;
			allTableEntries.get(j).y = y + 100 + elementNumber*25;
			allTableEntries.get(j).active = true;
			allTableEntries.get(j).draw();
			elementNumber++;
		}
		
	}
	
	public void createScrollBar() {
		
		// Create a new scrollbar
		scroll = new ScrollBar();
		scroll.x = x + 20;
		scroll.y = y + 100;	
		scroll.width = 15;
		scroll.height = 450;	
		
	}
	
	// Dialogue analysis for character
		public void createDialogueTableChart() {

			int indexStart = 0;
			int indexEnd = Parser.LIST_ALL.size() -1;

			for (int i = 0; i< Parser.LIST_ALL.size() ; i++) {
				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeStart.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeStart.getEpisode())
					indexStart = i;
				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeEnd.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeEnd.getEpisode())
					indexEnd = i;
			}
			
			allTableEntries.clear();
			
			resetAverageToBePlot();
			
			GLOBAL.COLORS.reset();

			// Create new table entries and deactivate all entries
			for ( int i = indexStart; i <= indexEnd; i++ ) {
				
				String label = "S" + Parser.LIST_ALL.get(i).getSeason() +
							  " E"+ Parser.LIST_ALL.get(i).getEpisode() +
							  " " +Parser.LIST_ALL.get(i).getName();
				
				ArrayList<Integer> values = new ArrayList<Integer>();
				
				for (int j=0; j<GLOBAL.charactersSelected.size(); j++){
					int value = Parser.LIST_ALL.get(i).getNumberOfLinesPerCharacter(GLOBAL.charactersSelected.get(j));
					values.add(new Integer(value));
					
					averagesToBePlot[j] = (averagesToBePlot[j] + value);
					countersForAverage[j] = countersForAverage[j] + 1;

				}
				
				TableEntry newTableEntry = new TableEntry(label, values, 14, plotWidth - 50 - 35, 25);
				newTableEntry.active = false;
				allTableEntries.add(newTableEntry);
				
			}

			for (int j=0; j<GLOBAL.charactersSelected.size(); j++){
				// Draw icon and info		
				GLOBAL.processing.fill(GLOBAL.colorText);
				GLOBAL.processing.textFont(GLOBAL.tFont,16);
				GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
				GLOBAL.processing.text(GLOBAL.charactersSelected.get(j).getName(), x + width - 40, y + 20 + j*200);

				if (character.getIcon() != null)
					GLOBAL.processing.image( GLOBAL.charactersSelected.get(j).getIcon(), x + width - 80, y + 30 + j*200, 80, 80);
			}
			
			// Averages info		
			GLOBAL.processing.textFont(GLOBAL.tFont,14);
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			GLOBAL.processing.text("Average amounts of dialogue among the selected episodes: ", x + 20, y + 20);
			for (int j=0; j<GLOBAL.charactersSelected.size(); j++) {
				GLOBAL.processing.fill(GLOBAL.COLORS.getNextColor());
				GLOBAL.processing.text(GLOBAL.charactersSelected.get(j).getName(), x + 20, y + 40 + 20*j);
				GLOBAL.processing.text(averagesToBePlot[j]/countersForAverage[j], x + 100, y + 40 + 20*j);
			}
			
		}
		

		public void createCatchphraseTableChart() {
			
			int indexStart = 0;
			int indexEnd = Parser.LIST_ALL.size() -1;

			allTableEntries.clear();
			
			resetAverageToBePlot();

			for (int i = 0; i< Parser.LIST_ALL.size() ; i++) {
				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeStart.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeStart.getEpisode())
					indexStart = i;
				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeEnd.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeEnd.getEpisode())
					indexEnd = i;
			}

			GLOBAL.COLORS.reset();
			
			int [] colors = new int[3];
			colors[0] = GLOBAL.COLORS.getNextColor();
			colors[1] = GLOBAL.COLORS.getNextColor();
			colors[2] = GLOBAL.COLORS.getNextColor();
			
			// Averages info		
			GLOBAL.processing.textFont(GLOBAL.tFont,14);
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			GLOBAL.processing.text("Average number of catchphrases in the selected interval: ", x + 20, y + 20);
			
			// for each character
			for (int k=0; k<GLOBAL.charactersSelected.size(); k++){

				ArrayList<Catchphrase> phrases = GLOBAL.charactersSelected.get(k).getAllPhrases();
				
				int color = colors[k]; // 3 color based on the character

				GLOBAL.processing.fill(color);
				// If character doesn't have any catchphrase, print and go to the next character
				if (phrases == null) {
					GLOBAL.processing.text(GLOBAL.charactersSelected.get(k).getName() + " doesn't have any catchphrase", x + 20, y + 40 + 20*k);
					continue; 
				}
				
				// for each phrase of the character
				for (int j=0; j < phrases.size(); j++) {
					
					int value = 0; 

					String phraseString = phrases.get(j).getPhrase(); // 1 the phrase

					// for each episode
					for ( int i = indexStart; i <= indexEnd; i++ ) {
						int numberOfTime = phrases.get(j).getTotalEpisode(Parser.LIST_ALL.get(i).getSeason(), Parser.LIST_ALL.get(i).getEpisode());
						if ( numberOfTime > 0){
							value = value + numberOfTime; // 2
							
							averagesToBePlot[k] = (averagesToBePlot[k] + value); // Average number of catchphrases per episode
							countersForAverage[k] = countersForAverage[k] + 1;

						}

					}

					// Put the value in the table
					TableEntry newTableEntry = new TableEntry(phraseString, value, color, 14, plotWidth -50 - 35, 25); // 1,2,3,4,
					newTableEntry.active = false;
					allTableEntries.add(newTableEntry);
				}

				GLOBAL.processing.text(GLOBAL.charactersSelected.get(k).getName(), x + 20, y + 40 + 20*k);
				GLOBAL.processing.text(averagesToBePlot[k]/countersForAverage[k], x + 100, y + 40 + 20*k);
			}			

		}
		
		public void createEpisodeTableChart() {
						
			allTableEntries.clear();
			
			resetAverageToBePlot();
			
			GLOBAL.COLORS.reset();
						
			// For each character that appears, plot the bar
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
				
				ArrayList<Integer> values = new ArrayList<Integer>();
				
				String label = Parser.ALL_CHARACTERS.get(i).getName(); 
				for (int j=0; j<GLOBAL.episodesSelected.size(); j++){
					int value = GLOBAL.episodesSelected.get(j).getNumberOfLinesPerCharacter(Parser.ALL_CHARACTERS.get(i));
					values.add(value);
					
					if (value > 0) {
						averagesToBePlot[j] = (averagesToBePlot[j] + value);
						countersForAverage[j] = countersForAverage[j] + 1;
					}

				}
				TableEntry newTableEntry = new TableEntry(label, values, 14, plotWidth -50 - 35, 25);
				newTableEntry.active = false;
				allTableEntries.add(newTableEntry);

			}
			
			
			// Averages info		
			GLOBAL.processing.textFont(GLOBAL.tFont,14);
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			GLOBAL.processing.text("Average amounts of dialogue among the characters appearing in the episode: ", x + 20, y + 20);
			for (int j=0; j<GLOBAL.episodesSelected.size(); j++) {
				GLOBAL.processing.fill(GLOBAL.COLORS.getNextColor());
				GLOBAL.processing.text("S" + GLOBAL.episodesSelected.get(j).getSeason() +
									  " E" + GLOBAL.episodesSelected.get(j).getEpisode() , x + 20, y + 40 + 20*j);
				GLOBAL.processing.text(averagesToBePlot[j]/countersForAverage[j], x + 100, y + 40 + 20*j);
			}
			
		}
		
		public void createSeasonTableChart() {
						
			allTableEntries.clear();
			
			resetAverageToBePlot();
			
			GLOBAL.COLORS.reset();
			
			// For each character
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
								
				String label = Parser.ALL_CHARACTERS.get(i).getName();
				ArrayList<Integer> values = new ArrayList<Integer>();
				
				for (int j=0; j<GLOBAL.seasonsSelected.size(); j++){
					int value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(GLOBAL.seasonsSelected.get(j));
					values.add(new Integer(value));
					
					if (value > 0) {
						averagesToBePlot[j] = (averagesToBePlot[j] + value);
						countersForAverage[j] = countersForAverage[j] + 1;
					}
					
				}
				
				TableEntry newTableEntry = new TableEntry(label, values, 14, plotWidth - 50 - 35, 25);
				newTableEntry.active = false;
				allTableEntries.add(newTableEntry);
			}
			
			// Averages info		
			GLOBAL.processing.textFont(GLOBAL.tFont,14);
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			GLOBAL.processing.text("Number of characters appearing in the selected season: ", x + 20, y + 20);
			for (int j=0; j<GLOBAL.seasonsSelected.size(); j++) {
				GLOBAL.processing.fill(GLOBAL.COLORS.getNextColor());
				if (GLOBAL.seasonsSelected.get(j).intValue() != 0)
					GLOBAL.processing.text("Season " + GLOBAL.seasonsSelected.get(j).intValue(), x + 20, y + 40 + 20*j);
				else
					GLOBAL.processing.text("All seasons", x + 20, y + 40 + 20*j);
				GLOBAL.processing.text(countersForAverage[j], x + 140, y + 40 + 20*j);
			}
			
		}
		
		public void resetAverageToBePlot() {
			averagesToBePlot = new float[3];
			averagesToBePlot[0] = 0;
			averagesToBePlot[1] = 0;
			averagesToBePlot[2] = 0;
			
			countersForAverage = new int[3];
			countersForAverage[0] = 0;
			countersForAverage[1] = 0;
			countersForAverage[2] = 0;

		}
		
		public void doAction() {
			if(scroll.mouseOver())
				scroll.mousePressed();
		}
	
}
