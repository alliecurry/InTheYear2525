import processing.core.PImage;


public class MultistateButton extends Widget{

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public MultistateButton()
	{
		width = (int)(GLOBAL.processing.textWidth("characters") + 20);
		height = 30;
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


		GLOBAL.processing.textFont(GLOBAL.tFont, 19);
        width = (int)(GLOBAL.processing.textWidth("characters")) + 20;
		GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);

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
		GLOBAL.processing.text(label, cx + 10 + GLOBAL.processing.textWidth("characters")/2, cy + 20);

	}
	
	public void doAction() {
		 if ( label.equals("characters") )
			  setLabel("seasons");
		  else if ( label.equals("seasons") )
			  setLabel("episodes");
		  else if ( label.equals("episodes") )
			  setLabel("characters");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// Private stuff.
	String label;
	  
}
