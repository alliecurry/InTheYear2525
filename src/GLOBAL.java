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
	public static int colorLinesLabelY;
	public static int colorButtonLabel;
	public static int colorBackgroundLayerTwo;
	public static int colorPlotArea;	
	public static int colorStatsArea;
	public static int colorMenuBackground;
	public static int colorIconBackground;
	public static int colorTagCloudBackground;

	public static ColorSwatch COLORS;
	
	////
	//// END COLORS 
	////
	
	/// Var storing selections for graphs
	// what kind of analysis to make: character, season or episode
	public static String ANALYSIS_TYPE; // could be seasons, characters or episodes
	public static int SEASON_SELECTED; // 0 means ALL
	public static Episode EPISODE_SELECTED;
	public static Character CHARACTER_SELECTED;

	/// Layer 1
	// All seasons buttons of the first layer
	public static ArrayList<SeasonButton> allSeasonsButtons = new ArrayList<SeasonButton>();
	// All episodes buttons of the first layer
	public static ArrayList<EpisodeButton> allEpisodesButtons = new ArrayList<EpisodeButton>();
	// All filter buttons of the first layer
	public static ArrayList<FilterButton> allFilterButtons = new ArrayList<FilterButton>();
	
	// Selected list of episodes based on the filter selection
	public static ArrayList<Episode> selectedEpisodesList = new ArrayList<Episode>();
	
	public static ArrayList<CharacterButton> allCharacterButtons = new ArrayList<CharacterButton>();
	public static ArrayList<CharacterButton> allCharacterListButtons = new ArrayList<CharacterButton>();
	
	public static boolean selectedEpisodesListChanged = false;
	
	// Graph to be shown in the graph area, their size is at most 3
	public static ArrayList<Character> charactersSelected = new ArrayList<Character>();
	public static ArrayList<Integer> seasonsSelected = new ArrayList<Integer>();
	public static ArrayList<Episode> episodesSelected = new ArrayList<Episode>();
	
	// Variables storing starting and ending point for characters analysis
	public static Episode episodeStart;
	public static Episode episodeEnd;
	
	// Character analysis: dialogues or catchphrases
	public static boolean CATCHPHRASES_ANALYSIS = false;
	public static boolean WORD_ANALYSIS = false;
	public static boolean STAT_VIEW = false;
	
	//Variable used to determine if parsing transcripts with full dialog (= false), 
	//	or only mapping words (= true)
	public static boolean parseForWordMap = false;
		
	public GLOBAL(PApplet p) {
		processing = p;
	}
	
}
