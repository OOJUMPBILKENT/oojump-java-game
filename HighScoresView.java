import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class HighScoresView extends JFrame implements MouseListener{
private JButton b;
	
	private Image highScoreViewImage = Toolkit.getDefaultToolkit().getImage("high_scores.png");
	
	private JPanel highScorePanel;
	private JLabel[] labels;
	private FileManager fileMan;
	private final int TOP_TEN = 10;
	private final int labelYDiff = 20;
	//private boolean firstTimeCall = true;
	
	
	public HighScoresView(){
		this.setName("High Scores");

		setSize(520, 815);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		b = new JButton(); // back button for helpView
		b.setBounds(350, 635, 130, 45);
		
		highScorePanel = new JPanel(){
			protected void paintComponent( Graphics g){
				super.paintComponent(g);
				g.drawImage(highScoreViewImage, 0, 0, null);
				repaint();
			
			}
		};
		
		highScorePanel.setLayout(null);
		
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.addMouseListener(this);
		highScorePanel.add(b);
		add(highScorePanel);
		highScorePanel.setVisible(true);
			
		add(highScorePanel);
		
		setVisible(true);
		
		fileMan = FileManager.getInstance(); 
		
		// high score labels
		labels = new JLabel[TOP_TEN];
		
		for(int i=0; i<TOP_TEN; i++)
		{
			JLabel l = new JLabel();
			labels[i] = l;
		}
		
		updateHighScoresView(fileMan.getHighScores());
		//fileMan.saveScore("aa", 1500);
		updateHighScoresView(fileMan.getHighScores());
	}
	public void updateHighScoresView(ArrayList<String[]> highScores){
	
		if(highScores.size()!=0){
			for(int i= 0; i < highScores.size(); i++)
			{
				if(highScores.get(i)[0]!=null)
					labels[i].setText("" + (i+1) + ".) " +highScores.get(i)[0] + " " + highScores.get(i)[1]);	
				labels[i].setBounds(100, 200+(labelYDiff*i), 300, 50);
				labels[i].setFont(new Font("Verdana" , Font.ITALIC , 18 ));
				add(labels[i]);
				highScorePanel.add(labels[i]);
				labels[i].setVisible(true);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b)
		{
			setVisible(false);
			MainMenu mainMenu = new MainMenu();
			mainMenu.setVisible(true);
		}	
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
