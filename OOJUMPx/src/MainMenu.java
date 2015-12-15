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

public class MainMenu extends JFrame implements MouseListener, KeyListener{
	
	private JButton b0, b1, b2, b3, b4;
	private ArrayList<JButton> buttons = new ArrayList<>();
	
	private boolean mute = false;
	private boolean onGame = false;
	
	private Image mainMenuImage = Toolkit.getDefaultToolkit().getImage("main_menu.png");
	private Image helpViewImage = Toolkit.getDefaultToolkit().getImage("help_view.png");
	private Image soundOnImage = Toolkit.getDefaultToolkit().getImage("sound_on.png");
	private Image soundOffImage = Toolkit.getDefaultToolkit().getImage("sound_off.png");
	
	GameManager manager;
	private JPanel mainPanel;
	
	public MainMenu(){
		this.setName("OOOOOOOO JUMP");

		setSize(520, 815);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		mainPanel = new JPanel(){
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
		};
		
		mainPanel.setLayout(null);
		
		
		for( JButton b : buttons){
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addMouseListener(this);
			mainPanel.add(b);
		}
		add(mainPanel);
		
		
		manager = new GameManager();
		setVisible(true);
	}
	public void disableButtons(){
		for( JButton b : buttons){
			b.setEnabled(false);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getSource() == b0){
			System.out.println( "pressed");
			setVisible( false);
			JFrame gameFrame= new JFrame();
			gameFrame.setSize(520, 815);
			gameFrame.setResizable(true);
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			manager.getGameMapManager().addKeyListener(this);
			gameFrame.add(manager.getGameMapManager());
			gameFrame.setVisible(true);
			if(!manager.isGameOver()){
				System.out.println(manager.isGameOver());
				manager.run();
			}
			if(manager.isGameOver()){
				System.out.println( "visible");
				gameFrame.setVisible(false);
				setVisible(true);
			}
			
				
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "typed");
		manager.keyPressed(e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		manager.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		manager.keyPressed(e);
	}
}
