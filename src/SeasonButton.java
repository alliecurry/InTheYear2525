
public class SeasonButton extends Button{
	
	public Integer seasonNumber;

	public SeasonButton( int number) {
		super();
		seasonNumber = new Integer(number);
	}
	
	//Override of the action to be performed, we need to set the filter for the episodes
	@Override
	public void doAction() {
		
		if (GLOBAL.seasonsSelected.contains(seasonNumber))
			return;

		// Add the season to the list to be analyzed in graphs, add it in 1st position if it is full ( size = 3 )
		if (GLOBAL.seasonsSelected.size() < 3) {
			GLOBAL.seasonsSelected.add(seasonNumber);
			GLOBAL.SEASON_SELECTED = seasonNumber;
		}
		else {
			GLOBAL.seasonsSelected.remove(2);
			GLOBAL.seasonsSelected.add(0,seasonNumber);
			GLOBAL.SEASON_SELECTED = seasonNumber;
		}

		Menu.selectingSeason = false;

		intheyear2525.graphArea.createSeasonGraph();
		
	}

}
