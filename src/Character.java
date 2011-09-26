import java.util.ArrayList;

import processing.core.PApplet;

//Character object 
//	Stores data for a specific character.
public class Character {

	PApplet processing;
	private String name;					//Character name (all lowercase by convention)
	private ArrayList<Integer> episodes;	//List of episode numbers character appears
	
	
	public Character (PApplet parent, String n){
		this.processing = parent;
		name = n;
		episodes = new ArrayList<Integer>();
	}
	
	//Add episode # to list of episodes character appears in
	public void addEpisode(int e) {
		//Convert int to Integer
		Integer i = new Integer(e);
		
		//Does not add episode to list if episode already exists in list.
		if(episodes.contains(i)) {
			return;	
		}
		episodes.add(i);
	}
	
	//Return number of episodes character appears in
	public int getTotalEpisodes(){
		return episodes.size();
	}
	
	//Return character name
	public String getName() {
		return name;
	}
}
