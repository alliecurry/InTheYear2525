
public class EpisodeSelectionPanel extends Widget{

	ScrollBar scroll;
	
	public EpisodeSelectionPanel(int xValue, int yValue) {
		
		x = xValue;
		y = yValue;
		
		width = 600;
		height = 400;
		
		// Create a new scrollbar
		scroll = new ScrollBar();
		scroll.x = x + 20;
		scroll.y = y + 100;	
		scroll.width = 15;
		scroll.height = 250;	
		// Draw all the series button
		scroll.size = (float)4 / GLOBAL.selectedEpisodesList.size();
		if (scroll.size > 1)
			scroll.size = 1;
		
		// Filter buttons
		FilterButton b1 = new FilterButton(1);
		b1.setLabel("S1");
		b1.x = x + 50;
		b1.y = y + 50;
		b1.active = true;
		FilterButton b2 = new FilterButton(2);
		b2.setLabel("S2");
		b2.x = x + 90;
		b2.y = y + 50;
		b2.active = true;
		FilterButton b3 = new FilterButton(3);
		b3.setLabel("S3");
		b3.x = x+ 130;
		b3.y = y + 50;
		b3.active = true;
		FilterButton b4 = new FilterButton(4);
		b4.setLabel("S4");
		b4.x = x + 170;
		b4.y = y + 50;
		b4.active = true;
		FilterButton b5 = new FilterButton(5);
		b5.setLabel("S5");
		b5.x = x + 210;
		b5.y = y + 50;
		b5.active = true;
		FilterButton b6 = new FilterButton(6);
		b6.setLabel("S6");
		b6.x = x + 250;
		b6.y = y + 50;
		b6.active = true;
		FilterButton bAll = new FilterButton(0);
		bAll.setLabel("ALL");
		bAll.x = x + 290;
		bAll.y = y + 50;
		bAll.active = true;
		GLOBAL.allFilterButtons.add(b1);
		GLOBAL.allFilterButtons.add(b2);
		GLOBAL.allFilterButtons.add(b3);
		GLOBAL.allFilterButtons.add(b4);
		GLOBAL.allFilterButtons.add(b5);
		GLOBAL.allFilterButtons.add(b6);
		GLOBAL.allFilterButtons.add(bAll);	
	}
	
	public void draw(){
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorStatsArea);
		GLOBAL.processing.rect(x, y, x+width, y+height);
		GLOBAL.processing.noFill();

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
		val = GLOBAL.processing.map(scroll.val,0, 1,0,GLOBAL.allEpisodesButtons.size()-5);
		// Draw all the visible series buttons
		for(int j = (int)val; j <= val + 5 && j < GLOBAL.allEpisodesButtons.size(); j++) {
//			GLOBAL.allEpisodesButtons.get(j).x = 600;
//			GLOBAL.allEpisodesButtons.get(j).y = 500 + elementNumber*40;
			GLOBAL.allEpisodesButtons.get(j).x = x + 50;
			GLOBAL.allEpisodesButtons.get(j).y = y + 100 + elementNumber*40;
			GLOBAL.allEpisodesButtons.get(j).active = true;
			GLOBAL.allEpisodesButtons.get(j).draw();
			elementNumber++;
		}
		
		// Draw all the series button
		for(int i = 0; i < GLOBAL.allFilterButtons.size(); i++) {
			GLOBAL.allFilterButtons.get(i).draw();	
		}
	}
	
	public void doAction() {
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
		if (scroll.mouseOver())
			scroll.mousePressed();
	}
}
