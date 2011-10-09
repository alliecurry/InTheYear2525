class GuiGradient {

	public void drawBox(int x, int y, int width, int height, int dx, int c, int sa){
		
		GLOBAL.processing.strokeWeight(1);
		GLOBAL.processing.stroke(c, sa);
		GLOBAL.processing.line(x, y - dx, x + width, y - dx);
		GLOBAL.processing.line(x, y + height + dx, x + width, y + height + dx);
		GLOBAL.processing.line(x - dx, y, x - dx, y + height);
		GLOBAL.processing.line(x + width + dx, y, x + width + dx, y + height);
	}

	public void drawGradient(int x, int y, int width, int height, int start, int sa, int end, int ea){
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.beginShape(GLOBAL.processing.QUADS);

		GLOBAL.processing.fill(start, sa);
		GLOBAL.processing.vertex(x, y);
		GLOBAL.processing.vertex(x + width, y);

		GLOBAL.processing.fill(end, ea);
		GLOBAL.processing.vertex(x + width, y + height);
		GLOBAL.processing.vertex(x, y + height);

		GLOBAL.processing.endShape(); 
	}

	public void drawVGradient(float x, float y, float width, float height, int start, int sa, int end, int ea, float pc){
		
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNER);
		GLOBAL.processing.noStroke();
		GLOBAL.processing.fill(start, sa);

		float h1 = (int)(height * pc);

		GLOBAL.processing.rect(x, y, width, h1);
		y += h1;
		height -= h1;

		GLOBAL.processing.beginShape(GLOBAL.processing.QUADS);

		GLOBAL.processing.vertex(x, y);
		GLOBAL.processing.vertex(x + width, y);

		GLOBAL.processing.fill(end, ea);
		GLOBAL.processing.vertex(x + width, y + height);
		GLOBAL.processing.vertex(x, y + height);

		GLOBAL.processing.endShape(); 
	}

	public void drawHGradient(float x, float y, float width, float height, int start, int sa, int end, int ea, float pc){
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.fill(start, sa);

		float w1 = (int)(width * pc);

		GLOBAL.processing.rect(x, y, w1, height);
		x += w1;
		width -= w1;

		GLOBAL.processing.beginShape(GLOBAL.processing.QUADS);

		GLOBAL.processing.vertex(x, y + height);
		GLOBAL.processing.vertex(x, y);

		GLOBAL.processing.fill(end, ea);
		GLOBAL.processing.vertex(x + width, y);
		GLOBAL.processing.vertex(x + width, y + height);

		GLOBAL.processing.endShape(); 
	}

}