import java.util.ArrayList;

import processing.core.PImage;


public class MultistateButton extends GuiElement{
	
	private ArrayList<String> states = new ArrayList<String>();
	private int stateIndex;
	private String label;
	public boolean color = false;

	public MultistateButton()
	{
		width = (int)(GLOBAL.processing.textWidth("characters") + 20);
		height = 30;
	}

	public void setLabel(String name)
	{
		label = name;
	}

	public void draw()
	{

		int cx = x;
		int cy = y;


		GLOBAL.processing.textFont(GLOBAL.tFont, 19);
//        width = (int)(GLOBAL.processing.textWidth(states.get(stateIndex))) + 20;
		width = intheyear2525.menu.width-40;
		GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
		
		GLOBAL.gu.drawBox(x, y, width, height, 0, GLOBAL.processing.color(50,50,50), 255);

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
		GLOBAL.processing.text(states.get(stateIndex).substring(0,1).toUpperCase() + states.get(stateIndex).substring(1), cx + 5, cy + 20);

	}
	
	
	public void addState(String s) {
		states.add(s);
	}

	public void changeState() {
		
		stateIndex++;
		
		if (stateIndex == states.size())
			stateIndex = 0;
		
	}
	
	public String getState() {
		return states.get(stateIndex);
	}
	
	public void doAction() {
		 changeState();
	}
 
}
