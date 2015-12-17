
import java.awt.Image;

public class Propeller extends Bonus{
	
	private final static int width = 40;
	private final static int height = 27;
	private final int maxJumpCount;
	private final int moveHeight;
	
	public static String PROPELLER_NAME = "PROPELLER";
	
	public Propeller( Image image){
		super(image, width, height);
		maxJumpCount = 150;
		moveHeight = 20;
	}
	public String getName(){
		return PROPELLER_NAME;
	}
	public int getMaxJumpCount() {
		return maxJumpCount;
	}

	public int getMoveHeight() {
		return moveHeight;
	}
}