import processing.core.*;

class GuiElement
{
	
    public int x;
    public int y;
    
    public int width;
    public int height;
    
    public int alpha;
    public int backColor;
    
    public boolean active = false;
  
    public GuiElement()
    {
        backColor = GLOBAL.processing.color(180, 180, 255, 40);
    }
  
    public void draw()
    {
    	GLOBAL.processing.rectMode(GLOBAL.processing.CORNER);

    	GLOBAL.processing.noStroke();
    	GLOBAL.processing.fill(backColor);
    	GLOBAL.processing.rect(x, y, width, height);

    	GLOBAL.gu.drawBox(x, y, width, height, 0, GLOBAL.processing.color(255, 255, 255), 255);
    	GLOBAL.gu.drawBox(x, y, width, height, 1, GLOBAL.processing.color(0, 0, 0), 180);
    }
    
    public boolean mouseOver()
    {
    	if(GLOBAL.processing.mouseX > x && GLOBAL.processing.mouseX < x + width && 
    			GLOBAL.processing.mouseY > y && GLOBAL.processing.mouseY < y + height) return true;
    	return false;
    }

}