import java.util.ArrayList;

import processing.core.PApplet;

//Object containing episode information
public class Episode {
        
    PApplet processing;
    private int episode;        //episode number
    private int season;         //season number
    private String name;        //episode name
    private ArrayList<String> chars;    //list of characters in episode
    

    public Episode(int e, int s, String n) {
            episode = e;
            season = s;
            name = n;
            chars = new ArrayList<String>();
    }
    
    //Add a character to the list of characters
    //  Checks to make sure character is not already in list
    public void addCharacter(String c)
    {
        //ignore characters already added to ArrayList
        if(chars.indexOf(c) < 0) {
                chars.add(c);
                //System.out.println("Character " + c + " added");
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
    
    //return list of characters
    public ArrayList<String> getChars() {
        return chars;
    }

}
