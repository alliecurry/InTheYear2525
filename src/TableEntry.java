import java.util.ArrayList;


public class TableEntry extends GuiElement {

	public TableEntry (String s, ArrayList<Integer> list, int font_size, int Width, int Height) {
		
		label = s;
		fontSize = font_size;
		height = Height;
		width = Width;
		
		values = list;
				
	}
	
	public TableEntry(String phraseString, int value,
			int color, int font_size, int Width, int Height) {
		
		catchphrase = phraseString;
		this.value = value;
		this.color = color;
		
		fontSize = font_size;
		height = Height;
		width = Width;
		
	}

	public void draw()
	{

		int cx = x;
		int cy = y;

		GLOBAL.processing.textFont(GLOBAL.tFont, fontSize);
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

		if (catchphrase == null) {
			GLOBAL.processing.fill(GLOBAL.colorText);
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			GLOBAL.processing.text(label, cx + 5, cy + 20);

			GLOBAL.COLORS.reset();

			int color1 = GLOBAL.COLORS.getNextColor();
			int color2 = GLOBAL.COLORS.getNextColor();
			int color3 = GLOBAL.COLORS.getNextColor();

			GLOBAL.processing.textAlign(GLOBAL.processing.RIGHT);

			switch (values.size()) {

			case 1:
				GLOBAL.processing.fill(color1);

				GLOBAL.processing.text(values.get(0), cx + width - 120 , cy + 20);
				break;
			case 2:
				GLOBAL.processing.fill(color1);
				GLOBAL.processing.text(values.get(0), cx + width - 120 , cy + 20);
				GLOBAL.processing.fill(color2);
				GLOBAL.processing.text(values.get(1), cx + width - 70 , cy + 20);
				break;
			case 3:
				GLOBAL.processing.fill(color1);
				GLOBAL.processing.text(values.get(0), cx + width - 120 , cy + 20);
				GLOBAL.processing.fill(color2);
				GLOBAL.processing.text(values.get(1), cx + width - 70 , cy + 20);
				GLOBAL.processing.fill(color3);
				GLOBAL.processing.text(values.get(2), cx + width - 20, cy + 20);
				break;
			}
		}
		else {
			GLOBAL.processing.fill(color);
			GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
			GLOBAL.processing.text(catchphrase, cx + 5, cy + 20);
			GLOBAL.processing.textAlign(GLOBAL.processing.RIGHT);
			GLOBAL.processing.text(value, cx + width - 50 , cy + 20);
		}
	}
	
	public void doAction() {
		
	}
	
	String label;
	ArrayList<Integer> values;
	
	// Catchphrase has different table 
	String catchphrase;
	int value;
	int color;
	
	int fontSize;
	
}
