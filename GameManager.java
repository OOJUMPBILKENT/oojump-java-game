import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.Timer;

public class GameManager implements Runnable{
	
	private final boolean GOING_UP = true;
	private final boolean GOING_DOWN = false;
	
	public static int score;
	private CollisionManager collMan;
	private GameMapManager gameMapMan;
	private GameMap map;
	private boolean gameOver;
	//private FileManager fileMan;
	//private SoundManager soundMan;
	//private ViewHighScoreMapManager viewHSMM;
	
	private Timer timer;
	private ActionListener timerAction;
	
	public GameManager(){
		map = new GameMap();
		gameMapMan = new GameMapManager();
		gameMapMan.setMap(map);
		collMan = new CollisionManager();
		collMan.updateMap(map);
		map.getCharacter();
		gameOver = false;
		
		
	}
	public void run(){
		gameLoop();
	}

/*
	public void runGameLoop()
	{
		Thread loop = new Thread()
	    {
	       public void run()
	       {
	          gameLoop();
	       }
	    };
	    loop.start();
	 }
*/
	public void gameLoop(){
		map.getCharacter().setDead(false);
		map.getCharacter().setJumpCount(0);
		
		timerAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				// TODO Auto-generated method stub
				if( !map.getCharacter().isDead()){
					if( map.getCharacter().getJumpCount() < map.getCharacter().getMaxJumpCounter()){
						
						jumpCharacter();
						if( !map.getCharacter().isBoosted()){
							operateObject( GOING_UP);
						}
						map.updateMap();
						gameMapMan.setMap(map);
						map.getCharacter().incrementJumpCount();
					}
					else if(map.getCharacter().getJumpCount() >= map.getCharacter().getMaxJumpCounter()){
						//SoundManager.trampolineHit
						map.getCharacter().setBoosted(false);
						//map.setCharToNormal();
						map.getCharacter().setVy(5);
						downCharacter();
						operateObject( GOING_DOWN);
						map.updateMap();
						gameMapMan.setMap(map);
						map.getCharacter().incrementJumpCount();
					}
				}
				else{
					endGame();
				}
			}
		};
		timer = new Timer(40, timerAction);
		timer.setRepeats(true);
		timer.start();
	}
	
	public void updateScore(){
		
	}
	public void operateObject( boolean goingUp){
		GameObject gameObject = collMan.getCollider(map, goingUp);
		
		
		if( map.getCharacter().getBonus() != null && !map.getCharacter().isBoosted()){
			if( map.getCharacter().getBonus().getName() == Coin.COIN_NAME){
				map.getCharacter().updateScore(((Coin)map.getCharacter().getBonus()).getPoint());
				map.getCharacter().setBonus(null);
			}
			else if( map.getCharacter().getBonus().getName() == Shield.SHIELD_NAME){
				map.getCharacter().setProtected(true);
			}
			else if( map.getCharacter().getBonus().getName() == Jetpack.JETPACK_NAME){
				map.getCharacter().setMaxJumpCounter(((Jetpack)map.getCharacter().getBonus()).getMaxJumpCount());
				map.getCharacter().setVy( ((Jetpack)map.getCharacter().getBonus()).getMoveHeight());
				map.getCharacter().setBoosted(true);
			}
			else if( map.getCharacter().getBonus().getName() == Propeller.PROPELLER_NAME){
				map.getCharacter().setMaxJumpCounter(((Propeller)map.getCharacter().getBonus()).getMaxJumpCount());
				map.getCharacter().setVy( ((Propeller)map.getCharacter().getBonus()).getMoveHeight());
				map.getCharacter().setBoosted(true);
			}
			else if( map.getCharacter().getBonus().getName() == Trampoline.TRAMPOLINE_NAME){
				map.getCharacter().setMaxJumpCounter(((Trampoline)map.getCharacter().getBonus()).getMaxJumpCount());
				map.getCharacter().setBoosted(false);
				map.getCharacter().setJumpCount(0);
				map.getCharacter().setBonus(null);
				SoundManager.trampolineHit.start();
			}
			else if( map.getCharacter().getBonus().getName() == Spring.SPRING_NAME){
				map.getCharacter().setMaxJumpCounter(((Spring)map.getCharacter().getBonus()).getMaxJumpCount());
				map.getCharacter().setBoosted(false);
				map.getCharacter().setJumpCount(0);
				map.getCharacter().setBonus(null);
			}
		}
		else if( gameObject != null && !map.getCharacter().isBoosted()){
			if( gameObject.getName() == Monster.MONSTER_NAME){
				if( !map.getCharacter().isBoosted()){    //!map.getCharacter().isProtected() || !map.getCharacter().isBoosted()){
					map.getCharacter().setDead(true);
					endGame();
				}
				// oyun burda bitecek
			}
			else if(gameObject.getName() == StandardBrick.STANDARD_BRICK_NAME ||gameObject.getName() == MovingBrick.MOVING_BRICK_NAME
						|| gameObject.getName() == BrokenBrick.BROKEN_BRICK_NAME){
				map.getCharacter().setBoosted(false);
				map.getCharacter().setJumpCount(0);
				map.getCharacter().setMaxJumpCounter(15);
				map.getCharacter().setVy(5);		//bunu düzelt		hýzý brick ten çekmeli
				if( gameObject.getName() == BrokenBrick.BROKEN_BRICK_NAME){
					((Brick)gameObject).setRemove(true);
				}
			}
		}
	}
	public void updateHighScores(){	
		if(FileManager.getInstance().checkScore(score))
		{
			CardLayout cardLayout = (CardLayout) MainFrame.mainPanel.getLayout();
			cardLayout.show(MainFrame.mainPanel, "gameOver");
			//MainFrame.gameOverView.askName.setVisible(true);
			//FileManager.getInstance().saveScore(MainFrame.gameOverView.askName.getText(), score);
		}
		else
		{
			CardLayout cardLayout = (CardLayout) MainFrame.mainPanel.getLayout();
			cardLayout.show(MainFrame.mainPanel, "highScores");
		}
			
	}
	public void endGame(){
		score = map.getCharacter().getScore();
		System.out.println( "endgame:" + score);			//@@@@@@@@@@@@@@@@@@@@@@@SCORE
		map.getCharacter().setDead(true);
		gameOver = true;
		timer.stop();
		updateHighScores();
		setToInitial();	
	}
	public void setToInitial(){
		map.initializeObjects();
		map.getCharacter().setScore();
		gameOver = false;
	}
	public boolean isGameOver(){
		return gameOver;
	}
	protected void jumpCharacter(){
		map.getCharacter().updateScore();
		if( map.getCharacter().isBoosted()){
			System.out.println( "boost is called");
			boostCharacter();
		}
		else{
			if( map.getCharacter().getJumpCount() != 0){
				int jumpHeight = (map.getCharacter().getMaxJumpCounter()/map.getCharacter().getJumpCount())*map.getCharacter().getVy();
				if( map.getCharacter().getPosY()>= map.getHeight()/2 -50){
					map.getCharacter().setPosY(map.getCharacter().getPosY()-jumpHeight);
				}
				else{
					for(Brick b : map.getBricks()){
						b.setPosY(b.getPosY()+jumpHeight);
					}
					for( Bonus b : map.getBonuses()){
						b.setPosY(b.getPosY() + jumpHeight);
					}
					for(Monster m : map.getMonsters()){
						m.setPosY(m.getPosY() + jumpHeight);
					}
				}
			}
		}
		map.updateMap();
	}
	public void boostCharacter(){
		if( map.getCharacter().getPosY()>= map.getHeight()/2 -50){
			map.getCharacter().setPosY(map.getCharacter().getPosY()- map.getCharacter().getVy()); //jetpackMoveHeight); Vy yi artýrmalý öncesinde
		}
		else{
			for(Brick b : map.getBricks()){
				b.setPosY(b.getPosY()+map.getCharacter().getVy());
			}
			for( Bonus b : map.getBonuses()){
				b.setPosY(b.getPosY() + map.getCharacter().getVy());
			}
			for( Monster m : map.getMonsters()){
				m.setPosY(m.getPosY() + map.getCharacter().getVy());
			}
		}
	}
	protected void downCharacter(){
		Character character = map.getCharacter();
		character.setPosY(character.getPosY()+(character.getJumpCount()/character.getMaxJumpCounter())*character.getVy());
	}
	public GameMapManager getGameMapManager(){
		return gameMapMan;
	}
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if( e.getKeyCode() == KeyEvent.VK_RIGHT){
	    	 map.getCharacter().setPosX(map.getCharacter().getPosX()+map.getCharacter().getVx());
	    	 map.setChartoRight();
	    	 if( map.getCharacter().getPosX() + map.getCharacter().getWidthDiff() > map.getWidth()){
	    		 map.getCharacter().setPosX(-map.getCharacter().getWidthDiff());
	    	 }
	    	 map.getCharacter().setDirection(Character.MoveDirection.RIGHT);
	     }
	     if( e.getKeyCode() == KeyEvent.VK_LEFT){
	    	 map.getCharacter().setPosX(map.getCharacter().getPosX()-map.getCharacter().getVx());
	    	 map.setCharToLeft();
	    	 if( map.getCharacter().getPosX()+ map.getCharacter().getWidth()-map.getCharacter().getWidthDiff() <= 0){
	    		 map.getCharacter().setPosX(map.getWidth()-map.getCharacter().getWidthDiff());
	    	 }
	    	 map.getCharacter().setDirection(Character.MoveDirection.LEFT);
	     }
	}
}