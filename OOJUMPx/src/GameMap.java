import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;


public class GameMap {
	
	private ArrayList<Brick> bricks;
	private ArrayList<Bonus> bonuses;
	private ArrayList<Monster> monsters;
	private Character character;
	
	private Image doodleRightImage;
	private Image doodleLeftImage;
	private Image backgroundImage;
	private Image greenBrickImage;
	private Image monsterImage;
	private Image springBrickImage;
	private Image trampolineBrickImage;
	private Image propellerImage;
	private Image jetpackImage;
	private Image topbarImage;
	private Image movingBrickImage;
	
	private final static int WIDTH = 520;
	private final static int HEIGHT = 815;
	private int posYDiff;
	private final int monsterHeightDiff = 90;
	private final int monsterWidthDiff = 20;
	
	
	
	private Random rand;
	
	public GameMap(){
		
		Thread imageThread = new Thread(){
			public void run(){
				doodleRightImage = Toolkit.getDefaultToolkit().getImage("doodle_right.png");
				doodleLeftImage = Toolkit.getDefaultToolkit().getImage("doodle_left.png");
				backgroundImage = Toolkit.getDefaultToolkit().getImage("background.png");
				greenBrickImage = Toolkit.getDefaultToolkit().getImage("green_platform.png");
				monsterImage = Toolkit.getDefaultToolkit().getImage("big_blue_monster.png");
				springBrickImage = Toolkit.getDefaultToolkit().getImage("spring_platform.png");
				trampolineBrickImage = Toolkit.getDefaultToolkit().getImage("trampoline_platform.png");
				propellerImage = Toolkit.getDefaultToolkit().getImage("propeller.png");
				jetpackImage = Toolkit.getDefaultToolkit().getImage("jetpack.png");
				topbarImage = Toolkit.getDefaultToolkit().getImage("topbar.png");
				movingBrickImage = Toolkit.getDefaultToolkit().getImage("blue_platform.png");
			}
		};
		imageThread.run();
		springBrickImage = springBrickImage.getScaledInstance(90, 23, Image.SCALE_DEFAULT);
		trampolineBrickImage = trampolineBrickImage.getScaledInstance(90, 23, Image.SCALE_DEFAULT);
		monsterImage = monsterImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		bricks = new ArrayList<Brick>();
		bonuses = new ArrayList<Bonus>();
		monsters = new ArrayList<Monster>();
		
		posYDiff = 80;
		rand = new Random();
		
		initializeObjects();
	
	
	}
	private void initializeObjects(){
		
		int charStartPosX = 200;
		int charStartPosY = 650;
		int brickStartPosY = HEIGHT-100;
		int brickStartPosX = WIDTH/2 - 50;
		int platformChooser = 120;
		int movingBrickChooser = 110;
		int trampolineChooser = 95;
		int springChooser = 90;
		
		character = new Character(doodleRightImage);
		character.setPos(charStartPosX, charStartPosY);
		for( int i = 0; i < 25; i++){
			brickStartPosY -=posYDiff;
			Brick b;
			int chooser = rand.nextInt(platformChooser);
			
			if( i == 0){
				b = new StandardBrick( greenBrickImage, StandardBrick.Kind.STANDARD);
				b.setPos(brickStartPosX, brickStartPosY);
				bricks.add(b);
			}
			else{
				if(chooser < springChooser){
					b = new StandardBrick(greenBrickImage, StandardBrick.Kind.STANDARD);
					b.setPos(determinePosX(i), brickStartPosY);
				}
				else if( chooser < trampolineChooser){
					b = new StandardBrick(springBrickImage, StandardBrick.Kind.SPRING);
					b.setPos(determinePosX(i), brickStartPosY);
				}
				else if( chooser < movingBrickChooser){
					b = new StandardBrick(trampolineBrickImage, StandardBrick.Kind.TRAMPOLINE);
					b.setPos(determinePosX(i), brickStartPosY);
				}
				else{
					if(rand.nextBoolean()){
						b = new MovingBrick( movingBrickImage, MovingBrick.Direction.RIGHT);
						b.setPos(determinePosX(i), brickStartPosY);
					}
					else{
						b = new MovingBrick( movingBrickImage, MovingBrick.Direction.LEFT);
						b.setPos(determinePosX(i), brickStartPosY);
					}
				}
				
			}
			bricks.add(b);
		}
		int monsterBrick = 10 + rand.nextInt(5);	// 10 is the number of bricks that screen can hold.
													// it needs to be empty for the start
		boolean tryNext = true;
		while(tryNext){
			if(bricks.get(monsterBrick).getName() == StandardBrick.STANDARD_BRICK_NAME)
				tryNext = false;
		}
		Monster monster = new Monster(monsterImage);
		monster.setPos(bricks.get(monsterBrick).getPosX()- monsterWidthDiff, bricks.get(monsterBrick).getPosY()-monsterHeightDiff);
		monsters.add(monster);
	}
	public int determinePosX( int i){
		boolean OK = false;
		int posX = -1;
		double diffRatio = 3/4;
		
		int widthBrick = bricks.get(0).getWidth();
		while(!OK){
			posX = rand.nextInt(WIDTH- widthBrick);
			if( posX > bricks.get(i-1).getPosX() + diffRatio*widthBrick || posX < bricks.get(i-1).getPosX() - diffRatio*widthBrick){
				OK = true;
			}
		}
		return posX;
	}
	public ArrayList<Brick> getBricks(){
		return bricks;
	}
	public ArrayList<Monster> getMonsters(){
		return monsters;
	}
	public ArrayList<Bonus> getBonuses(){
		return bonuses;
	}
	public Character getCharacter(){
		return character;
	}
	public void updateMap(){
		
		int platformChooser = 120;
		int movingBrickChooser = 110;
		int trampolineChooser = 95;
		int springChooser = 90;
		
		int bonusChooser = 100;
		int jetpackChooser = 20;
		int propellerChooser = 80;
		
		moveBricks();
		moveCharacterHorizontal();
		
		int oldIndexBonus = -1;
		if( bonuses.size() > 0){
			for( Bonus b : bonuses){
				if( b.getPosY() > HEIGHT){
					oldIndexBonus = bonuses.indexOf(b);
				}
			}
		}
		
		int minHeight = bricks.get(0).getPosY();
		int oldIndex = -1;
		for( int i = 0; i < bricks.size(); i++){
			if( bricks.get(i).getPosY() > HEIGHT){
				oldIndex = i;
			}
			if( bricks.get(i).getPosY() < minHeight){
				minHeight = bricks.get(i).getPosY();
			}
		}
		if( oldIndex != -1){
			Brick b;
			int chooser = rand.nextInt(platformChooser);
			minHeight = minHeight-posYDiff;
			int widthBrick = bricks.get(0).getWidth();
			
			
			if( chooser < springChooser){
				b = new StandardBrick(greenBrickImage,  StandardBrick.Kind.STANDARD);
				b.setPos(rand.nextInt(WIDTH-widthBrick), minHeight);
				chooser = rand.nextInt(bonusChooser);
				if( chooser < jetpackChooser){
					Bonus jet = new Jetpack(jetpackImage);
					jet.setPos( b.getPosX() + widthBrick/2 - jet.getWidth()/2, b.getPosY()-jet.getHeight());
					if( oldIndexBonus == -1){
						bonuses.add(jet);
					}
					else{
						bonuses.set(oldIndexBonus,jet);
					}
				}
				else if( chooser >propellerChooser){
					Bonus propeller = new Propeller( propellerImage);
					propeller.setPos(b.getPosX() + widthBrick/2 - propeller.getWidth()/2, b.getPosY() - propeller.getHeight());
					if( oldIndexBonus == -1){
						bonuses.add(propeller);
					}
					else{
					bonuses.set(oldIndexBonus, propeller);
					}
				}
			}
			else if( chooser < trampolineChooser){
				b = new StandardBrick(springBrickImage, StandardBrick.Kind.SPRING);
				b.setPos(rand.nextInt(WIDTH-widthBrick), minHeight);
			}
			else if( chooser < movingBrickChooser){
				if( rand.nextBoolean()){
					b = new MovingBrick(movingBrickImage, MovingBrick.Direction.RIGHT);
					b.setPos(rand.nextInt(WIDTH-widthBrick), minHeight);
				}
				else{
					b = new MovingBrick( movingBrickImage, MovingBrick.Direction.LEFT);
					b.setPos(rand.nextInt(WIDTH-widthBrick), minHeight);
				}
				
			}	
			else{
				b = new StandardBrick(trampolineBrickImage, StandardBrick.Kind.TRAMPOLINE);
				b.setPos(rand.nextInt(WIDTH-widthBrick), minHeight);
			}
			bricks.set(oldIndex, b);
		}
		
		///this part for the monster
		int brickWhereMonsterPlaced = 0;
		for( int i = 0; i < monsters.size(); i++){
			if( monsters.get(i).getPosY() > HEIGHT){
				boolean tryNext = true;
				while( tryNext){
					brickWhereMonsterPlaced = rand.nextInt(bricks.size());
					if( bricks.get(brickWhereMonsterPlaced).getPosY() < 0 && bricks.get(brickWhereMonsterPlaced).getName() == StandardBrick.STANDARD_BRICK_NAME){
						tryNext = false;
					}
				}	
					monsters.get(i).setPosX(bricks.get(brickWhereMonsterPlaced).getPosX() - monsterWidthDiff);
					monsters.get(i).setPosY(bricks.get(brickWhereMonsterPlaced).getPosY() - monsterHeightDiff);
			}
		}
		int widthBrick = bricks.get(0).getWidth();
		for( Brick b : bricks){
			if( b.getPosY() < 0 &&(b.getPosY() == bricks.get(brickWhereMonsterPlaced).getPosY() + posYDiff)){
				boolean OK = false;
				int posX = -1;
				while(!OK){
					posX = rand.nextInt(WIDTH - widthBrick);
					if( posX + 3*widthBrick/4 <= bricks.get(brickWhereMonsterPlaced).getPosX() || posX >= bricks.get(brickWhereMonsterPlaced).getPosX() + 3*widthBrick/4){
						OK = true;
					}
				}
				b.setPosX(posX);		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ dene, çalýþmýyor olabilir
			}
		
		}
	}
	
	private  void updateDirections(){
		for( Brick b : bricks){
			if( b.getName() == MovingBrick.MOVING_BRICK_NAME){
				if( ((MovingBrick)b).getDirection() == MovingBrick.Direction.RIGHT){
					if( b.getPosX() + b.getWidth() >= WIDTH){
						((MovingBrick)b).setDirection( MovingBrick.Direction.LEFT);
					}
				}
				if( ((MovingBrick)b).getDirection() == MovingBrick.Direction.LEFT){
					if( b.getPosX() <= 0){
						((MovingBrick)b).setDirection(MovingBrick.Direction.RIGHT);
					}
				}
			}
		}
	}
	protected void moveBricks(){
		updateDirections();
		for( Brick b : bricks){
			if( b.getName() == MovingBrick.MOVING_BRICK_NAME){
				if( ((MovingBrick)b).getDirection() == MovingBrick.Direction.RIGHT){
					((MovingBrick)b).setPosX(b.getPosX() + ((MovingBrick)b).getVx());
				}
				else if( ((MovingBrick)b).getDirection() == MovingBrick.Direction.LEFT){
					((MovingBrick)b).setPosX(b.getPosX() - ((MovingBrick)b).getVx());
				}
			}
		}
	}
	protected void moveCharacterHorizontal(){
		if( character.getDirection() == Character.MoveDirection.RIGHT){
			character.setPosX(character.getPosX() + 3);
		}
		else if( character.getDirection() == Character.MoveDirection.LEFT){
			character.setPosX(character.getPosX() - 3);
		}
	}
	public Image getBackgroundImage(){
		return backgroundImage;
	}
	public Image getTopBarImage(){
		return topbarImage;
	}
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}
	public void setCharToLeft() {
		character.setImage(doodleLeftImage);
	}
	public void setChartoRight() {
		character.setImage(doodleRightImage);
	}
}



/*		SOUND MANAGER
Thread soundThread = new Thread(){
public void run(){
	
	try{
		File soundFile = new File("trampoline.wav");
	    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

	    // load the sound into memory (a Clip)
	    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
	    trampolineHit = (Clip) AudioSystem.getLine(info);
	    trampolineHit.open(sound);
	}catch(IOException e){
		e.printStackTrace();
	}
	catch(UnsupportedAudioFileException e){
		e.printStackTrace();
	} 
	catch (LineUnavailableException e) {
		e.printStackTrace();
	}
}
};

imageThread.run();
soundThread.run();
*/