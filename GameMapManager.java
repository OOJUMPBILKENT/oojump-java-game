import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameMapManager extends JPanel implements Drawable{ //extends JPanel{
	
	private GameMap map;
	
	private JLabel scoreLabel;
	
	//Constructor
	public GameMapManager(){
		
		setSize(WIDTH, HEIGHT);
		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
		setVisible(true);
		scoreLabel = new JLabel();
		
		//map = new GameMap();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
		repaint();
	}
	
	
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(map.getBackgroundImage(), 0, 0, null);
		for( Brick b : map.getBricks()){
			if( b.getPosY() >= 0){
				b.draw(g);
			}
		}
		ArrayList<Bonus> bonuses = map.getBonuses();
		if( bonuses.size() > 0){
			for( Bonus bonus : bonuses){
				bonus.draw(g);
			}
		}
		ArrayList<Monster> monsters = map.getMonsters();
		if( monsters.size() > 0){
			for( Monster monster : monsters){
				monster.draw(g);
			}
		}
		
		map.getCharacter().draw(g);
		g.drawImage(map.getTopBarImage(), 0, 0, null);
		scoreLabel.setText("" + map.getCharacter().getScore());
		scoreLabel.setBounds(0, 0, 100, 50);
		scoreLabel.setFont(new Font("Verdana" , Font.ITALIC , 30));
		add(scoreLabel);
	}
	public void setMap( GameMap map){
		this.map = map;
	}
}
