
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOverView extends JPanel implements MouseListener{

	
	private Image gameOverViewImage;

	public static JTextField askName;
	public static String name;
	
	public GameOverView(){
		name = null;
		Thread imageThread = new Thread(){
			public void run(){
				gameOverViewImage = Toolkit.getDefaultToolkit().getImage("res/images/gameOver.png");
			}
		};
		imageThread.run();
		this.setName("GameOver");
		setSize(520, 815);
		setLayout(null);
		
		askName = new JTextField();
		askName.setVisible(true);
		askName.setBounds(210, 400, 100, 50);
		add(askName);
		askName.addActionListener(new ActionListener(){
			
            public void actionPerformed(ActionEvent e){
            	name = askName.getText();
            	askName.setText(null);
            	FileManager.getInstance().saveScore(name, GameManager.score);
            	MainFrame.highScores.updateHighScoresView(FileManager.getInstance().getHighScores());
        		CardLayout cardLayout = (CardLayout)MainFrame.mainPanel.getLayout();
        		MainFrame.highScores.scoreLabel.setText("Your score: " + GameManager.score);
        		MainFrame.highScores.scoreLabel.setVisible(true);
        		MainFrame.highScores.repaint();
    			cardLayout.addLayoutComponent(MainFrame.highScores, "highScores");
    			cardLayout.show(MainFrame.mainPanel, "highScores");
    	
    			MainFrame.mainPanel.requestFocus();
    			MainFrame.mainPanel.revalidate();	
        		
            }});

		

		
	}
	protected void paintComponent( Graphics g){
		super.paintComponent(g);
		g.drawImage(gameOverViewImage, 0, 0, null);
		repaint();
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
	}
}

