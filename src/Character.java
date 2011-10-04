import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

//Character object
//      Stores data for a specific character.
public class Character {

        PApplet processing;
        private String name;                       //Character name (all lowercase by convention)
        private PImage icon;                       //Character icon
        private ArrayList<Integer> episodes_s1;    //List of episode numbers character appears (season 1)
        private ArrayList<Integer> episodes_s2;    //List of episode numbers character appears (season 2)
        private ArrayList<Integer> episodes_s3;    //List of episode numbers character appears (season 3)
        private ArrayList<Integer> episodes_s4;    //List of episode numbers character appears (season 4)
        private ArrayList<Integer> episodes_s5;    //List of episode numbers character appears (season 5)
        private ArrayList<Integer> episodes_s6;    //List of episode numbers character appears (season 6)
        
        private ArrayList<Episode> episodes;       //List of all episodes character appears (among all seasons)
        
        private ArrayList<Catchphrase> phrases;		//List of catchphrases the Character is known to say.
        
        public Character (String n){
	        name = n;
	        episodes_s1 = new ArrayList<Integer>();
	        episodes_s2 = new ArrayList<Integer>();
	        episodes_s3 = new ArrayList<Integer>();
	        episodes_s4 = new ArrayList<Integer>();
	        episodes_s5 = new ArrayList<Integer>();
	        episodes_s6 = new ArrayList<Integer>();
	        
	        episodes = new ArrayList<Episode>();
        }
       
        //Add episode # to list of episodes character appears in
        public void addEpisode(int s, int e) {
                //Convert int to Integer
                Integer e2 = new Integer(e);
                
                for (int i=0; i< Parser.LIST_ALL.size(); i++)
                	if( Parser.LIST_ALL.get(i).getSeason() == s &&  Parser.LIST_ALL.get(i).getEpisode() == e) {
                		episodes.add(Parser.LIST_ALL.get(i));
                	}
                		
               
                switch(s) {
                case 1: //If season 1
                		if(!episodes_s1.contains(e2)) { //Does not add episode to list if episode already exists in list.
                			episodes_s1.add(e2);
                		} break;
                case 2: //If season 2
            		if(!episodes_s2.contains(e2)) { 
            			episodes_s2.add(e2);
            		} break;
                case 3: //If season 3
            		if(!episodes_s3.contains(e2)) { 
            			episodes_s3.add(e2);
            		} break;
                case 4: //If season 4
            		if(!episodes_s4.contains(e2)) { 
            			episodes_s4.add(e2);
            		} break;
                case 5: //If season 5
            		if(!episodes_s5.contains(e2)) { 
            			episodes_s5.add(e2);
            		} break;
                case 6: //If season 6
            		if(!episodes_s6.contains(e2)) {
            			episodes_s6.add(e2);
            		} break;
                
                }
                
        }
        
      //Return number of episodes character appears in
        public int getTotalEpisodes(){
    		return episodes_s1.size() + episodes_s2.size() + episodes_s3.size()
        			+ episodes_s4.size() + episodes_s5.size() + episodes_s6.size();
        }
       
        //Return number of episodes character appears in by season
        public int getTotalEpisodesBySeason(int s){
        	switch(s) {
        	case 0: // 0 means all seasons
        		return getTotalEpisodes();
        	case 1:
        		return episodes_s1.size();
        	case 2:
        		return episodes_s2.size();
        	case 3:
        		return episodes_s3.size();
        	case 4:
        		return episodes_s4.size();
        	case 5:
        		return episodes_s5.size();
        	case 6:
        		return episodes_s6.size();
        	}
        	return -1;
        }
        
        //Return the list of episodes character appears
        public ArrayList<Episode> getEpisodes() {
        	return episodes;
        }
       
        //Return character name
        public String getName() {
                return name;
        }
        
        //Add a new catchphrase to phrases arraylist
        public void addPhrase(String p, String r) {
        	if(phrases == null) {
        		phrases = new ArrayList<Catchphrase>();
        	}
        	phrases.add(new Catchphrase(p, r));
        }
        
        //add a new occurrence of a catchphrase (which may or may not already be in the list)
        public void addPhraseOccurrence(String p, String r, int s, int e) {
        	int i = getPhraseIndex(p);
        	if(i == -1) {
        		addPhrase(p, r);
        		i = phrases.size()-1;
        	}
        	
        	phrases.get(i).addToTotals(s, e, 1);
        }
        
        //get index of phrase p
        private int getPhraseIndex(String p) {
        	for(int x=0; x<phrases.size(); ++x) {
        		if(phrases.get(x).getPhrase().equals(p)) {
        			return x;
        		}
        	}

        	return -1;
        }
        
        //Return total number of phrases character has
        public int getTotalPhrases() {
        	return phrases.size();
        }
        
        //Return a phrase bsed on index number
        public Catchphrase getPhrase(int i) {
        	return phrases.get(i);
        }
        
        //FOR TESTING::
        public void printPhrases() {
        	System.out.println(name.toUpperCase());
        	for(int x=0; x<getTotalPhrases(); ++x) {
        		System.out.println("\t" + phrases.get(x).getTotal() + "\t" + phrases.get(x).getPhrase());
        	}
        }
        
        public void setIcon() {
        	icon = GLOBAL.processing.loadImage("images/" +name+ ".jpg");
        }
        
        public PImage getIcon() {
        	return icon;
        }

}
