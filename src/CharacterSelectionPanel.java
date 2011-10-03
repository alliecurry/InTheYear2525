import processing.core.PImage;

public class CharacterSelectionPanel extends Widget {
	
	public Button treeView;
	public Button listView;
	
	public ScrollBar scroll; // scrollbar for the list view
	
	public boolean list = false;  // false = treeMap view. true = listView
	
	// test
	public PImage treemap;
	
	public CharacterSelectionPanel( int xValue, int yValue) {
		
		x = xValue;
		y=yValue;
		
		height = 600;
		width = 660;
		
		/*
		int offsetX = 0, offsetY= 0;
		// TODO make all buttons after having all the occurrences. for all in ALL_CHARACTER, create a button with getName and getOccurrences
		for(int i = 0; i < Parser.ALL_CHARACTERS.size(); i++) {	
			// TODO eliminate this if when all the character images will be available
			if(Parser.ALL_CHARACTERS.get(i).getTotalEpisodes() > 4) {
				CharacterButton cb = new CharacterButton(Parser.ALL_CHARACTERS.get(i).getName(), Parser.ALL_CHARACTERS.get(i).getTotalEpisodes(), true);
				//cb.x = 40 + offsetX;
				cb.x = x + 20 + offsetX;
//				cb.y = 150 + offsetY;
				cb.y = y + 100 + offsetY;
				cb.active = true;
				
				if (offsetX == 200) {
					offsetX = 0;
					offsetY += 100;
				}
				else
					offsetX += 100;
				
				//cb.setLabel(Parser.ALL_CHARACTERS.get(i).getName());
				GLOBAL.allCharacterButtons.add(cb);
			}
		}
		*/
		
		// Create a new scrollbar
		scroll = new ScrollBar();
		scroll.x = x + 20;
		scroll.y = y + 100;	
		scroll.width = 15;
		scroll.height = 450;	
		scroll.size = (float)4 / Parser.ALL_CHARACTERS.size();
		
		treeView = new Button();
		treeView.x = x + 20;
		treeView.y = y + 50;
		treeView.active = true;
		treeView.setLabel("Treemap View");
		
		listView = new Button();
		listView.x = x + 200;
		listView.y = y + 50;
		listView.active = true;
		listView.setLabel("List View");
		
		// test
		treemap = GLOBAL.processing.loadImage("images/treemap.jpg");
		
	}
	
	
	public void draw() {
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorStatsArea);
		GLOBAL.processing.rect(x, y, x+width, y+height);
		GLOBAL.processing.noFill();
		
		treeView.draw();
		listView.draw();

		if ( !list ) {/*
			for(int i = 0; i < GLOBAL.allCharacterButtons.size(); i++) {	
				GLOBAL.allCharacterButtons.get(i).draw();
			}*/
			
			GLOBAL.processing.image(treemap, x, y + 100, 660, 500);
			
		}
		else {
			scroll.draw();
			
			// First, create and deactivate all buttons	
			GLOBAL.allCharacterListButtons.clear();
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
				CharacterButton b = new CharacterButton(Parser.ALL_CHARACTERS.get(i).getName(), Parser.ALL_CHARACTERS.get(i).getTotalEpisodes(), false);
				b.active = false;
				GLOBAL.allCharacterListButtons.add(b);
				
			}
			
			// elementNumber: var for offset calculation
			int elementNumber = 0;	
						
			// Find from what button we have to print, based on the value of the scrollbar in this moment
			float val = GLOBAL.processing.map(scroll.val,0, 1,0,GLOBAL.allCharacterListButtons.size()-10);
			// Draw all the visible series buttons
			for(int j = (int)val; j <= val + 10 && j < GLOBAL.allCharacterListButtons.size(); j++) {
				GLOBAL.allCharacterListButtons.get(j).x = x + 50;
				GLOBAL.allCharacterListButtons.get(j).y = y + 100 + elementNumber*40;
				GLOBAL.allCharacterListButtons.get(j).active = true;
				GLOBAL.allCharacterListButtons.get(j).draw();
				elementNumber++;
			}
			
		}
		
		
	}
	
	public void doAction() {
		if(!list) {
			for(int i = 0; i < GLOBAL.allCharacterButtons.size(); i++) {
				if( GLOBAL.allCharacterButtons.get(i).mouseOver() && GLOBAL.allCharacterButtons.get(i).active) {
					GLOBAL.allCharacterButtons.get(i).doAction();
				}
			}
		}
		else {
			if (scroll.mouseOver())
				scroll.mousePressed();
			else {
				for(int i = 0; i < GLOBAL.allCharacterListButtons.size(); i++) {
					if( GLOBAL.allCharacterListButtons.get(i).mouseOver() && GLOBAL.allCharacterListButtons.get(i).active) {
						GLOBAL.allCharacterListButtons.get(i).doAction();
					}
				}
			}
		}
		if (treeView.mouseOver()) 
			list = false;
		else if (listView.mouseOver())
			list = true;
	}
	
}
