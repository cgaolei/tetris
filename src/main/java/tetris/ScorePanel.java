package tetris;

import java.awt.*;

import javax.swing.JPanel;

/**
 * The Upper Left panel for level and score display
 * @author Lei Gao
 */
public class ScorePanel extends JPanel {

	private static final long serialVersionUID = -9132982378357189445L;

	public ScorePanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBorder(new javax.swing.border.LineBorder(Color.BLUE, 10, true));
		setMinimumSize(new java.awt.Dimension(160, 120));
		setPreferredSize(new java.awt.Dimension(160, 120));
	}

	public void paint(Graphics g) {
		int level = TetrixApp.gamePanel.getLevel();
		int score = TetrixApp.gamePanel.getScore();
		int gameStatus = TetrixApp.gamePanel.getGameStatus();
		super.paint(g);

		g.setFont(new Font("Impact", Font.PLAIN, 36));
		g.setColor(Color.BLUE);
		g.drawString("Level:" + level, 20, 40);
		g.setColor(Color.RED);

		if (gameStatus == 1) {
			g.drawString("" + score, 30, 100);
			g.setFont(new Font("Impact", Font.PLAIN, 18));
			g.setColor(Color.ORANGE);
			g.drawString("Score:", 20, 70);
		} else if (gameStatus == 0) {
			g.setFont(new Font("Impact", Font.PLAIN, 24));
			g.setColor(Color.MAGENTA);
			g.drawString("Game Paused", 15, 70);
			g.setFont(new Font("Impact", Font.PLAIN, 16));
			g.setColor(Color.RED);
			g.drawString("Press F2 to RESUME..", 15, 90);
		} else if (gameStatus == -1) {
			g.setFont(new Font("Impact", Font.PLAIN, 24));
			g.setColor(Color.MAGENTA);
			g.drawString("New Game", 20, 70);
			g.setFont(new Font("Impact", Font.PLAIN, 16));
			g.setColor(Color.RED);
			g.drawString("Press F2 to START..", 20, 90);
		}else {
			g.setFont(new Font("Impact", Font.PLAIN, 24));
			g.setColor(Color.MAGENTA);
			g.drawString("GAME OVER", 20, 70);
		}
	}
}
