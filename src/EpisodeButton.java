
public class EpisodeButton extends Button{
	
	public Episode episode;

	public EpisodeButton( Episode ep) {
		super();
		episode = ep;
	}
	
	//Override of the action to be performed, we need to set the filter for the episodes
	@Override
	public void doAction() {

		GLOBAL.EPISODE_SELECTED = episode;

		// Add the episode to the list to be analyzed in graphs, add it in 1st position if it is full ( size = 3 )
		if (GLOBAL.episodesSelected.size() < 3) {
			GLOBAL.episodesSelected.add(episode);
		}
		else {
			GLOBAL.episodesSelected.remove(2);
			GLOBAL.episodesSelected.add(0,episode);
		}
		
		//main_class.graphArea.clearGraphs();

		// Create the new graph to be plot
		main_class.graphArea.createEpisodeGraph();
		
		Menu.selectingEpisode = false;
		
		System.out.println("Episodes selected: " + GLOBAL.episodesSelected.toString());
		
		

	}

}
