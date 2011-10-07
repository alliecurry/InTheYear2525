public class ColorSwatch {
	private int index;
	private int[] colors;
	
	public ColorSwatch(int[] c) {
		colors = c;
		index = 0;
	}
	
	public void setColors(int[] c) {
		colors = c;
	}
	
	//Start from beginnng of colors
	public void reset() {
		index = 0;
	}
	
	//Return the next color in the series.
	public int getNextColor() {
		int c = colors[index++];
		
		//Reset index once end of array is met.
		if(index == colors.length) { index  = 0; }
		
		return c;
	}
	
	public int[] getArray() {
		return colors;
	}
	
}
