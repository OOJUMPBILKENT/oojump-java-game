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
						return bonus;
					}
					else if( monster != null){
						return monster;
					}
					
				}
			}
			else{
				if( !character.isBoosted()){
					if( bonus != null){
						return bonus;
					}
					else if( monster != null){
						return monster;
					}
					else if( brick != null){
						return brick;
					}
				}
				return brick;
			}
		}
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
				return monster;
				
			}
		}
		return null;
	}
	public Bonus collideBonus(){
		
		for( Bonus b : bonuses){
			if( (character.getPosX()+ character.getWidth() -character.getWidthDiff() >= b.getPosX() && character.getPosX()+character.getWidthDiff() <= b.getPosX() + b.getWidth())
					&&(b.getPosY() == character.getPosY()+ character.getHeight() || 
					((character.getPosY()+ character.getHeight() -10 <= b.getPosY() && character.getPosY()+character.getHeight() +10 >= b.getPosY())
			))
			){
				map.getCharacter().setBonus(b);
				if( b.getName() == Jetpack.JETPACK_NAME){
					map.getCharacter().setBonus(b);
					b.setRemove(true);
					map.getCharacter().setBoosted(true);
					
				}
				else if( b.getName() == Propeller.PROPELLER_NAME){
					map.getCharacter().setBonus(b);
					b.setRemove(true);
					map.getCharacter().setBoosted(true);
					
				}
				else if( b.getName() == Coin.COIN_NAME){
					map.getCharacter().setBonus(b);
					b.setRemove(true);
				}
				return b;
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
	public void updateMap( GameMap map){
		
		this.map = map;
		bricks = map.getBricks();
		bonuses = map.getBonuses();
		monsters = map.getMonsters();
		character = map.getCharacter();
	}
}
