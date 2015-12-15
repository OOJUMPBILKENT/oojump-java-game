import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObject implements Drawable{
	
	
	protected Sprite sprite;
	private String name;
	private boolean remove;
	
	public GameObject(Image image, int Vx, int Vy, int width, int height){
		sprite = new Sprite(image, Vx, Vy, 0, 0, width, height);
		//setName("GAME_OBJECT");
		remove = false;
	}
	public void setImage( Image image){
		sprite.setImage(image);
	}
	public Image getImage(){
		return sprite.getImage();
	}
	public int getVx(){
		return sprite.getVx();
	}
	public void setVx( int Vx){
		sprite.setVx(Vx);
	}
	public int getVy(){
		return sprite.getVy();
	}
	public void setVy( int Vy){
		sprite.setVy(Vy);
	}
	public void setPosX( int posX){
		sprite.setPosX(posX);
	}
	public int  getPosX(){
		return sprite.getPosX();
	}
	public void setPosY( int posY){
		sprite.setPosY(posY);
	}
	public int getPosY(){
		return sprite.getPosY();
	}
	public void setPos( int posX, int posY){
		sprite.setPosX(posX);
		sprite.setPosY(posY);
	}
	public int getWidth(){
		return sprite.getWidth();
	}
	public int getHeight(){
		return sprite.getHeight();
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(getImage(), getPosX(), getPosY(), getWidth(), getHeight(), null);
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRemove() {
		return remove;
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
	
	/*
	protected int posX;
	protected int posY;
	protected Image image;
	protected String name;
	protected int width;
	protected int height;
	
	public GameObject( int posX, int posY, Image image){
		this.name = "GAME_OBJECT";
		this.posX = posX;
		this.posY = posY;
		this.image = image;
		
	}
	public String getName(){
		return name;
	}
	
	public void setImage( Image image){
		this.image = image;
	}
	public Image getImage(){
		return image;
	}
	public void setPosX( int posX){
		this.posX = posX;
	}
	public int  getPosX(){
		return posX;
	}
	public void setPosY( int posY){
		this.posY = posY;
	}
	public int getPosY(){
		return posY;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	*/
}
