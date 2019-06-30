package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The bottom Left pannel for genernal infomation display
 * @author Lei Gao
 */
public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -9132982378357189445L;

	public InfoPanel() {
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setBorder(new javax.swing.border.LineBorder(Color.GRAY, 10, true));
		setMinimumSize(new java.awt.Dimension(160, 250));
		setPreferredSize(new java.awt.Dimension(160, 250));
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.drawString("How to Play:",20,40);
		g.setColor(Color.BLUE);
		g.drawString("\u2191",40,55);
		g.drawString("\u2193",40,70);
		g.drawString("\u2192",40,85);
		g.drawString("\u2190",40,100);
		g.drawString(": Rotation",50,55);
		g.drawString(": Drop",50,70);
		g.drawString(": Left",50,85);
		g.drawString(": Right",50,100);
		g.drawString("F2 : Pause/Resume",20,120);
		g.drawString("F5/F6 -- Cheating",20,150);
		g.drawString("Up/Down a Level",20,163);
		
	}
}
