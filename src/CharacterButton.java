import processing.core.*;

// A character buttons should contain an image to be displayed (we may eventually change this behavior )
public class CharacterButton extends Widget{

	// For now, the length of the square depends on the occurrences
	public CharacterButton(String name, int occurrences) {
		label = name;
		setIcon("images/"+name+".jpg");
		int importanceScaleFactor = (int)((float)occurrences / 2 * 20); 
		height = importanceScaleFactor;
		width = importanceScaleFactor;
		System.out.println(width+ " "+height);

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
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void draw()
	{

		int cx = x;
		int cy = y;
		if(icon != null)
		{
			GLOBAL.processing.image(icon, x, y, width, height);
		}

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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void doAction() {
		System.out.println("You have selected " + label);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// Private stuff.
	PImage icon;
	String label;

}


