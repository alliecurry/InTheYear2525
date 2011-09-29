import java.util.ArrayList;

import javax.swing.JFrame;

import processing.core.PApplet;
import processing.core.PFont;

// class for global variables and constants
public class GLOBAL {
	
	// Processing obj, use this for getting all the processing functions
	public static PApplet processing;
	
	// Gradient functions
	public static GradientUtils gu;
	
	// Text font
	public static PFont tFont;
	
	// var for storing the current layer 
	public static int LAYER = 2;

	////
	//// COLORS (defined in setupColors function)  
	////
	
	public static int colorToggleOff;
	public static int colorText;
	public static int colorBackgroundLayerOne;
	public static int colorLinesLayerOne;
	public static int colorButtonLabel;
	public static int colorBackgroundLayerTwo;
	public static int colorPlotArea;	
	public static int colorStatsArea;

	
	////
	//// END COLORS 
	////
	
	/// Var storing selections for graphs
	// what kind of analysis to make: character, season or episode
	public static String ANALYSIS_TYPE;
	public static int SEASON_SELECTED; // 0 means ALL
	public static int EPISODE_SELECTED;
	public static String CHARACTER_SELECTED;

	/// Layer 1
	// All seasons buttons of the first layer
	public static ArrayList<SeasonButton> allSeasonsButtons = new ArrayList<SeasonButton>();
	// All episodes buttons of the first layer
	public static ArrayList<Button> allEpisodesButtons = new ArrayList<Button>();
	// All filter buttons of the first layer
	public static ArrayList<FilterButton> allFilterButtons = new ArrayList<FilterButton>();
	
	// Selected list of episodes based on the filter selection
	public static ArrayList<Episode> selectedEpisodesList = new ArrayList<Episode>();
	
	public static ArrayList<CharacterButton> allCharacterButtons = new ArrayList<CharacterButton>();
	public static ArrayList<CharacterButton> allCharacterListButtons = new ArrayList<CharacterButton>();
	
	public static boolean selectedEpisodesListChanged = false;
	
	/// Layer 2
	// Corners of the stats rect
	public static int statX1 = 20; 
	public static int statX2 = 380;
	public static int statY1 = 70;
	public static int statY2 = 190;
	
	//New
	public static ArrayList<String> characterSelected = new ArrayList<String>();
		
	public GLOBAL(PApplet p) {
		processing = p;
	}
	
}
