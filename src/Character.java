import java.util.ArrayList;

import processing.core.PApplet;

//Character object
//      Stores data for a specific character.
public class Character {

        PApplet processing;
        private String name;                                    //Character name (all lowercase by convention)
        private ArrayList<Integer> episodes_s1;    //List of episode numbers character appears (season 1)
        private ArrayList<Integer> episodes_s2;    //List of episode numbers character appears (season 2)
        private ArrayList<Integer> episodes_s3;    //List of episode numbers character appears (season 3)
        private ArrayList<Integer> episodes_s4;    //List of episode numbers character appears (season 4)
        private ArrayList<Integer> episodes_s5;    //List of episode numbers character appears (season 5)
        private ArrayList<Integer> episodes_s6;    //List of episode numbers character appears (season 6)
       
        public Character (String n){
	        name = n;
	        episodes_s1 = new ArrayList<Integer>();
	        episodes_s2 = new ArrayList<Integer>();
	        episodes_s3 = new ArrayList<Integer>();
	        episodes_s4 = new ArrayList<Integer>();
	        episodes_s5 = new ArrayList<Integer>();
	        episodes_s6 = new ArrayList<Integer>();
        }
       
        //Add episode # to list of episodes character appears in
        public void addEpisode(int s, int e) {
                //Convert int to Integer
                Integer e2 = new Integer(e);
               
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
       
        //Return character name
        public String getName() {
                return name;
        }
}
