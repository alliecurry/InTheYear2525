import java.util.ArrayList;

import processing.core.*;
import wordcram.*;
import wordcram.text.StopWords;

public class TagCloud extends Widget{
	
	public ArrayList<wordcram.Word> wordList;
	public wordcram.Word[] w;
	
	public WordCram wc;
	PGraphics buffer;
	
	public int plotWidth;
	public int infoWidth = 100;
	
	public Character character;
	
	public TagCloud(Character c, int xValue, int yValue, int WEIGHT, int HEIGHT) {
	
		x = xValue;
		y = yValue;
		
		width = WEIGHT;
		height = HEIGHT;
				
		plotWidth = width - infoWidth; // Need space for icons and stat
		
		character = c;
		
		wordList = new ArrayList<wordcram.Word>();
		
		setTagCloud();

	}
	
	public void setTagCloud() {
		int i = Parser.findCharacter(character.getName()); //Find index of the character based on String name.
		//The above is a static method.

		//You need to create a new ArrayList<Word> 
		// The following method will populate it with all the words said from season s1 episode e1, through (and including) season s2, episode e2
//		    To clarify,if you want the words from season 1 episode 1 only, [s1=1, e1 =1, s2=1, s2=1]
		//   if you want the words from season 2 episode 3 through season 4 episode 6 : [s1=2 e1=3 s2=4 e2=6]
		ArrayList<Word> myList = Parser.ALL_CHARACTERS.get(i).getWordRange(GLOBAL.episodeStart.getSeason(), GLOBAL.episodeStart.getEpisode(), GLOBAL.episodeEnd.getSeason(), GLOBAL.episodeEnd.getEpisode());

		//Now you have myList (ordered by weight! biggest first! yay!)... so you need to decide how many words you want to display on the map. make a variable for this...
		int max = 100;  //if we want to display 50 words.
		//Why? because the list could contain hundreds of words! I don't know how big the treeMap can be, but
		//I figure you may need a limit.

		System.out.println(myList.size());
		
		if(myList.size() == 0) {
			w = new wordcram.Word[1];
			w[0] = new wordcram.Word("No Words Said in Range", 1);
			max = 1;
		}
			
		else { 
			//Set max to a lower number if array size is less than max
			if(myList.size() < max) { max = myList.size(); }
			w = new wordcram.Word[max];
			
			//Parse your new ArrayList
			for(int x=0; x<max; ++x) {
				
				String aWord = myList.get(x).getWord();  
				int itsWeight = myList.get(x).getWeight();
				
				wordList.add( new wordcram.Word(aWord,itsWeight) );
				//System.out.println("Added Word " + aWord + " - Weight " + itsWeight);
				
				w[x] = new wordcram.Word(aWord,itsWeight);
				
			}
		}

		buffer = GLOBAL.processing.createGraphics(plotWidth, height, GLOBAL.processing.JAVA2D);
		buffer.beginDraw();
		buffer.background(GLOBAL.colorTagCloudBackground);
				
		  wc = new WordCram(GLOBAL.processing)
		  .fromWords(w)
				//.withColors(color(255,0,0), color(0), color(0,0,255)) // red, black, and blue
		  .withCustomCanvas(buffer)
		 .withColors(GLOBAL.COLORS.getArray())
				    .sizedByWeight(6,60).withWordPadding(2).withAngler(Anglers.horiz())
				    .withPlacer(Placers.centerClump())
				    .maxNumberOfWordsToDraw(max)
				    .withStopWords(StopWords.ENGLISH)
				    .maxAttemptsToPlaceWord(10000);
	}
	
	public void draw() {
		
		GLOBAL.processing.noStroke();
		GLOBAL.processing.rectMode(GLOBAL.processing.CORNERS);
		GLOBAL.processing.fill(GLOBAL.colorPlotArea);
		GLOBAL.processing.rect( x,y, x + plotWidth, y + height);
		GLOBAL.processing.fill(GLOBAL.processing.color(255));

		if (wc.hasMore()) {  

			// draw the progress bar
			//float progress = wc.getProgress();
			//drawProgressBar(progress);
			//drawProgressText(progress);

			wc.drawNext();
			
		}
		else {

			buffer.endDraw();
			GLOBAL.processing.image(buffer, x, y);

			//System.out.println(wc.getSkippedWords().length);

		}

		// Draw icon and info		
		GLOBAL.processing.fill(GLOBAL.colorText);
		GLOBAL.processing.textFont(GLOBAL.tFont,16);
		GLOBAL.processing.textAlign(GLOBAL.processing.CENTER);
		GLOBAL.processing.text(character.getName(), x + width - 40, y + 20);

		if (character.getIcon() != null)
			GLOBAL.processing.image( character.getIcon(), x + width - 80, y + 30, 80, 80);
		
	}
	
	
	public void changePosition(int xValue, int yValue, int w, int l) {
		
		x = xValue;
		y = yValue;
		
		width = w;
		height = l;
		
		plotWidth = width - infoWidth;
		
		buffer.width = plotWidth;
		buffer.height = height;
		
		setTagCloud();
		
	}

}