import java.awt.Image;

public class StandardBrick extends Brick{
	
	public enum Kind{ STANDARD, SPRING, TRAMPOLINE};	//because of the transparency problem
	private Kind kind;
	
	private int stringStartPosX = 7;
	private int stringEndPosX = 22;
	private int trampolineStartPosX = 12;
	private int trampolineEndPosX = 48;
	
	public static String STANDARD_BRICK_NAME = "STANDARD_BRICK";
	
	public StandardBrick(Image image, Kind kind){
		super(image, 0, 0);
		this.kind = kind;
	}
	public String getName(){
		return STANDARD_BRICK_NAME;
	}
	public Kind getKind(){
		return kind;
	}
	public int getStringStartPosX() {
		return stringStartPosX;
	}
	public void setStringStartPosX(int stringStartPosX) {
		this.stringStartPosX = stringStartPosX;
	}
	public int getStringEndPosX() {
		return stringEndPosX;
	}
	public void setStringEndPosX(int stringEndPosX) {
		this.stringEndPosX = stringEndPosX;
	}
	public int getTrampolineStartPosX() {
		return trampolineStartPosX;
	}
	public void setTrampolineStartPosX(int trampolineStartPosX) {
		this.trampolineStartPosX = trampolineStartPosX;
	}
	public int getTrampolineEndPosX() {
		return trampolineEndPosX;
	}
	public void setTrampolineEndPosX(int trampolineEndPosX) {
		this.trampolineEndPosX = trampolineEndPosX;
	}
}
