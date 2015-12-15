import java.awt.Graphics;
import java.awt.Image;

public class MovingBrick extends Brick{
	
	public enum Direction { RIGHT, LEFT};
	private Direction direction;
	public static String MOVING_BRICK_NAME = "MOVING_BRICK";
	
	public MovingBrick(Image image, Direction direction){
		super(image, 5, 0);
		this.direction = direction;
	}
		
	public String getName(){
		return MOVING_BRICK_NAME;
	}
	public Direction getDirection(){
		return direction;
	}
	public void setDirection( Direction direction){
		this.direction = direction;
	}
}
