
public class Menu extends GuiElement{
	
	public Button cButton;
	public Button eButton;
	public Button sButton;
	public Button phButton;
	public Button dialButton;
	public Button wordButton;
	public Button clearButton;
	
	public MultistateButton viewTypeButton;
	
	public CharacterSelectionPanel characterPicker;
	public EpisodeSelectionPanel episodePicker;
	public SeasonSelectionPanel seasonPicker;
	
	public static boolean selectingCharacter = false;
	public static boolean selectingEpisode = false;
	public static boolean selectingSeason = false;

	// Menu contents
	public Menu() {
		characterPicker = new CharacterSelectionPanel(240,80);
		episodePicker = new EpisodeSelectionPanel(250,200);
		seasonPicker = new SeasonSelectionPanel(300,200);
		
		cButton = new Button();
		cButton.x = 20;
		cButton.y = 150;
		cButton.active = true;
		cButton.setLabel("Pick a character");
		sButton = new Button();
		sButton.x = 20;
		//sButton.y = 270;
		sButton.y = 150;
		sButton.active = true;
		sButton.setLabel("Pick a season");
		eButton = new Button();
		eButton.x = 20;
		//eButton.y = 210;
		eButton.y = 150;
		eButton.active = true;
		eButton.setLabel("Pick a episode");
		
		// Character buttons
		// Number of lines
		dialButton = new Button();
		dialButton.x = 20;
		dialButton.y = 190;
		dialButton.setLabel("All Dialog");
		dialButton.active = true;
		dialButton.setFontSize(14);
		
		// Catchphrases
		phButton = new Button();
		phButton.x = 20;
		phButton.y = 230;
		phButton.setLabel("Catchphrases");
		phButton.active = true;
		phButton.setFontSize(14);
		
		// Word
		wordButton= new Button();
		wordButton.x = 20;
		wordButton.y = 270;
		wordButton.setLabel("Tag cloud");
		wordButton.active = true;
		wordButton.setFontSize(14);
		
		//Statistical view button
		viewTypeButton = new MultistateButton();
		viewTypeButton.x = 20;
		viewTypeButton.y = 400;
		viewTypeButton.addState("Plots");
		viewTypeButton.addState("Statistical");
		viewTypeButton.active = true;
		
		//Reset button
		clearButton = new Button();
		clearButton.x = 20;
		clearButton.y = 700;
		clearButton.setLabel("Clear graphs");
		clearButton.active = true;
		clearButton.setFontSize(14);

	}

	public void draw() {

		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorMenuBackground);
		GLOBAL.processing.rect(x, y, x+width, y+height);

		GLOBAL.processing.strokeWeight(2);
		GLOBAL.processing.stroke(GLOBAL.colorLinesLabelY);
		GLOBAL.processing.line(x + width, y, x+ width, y+height);
		GLOBAL.processing.noFill();

		if (GLOBAL.ANALYSIS_TYPE.equals("characters")) {
			cButton.draw();
			dialButton.draw();
			phButton.draw();
			wordButton.draw();
		}
		else if (GLOBAL.ANALYSIS_TYPE.equals("seasons"))
			sButton.draw();
		else if (GLOBAL.ANALYSIS_TYPE.equals("episodes"))
			eButton.draw();
		
		GLOBAL.processing.fill(GLOBAL.colorText);
		GLOBAL.processing.textFont(GLOBAL.tFont,24);
		GLOBAL.processing.text("Menu", 20, 100);

		if (selectingCharacter)
			characterPicker.draw();
		else if (selectingSeason)
			seasonPicker.draw();
		else if(selectingEpisode)
			episodePicker.draw();

		if ( GLOBAL.WORD_ANALYSIS)
			viewTypeButton.active = false;
		else {
			viewTypeButton.draw();
			viewTypeButton.active = true;
		}
		
		clearButton.draw();

	}

	public void doAction() {
		
		// Selection panels buttons
		if (GLOBAL.ANALYSIS_TYPE.equals("characters") && cButton.mouseOver()) {
			selectingEpisode = false;
			selectingSeason = false;
			if (selectingCharacter)
				selectingCharacter = false;
			else
				selectingCharacter = true;
			}

		else if (GLOBAL.ANALYSIS_TYPE.equals("seasons") && sButton.mouseOver()){
			selectingEpisode = false;
			selectingCharacter= false;
			if (selectingSeason)
				selectingSeason = false;
			else
				selectingSeason = true;
			}
		
		else if (GLOBAL.ANALYSIS_TYPE.equals("episodes") && eButton.mouseOver()){
			selectingSeason = false;
			selectingCharacter= false;
			if (selectingEpisode)
				selectingEpisode = false;
			else
				selectingEpisode = true;
			}
		
		// Type of graphs buttons
		else if ( GLOBAL.ANALYSIS_TYPE.equals("characters") && dialButton.mouseOver() && (GLOBAL.CATCHPHRASES_ANALYSIS == true || 
				GLOBAL.WORD_ANALYSIS == true)) {
			GLOBAL.WORD_ANALYSIS = false;
			GLOBAL.CATCHPHRASES_ANALYSIS = false;	
			intheyear2525.graphArea.clearGraphs();
			for (int i=0; i < GLOBAL.charactersSelected.size() ; i++ ) {
				GLOBAL.CHARACTER_SELECTED = GLOBAL.charactersSelected.get(i);
				intheyear2525.graphArea.createCharacterGraph();
			}			
		}
		else if ( GLOBAL.ANALYSIS_TYPE.equals("characters") && phButton.mouseOver() && (GLOBAL.CATCHPHRASES_ANALYSIS == false|| 
				GLOBAL.WORD_ANALYSIS == true)) {
			GLOBAL.WORD_ANALYSIS = false;
			GLOBAL.CATCHPHRASES_ANALYSIS = true;
			intheyear2525.graphArea.clearGraphs();
			for (int i=0; i < GLOBAL.charactersSelected.size() ; i++ ) {
				GLOBAL.CHARACTER_SELECTED = GLOBAL.charactersSelected.get(i);
				intheyear2525.graphArea.createCharacterGraph();
			}			}
		else if (GLOBAL.ANALYSIS_TYPE.equals("characters") && wordButton.mouseOver() && GLOBAL.WORD_ANALYSIS == false) {
			GLOBAL.WORD_ANALYSIS = true;
			GLOBAL.CATCHPHRASES_ANALYSIS = false;
			intheyear2525.graphArea.clearGraphs();
			for (int i=0; i < GLOBAL.charactersSelected.size() ; i++ ) {
				GLOBAL.CHARACTER_SELECTED = GLOBAL.charactersSelected.get(i);
				intheyear2525.graphArea.createCharacterGraph();
			}	
		}
		
		
		else if (selectingCharacter && characterPicker.mouseOver()) {
			characterPicker.doAction();
		}
		else if (selectingEpisode && episodePicker.mouseOver())
			episodePicker.doAction();
		else if(selectingSeason && seasonPicker.mouseOver())
			seasonPicker.doAction();
		
		else if (viewTypeButton.mouseOver() && viewTypeButton.active) {
			GLOBAL.STAT_VIEW = !GLOBAL.STAT_VIEW;
			viewTypeButton.doAction();
		}
		
		else if (clearButton.mouseOver()) {
			intheyear2525.graphArea.clearGraphs();
			GLOBAL.episodesSelected.clear();
			GLOBAL.charactersSelected.clear();
			GLOBAL.seasonsSelected.clear();
		}

	}
	
}
