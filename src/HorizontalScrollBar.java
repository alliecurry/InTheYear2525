import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.axes.SelfIteratorNoPredicate;


public class HorizontalScrollBar extends Widget{
	
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public float size;
    public float valStart;
    public float valEnd;
    public boolean draggingStart;
    public boolean draggingEnd;
    
    public ArrayList<Episode> episodes;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public HorizontalScrollBar( ArrayList<Episode> list)
    {
    	episodes = list;
    	
        selectionStart = new Widget();
        selectionStart.backColor = GLOBAL.processing.color(200, 200, 255, 100);
        
        selectionEnd = new Widget();
        selectionEnd.backColor = GLOBAL.processing.color(200, 200, 255, 100);
        valEnd = (float)0.98;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void draw()
    {
        int bh = (int)(size * width);
        if(bh < 10) bh = 10;
        
        GLOBAL.processing.stroke(0);
        GLOBAL.processing.fill(GLOBAL.colorPlotArea);
        GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
        GLOBAL.processing.rect(x, y, x + width, y + height);
        
        // Draw selectionStart region
        selectionStart.y = y;
        selectionStart.x = x + (int)(valStart * width);
        selectionStart.width = bh;
        selectionStart.height = height;
        //selectionStart.draw();
        
        // Draw selectionEnd region
        selectionEnd.y = y;
        selectionEnd.x = x + (int)(valEnd * width);
        selectionEnd.width = bh;
        selectionEnd.height = height;
        //selectionEnd.draw();     
        
        //GLOBAL.gu.drawVGradient(selectionStart.x + selectionStart.width, selectionStart.y, -selectionStart.width, selectionStart.height, GLOBAL.processing.color(200, 200, 200), 255, GLOBAL.processing.color(255, 255, 255), 255, (float) 0.5);
        //GLOBAL.gu.drawVGradient(selectionEnd.x + selectionEnd.width, selectionEnd.y, -selectionEnd.width, selectionEnd.height, GLOBAL.processing.color(200, 200, 200), 255, GLOBAL.processing.color(255, 255, 255), 255, (float) 0.5);
        GLOBAL.gu.drawVGradient(selectionEnd.x + selectionEnd.width, selectionEnd.y, -(selectionEnd.x - selectionStart.x+ selectionEnd.width), selectionStart.height, GLOBAL.processing.color(200, 200, 200), 255, GLOBAL.processing.color(255, 255, 255), 255, (float) 0.5);
       
        // Handle start widget dragging.
        if(draggingStart && GLOBAL.processing.mouseX < selectionEnd.x)
        {
            float d = (float)(GLOBAL.processing.mouseX - dragX) / width;
            float tmpV = valStart + d;
            if(tmpV >= 0 && tmpV + size <= 1)
            {
                valStart = tmpV;
            }
            dragX = GLOBAL.processing.mouseX;
        }
        // Handle start widget dragging.
        if(draggingEnd  && GLOBAL.processing.mouseX > (selectionStart.x + selectionStart.width))
        {
            float d = (float)(GLOBAL.processing.mouseX - dragX) / width;
            float tmpV = valEnd + d;
            if(tmpV >= 0 && tmpV + size <= 1)
            {
                valEnd = tmpV;
            }
            dragX = GLOBAL.processing.mouseX;
        }
                
        indexStart = GLOBAL.processing.map(valStart, 0, (float)0.98, 0, (Parser.LIST_ALL.size() - 1));
        GLOBAL.episodeStart = Parser.LIST_ALL.get((int)indexStart);
        
        indexEnd = GLOBAL.processing.map(valEnd, 0, (float)0.98,0, (Parser.LIST_ALL.size() - 1));
        if (indexEnd > (Parser.LIST_ALL.size() - 1) )
        	indexEnd = (Parser.LIST_ALL.size() - 1);
        GLOBAL.episodeEnd = Parser.LIST_ALL.get((int)indexEnd);

		GLOBAL.processing.fill(GLOBAL.colorText);
		GLOBAL.processing.textFont(GLOBAL.tFont,14);
		GLOBAL.processing.textAlign(GLOBAL.processing.LEFT);
		GLOBAL.processing.text(GLOBAL.episodeStart.getName(), x, y + 40);
		GLOBAL.processing.textAlign(GLOBAL.processing.RIGHT);
		GLOBAL.processing.text(GLOBAL.episodeEnd.getName(), x + width, y + 40);
		
        
    }
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void mousePressed()
    {
    	if (selectionStart.mouseOver() ) {
    		int bh = (int)(size * width);
    		if(bh < 10) bh = 10;
    		int x1 = x + (int)(valStart * width);
    		int x2 = x1 + bh;
    		if(GLOBAL.processing.mouseX >= x1 && GLOBAL.processing.mouseX <= x2)
    		{
    			draggingStart = true;
    			dragX = GLOBAL.processing.mouseX;
    			return;
    		}
    	}
    	else if (selectionEnd.mouseOver()) {
    		int bh = (int)(size * width);
    		if(bh < 10) bh = 10;
    		int x1 = x + (int)(valEnd * width);
    		int x2 = x1 + bh;
    		if(GLOBAL.processing.mouseX >= x1 && GLOBAL.processing.mouseX <= x2)
    		{
    			draggingEnd = true;
    			dragX = GLOBAL.processing.mouseX;
    			return;
    		}
    	}
        
    }
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void mouseReleased()
    {
        draggingStart = false;
        draggingEnd = false;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private stuff.
    Widget selectionStart;
    Widget selectionEnd;
    private int dragX;
    private float indexStart;
    private float indexEnd;

}
