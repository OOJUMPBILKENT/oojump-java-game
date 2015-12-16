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
	private Image doodlePropellerLeftImage;
	private Image doodlePropellerRightImage;
	private Image doodleJetpackLeftImage;
	private Image doodleJetpackRightImage;
	
	private Image backgroundImage;
	private Image greenBrickImage;
	private Image monsterImage;
	private Image springBrickImage;
	private Image trampolineBrickImage;
	private Image propellerImage;
	private Image jetpackImage;
	private Image topbarImage;
	private Image movingBrickImage;
	private Image brokenBrickImage;
	private Image coinImage;
	
	private final static int WIDTH = 520;
	private final static int HEIGHT = 815;
	private int posYDiff;
	private final int monsterHeightDiff = 90;
	private final int monsterWidthDiff = 20;
	
	
	
	private Random rand;
	
	public GameMap(){
		
		Thread imageThread = new Thread(){
			public void run(){
				doodleRightImage = Toolkit.getDefaultToolkit().getImage("res/images/doodle_right.png");
				doodleLeftImage = Toolkit.getDefaultToolkit().getImage("res/images/doodle_left.png");
				doodleJetpackLeftImage = Toolkit.getDefaultToolkit().getImage("res/images/doodle_jetpack_left.png");
				doodleJetpackRightImage = Toolkit.getDefaultToolkit().getImage("res/images/doodle_jetpack_right.png");
				doodlePropellerLeftImage = Toolkit.getDefaultToolkit().getImage("res/images/doodle_propeller_left.png");
				doodlePropellerRightImage = Toolkit.getDefaultToolkit().getImage("res/images/doodle_propeller_right.png");
				backgroundImage = Toolkit.getDefaultToolkit().getImage("res/images/background.png");
				greenBrickImage = Toolkit.getDefaultToolkit().getImage("res/images/green_platform.png");
				monsterImage = Toolkit.getDefaultToolkit().getImage("res/images/big_blue_monster.png");
				springBrickImage = Toolkit.getDefaultToolkit().getImage("res/images/spring_platform.png");
				trampolineBrickImage = Toolkit.getDefaultToolkit().getImage("res/images/trampoline_platform.png");
				propellerImage = Toolkit.getDefaultToolkit().getImage("res/images/propeller.png");
				jetpackImage = Toolkit.getDefaultToolkit().getImage("res/images/jetpack.png");
				topbarImage = Toolkit.getDefaultToolkit().getImage("res/images/topbar.png");
				movingBrickImage = Toolkit.getDefaultToolkit().getImage("res/images/blue_platform.png");
				brokenBrickImage = Toolkit.getDefaultToolkit().getImage("res/images/broken_brick.png");
				coinImage = Toolkit.getDefaultToolkit().getImage("res/images/coin.png");
			}
		};
		imageThread.run();
		springBrickImage = springBrickImage.getScaledInstance(90, 23, Image.SCALE_DEFAULT);
		trampolineBrickImage = trampolineBrickImage.getScaledInstance(90, 23, Image.SCALE_DEFAULT);
		monsterImage = monsterImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		doodleJetpackLeftImage = doodleJetpackLeftImage.getScaledInstance(100, 97, Image.SCALE_SMOOTH);
		doodleJetpackRightImage = doodleJetpackRightImage.getScaledInstance(100, 97, Image.SCALE_SMOOTH);
		doodlePropellerLeftImage = doodlePropellerLeftImage.getScaledInstance(100, 97, Image.SCALE_SMOOTH);
		doodlePropellerRightImage = doodlePropellerRightImage.getScaledInstance(100, 97, Image.SCALE_SMOOTH);
		
		bricks = new ArrayList<Brick>();
		bonuses = new ArrayList<Bonus>();
		monsters = new ArrayList<Monster>();
		
		posYDiff = 80;
		rand = new Random();
		
		initializeObjects();
	
	
	}
	public void initializeObjects(){
		character = null;
		monsters = new ArrayList<Monster>();
		bonuses = new ArrayList<Bonus>();
		bricks = new ArrayList<Brick>();
		
		int charStartPosX = 200;
		int charStartPosY = 650;
		int brickStartPosY = HEIGHT-100;
		int brickStartPosX = WIDTH/2 - 50;
		int platformChooser = 120;
		int movingBrickChooser = 110;
		int coinChooser = 115;
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
					b.setEmpty(false);
					b.setPos(determinePosX(i), brickStartPosY);
				}
				else if( chooser < movingBrickChooser){
					b = new StandardBrick(trampolineBrickImage, StandardBrick.Kind.TRAMPOLINE);
					b.setEmpty(false);
					b.setPos(determinePosX(i), brickStartPosY);
				}
				else if( chooser < coinChooser){
					if(rand.nextBoolean()){
						b = new MovingBrick( movingBrickImage, MovingBrick.Direction.RIGHT);
						b.setPos(determinePosX(i), brickStartPosY);
					}
					else{
						b = new MovingBrick( movingBrickImage, MovingBrick.Direction.LEFT);
						b.setPos(determinePosX(i), brickStartPosY);
					}
				}
				else{
					b = new StandardBrick(greenBrickImage, StandardBrick.Kind.STANDARD);
					b.setPos(determinePosX(i), brickStartPosY);
					Coin c = new Coin(coinImage);
					c.setPos(b.getPosX() + b.getWidth()/2 - 15, b.getPosY()-30);
					bonuses.add(c);
					b.setEmpty(false);
				}
				
			}
			bricks.add(b);
		}
		int monsterBrick = 10 + rand.nextInt(5);	// 10 is the number of bricks that screen can hold.
													// it needs to be empty for the start
		boolean tryNext = true;
		while(tryNext){
			if(bricks.get(monsterBrick).getName() == StandardBrick.STANDARD_BRICK_NAME 
						&& ((StandardBrick)bricks.get(monsterBrick)).getKind() == StandardBrick.Kind.STANDARD)
				tryNext = false;
		}
		Monster monster = new Monster(monsterImage);
		monster.setPos(bricks.get(monsterBrick).getPosX()- monsterWidthDiff, bricks.get(monsterBrick).getPosY()-monsterHeightDiff);
		bricks.get(monsterBrick).setEmpty(false);
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
			for( int i = 0; i < bonuses.size(); i++){
				if( bonuses.get(i).getPosY() > HEIGHT){
					oldIndexBonus = i;
				}
				else if( bonuses.get(i).isRemove()){
					bonuses.remove(i);
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
			if(  bricks.get(i).isRemove()){
				bricks.remove(i);
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
					b.setEmpty(false);
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
					b.setEmpty(false);
				}
				else{
					if( rand.nextInt(100) > 50){
						Coin coin = new Coin(coinImage);
						coin.setPos(b.getPosX() + b.getWidth()/2 - 15, b.getPosY()-30);
						b.setEmpty(false);
						if( oldIndexBonus == -1){
							bonuses.add(coin);
						}
						else{
						bonuses.set(oldIndexBonus, coin);
						}
					}
				}
				int brokenChooser = rand.nextInt(100);
				if( b.isEmpty() && brokenChooser > 50){
					b = new BrokenBrick(brokenBrickImage);
					b.setPos(rand.nextInt(WIDTH-widthBrick), minHeight);
					b.setEmpty(false);
				}
			}
			else if( chooser < trampolineChooser){
				b = new StandardBrick(springBrickImage, StandardBrick.Kind.SPRING);
				b.setPos(rand.nextInt(WIDTH-widthBrick), minHeight);
				b.setEmpty(false);
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
				b.setEmpty(false);
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
					if(  bricks.get(brickWhereMonsterPlaced).getName() == StandardBrick.STANDARD_BRICK_NAME 
							&& ((StandardBrick)bricks.get(brickWhereMonsterPlaced)).getKind() == StandardBrick.Kind.STANDARD
							&& bricks.get(brickWhereMonsterPlaced).getPosY() < 0 && bricks.get(brickWhereMonsterPlaced).isEmpty()){
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
	public void removeObject(GameObject object, String name){
		if( name == Propeller.PROPELLER_NAME || name == Jetpack.JETPACK_NAME){
			bonuses.remove((Bonus)object);
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
		if( !character.isBoosted()){
			character.setImage(doodleLeftImage);
		}
		else{
			if( character.getBonus().getName() == Propeller.PROPELLER_NAME)
				character.setImage(doodlePropellerLeftImage);
			else if(character.getBonus().getName() == Jetpack.JETPACK_NAME)
				character.setImage(doodleJetpackLeftImage);
		}

	}
//	public void setCharToNormal(){
//		character.setImage(doodleRightImage);
//	}
	public void setChartoRight() {
		if( !character.isBoosted()){
			character.setImage(doodleRightImage);
		}
		else{
			if( character.getBonus().getName() == Propeller.PROPELLER_NAME)
				character.setImage(doodlePropellerRightImage);
			else if(character.getBonus().getName() == Jetpack.JETPACK_NAME)
				character.setImage(doodleJetpackRightImage);
		}
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