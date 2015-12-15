import java.awt.Graphics;
import java.awt.Image;

public class Jetpack extends Bonus{
	
	private final static int width = 35;
	private final static int height = 51;
	private final int maxJumpCount;
	private final int moveHeight;
	
	public static String JETPACK_NAME = "JETPACK";
	
	public Jetpack( Image image){
		super(image, width, height);
		maxJumpCount = 200;
		moveHeight = 30;
	}
	public String getName(){
		return JETPACK_NAME;
	}
	public int getMaxJumpCount() {
		return maxJumpCount;
	}

	public int getMoveHeight() {
		return moveHeight;
	}
}
