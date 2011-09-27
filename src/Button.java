import processing.core.*;

class Button extends Widget
{
  ////////////////////////////////////////////////////////////////////////////////////////////////////
  public Button()
  {
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
    width = (int)(GLOBAL.processing.textWidth(name) + 10);
    if(icon != null) width += 15;
    height = 30;
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////////////////
  public void draw()
  {
    
    int cx = x;
    int cy = y;
    if(icon != null)
    {
    	GLOBAL.processing.image(icon, cx - 8, y);
    }
    if(label != null)
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
      if(icon != null)
      {
    	  GLOBAL.processing.fill(GLOBAL.colorButtonLabel);
    	  GLOBAL.processing.text(label, cx + 15, cy + 20);
      }
      else
      {
    	  GLOBAL.processing.fill(GLOBAL.colorButtonLabel);
    	  GLOBAL.processing.text(label, cx + 5, cy + 20);
      }
    }
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