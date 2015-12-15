import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements KeyListener{

	private GameManager manager;
	private boolean wait;
	private JPanel mainPanel;
	
	public MainFrame(){
		setSize(520, 815);
		setResizable(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		addKeyListener(this);
		setVisible(true);
		wait = true;
		manager = new GameManager();
	}
	public void run(){
		MainMenu mainMenu = new MainMenu();
		add(mainMenu);
		mainPanel = mainMenu;
		getContentPane().add(mainPanel);
		setVisible(true);
		while( wait){
			if( mainMenu.getPanelIndex() != -1)
				wait = false;
			else
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if( mainMenu.getPanelIndex() == 0){
			getContentPane().remove(mainMenu);
			getContentPane().add(manager.getGameMapManager());
			manager.getGameMapManager().revalidate();
			requestFocus();
			manager.run();
		}
		wait = true;
		while( wait){
			if( manager.isGameOver())
				wait = false;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if( manager.isGameOver()){
			getContentPane().remove(manager.getGameMapManager());
			getContentPane().add( mainMenu);
		}
		
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

/*
 * JFrame gameFrame= new JFrame();
			gameFrame.setSize(520, 815);
			gameFrame.setResizable(true);
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			manager = new GameManager();
			manager.getGameMapManager().addKeyListener(this);
			gameFrame.add(manager.getGameMapManager());
			gameFrame.setVisible(true);
			if(!manager.isGameOver()){
				System.out.println(manager.isGameOver());
				manager.run();
			}
			*/
