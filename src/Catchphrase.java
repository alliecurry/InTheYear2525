import java.util.HashMap;


public class Catchphrase {
	
	private String thePhrase;
	
	HashMap<String, Integer> episodeTotals;	//Key: season#_episode#  
											//Value: Number of times phrase is said
	
	int[] seasonTotals = {0,0,0,0,0,0};		//Total number of times catchphrase is said in season
											//index = season# - 1
	
	public Catchphrase(String p) {
		thePhrase = p;
	}
	
	//Add the number n to total count in season s, episode e
	public void addToTotals(int s, int e, int n) {
		
		//Add n to hashmap
		String key = Integer.toString(s) + "_" + Integer.toString(e);
		
		Integer val = episodeTotals.get(key);
		if(val == null) { val = n; }
		else { val += n; }
		
		episodeTotals.put(key, val);
		
		//Add n to int array
		seasonTotals[s-1] += n;
	}
	
	//Get total number for season s, episode e
	public int getTotalEpisode(int s, int e) {
		String key = Integer.toString(s) + "_" + Integer.toString(e);
		Integer val = episodeTotals.get(key);
		
		if(val == null) { return 0; }
		
		return val.intValue();
	}
	
	//Get total number for season s
	public int getTotalSeason(int s) {
		return seasonTotals[s-1];
	}
	
	//Get total number over all seasons
	public int getTotal() {
		int val = 0;
		
		for(int x=0; x<6; ++x) {
			val += seasonTotals[x];
		}
		
		return val;
	}
	
}
