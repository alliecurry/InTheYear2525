class VerticalScrollBar extends GuiElement
{    
    public float size;
    public float value;
    public boolean dragging;
    
    public GuiElement selection;
    public int dragY;

    public VerticalScrollBar() {
        selection = new GuiElement();
        selection.backColor = GLOBAL.processing.color(200, 200, 255, 100);
    }

    public void draw() {
        int bh = (int)(size * height);
        if(bh < 10) bh = 10;
        
        GLOBAL.processing.strokeWeight(2);
        GLOBAL.processing.stroke(GLOBAL.colorLinesLabelY);
        GLOBAL.processing.line(x + width / 2, y, x + width / 2, y + height);
        
        // Draw selection region
        selection.x = x;
        selection.y = y + (int)(value * height);
        selection.width = width;
        selection.height = bh;
        selection.draw();
        GLOBAL.gu.drawHGradient(selection.x + selection.width, selection.y, -selection.width, selection.height, GLOBAL.processing.color(200, 200, 200), 255, GLOBAL.processing.color(255, 255, 255), 255, (float) 0.5);
        
        // Handle scrollbar dragging.
        if(dragging)
        {
            float d = (float)(GLOBAL.processing.mouseY - dragY) / height;
            float tmpV = value + d;
            if(tmpV >= 0 && tmpV + size <= 1)
            {
                value = tmpV;
            }
            dragY = GLOBAL.processing.mouseY;
        }
    }
  
    public void mousePressed() {
        int bh = (int)(size * height);
        if(bh < 10) bh = 10;
        int y1 = y + (int)(value * height);
        int y2 = y1 + bh;
        if(GLOBAL.processing.mouseY > y1 && GLOBAL.processing.mouseY < y2)
        {
            dragging = true;
            dragY = GLOBAL.processing.mouseY;
            return;
        }
    }
  
    public void mouseReleased() {
        dragging = false;
    }
    
}