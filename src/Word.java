
public class Word {
	
	private String theWord;	//a word
	private int frequency;	//number of times the word appears
	private int season;		//season the word is appearing in
	private int episode;	//episode the word is appearing in
	
	public Word(String w, int s, int e) {
		theWord = w;
		season = s;
		episode = e;
		frequency = 1;
	}
	
	public Word(String w, int s, int e, int f) {
		theWord = w;
		season = s;
		episode = e;
		frequency = f;
	}
	
	//Add one to frequency
	public void addOne() {
		++frequency;
	}
	
	//Add n to frequency
		public void addN(int n) {
			frequency += n;
		}
	
	public String getWord() {
		return theWord;
	}
	
	public int getFreq() {
		return frequency;
	}
	
	public int getSeason() {
		return season;
	}
	
	public int getEpisode() {
		return episode;
	}
	
	

}
