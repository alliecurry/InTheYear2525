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
//				createCatchphraseTableChart();
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
		scroll.draw();
		
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
			
			// Averagesinfo		
			GLOBAL.processing.textFont(GLOBAL.tFont,14);
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			GLOBAL.processing.text("Average amounts of dialogue among the selected episodes: ", x + 20, y + 20);
			for (int j=0; j<GLOBAL.charactersSelected.size(); j++) {
				GLOBAL.processing.fill(GLOBAL.COLORS.getNextColor());
				GLOBAL.processing.text(GLOBAL.charactersSelected.get(j).getName(), x + 20, y + 40 + 20*j);
				GLOBAL.processing.text(averagesToBePlot[j]/countersForAverage[j], x + 100, y + 40 + 20*j);
			}
			
		}
		

//		public void createCatchphraseTableChart() {
//			
//			float value;
//
//			int indexStart = 0;
//			int indexEnd = Parser.LIST_ALL.size() -1;
//
//			allTableEntries.clear();
//
//			for (int i = 0; i< Parser.LIST_ALL.size() ; i++) {
//				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeStart.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeStart.getEpisode())
//					indexStart = i;
//				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeEnd.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeEnd.getEpisode())
//					indexEnd = i;
//			}
//
//			
//			for ( int i = indexStart; i <= indexEnd; i++ ) {
//				
//				ArrayList<Catchphrase> phrases = character.getAllPhrases();
//				
//				ArrayList<Integer> values = new ArrayList<Integer>();
//				
//				String label = "S" + Parser.LIST_ALL.get(i).getSeason() +
//						  " E"+ Parser.LIST_ALL.get(i).getEpisode();
//				
//				for (int j=0; j<GLOBAL.charactersSelected.size(); j++){
//					values.add(new Integer());
//					
//				}
//				
//				// for each phrase
//				for (int j=0; j < phrases.size(); j++) {
//					int numberOfTime = phrases.get(j).getTotalEpisode(Parser.LIST_ALL.get(i).getSeason(), Parser.LIST_ALL.get(i).getEpisode());
//					if ( numberOfTime > 0){
//					    stringPhrase.put(phrases.get(j).getPhrase(), new Integer((int)numberOfTime));
//					    
//					    
//					}
//				}
//				
//
//
//			}
//
//			// Draw icon and info		
//			GLOBAL.processing.fill(GLOBAL.colorText);
//			GLOBAL.processing.textFont(GLOBAL.tFont,16);
//			GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
//			GLOBAL.processing.text(character.getName(), x + width - 40, y + 20);
//
//			if (character.getIcon() != null)
//				GLOBAL.processing.image( character.getIcon(), x + width - 80, y + 30, 80, 80);
//			
//		}
		
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
			
			
			// Averagesinfo		
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
			
			// For each character
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
								
				String label = Parser.ALL_CHARACTERS.get(i).getName();
				ArrayList<Integer> values = new ArrayList<Integer>();
				
				for (int j=0; j<GLOBAL.seasonsSelected.size(); j++){
					values.add(new Integer(Parser.ALL_CHARACTERS.get(i).getTotalEpisodesBySeason(GLOBAL.seasonsSelected.get(j))));
				}
				
				TableEntry newTableEntry = new TableEntry(label, values, 14, plotWidth - 50 - 35, 25);
				newTableEntry.active = false;
				allTableEntries.add(newTableEntry);
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
