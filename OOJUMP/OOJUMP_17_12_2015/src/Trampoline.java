import java.awt.Image;

public class Trampoline extends Bonus{

	private final static int width = -1;
	private final static int height = -1;
	private final int maxJumpCount;
	
	public static String TRAMPOLINE_NAME = "TRAMPOLINE";
	
	public Trampoline(Image image) {
		super(image, width, height);
		// TODO Auto-generated constructor stub
		maxJumpCount = 25;
	}
	public String getName(){
		return TRAMPOLINE_NAME;
	}

	public int getMaxJumpCount() {
		return maxJumpCount;
	}

	
}
