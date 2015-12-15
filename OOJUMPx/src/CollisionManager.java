import java.util.ArrayList;

public class CollisionManager {

	private ArrayList<Brick> bricks;
	private ArrayList<Bonus> bonuses;
	private ArrayList<Monster> monsters;
	private Character character;
	
	private GameMap map;
	
	
	public CollisionManager(){
		map = null;
	}
	public GameObject getCollider( GameMap map, boolean goingUp){
		updateMap(map);
		if( character.getPosY() > map.getHeight()){
			map.getCharacter().setDead(true);
		}
		else{
			
			Monster monster = collideMonster();
			Brick brick = collideBrick(goingUp);
			Bonus bonus = collideBonus();
			
			if( goingUp){
				if( !character.isBoosted()){
					if( bonus != null){
						System.out.println( "1 BONUS " + bonus.getName());
						return bonus;
					}
					else if( monster != null){
						System.out.println( "1 MONSTER" + monster.getName());
						return monster;
					}
					
				}
			}
			else{
				if( !character.isBoosted()){
					if( bonus != null){
						System.out.println("2 BONUS" +  bonus.getName());
						return bonus;
					}
					else if( monster != null){
						System.out.println( "2 MONSTER" +monster.getName());
						return monster;
					}
					else if( brick != null){
						System.out.println("2 BRICK" + brick.getName());
						return brick;
					}
				}
				System.out.println( "RETRU N BRICK");
				return brick;
			}
		}
		System.out.println( "RETURN NULL");
		return null;
	}
	public Monster collideMonster(){
		
		for( Monster monster : monsters){
			if( ((character.getPosX()+ character.getWidth() -character.getWidthDiff()>= monster.getPosX() 
					&& character.getPosX()+ character.getWidthDiff() <= monster.getPosX() + monster.getWidth()))
					&&( character.getPosY() <= monster.getPosY() + monster.getHeight()-character.getHeightDiff()
							&& character.getPosY() + character.getHeight() >= monster.getPosY())
					){
				
				map.getCharacter().setDead(true);
				System.out.println( "dead");
				return monster;
				
			}
		}
		return null;
	}
	public Bonus collideBonus(){
		
		for( Bonus bonus : bonuses){
			if( ( ( (int)character.getPosY() <= (int)bonus.getPosY() + bonus.getHeight() +5 ) &&	( (int)character.getPosY() >= (int)bonus.getPosY() + bonus.getHeight() -5 ) )
					&& ( character.getPosX() + character.getWidth() >= bonus.getPosX() && character.getPosX() <= bonus.getPosX() + bonus.getWidth()) 
					){
				map.getCharacter().setBonus(bonus);
				if( bonus.getName() == Jetpack.JETPACK_NAME){
					map.getCharacter().setBonus(bonus);
					map.getCharacter().setBoosted(true);
					System.out.println( "jetpack");
				}
				else if( bonus.getName() == Propeller.PROPELLER_NAME){
					map.getCharacter().setBonus(bonus);
					map.getCharacter().setBoosted(true);
					System.out.println( "propeller");
				}
				return bonus;
			}
		}
		return null;
	}
	public Brick collideBrick(boolean goingUp){
		if( !goingUp){
			for( Brick b : bricks){
				if( b.getName() == StandardBrick.STANDARD_BRICK_NAME){		// NORMAL BRICK
					if( ((StandardBrick)b).getKind() == StandardBrick.Kind.SPRING){		//SPRING BRICK
						
						if(		(character.getPosX()+ character.getWidth() -character.getWidthDiff() >= b.getPosX() && character.getPosX()+character.getWidthDiff() <= b.getPosX() + b.getWidth())
							&&(b.getPosY() == character.getPosY()+ character.getHeight() || 
											((character.getPosY()+ character.getHeight() -10 <= b.getPosY() && character.getPosY()+character.getHeight() +10 >= b.getPosY())
									))
									){
							
							if( character.getPosX() + character.getWidth() >= b.getPosX() + ((StandardBrick)b).getStringStartPosX() && character.getPosX() <= b.getPosX() + ((StandardBrick)b).getStringEndPosX()){
								map.getCharacter().setBonus(new Spring(null));
									System.out.println( "spring");
									
							}
							return b;
						}
					}
					else if ( ((StandardBrick) b).getKind() == StandardBrick.Kind.TRAMPOLINE){			// TRAMPOLINE BRICK
						if(		(character.getPosX()+ character.getWidth() -character.getWidthDiff() >= b.getPosX() 
								&& character.getPosX()+character.getWidthDiff() <= b.getPosX() + b.getWidth())
							&&(b.getPosY() == character.getPosY()+ character.getHeight()
							|| (character.getPosY() + character.getHeight() -10 <= b.getPosY() 
							&& character.getPosY() + character.getHeight() +10 >= b.getPosY())
									)
									){
							if( character.getPosX() + character.getWidth() >= b.getPosX() + ((StandardBrick) b).getTrampolineStartPosX() 
											&& character.getPosX() <= b.getPosX() + ((StandardBrick) b).getTrampolineEndPosX()){
								map.getCharacter().setBonus(new Trampoline(null));
								System.out.println( "trampoline");
								
							}
							return b;
						}
					}
					else if(		(character.getPosX()+ character.getWidth() -character.getWidthDiff() >= b.getPosX() 
								&& character.getPosX()+character.getWidthDiff() <= b.getPosX() + b.getWidth())
							&&(b.getPosY() == character.getPosY()+ character.getHeight()
							|| (character.getPosY()+character.getHeight() -10 <= b.getPosY() 
							&& character.getPosY()+character.getHeight() +10 >= b.getPosY())
									)
									){
						map.getCharacter().setBonus(null);
						
						return b;
					}
				}
				else{
					if(		(character.getPosX()+ character.getWidth() -character.getWidthDiff() >= b.getPosX() 
							&& character.getPosX()+character.getWidthDiff() <= b.getPosX() + b.getWidth())
						&&(b.getPosY() == character.getPosY()+ character.getHeight()
						|| (character.getPosY()+character.getHeight() -10 <= b.getPosY() 
						&& character.getPosY()+character.getHeight() +10 >= b.getPosY())
								)
								){
						map.getCharacter().setBonus(null);
						
						return b;
					}
				}
				
			}
		}
		return null;
	}
	private void updateMap( GameMap map){
		
		this.map = map;
		bricks = map.getBricks();
		bonuses = map.getBonuses();
		monsters = map.getMonsters();
		character = map.getCharacter();
	}
}
