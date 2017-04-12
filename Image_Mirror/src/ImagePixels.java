public class ImagePixels {
	private int[][] Pixels;
	private char mOption;
	
	
	public ImagePixels(int[][] pixels, char mOption) {
		super();
		Pixels = pixels;
		this.mOption = mOption;
	}


	public int[][] getPixels() {
		return Pixels;
	}


	public char getmOption() {
		return mOption;
	}
}
