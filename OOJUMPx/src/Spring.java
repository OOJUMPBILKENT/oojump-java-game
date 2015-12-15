import java.awt.Image;

public class Spring extends Bonus{

	private final static int width = -1;
	private final static int height = -1;
	private final int maxJumpCount;
	
	public static String SPRING_NAME = "SPRING";
	
	public Spring(Image image) {
		super(image, width, height);
		// TODO Auto-generated constructor stub
		maxJumpCount = 20;
	}
	public String getName(){
		return SPRING_NAME;
	}
	public int getMaxJumpCount() {
		return maxJumpCount;
	}

}
