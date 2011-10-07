
public class GraphsArea extends Widget{
	
	// Define
	public int CHARACTER_GRAPH = 0;
	public int EPISODE_GRAPH = 1;
	public int SEASON_GRAPH = 2;

	// Graph area contains at most 3 charts
	BarChart chart1;
	BarChart chart2;
	BarChart chart3;
	
	TagCloud tagCloud1;
	TagCloud tagCloud2;
	TagCloud tagCloud3;
	
	TableChart tableChart1;
	TableChart tableChart2;
	TableChart tableChart3;
	
	// Number of charts we want to display at the moment
	public int chartsNumber = 0;
	
	// Horizontal scrollbar for selecting starting and ending episodes
	public HorizontalScrollBar scroll;
	
	// Rollover rectangle and label to be displayed 
	public Widget rolloverRect_CharacterCharts;
	public Widget rolloverRect_SeasonCharts;
	public String rolloverLabel_CharacterCharts;
	public String rolloverLabel_SeasonCharts;
	
	public GraphsArea() {
	
	}
	
	public void draw() {
		
		if (GLOBAL.STAT_VIEW == true && GLOBAL.WORD_ANALYSIS == false) {

			if (tableChart1 != null)
				tableChart1.draw();
			if (tableChart2 != null)
				tableChart2.draw();
			if (tableChart3 != null)
				tableChart3.draw();
			if (GLOBAL.ANALYSIS_TYPE.equals("characters"))
				scroll.draw();
		}
		else if (GLOBAL.STAT_VIEW == false && GLOBAL.WORD_ANALYSIS == false) {
			if (chart1 != null)
				chart1.draw();
			if (chart2 != null)
				chart2.draw();
			if (chart3 != null)
				chart3.draw();
		}
		else if (GLOBAL.STAT_VIEW == false && GLOBAL.WORD_ANALYSIS == true) {
			if (tagCloud1 != null)
				tagCloud1.draw();
			if (tagCloud2 != null)
				tagCloud2.draw();
			if (tagCloud3 != null)
				tagCloud3.draw();
		}
		
		
		if (GLOBAL.ANALYSIS_TYPE.equals("characters") && GLOBAL.STAT_VIEW == false) {
			scroll.draw();
			
			if (rolloverRect_CharacterCharts != null && ( chart1 != null && chart1.mouseOver()) || ( chart2 != null && chart2.mouseOver()) || (chart3 != null && chart3.mouseOver()) ) {
				rolloverRect_CharacterCharts.draw();
				GLOBAL.processing.fill(GLOBAL.colorText);
				GLOBAL.processing.textFont(GLOBAL.tFont,18);
				GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
				GLOBAL.processing.text(rolloverLabel_CharacterCharts, x + width/2 - 50, y + height - 70);
			}
		}
		
		if (GLOBAL.ANALYSIS_TYPE.equals("seasons") && GLOBAL.STAT_VIEW == false) {
			
			if (rolloverRect_SeasonCharts != null && ( chart1 != null && chart1.mouseOver()) || ( chart2 != null && chart2.mouseOver()) || (chart3 != null && chart3.mouseOver()) ) {
				rolloverRect_SeasonCharts.draw();
			}
		}

	}

	// Create a new graph for character analysis
	public void createCharacterGraph() {
		// IF plot graphs
		if (GLOBAL.WORD_ANALYSIS == false) {
			if (chart1 == null) {
				chart1 = new BarChart(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 20, height - 120, CHARACTER_GRAPH);
			}
			else if (chart2 == null) {
				int chartHeight = (height - 140)/2;
				chart2 = chart1;
				chart2.changePosition(x + 20, y + 40 + chartHeight, width - 20, chartHeight);
				chart1 = new BarChart(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 20, chartHeight, CHARACTER_GRAPH);
			}
			else {
				int chartHeight = (height - 160)/3;
				chart3 = chart2;
				chart2 = chart1;
				// chart 2 and 3 = change x y width height
				chart2.changePosition(x + 20, y + 40 + chartHeight, width - 20, chartHeight);
				chart3.changePosition(x + 20, y + 60 + 2*chartHeight, width - 20, chartHeight);
				chart1 = new BarChart(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 20, chartHeight, CHARACTER_GRAPH);	
			}
		}
		else {
			if (tagCloud1 == null) {
				tagCloud1 = new TagCloud(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 20, height - 120);
			}
			else if (tagCloud2 == null) {
				int chartHeight = (height - 140)/2;
				tagCloud2 = tagCloud1;
				tagCloud2.changePosition(x + 20, y + 40 + chartHeight, width - 20, chartHeight);
				tagCloud1 = new TagCloud(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 20, chartHeight);
			}
			else {
				int chartHeight = (height - 160)/3;
				tagCloud3 = tagCloud2;
				tagCloud2 = tagCloud1;
				// chart 2 and 3 = change x y width height
				tagCloud2.changePosition(x + 20, y + 40 + chartHeight, width - 20, chartHeight);
				tagCloud3.changePosition(x + 20, y + 60 + 2*chartHeight, width - 20, chartHeight);
				tagCloud1 = new TagCloud(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 20, chartHeight);	
			}
		}

		if (GLOBAL.WORD_ANALYSIS == false) {
			if (tableChart1 == null) {
				tableChart1 = new TableChart(GLOBAL.CHARACTER_SELECTED, x + 20, y + 20, width - 20, height - 120, CHARACTER_GRAPH);
			}
		}


	}

	// Create a new graph for episode analysis	
	public void createEpisodeGraph() {

		if (chart1 == null)
			chart1 = new BarChart(GLOBAL.EPISODE_SELECTED, x + 20, y + 20, width - 100, height - 80, EPISODE_GRAPH);
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
			chart1 = new BarChart(GLOBAL.EPISODE_SELECTED, x + 20, y + 20, width - 100, chartHeight, EPISODE_GRAPH);		
		}


		if (tableChart1 == null) {
			tableChart1 = new TableChart(GLOBAL.EPISODE_SELECTED, x + 20, y + 20, width - 20, height - 40, EPISODE_GRAPH);
		}


	}

	// Create a new graph for season analysis	
	public void createSeasonGraph() {

		if (chart1 == null)
			chart1 = new BarChart(GLOBAL.SEASON_SELECTED, x + 20, y + 20, width - 100, height - 40, SEASON_GRAPH);
		else if (chart2 == null) {
			chart2 = chart1;
			chart2.changePosition(x + 20, y + (height - 100)/2 + 80, width - 100, (height - 100) /2);
			chart1 = new BarChart(GLOBAL.SEASON_SELECTED, x + 20, y + 20, width - 100, (height - 100) /2, SEASON_GRAPH);
		}
		else {
			int chartHeight = (height - 160)/3;
			chart3 = chart2;
			chart2 = chart1;
			// chart 2 and 3 = change x y width height
			chart2.changePosition(x + 20, y + 80 + chartHeight, width - 100, chartHeight);
			chart3.changePosition(x + 20, y + 140 + 2*chartHeight, width - 100, chartHeight);
			chart1 = new BarChart(GLOBAL.SEASON_SELECTED, x + 20, y + 20, width - 100, chartHeight, SEASON_GRAPH);		
		}

		if (tableChart1 == null) {
			tableChart1 = new TableChart(GLOBAL.SEASON_SELECTED, x + 20, y + 20, width - 20, height - 40, SEASON_GRAPH);
		}

	}
	
	public void clearGraphs() {
		chart1 = null;
		chart2 = null;
		chart3 = null;
		tagCloud1 = null;
		tagCloud2 = null;
		tagCloud3 = null;
		tableChart1 = null;
		tableChart2 = null;
		tableChart3 = null;
	}
	
	public void doAction() {

		if (scroll.mouseOver())
			scroll.mousePressed();
		
		if (GLOBAL.STAT_VIEW) {
			if (tableChart1 != null && tableChart1.mouseOver())
				tableChart1.doAction();
			if (tableChart2 != null && tableChart2.mouseOver())
				tableChart2.doAction();
			if (tableChart3 != null && tableChart3.mouseOver())
				tableChart3.doAction();
		}
	}

	public void createScrollBar() {
		
		scroll = new HorizontalScrollBar(Parser.LIST_ALL);
		scroll.x = this.x + 20;
		scroll.y = this.y + this.height - 50;
		scroll.width = this.width - 120;
		scroll.height = 15;	
		scroll.size = (float)2/ Parser.LIST_ALL.size();
		
	}
	
	public void mouseCharacterRolloverFunction( float RectWidth, float x, String s ) {
		
		rolloverRect_CharacterCharts = new Widget();
		rolloverRect_CharacterCharts.x = (int)(x - RectWidth/2);
		rolloverRect_CharacterCharts.y = (int)(y + 20);
		rolloverRect_CharacterCharts.width = (int)(RectWidth);
		rolloverRect_CharacterCharts.height = (int)(height - 120);
		
		rolloverLabel_CharacterCharts = s;
		
	}
	
	public void mouseSeasonRolloverFunction( float RectWidth, float x, Character c ) {
		
		rolloverRect_SeasonCharts = new Widget();
		rolloverRect_SeasonCharts.x = (int)(x - RectWidth/2);
		rolloverRect_SeasonCharts.y = (int)(y + 20);
		rolloverRect_SeasonCharts.width = (int)(RectWidth);
		rolloverRect_SeasonCharts.height = (int)(height - 40);
		
		rolloverLabel_SeasonCharts = c.getName();
				
	}

	public void mouseReleased() {
		scroll.mouseReleased();
		if (GLOBAL.STAT_VIEW) {
			if (tableChart1 != null)
				tableChart1.scroll.mouseReleased();
			if (tableChart2 != null)
				tableChart2.scroll.mouseReleased();
			if (tableChart3 != null)
				tableChart3.scroll.mouseReleased();
		}	}

}
