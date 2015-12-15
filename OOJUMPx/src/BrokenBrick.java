import java.awt.Image;

public class BrokenBrick extends Brick{

	private boolean isBroken;
	public static String BROKEN_BRICK_NAME = "BROKEN_BRICK";
	
	public BrokenBrick(Image image){
		super(image, 0, 0);
		isBroken = false;
	}
	public boolean getBroken(){
		return isBroken;
	}
	public void setBroken( boolean isBroken){
		this.isBroken = isBroken;
	}
	public String getName(){
		return BROKEN_BRICK_NAME;
	}
}
