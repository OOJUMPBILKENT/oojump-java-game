import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HelpView extends JFrame implements MouseListener{

	private JButton b;
	
	private Image helpViewImage = Toolkit.getDefaultToolkit().getImage("help_view.png");
	
	private JPanel helpPanel;
	
	public HelpView(){
		this.setName("Help");

		setSize(520, 815);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		b = new JButton(); // back button for helpView
		
		b.setBounds(335, 470, 127, 45);

		
		helpPanel = new JPanel(){
			protected void paintComponent( Graphics g){
				super.paintComponent(g);
				g.drawImage(helpViewImage, 0, 0, null);
				repaint();
			
			}
		};
		
		helpPanel.setLayout(null);
		
		
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addMouseListener(this);
			helpPanel.add(b);
			
		add(helpPanel);
		
		setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getSource() == b){

			helpPanel = new JPanel(){
				protected void paintComponent( Graphics g){
					super.paintComponent(g);
					g.drawImage(helpViewImage, 0, 0, null);
					repaint();				
				}
			};
			
			helpPanel.setLayout(null);
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addMouseListener(this);
			b.setEnabled(false);
			helpPanel.add(b);
			add(helpPanel);
			helpPanel.setVisible(true);
			
			if(e.getSource() == b)
			{
				setVisible(false);
				MainMenu mainMenu = new MainMenu();
				mainMenu.setVisible(true);
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
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
}
