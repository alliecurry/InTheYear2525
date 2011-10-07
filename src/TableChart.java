import java.util.ArrayList;
import java.util.HashMap;


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
	
	public TableChart(int s, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		plotWidth = width;
		
		season = new Integer(s);
		
		GRAPH_TYPE = TYPE;
		
		allTableEntries = new ArrayList<TableEntry>();

		createScrollBar();
		
	}
	
	public void changePosition(int xValue, int yValue, int w, int l) {
		
		x = xValue;
		y = yValue;

		width = w;
		height = l;
		
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
				createDialogueBarChart();
			else
				createCatchphraseBarChart();
			
		}// end if
		else if (GRAPH_TYPE == EPISODE_GRAPH) {		

		} // end if
		else if (GRAPH_TYPE == SEASON_GRAPH) {
		}// end if
		
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
		public void createDialogueBarChart() {

			int indexStart = 0;
			int indexEnd = Parser.LIST_ALL.size() -1;

			for (int i = 0; i< Parser.LIST_ALL.size() ; i++) {
				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeStart.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeStart.getEpisode())
					indexStart = i;
				if (Parser.LIST_ALL.get(i).getSeason() == GLOBAL.episodeEnd.getSeason() && Parser.LIST_ALL.get(i).getEpisode() == GLOBAL.episodeEnd.getEpisode())
					indexEnd = i;
			}
			
			allTableEntries.clear();

			// Create new table entries and deactivate all entries
			for ( int i = indexStart; i <= indexEnd; i++ ) {
				
				String label = "S" + Parser.LIST_ALL.get(i).getSeason() +
							  " E"+ Parser.LIST_ALL.get(i).getEpisode() +
							  " " +Parser.LIST_ALL.get(i).getName();
				
				ArrayList<Integer> values = new ArrayList<Integer>();
				
				for (int j=0; j<GLOBAL.charactersSelected.size(); j++){
					values.add(new Integer(Parser.LIST_ALL.get(i).getNumberOfLinesPerCharacter(GLOBAL.charactersSelected.get(j))));
					
				}
				
				TableEntry newTableEntry = new TableEntry(label, values, 14, plotWidth - 100 - 35, 25);
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
					main_class.graphArea.mouseRolloverFunction(rectWidth, barX, label);
					
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
		
		public void doAction() {
			if(scroll.mouseOver())
				scroll.mousePressed();
		}
	
}
