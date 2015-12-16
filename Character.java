import java.awt.Image;
import java.util.Observer;

public class Character extends GameObject implements Subject{
	
	private final static int width = 100;
	private final static int height = 97;
	
	public enum MoveDirection{LEFT, NONE, RIGHT};
	private boolean boosted;
	private Bonus bonus;
	private MoveDirection direction;
	private boolean isProtected;
	private int jumpCount;
	private int maxJumpCounter;
	private int score;
	
	private final int widthDiff = 25;
	private final int heightDiff = 90;
	// private int horMvmnt;    Vx olarak kullan
	private boolean dead;
	public static String CHARACTER_NAME= "CHARACTER";
	
	public Character(Image image){
		super(image, 8, 5, width, height);
		boosted = false;
		bonus = null;
		direction = MoveDirection.NONE;
		setProtected(false);
		setDead(false);
		jumpCount = 0;
		maxJumpCounter = 15;
		score = 0;
	}
	public int getScore(){
		return score;
	}
	public void setScore(){
		score = 0;
	}
	public void updateScore(){
		score+= sprite.getVy();
	}
	public void updateScore( int point){
		score+= point;
	}
	public String getName(){
		return CHARACTER_NAME;
	}
	public boolean isBoosted(){
		return boosted;
	}
	public void setBoosted( boolean boosted){
		this.boosted = boosted;
	}
	public void setBonus( Bonus bonus){
		this.bonus = bonus;
	}
	public Bonus getBonus(){
		return bonus;
	}
	public MoveDirection getDirection(){
		return direction;
	}
	public void setDirection( MoveDirection direction){
		this.direction = direction;
	}
	public int getWidthDiff(){
		return widthDiff;
	}
	public int getHeightDiff(){
		return heightDiff;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public boolean isProtected() {
		return isProtected;
	}
	public void setProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}
	public int getJumpCount() {
		return jumpCount;
	}
	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}
	public int getMaxJumpCounter() {
		return maxJumpCounter;
	}
	public void setMaxJumpCounter(int maxJumpCounter) {
		this.maxJumpCounter = maxJumpCounter;
	}
	public void incrementJumpCount() {
		jumpCount++;
	}
	@Override
	public void register(Observer obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void unregister(Observer obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getUpdate(Observer obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
