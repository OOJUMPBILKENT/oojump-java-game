import java.awt.Image;

public class Sprite {
	protected Image objectImage;
	protected int Vx;
	protected int Vy;
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	
	public Sprite(Image image, int Vx, int Vy, int posX, int posY, int width, int height){
		this.objectImage = image;
		this.Vx = Vx;
		this.Vy = Vy;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	public void update(int elapsedTime){
		posX += elapsedTime*Vx;
		posY -= elapsedTime*Vy;
	}
	public void setImage( Image image){
		this.objectImage = image;
	}
	public Image getImage(){
		return objectImage;
	}
	public int getVx(){
		return Vx;
	}
	public void setVx( int Vx){
		this.Vx = Vx;
	}
	public int getVy(){
		return Vy;
	}
	public void setVy( int Vy){
		this.Vy = Vy;
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
}
