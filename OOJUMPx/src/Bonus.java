import java.awt.Graphics;
import java.awt.Image;

public abstract class Bonus extends GameObject{
	
	public static String BONUS_NAME = "BONUS";
	public Bonus( Image image, int width, int height){
		super(image, 0, 0, width, height);
	}
	public String getName(){
		return BONUS_NAME;
	}
}
