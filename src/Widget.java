import processing.core.*;

class Widget
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public int x;
    public int y;
    public int width;
    public int height;
    public int style;
    public int alpha;
    public int backColor;
    
    public boolean active = false;
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public Widget()
    {
        backColor = GLOBAL.processing.color(180, 180, 255, 40);
    }
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void draw()
    {
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNER);
        if(style == 0)
        {
        	GLOBAL.processing.noStroke();
        	GLOBAL.processing.fill(backColor);
        	GLOBAL.processing.rect(x, y, width, height);
            
        	GLOBAL.gu.drawBox(x, y, width, height, 0, GLOBAL.processing.color(255, 255, 255), 255);
        	GLOBAL.gu.drawBox(x, y, width, height, 1, GLOBAL.processing.color(0, 0, 0), 180);
        }
        else
        {
        	GLOBAL.processing.noStroke();
        	GLOBAL.processing.fill(255, 255, 255, alpha);

            int sz = 24;
            int hsz = 12;

            //fill(255, 255, 255, 80);
            GLOBAL.processing.rect(x, y, width, height);
            GLOBAL.processing.arc(x, y, sz, sz, GLOBAL.processing.PI, GLOBAL.processing.PI * 3 / 2);
            GLOBAL.processing.arc(x, y + height, sz, sz, GLOBAL.processing.PI / 2, GLOBAL.processing.PI);
            GLOBAL.processing.rect(x, y - hsz, width, hsz);
            GLOBAL.processing.rect(x, y + height, width, hsz);
            GLOBAL.processing.rect(x - hsz, y, hsz, height);
            GLOBAL.processing.arc(x + width, y, sz, sz, GLOBAL.processing.PI * 3 / 2, GLOBAL.processing.TWO_PI);
            GLOBAL.processing.arc(x + width, y + height, sz, sz, 0, GLOBAL.processing.PI / 2);
            GLOBAL.processing.rect(x + width, y, hsz, height);

            GLOBAL.processing.fill(0, 0, 0, alpha + 10);
            // Draw window background.    
            GLOBAL.processing.rect(x, y, width, height);

            sz = 20;
            hsz = 10;

            GLOBAL.processing.arc(x, y, sz, sz, GLOBAL.processing.PI, GLOBAL.processing.PI * 3 / 2);
            GLOBAL.processing.arc(x, y + height, sz, sz, GLOBAL.processing.PI / 2, GLOBAL.processing.PI);
            GLOBAL.processing.rect(x, y - hsz, width, hsz);
            GLOBAL.processing.rect(x, y + height, width, hsz);
            GLOBAL.processing.rect(x - hsz, y, hsz, height);
            GLOBAL.processing.arc(x + width, y, sz, sz, GLOBAL.processing.PI * 3 / 2, GLOBAL.processing.TWO_PI);
            GLOBAL.processing.arc(x + width, y + height, sz, sz, 0, GLOBAL.processing.PI / 2);
            GLOBAL.processing.rect(x + width, y, hsz, height);
        }
    }
  
  ////////////////////////////////////////////////////////////////////////////////////////////////////
  public boolean mouseOver()
  {
    if(GLOBAL.processing.mouseX > x && GLOBAL.processing.mouseX < x + width && 
    		GLOBAL.processing.mouseY > y && GLOBAL.processing.mouseY < y + height) return true;
      return false;
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////////////////
  // Private stuff.
}