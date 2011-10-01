
public class GraphsArea extends Widget{
	
	// Define
	public int CHARACTER_GRAPH = 0;
	public int EPISODE_GRAPH = 1;
	public int SEASON_GRAPH = 2;

	// Graph area contains at most 3 charts
	BarChart chart1;
	BarChart chart2;
	BarChart chart3;
	
	// Number of charts we want to display at the moment
	public int chartsNumber = 0;
	
	public GraphsArea() {
	
	}
	
	public void draw() {
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorPlotArea);
		GLOBAL.processing.rect(x,y, x + width, y + height);
		GLOBAL.processing.fill(GLOBAL.processing.color(255));
		
		if (chart1 != null)
			chart1.draw();
		if (chart2 != null)
			chart2.draw();
		if (chart3 != null)
			chart3.draw();

	}
	
	// Create a new graph for character analysis
	public void createCharacterGraph() {
		
//		if (chart1 == null)
//			chart1 = new BarChart(GLOBAL.charactersSelected.get(0), x + 20, y + 20, width - 100, height - 20, CHARACTER_GRAPH);
//		else if (chart2 == null) {
//			chart2 = chart1;
//			//chart1 = change x y width height
//			//chart1 = new BarChart();
//		}
//		else {
//			chart2 = chart1;
//			chart3 = chart2;
//			// chart 2 and 3 = change x y width height
//			//chart1 = new BarChart();
//		}
		
		if (chart1 == null)
			chart1 = new BarChart(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 100, height - 20, CHARACTER_GRAPH);
		else if (chart2 == null) {
			chart2 = chart1;
			chart2.changePosition(x + 20, y + (height)/2 + 30, width - 100, (height - 100) /2);
			chart1 = new BarChart(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 100, (height - 100) /2, CHARACTER_GRAPH);
			}
		else {
			int chartHeight = (height - 160)/3;
			chart3 = chart2;
			chart2 = chart1;
			// chart 2 and 3 = change x y width height
			chart2.changePosition(x + 20, y + 80 + chartHeight, width - 100, chartHeight);
			chart3.changePosition(x + 20, y + 140 + 2*chartHeight, width - 100, chartHeight);
			chart1 = new BarChart(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 100, chartHeight, CHARACTER_GRAPH);	
		}
		
	}
	
	// Create a new graph for episode analysis	
	public void createEpisodeGraph() {
		if (chart1 == null)
			chart1 = new BarChart(GLOBAL.EPISODE_SELECTED, x + 20, y + 20, width - 100, height - 20, EPISODE_GRAPH);
		else if (chart2 == null) {
			chart2 = chart1;
			chart2.changePosition(x + 20, y + (height)/2 + 30, width - 100, (height - 100) /2);
			chart1 = new BarChart(GLOBAL.EPISODE_SELECTED, x + 20, y + 20, width - 100, (height - 100) /2, EPISODE_GRAPH);
			}
		else {
			int chartHeight = (height - 160)/3;
			chart3 = chart2;
			chart2 = chart1;
			// chart 2 and 3 = change x y width height
			chart2.changePosition(x + 20, y + 80 + chartHeight, width - 100, chartHeight);
			chart3.changePosition(x + 20, y + 140 + 2*chartHeight, width - 100, chartHeight);
			chart1 = new BarChart(GLOBAL.EPISODE_SELECTED, x + 20, y + 20, width - 100, chartHeight, EPISODE_GRAPH);		}
		
	}
	
	// Create a new graph for season analysis	
	public void createSeasonGraph() {
		
		if (chart1 == null)
			chart1 = new BarChart(GLOBAL.SEASON_SELECTED, x + 20, y + 20, width - 100, height - 20, SEASON_GRAPH);
		else if (chart2 == null) {
			chart2 = chart1;
			chart2.changePosition(x + 20, y + (height)/2 + 30, width - 100, (height - 100) /2);
			chart1 = new BarChart(GLOBAL.SEASON_SELECTED, x + 20, y + 20, width - 100, (height - 100) /2, SEASON_GRAPH);
			}
		else {
			int chartHeight = (height - 160)/3;
			chart3 = chart2;
			chart2 = chart1;
			// chart 2 and 3 = change x y width height
			chart2.changePosition(x + 20, y + 80 + chartHeight, width - 100, chartHeight);
			chart3.changePosition(x + 20, y + 140 + 2*chartHeight, width - 100, chartHeight);
			chart1 = new BarChart(GLOBAL.SEASON_SELECTED, x + 20, y + 20, width - 100, chartHeight, SEASON_GRAPH);		}
		
	}
	
	public void clearGraphs() {
		chart1 = null;
		chart2 = null;
		chart3 = null;
	}
	
}
