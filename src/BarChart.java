import java.util.ArrayList;


public class BarChart extends Widget{
	
	// Define
	public int CHARACTER_GRAPH = 0;
	public int EPISODE_GRAPH = 1;
	public int SEASON_GRAPH = 2;
	
	public int GRAPH_TYPE;
	
	public Character character;
	public Episode episode;
	public Integer season;
	
	public BarChart(Character c, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		character = c;
		
		GRAPH_TYPE = TYPE;
		
	}
	
	public BarChart(Episode e, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		episode = e;
		
		GRAPH_TYPE = TYPE;
	}
	
	public BarChart(int s, int xValue, int yValue, int w, int l, int TYPE) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
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
		
		if (GRAPH_TYPE == CHARACTER_GRAPH) {
			
			// TODO now only season aggregation, need to implement others
			
			// Width of a single bar
			float rectWidth = (float)(width)/(2*6); // 6 is the number of seasons

			float barY = y + height;
			float value;

			// For each season
			for(int i = 1; i < 7; i++) {
				value = character.getTotalEpisodesBySeason(i);
//				System.out.println(value);
				barY = GLOBAL.processing.map(value, 0, 30, y + height, y);					 // TODO 30 is a default value, must be set as the max	
				float barX = GLOBAL.processing.map(i, 1, 6, x, x + width);
				GLOBAL.processing.rect( barX - rectWidth/2 + 20, barY, barX+ rectWidth/2, y + height);
			}

		}
		
		else if (GRAPH_TYPE == EPISODE_GRAPH) {
			
			// Width of a single bar
			float rectWidth = (float)(width)/(2*Parser.ALL_CHARACTERS.size());

			float barY = y + height;
			float value;
			
			// For each character
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
				
				value = episode.getNumberOfLinesPerCharacter(Parser.ALL_CHARACTERS.get(i));
				barY = GLOBAL.processing.map(value, 0, 100, y + height, y);					 // TODO 100 is a default value, must be set as the max	
				float barX = GLOBAL.processing.map(i, 0, Parser.ALL_CHARACTERS.size(), x, x + width);
				GLOBAL.processing.rect( barX - rectWidth/2, barY, barX+ rectWidth/2, y + height);
			}
			
		} // end if
		
		else if (GRAPH_TYPE == SEASON_GRAPH) {

			// Width of a single bar
			float rectWidth = (float)(width)/(2*Parser.ALL_CHARACTERS.size());

			float barY = y + height;
			float value;
			
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
								
				float barX = GLOBAL.processing.map(i, 0, Parser.ALL_CHARACTERS.size(), x, x + width);
				GLOBAL.processing.rect( barX - rectWidth/2, barY, barX+ rectWidth/2, y + height);
			}
			
		} // end if
		
	}
	
	

}
