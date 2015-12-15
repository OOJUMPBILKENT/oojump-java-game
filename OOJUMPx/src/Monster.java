import java.awt.Graphics;
import java.awt.Image;

public class Monster extends GameObject implements Observer{

	private final static int width = 140;
	private final static int height = 89;
	
	public static String MONSTER_NAME = "MONSTER";
	
	public Monster( Image image){
		super(image, 0, 0, width, height);
	}
	public String getName(){
		return MONSTER_NAME;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSubject(Subject sub) {
		// TODO Auto-generated method stub
		
	}
}
