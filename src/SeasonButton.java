
public class SeasonButton extends Button{
	
	public int seasonNumber;

	public SeasonButton( int number) {
		super();
		seasonNumber = number;
	}
	
	//Override of the action to be performed, we need to set the filter for the episodes
	@Override
	public void doAction() {

		GLOBAL.SEASON_SELECTED = seasonNumber;
		GLOBAL.ANALYSIS_TYPE = "season";
		GLOBAL.LAYER = 2;
		
		System.out.println("Season selected: " + seasonNumber);

	}

}
