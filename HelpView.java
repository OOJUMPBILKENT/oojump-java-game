import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HelpView extends JPanel implements MouseListener{

	private JButton b;
	
	private Image helpViewImage;
	
	private JPanel helpPanel;
	
	public HelpView(){
		Thread imageThread = new Thread(){
			public void run(){
				helpViewImage = Toolkit.getDefaultToolkit().getImage("help_view.png");
			}
		};
		imageThread.run();
		this.setName("Help");

		setSize(520, 815);
		b = new JButton(); // back button for helpView
		
		b.setBounds(335, 470, 127, 45);

		setLayout(null);
		
		
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addMouseListener(this);
			add(b);
		
	}
	protected void paintComponent( Graphics g){
		super.paintComponent(g);
		g.drawImage(helpViewImage, 0, 0, null);
		repaint();
	
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
