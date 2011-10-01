class ScrollBar extends Widget
{    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public float size;
    public float val;
    public boolean dragging;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public ScrollBar()
    {
        selection = new Widget();
        selection.backColor = GLOBAL.processing.color(200, 200, 255, 100);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void draw()
    {
        int bh = (int)(size * height);
        if(bh < 10) bh = 10;
        
        GLOBAL.processing.stroke(0);
        GLOBAL.processing.line(x + width / 2, y, x + width / 2, y + height);
        
        // Draw selection region
        selection.x = x;
        selection.y = y + (int)(val * height);
        selection.width = width;
        selection.height = bh;
        selection.draw();
        GLOBAL.gu.drawHGradient(selection.x + selection.width, selection.y, -selection.width, selection.height, GLOBAL.processing.color(200, 200, 200), 255, GLOBAL.processing.color(255, 255, 255), 255, (float) 0.5);
        
        // Handle scrollbar dragging.
        if(dragging)
        {
            float d = (float)(GLOBAL.processing.mouseY - dragY) / height;
            float tmpV = val + d;
            if(tmpV >= 0 && tmpV + size <= 1)
            {
                val = tmpV;
            }
            dragY = GLOBAL.processing.mouseY;
        }
    }
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void mousePressed()
    {
        int bh = (int)(size * height);
        if(bh < 10) bh = 10;
        int y1 = y + (int)(val * height);
        int y2 = y1 + bh;
        if(GLOBAL.processing.mouseY > y1 && GLOBAL.processing.mouseY < y2)
        {
            dragging = true;
            dragY = GLOBAL.processing.mouseY;
            return;
        }
    }
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void mouseReleased()
    {
        dragging = false;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private stuff.
    Widget selection;
    private int dragY;
}