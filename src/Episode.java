import java.util.ArrayList;
import java.util.HashMap;
import processing.core.PApplet;

//Object containing episode information
public class Episode {
        
    PApplet processing;
    private int episode;        //episode number
    private int season;         //season number
    private String name;        //episode name
    private String airdate;		//"MM/DD/YYYY"
    
    private ArrayList<Character> chars;    //list of characters in episode
    private HashMap<Character, Integer> numberOfLinesPerCharacter; // hash map containing for each character the number of lines he/she speak
    
    
    public Episode(int e, int s, String n, String a) {
            episode = e;
            season = s;
            name = n;
            airdate = a;
            
            chars = new ArrayList<Character>();
            numberOfLinesPerCharacter = new HashMap<Character, Integer>();
    }
    
    //Add a character to the list of characters
    //  Checks to make sure character is not already in list
    public void addCharacter(Character c)
    {
    	
    	Character foundCharacter = null; // the character, if already present in the database
    	
    	for(int i=0; i<chars.size(); i++) {
    		if (chars.get(i).getName().equals(c.getName())) {
    			foundCharacter = chars.get(i);
    			break;
    		}
    	}
        //ignore characters already added to ArrayList
//        if(chars.indexOf(c) < 0) {
    	if (foundCharacter == null) {
                chars.add(c);
                //System.out.println("Character " + c + " added");
            
                // If new, add character and #lines = 1
                numberOfLinesPerCharacter.put(c, new Integer(1));
//            	System.out.println(c.getName() + "lines: " + numberOfLinesPerCharacter.get(c).intValue());
                
        }
        else {
        	// If already added, increment number of lines of that character        	
	        	numberOfLinesPerCharacter.put(foundCharacter, new Integer(numberOfLinesPerCharacter.get(foundCharacter).intValue() +1));
//	        	System.out.println( foundCharacter.getName() +" lines: " + numberOfLinesPerCharacter.get(foundCharacter).intValue());
        	
        }

        
    }
    
    
    //GET METHODS ---------------------------------------------------
    
    //Return episode number
    public int getEpisode() {
        return episode;
    }
    
    //Return season number
    public int getSeason() {
        return season;
    }
    
    //return episode name
    public String getName() {
        return name;
    }
    
    //return air date
    public String getAirDate() {
    	return airdate;
    }
    
    //get network the episode aired on
    public String getNetwork() {
    	if(season < 5) { return "FOX"; }
    	return "Comedy Central";
    }
    
    //return list of characters
    public ArrayList<Character> getChars() {
        return chars;
    }
    
    // Return the number of lines of the character in this episode
    public int getNumberOfLinesPerCharacter( Character c ) {
       	
    	for(int i=0; i<chars.size(); i++) {
    		if (chars.get(i).getName().equals(c.getName())) {
//    	    	System.out.println(numberOfLinesPerCharacter.get(chars.get(i)).intValue());
    	    	return numberOfLinesPerCharacter.get(chars.get(i)).intValue();
    		}
    	}
    	
    	return 0; // if not found, he/she speaks 0 lines in this episode
    	
    }
    

}
