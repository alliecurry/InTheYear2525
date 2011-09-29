
public class SeasonSelectionPanel extends Widget{
	
	public SeasonSelectionPanel(int xValue, int yValue) {
		
		x = xValue;
		y = yValue;
		
		width = 500;
		height = 350;
		
		SeasonButton b1 = new SeasonButton(1);
		b1.setLabel("Season 1");
		b1.x = x + 100;
		b1.y = y + 100;
		b1.active = true;
		SeasonButton b2 = new SeasonButton(2);
		b2.setLabel("Season 2");
		b2.x = x + 200;
		b2.y = y + 100;
		b2.active = true;
		SeasonButton b3 = new SeasonButton(3);
		b3.setLabel("Season 3");
		b3.x = x + 300;
		b3.y = y + 100;
		b3.active = true;
		SeasonButton b4 = new SeasonButton(4);
		b4.setLabel("Season 4");
		b4.x = x + 100;
		b4.y = y + 140;
		b4.active = true;
		SeasonButton b5 = new SeasonButton(5);
		b5.setLabel("Season 5");
		b5.x = x + 200;
		b5.y = y + 140;
		b5.active = true;
		SeasonButton b6 = new SeasonButton(6);
		b6.setLabel("Season 6");
		b6.x = x + 300;
		b6.y = y + 140;
		b6.active = true;
		SeasonButton bAll = new SeasonButton(0);
		bAll.setLabel("All seasons");
		bAll.x = x + 180;
		bAll.y = y + 180;
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
	public void draw() {
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorStatsArea);
		GLOBAL.processing.rect(x, y, x+width, y+height);
		GLOBAL.processing.noFill();
		
		// Draw all the series button
		for(int i = 0; i < GLOBAL.allSeasonsButtons.size(); i++) {
			GLOBAL.allSeasonsButtons.get(i).draw();	
		}

	}
	
	public void doAction() {
		for(int i = 0; i < GLOBAL.allSeasonsButtons.size(); i++) {
			if( GLOBAL.allSeasonsButtons.get(i).mouseOver() && GLOBAL.allSeasonsButtons.get(i).active) {
				GLOBAL.allSeasonsButtons.get(i).doAction();
			}
		}
		Menu.selectingSeason = false;
	}

}
