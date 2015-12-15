import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements MouseListener{
	
	private JButton b0, b1, b2, b3, b4;
	private ArrayList<JButton> buttons = new ArrayList<>();
	
	private boolean mute = false;
	private int panelIndex = -1;
	
	private Image mainMenuImage = Toolkit.getDefaultToolkit().getImage("main_menu.png");
	//private Image helpViewImage = Toolkit.getDefaultToolkit().getImage("help_view.png");
	private Image soundOnImage = Toolkit.getDefaultToolkit().getImage("sound_on.png");
	private Image soundOffImage = Toolkit.getDefaultToolkit().getImage("sound_off.png");
	
	public MainMenu(){
		this.setName("OOOOOOOO JUMP");

		setSize(520, 815);
		
		b0 = new JButton();	// play game
		b1 = new JButton(); // help me
		b2 = new JButton(); // high scores
		b3 = new JButton(); // exit
		b4 = new JButton(); // sound on/off
		
		b0.setBounds(85, 210, 185, 60);
		b1.setBounds(135, 310, 185, 60);
		b2.setBounds(315, 490, 185, 60);
		b3.setBounds(260, 580, 185, 60);
		b4.setBounds(30, 710, 48, 48);
		
		
		buttons.add(b0);
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		
		setLayout(null);
		for( JButton b : buttons){
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addMouseListener(this);
			add(b);
		}
	}
	protected void paintComponent( Graphics g){
		super.paintComponent(g);
		g.drawImage(mainMenuImage, 0, 0, null);
		if( !mute){
			g.drawImage(soundOnImage, 30, 710, null);
		}
		else{
			g.drawImage(soundOffImage, 30, 710, null);
		}
		repaint();
	
	}
	public void disableButtons(){
		for( JButton b : buttons){
			b.setEnabled(false);
		}
	}
	public int getPanelIndex(){
		return panelIndex;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getSource() == b0){
			System.out.println( "pressed");
			panelIndex = 0;
			
		}
		else if( e.getSource() == b1){
			setVisible(false);
			HelpView helpView = new HelpView();
			helpView.setVisible(true);
		}
		else if( e.getSource() == b2){
			setVisible(false);
			HighScoresView highScoresView = new HighScoresView();
			highScoresView.setVisible(true);
		}
		else if( e.getSource() == b3){
			System.exit(0);
		}
		else if( e.getSource() == b4){
			
			if( !mute){
				mute = true;
			}
			else{
				mute = false;
			}
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
}
