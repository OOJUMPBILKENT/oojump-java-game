import java.awt.Image;

public class BrokenBrick extends Brick{

	public static String BROKEN_BRICK_NAME = "BROKEN_BRICK";
	
	public BrokenBrick(Image image){
		super(image, 0, 0);
	}
	public String getName(){
		return BROKEN_BRICK_NAME;
	}
}
