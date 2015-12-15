import java.awt.Image;

public class Coin extends Bonus{

	private final static int width = 30;
	private final static int height = 30;
	private int point;
	
	public static String COIN_NAME = "COIN";
	
	public Coin(Image image) {
		super(image, width, height);
		setPoint(10);
	}
	public String getName(){
		return COIN_NAME;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}
}
