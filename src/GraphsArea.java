
public class GraphsArea extends Widget{

	public GraphsArea() {
	
	}
	
	public void draw() {
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorPlotArea);
		GLOBAL.processing.rect(300,300, 1000,700);
		GLOBAL.processing.fill(GLOBAL.processing.color(255));

		float rectWidth = (float)(1000 - 300)/(2*Parser.ALL_CHARACTERS.size());
		//float x = 300 + rectWidth; //starting point for plot
		for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
			float value = Parser.ALL_CHARACTERS.get(i).getTotalEpisodes();
			float x = GLOBAL.processing.map(i, 0, Parser.ALL_CHARACTERS.size(), 300,1000);
			float y = GLOBAL.processing.map(value, 0, Parser.LIST_ALL.size(), 700,300);
			GLOBAL.processing.rect( x - rectWidth/2, y, x+ rectWidth/2, 700);
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
	
}
