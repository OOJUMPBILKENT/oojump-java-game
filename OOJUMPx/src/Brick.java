import java.awt.Image;

public abstract class Brick extends GameObject{
	
	private final static int width = 90;
	private final static int height = 23;
	
	public static String BRICK_NAME = "BRICK";
	public Brick(Image image, int Vx, int Vy){
		super(image, Vx, Vy, width, height);
	}
	public String getName(){
		return BRICK_NAME;
	}
	
}
