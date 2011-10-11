import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;

public class CharacterSelectionPanel extends GuiElement {
	
	public Button treeView;
	public Button listView;
	
	public VerticalScrollBar scroll; // scrollbar for the list view
	
	public boolean list = false;  // false = treeMap view. true = listView
	
	public PImage treemap;
	public PImage legend;
	
	public ArrayList<CharacterButton> characterIconsTreemap;
	
	public CharacterSelectionPanel( int xValue, int yValue) {
		
		x = xValue;
		y=yValue;
		
		height = 600;
		width = 660;
		
		// Treemap
		
		characterIconsTreemap = new ArrayList<CharacterButton>();
		
		// Treemap list of character, it depends on the jpeg of treemap.jpg !!
		setCharacterIconsTreemap(240, 180, 348, 433, "fry");
		setCharacterIconsTreemap(240,435,352,679,"farnsworth");
		setCharacterIconsTreemap(353,435,574,558,"zoidberg");
		setCharacterIconsTreemap(353,559,574,679,"hermes");
		setCharacterIconsTreemap(576,179,741,331,"amy");
		setCharacterIconsTreemap(351, 181, 572, 302, "leela");
		setCharacterIconsTreemap(351, 305, 575, 433, "bender");
		setCharacterIconsTreemap(576,331,659,432,"zapp brannigan");
		setCharacterIconsTreemap(659,333,741,431,"morbo");
		setCharacterIconsTreemap(576,433,646,491,"url");
		setCharacterIconsTreemap(577,492,648,548,"elzar");
		setCharacterIconsTreemap(648,434,742,473,"mom");
		setCharacterIconsTreemap(648,474,694,547,"mr. wong");
		setCharacterIconsTreemap(694,474,742,548,"labarbara");
		setCharacterIconsTreemap(576,549,652,594,"cubert");
		setCharacterIconsTreemap(576,595,652,638,"hattie");
		setCharacterIconsTreemap(576,638,652,679,"randy");
		setCharacterIconsTreemap(653,549,697,617,"preacherbot");
		setCharacterIconsTreemap(698,549,742,616,"igner");
		setCharacterIconsTreemap(654,617,696,679,"mrs. wong");
		setCharacterIconsTreemap(698,618,743,679,"petunia");
		setCharacterIconsTreemap(741,181,802,298,"linda");
		setCharacterIconsTreemap(803,179,899,237,"kif");
		setCharacterIconsTreemap(804,238,899,297,"nixon");
		setCharacterIconsTreemap(743,298,825,368,"scruffy");
		setCharacterIconsTreemap(826,300,899,367,"calculon");
		setCharacterIconsTreemap(742,369,825,431,"sal");
		setCharacterIconsTreemap(825,369,899,433,"smitty");
		setCharacterIconsTreemap(744,433,787,495,"nibbler");
		setCharacterIconsTreemap(787,434,827,495,"walt");
		setCharacterIconsTreemap(829,433,864,494,"lrrr");
		setCharacterIconsTreemap(865,434,900,494,"hedonism bot");
		setCharacterIconsTreemap(744,496,785,550,"larry");
		setCharacterIconsTreemap(785,497,828,550,"wernstrom");
		setCharacterIconsTreemap(829,495,865,550,"hyper-chicken");
		setCharacterIconsTreemap(865,495,899,550,"dwight");
		setCharacterIconsTreemap(744,551,769,618,"poopenmeyer");
		setCharacterIconsTreemap(743,619,767,679,"roberto");
		setCharacterIconsTreemap(768,551,818,587,"tinny tim");
		setCharacterIconsTreemap(818,551,863,584,"bubblegum");
		setCharacterIconsTreemap(863,551,899,584,"panucci");
		setCharacterIconsTreemap(770,587,818,618,"monique");
		setCharacterIconsTreemap(818,585,863,618,"transition announcer");
		setCharacterIconsTreemap(863,585,899,618,"terry");
		setCharacterIconsTreemap(768,620,817,650,"munda");
		setCharacterIconsTreemap(818,619,859,649,"nd-nd");
		setCharacterIconsTreemap(859,620,900,649,"paper-hatted salesman");
		setCharacterIconsTreemap(769,650,818,679,"morris");
		setCharacterIconsTreemap(819,649,859,679,"clamps");
		setCharacterIconsTreemap(859,649,899,679,"dr. cahill");		
		
		// Create a new scrollbar
		scroll = new VerticalScrollBar();
		scroll.x = x + 20;
		scroll.y = y + 100;	
		scroll.width = 15;
		scroll.height = 430;	
		scroll.size = (float)4 / Parser.ALL_CHARACTERS.size();
		
		treeView = new Button();
		treeView.x = x + 20;
		treeView.y = y + 40;
		treeView.active = true;
		treeView.setLabel("Treemap View");
		
		listView = new Button();
		listView.x = x + 200;
		listView.y = y + 40;
		listView.active = true;
		listView.setLabel("List View");
		
		treemap = GLOBAL.processing.loadImage("images/treemap2.jpg");
		legend = GLOBAL.processing.loadImage("images/legend.jpg");
		
	}
	
	
	public void draw() {
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorBackgroundLayerTwo);
		GLOBAL.processing.rect(intheyear2525.graphArea.x, 0, x+width, GLOBAL.processing.height);
		GLOBAL.processing.fill(GLOBAL.colorStatsArea);
		GLOBAL.processing.rect(x, y, x+width, y+height);
		GLOBAL.processing.stroke(GLOBAL.colorLinesLabelY);
		GLOBAL.processing.strokeWeight(3);
		GLOBAL.processing.line(x-3, y-3, x+width+3, y-3);
		GLOBAL.processing.line(x+width+3, y-3, x+width+3, y+height+3);
		GLOBAL.processing.line(x-3, y+height+3, x+width+3, y+height+3);
		GLOBAL.processing.line(x-3, y-3, x-3, y+height+3);
		GLOBAL.processing.noFill();

		treeView.draw();
		listView.draw();

		if ( !list ) {
			
			if (treemap!= null) 
				GLOBAL.processing.image(treemap, x, y + 100, 660, 500);
			
			if (legend != null) {
				GLOBAL.processing.image(legend, x + 390, y + 40);
				GLOBAL.processing.fill(GLOBAL.colorText);
				GLOBAL.processing.textFont(GLOBAL.tFont,10);
				GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
				GLOBAL.processing.text(" 0-25    26-50   51-75   76-100   101-120", x + 390, y + 30);
				GLOBAL.processing.text("- min      6", x + 597, y + 50);
				GLOBAL.processing.text("+ max 114", x + 597, y + 67);
			}
			
			for (int i=0; i< characterIconsTreemap.size() ; i++) {
				characterIconsTreemap.get(i).draw();
			}
			
		}
		else {
			scroll.draw();
			
			// First, create and deactivate all buttons	
			GLOBAL.allCharacterListButtons.clear();
			for(int i =0; i < Parser.ALL_CHARACTERS.size(); i++) {
				CharacterButton b = new CharacterButton(Parser.ALL_CHARACTERS.get(i).getName(), CharacterButton.LABEL_TYPE);
				b.active = false;
				GLOBAL.allCharacterListButtons.add(b);
				
			}
			
			// elementNumber: var for offset calculation
			int elementNumber = 0;	
						
			// Find from what button we have to print, based on the value of the scrollbar in this moment
			float val = GLOBAL.processing.map(scroll.value,0, 1,0,GLOBAL.allCharacterListButtons.size());
			// Draw all the visible series buttons
			for(int j = (int)val; j <= val + 10 && j < GLOBAL.allCharacterListButtons.size(); j++) {
				if (GLOBAL.allCharacterListButtons.get(j) == null)
					break;
				GLOBAL.allCharacterListButtons.get(j).x = x + 70;
				GLOBAL.allCharacterListButtons.get(j).y = y + 100 + elementNumber*40;
				GLOBAL.allCharacterListButtons.get(j).active = true;
				GLOBAL.allCharacterListButtons.get(j).draw();
				elementNumber++;
			}
			
			// Rollover
			for(int j = (int)val; j <= val + 10 && j < GLOBAL.allCharacterListButtons.size(); j++) {
				if (GLOBAL.allCharacterListButtons.get(j).active && GLOBAL.allCharacterListButtons.get(j).mouseOver()) 
					GLOBAL.allCharacterListButtons.get(j).mouseRolloverFunction();
			}
			
		}
		
		
	}
	
	// Create a new Character Button for each one of the tree map
	public void setCharacterIconsTreemap(int x_min, int y_min, int x_max , int y_max, String name ) {
		
		CharacterButton w = new CharacterButton(name, CharacterButton.NO_LABEL_TYPE);
		
		w.x = x_min;
		w.y = y_min;
		
		w.width = x_max - x_min;
		w.height = y_max - y_min;
		
		w.active = true;
		
		characterIconsTreemap.add(w);

	}
	
	public void doAction() {
		if(!list) {
			for(int i = 0; i < characterIconsTreemap.size(); i++) {
				if( characterIconsTreemap.get(i).mouseOver() && characterIconsTreemap.get(i).active) {
					characterIconsTreemap.get(i).doAction();
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







