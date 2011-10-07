
public class TableEntry extends Widget {

	public TableEntry (String s, int v) {
		
		label = s;
		val = v;
		
	}
	
	public void draw()
	{

		int cx = x;
		int cy = y;

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
		GLOBAL.processing.text(label + "          " + val, cx + 5, cy + 20);

	}
	
	public void doAction() {
		
	}
	
	String label;
	int val;
}
