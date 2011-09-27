import processing.core.*;

public class FilterButton extends Button {
	
	public int seasonFilter;

	public FilterButton( int filter) {
		super();
		seasonFilter = filter;
	}
	
	//Override of the action to be performed, we need to set the filter for the episodes
	@Override
	public void doAction() {
		switch(seasonFilter) {
		//ALL
		case 0: GLOBAL.selectedEpisodesList = Parser.LIST_ALL;
		break;
		case 1: GLOBAL.selectedEpisodesList = Parser.LIST_S1;
		break;
		case 2: GLOBAL.selectedEpisodesList = Parser.LIST_S2;
		break;
		case 3: GLOBAL.selectedEpisodesList = Parser.LIST_S3;
		break;
		case 4: GLOBAL.selectedEpisodesList = Parser.LIST_S4;
		break;
		case 5: GLOBAL.selectedEpisodesList = Parser.LIST_S5;
		break;
		case 6: GLOBAL.selectedEpisodesList = Parser.LIST_S6;
		break;
		default:
			break;
		}

		System.out.println("switched to " + seasonFilter);
	}
	
}
