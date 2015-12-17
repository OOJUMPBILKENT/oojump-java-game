import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements KeyListener{

	public static GameManager manager;
	private MainMenu mainMenu;
	private HelpView helpMenu;
	public static HighScoresView highScores;
	public static GameOverView gameOverView;
	public static JPanel mainPanel;
	
	public MainFrame(){
		setSize(520, 815);
		setResizable(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainPanel = new JPanel(new CardLayout());
		//mainPanel.addKeyListener(this);
		addKeyListener(this);
		manager = new GameManager();
	
		helpMenu = new HelpView();
		highScores = new HighScoresView(FileManager.getInstance().getHighScores());
		mainMenu = new MainMenu();
		gameOverView = new GameOverView();
		
		
		mainPanel.add(manager.getGameMapManager(), "manager");
		mainPanel.add(highScores, "highScores");
		mainPanel.add(helpMenu, "helpMenu");
		mainPanel.add(mainMenu, "mainMenu");
		mainPanel.add(gameOverView, "gameOver");
		mainPanel.addKeyListener(this);
		getContentPane().add(mainPanel);
		
	}
	public void run(){
		setVisible(true);
		CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
		cardLayout.show(mainPanel, "mainMenu");
		mainMenu.requestFocus();
		mainMenu.revalidate();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
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