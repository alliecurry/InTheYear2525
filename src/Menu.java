
public class Menu extends Widget{
	
	public Button cButton;
	public Button eButton;
	public Button sButton;
	
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

	}

	public void draw() {

		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorMenuBackground);
		GLOBAL.processing.rect(x, y, x+width, y+height);

		GLOBAL.processing.strokeWeight(2);
		GLOBAL.processing.stroke(GLOBAL.colorLinesLayerOne);
		GLOBAL.processing.line(x + width, y, x+ width, y+height);
		GLOBAL.processing.noFill();

		if (GLOBAL.ANALYSIS_TYPE.equals("characters"))
			cButton.draw();
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
		
	}
	
	public void doAction() {
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
		
		if (selectingCharacter && characterPicker.mouseOver()) {
			characterPicker.doAction();
		}
		else if (selectingEpisode && episodePicker.mouseOver())
			episodePicker.doAction();
		else if(selectingSeason && seasonPicker.mouseOver())
			seasonPicker.doAction();

	}
	
}
