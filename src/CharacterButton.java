import processing.core.*;

// A character buttons should contain an image to be displayed (we may eventually change this behavior )
public class CharacterButton extends Widget{
	
	// For now, the length of the square depends on the occurrences
	public CharacterButton(String name, int occurrences, boolean imageButton) {	
		if (imageButton) {
			label = name;
			setIcon("images/"+name+".jpg");
			int importanceScaleFactor = (int)((float)occurrences / 10 * 20); 
			height = importanceScaleFactor;
			width = importanceScaleFactor;
			//System.out.println(width+ " "+height);
		}
		else {
			setLabel(name);
			width = (int)(GLOBAL.processing.textWidth(label)) + 10;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setIcon(String name)
	{
		if(name != null)
		{
			icon = GLOBAL.processing.loadImage(name);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setLabel(String name)
	{
		label = name;
		
		if ( icon == null ) {
			width += 15;
		    height = 30;
		}
			
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void draw()
	{

		int cx = x;
		int cy = y;
		
		// with icon
		if(icon != null)
		{
			GLOBAL.processing.image(icon, x, y, width, height);
			if(mouseOver()) 
			{
				super.draw();
				if(GLOBAL.processing.mousePressed)
				{
					//GLOBAL.gu.drawVGradient(x, y + height, width, -height, GLOBAL.processing.color(150, 150, 180), 80, GLOBAL.processing.color(150, 150, 180), 255, (float)0.8);    
					GLOBAL.gu.drawBox(x, y, width, height, 0, GLOBAL.processing.color(150, 150, 200), 255);
					GLOBAL.gu.drawBox(x, y, width, height, 1, GLOBAL.processing.color(150, 150, 200), 150);
					cx += 2;
					cy += 2;
				}
			}
			GLOBAL.processing.strokeWeight(1);
		}
		// without icon
		else 
		{
			GLOBAL.processing.textFont(GLOBAL.tFont, 19);
			width = (int)(GLOBAL.processing.textWidth(label)) + 10;
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			if(mouseOver()) 
			{
				super.draw();
				if(GLOBAL.processing.mousePressed)
				{
					//GLOBAL.gu.drawVGradient(x, y + height, width, -height, GLOBAL.processing.color(150, 150, 180), 80, GLOBAL.processing.color(150, 150, 180), 255, (float)0.8);    
					GLOBAL.gu.drawBox(x, y, width, height, 0, GLOBAL.processing.color(150, 150, 200), 255);
					GLOBAL.gu.drawBox(x, y, width, height, 1, GLOBAL.processing.color(150, 150, 200), 150);
					cx += 2;
					cy += 2;
				}
			}

			GLOBAL.processing.strokeWeight(1);
			GLOBAL.processing.fill(GLOBAL.colorButtonLabel);
			GLOBAL.processing.text(label, cx + 5, cy + 20);
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void doAction() {
		
		// Add the character to the list to be analyzed in graphs, add it in 1st position if it is full ( size = 3 )
		if (GLOBAL.charactersSelected.size() < 3) {
			for (int i = 0 ; i < Parser.ALL_CHARACTERS.size() ; i++ ) {
				if (Parser.ALL_CHARACTERS.get(i).getName().equalsIgnoreCase(label))
					GLOBAL.charactersSelected.add(Parser.ALL_CHARACTERS.get(i));
			}
		}
		else {
			for (int i = 0 ; i < Parser.ALL_CHARACTERS.size() ; i++ ) {
				if (Parser.ALL_CHARACTERS.get(i).getName().equalsIgnoreCase(label)) {
					GLOBAL.charactersSelected.remove(2);
					GLOBAL.charactersSelected.add(0,Parser.ALL_CHARACTERS.get(i));
				}
			}
		}
		
		// Create the new graph to be plot
		main_class.graphArea.createCharacterGraph();
		
		Menu.selectingCharacter = false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// Private stuff.
	PImage icon;
	String label;
	
}


