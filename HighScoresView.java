import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Font;

public class HighScoresView extends JPanel implements MouseListener{
private JButton b;
	
	private Image highScoreViewImage;
	
	private JLabel[] labels;
	public static JLabel scoreLabel;
	private FileManager fileMan;
	private final int TOP_TEN = 10;
	private final int labelYDiff = 20;
	//private boolean firstTimeCall = true;
	
	
	public HighScoresView(ArrayList<String[]> highScores){
		
		Thread imageThread = new Thread(){
			public void run(){
				highScoreViewImage = Toolkit.getDefaultToolkit().getImage("res/images/high_scores.png");
			
			}
		};
		imageThread.run();
		this.setName("High Scores");

		setSize(520, 815);
		
		b = new JButton(); // back button for helpView
		b.setBounds(350, 635, 130, 45);
		
		setLayout(null);
		
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.addMouseListener(this);
		add(b);
		
		setVisible(true);
		
		fileMan = FileManager.getInstance(); 
		highScores = fileMan.getHighScores();
		
		// high score labels
		labels = new JLabel[TOP_TEN];
		scoreLabel = new JLabel();
		//scoreLabel.setText("" + GameManager.score);
		scoreLabel.setFont(new Font("Verdana" , Font.ITALIC , 30 ));
		scoreLabel.setBounds(257, 40, 100, 30);
		scoreLabel.setVisible(false);
		add(scoreLabel);
		
		for(int i=0; i<TOP_TEN; i++)
		{
			JLabel l = new JLabel();
			labels[i] = l;
		}
		
		updateHighScoresView(fileMan.getHighScores());

	}
	protected void paintComponent( Graphics g){
		super.paintComponent(g);
		g.drawImage(highScoreViewImage, 0, 0, null);
		repaint();
	
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
				add(labels[i]);
				labels[i].setVisible(true);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == b){
			CardLayout cardLayout = (CardLayout)MainFrame.mainPanel.getLayout();
			cardLayout.show(MainFrame.mainPanel, "mainMenu");
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
