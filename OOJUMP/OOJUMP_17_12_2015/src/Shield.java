import java.awt.Image;

public class Shield extends Bonus{

	private final static int width = -1;
	private final static int height = -1;
	
	public static String SHIELD_NAME = "SHIELD";
	
	public Shield(Image image) {
		super(image, width, height);
	}
	public String getName(){
		return SHIELD_NAME;
	}
}
