package tetris;

import java.awt.*;
import java.awt.event.KeyEvent;

import sun.audio.*;

import java.io.*;

/**
 * The is the core class for this application
 * All game functions and infomation are implemented here
 *
 * @author Lei Gao
 */
public class GamePanel extends javax.swing.JPanel implements Runnable {

    private static final long serialVersionUID = -8083717917627508415L;

    // Data Field -- Game infomation
    private int nextObj; // The Code for next object

    private int score; // The Score for current game

    private int level; // The Current game level

    private int gameStatus; // The Game status, Pause, Game Over etc..

    // 1 - game running;
    // 0 - game paused;
    // -1 - New Game;
    // -2 - Game Over;
    private int autoLevelUp = 5000; // Level Up every 5000

    // Data Field
    private SquareObject O;

    protected Thread t = new Thread(this);

    // Data Field End

    /**
     * Creates new form NewJPanel
     */
    public GamePanel() {
        initComponents();
        SquareMatrix.initMatrix();
        this.requestFocus();
        nextObj = -1;
        score = 0;
        level = 1;
        gameStatus = -1;
    }

    public int getNextObject() {
        return nextObj;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    private void levelUp() {
        if (level < 6) {
            this.level++;
            this.autoLevelUp += 5000;
        }
        this.wavePlay(3);
    }

    private void levelDown() {
        if (level > 1) {
            this.level--;
            this.autoLevelUp -= 5000;
        }
    }

    private int getTime() {
        switch (level) {
            case 2:
                return 350;
            case 3:
                return 230;
            case 4:
                return 150;
            case 5:
                return 100;
            case 6:
                return 70;
        }
        return 500;
    }

    /**
     * Play wave audio file
     *
     * @param code the wave file coding. see comments below
     */
    private void wavePlay(int code) {
        String waveName = new String();
        switch (code) {
            case 1: // Rotate
                waveName = "/rotate.wav";
                break;
            case 2: // Down
                waveName = "/Down.wav";
                break;
            case 3: // LevelUp
                waveName = "/LevelUp.wav";
                break;
            case 4: // GameOver
                waveName = "/GameOver.wav";
                break;
            case 5: // RemoveRow
                waveName = "/removeRow.wav";
                break;
            default: // Rotate
                waveName = "/rotate.wav";
                break;
        }

        try {
            InputStream ws = GamePanel.class.getResourceAsStream(waveName);
            AudioStream as = new AudioStream(ws);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Random generate an integer between low and high
     *
     * @param lo the low integer value
     * @param hi the high integer value
     * @return the generated random integer value
     */

    private int randInRage(int lo, int hi) {
        return lo + (int) (Math.random() * ((hi - lo) + 1));
    }

    /**
     * Set and initiate an square object according to its type code
     *
     * @param type the object type
     * @return an instance of square object
     */

    private SquareObject setObject(int type) {
        switch (type) {
            case 0:
                return new SquareTypeZero(20);
            case 1:
                return new SquareTypeOne(20);
            case 2:
                return new SquareTypeTwo(20);
            case 3:
                return new SquareTypeThree(20);
            case 4:
                return new SquareTypeFour(20);
            case 5:
                return new SquareTypeFive(20);
            case 6:
                return new SquareTypeSix(20);
        }
        return null;
    }

    /**
     * Level up the game when score meets standards
     */
    private void autoLevelUp() {
        if (score >= autoLevelUp)
            levelUp();
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (O != null)
            O.paintObject(g);
        int row = 27;
        while (!SquareMatrix.isEmptyRow(row)) {
            for (int col = 1; col < 18; col++) {
                if (SquareMatrix.matrix[row][col] != 0) {
                    int x = (col - 1) * 20;
                    int y = (row - 1) * 20;
                    Color color = O.getColor(SquareMatrix.matrix[row][col]);
                    g.setColor(color);
                    g.fill3DRect(x, y, 20, 20, false);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, 20, 20);
                }
            }
            row--;
        }
    }

    /**
     * Test if it is the end of game
     *
     * @return true if the end of game
     */
    private boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            if (SquareMatrix.getValue(O.S[i].getX(), O.S[i].getY()) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add object into the square matrix when an object reach the bottom.
     */
    private void addStore() {
        for (int i = 0; i < 4; i++) {
            SquareMatrix.setValue(O.S[i].getX(), O.S[i].getY(), O.S[i]
                    .getColorCode());
            O.S[i] = null;
        }
    }

    /**
     * Actions need to be done when an object reach the bottom
     *
     * @throws InterruptedException
     */
    private void AcBottom() {
        if (O.isBottom) {
            addStore();
            score += 10;
            int rowRemoved = 0;
            int row = 27;
            while (row > 1 && !SquareMatrix.isEmptyRow(row)) {
                if (SquareMatrix.isFullRow(row)) {
                    rowRemoved++;
                    removeRowAnimation(row, rowRemoved * 100);
                    SquareMatrix.removeRow(row);
                    repaint();
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException E) {
                    }
                    row++;
                    score = score + rowRemoved * 100;
                }
                row--;
            }

            O = setObject(nextObj);
            nextObj = randInRage(0, 6);

            if (isGameOver()) {
                gameStatus = -2;
                repaint();
            }
        }
    }

    /**
     * Animation When remove a full row object.
     *
     * @param row the row need to be removed
     */
    private void removeRowAnimation(int row, int addScore) {
        Graphics g = this.getGraphics();
        SquareMatrix.emptyRow(row, 5);
        repaint();
        g.setColor(Color.RED);
        g.setFont(new Font("Impact", Font.ITALIC, 24));
        g.drawString("+" + addScore, 100, 200);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SquareMatrix.emptyRow(row, 6);
        repaint();
        g.setFont(new Font("Impact", Font.ITALIC, 36));
        g.drawString("+" + addScore, 50, 180);
        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SquareMatrix.emptyRow(row, 5);
        repaint();
        this.wavePlay(5);
        g.setFont(new Font("Impact", Font.ITALIC, 48));
        g.drawString("+" + addScore, 50, 140);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Implement Runnable interface method run()
     */
    public void run() {
        this.gameStatus = -1;    //New Game
        while (gameStatus != -2) {
            this.requestFocus();
            if (gameStatus > 0) {
                O.moveDown();
                AcBottom();
                repaint();
                update();
                try {
                    Thread.sleep(getTime());
                } catch (InterruptedException E) {
                }
            }
        }
        this.wavePlay(4);
    }

    private void update() {
        autoLevelUp();
        repaint();
        TetrixApp.testPanel.repaint();
        TetrixApp.scorePanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents

        setLayout(null);

        setBackground(new java.awt.Color(255, 204, 204));
        setBorder(new javax.swing.border.LineBorder(
                new java.awt.Color(0, 0, 0), 2, true));
        setMinimumSize(new java.awt.Dimension(340, 540));
        setPreferredSize(new java.awt.Dimension(340, 540));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserContral(evt);
            }
        });

    }// GEN-END:initComponents

    private void UserContral(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_UserContral
        int keyCode = evt.getKeyCode();

        if (keyCode == KeyEvent.VK_F5 && gameStatus > 0) {
            levelDown();
        }

        if (keyCode == KeyEvent.VK_F6 && gameStatus > 0) {
            levelUp();
        }

        if (keyCode == KeyEvent.VK_LEFT && gameStatus > 0) {
            O.moveLeft();
            repaint();
        }
        if (keyCode == KeyEvent.VK_RIGHT && gameStatus > 0) {
            O.moveRight();
            repaint();
        }
        if (keyCode == KeyEvent.VK_DOWN && gameStatus > 0) {
            while (!O.isBottom) {
                O.moveDown();
            }
            this.wavePlay(2);
            repaint();
        }
        if (keyCode == KeyEvent.VK_UP && gameStatus > 0) {
            O.rotate();
            this.wavePlay(1);
            repaint();
        }
        if (keyCode == KeyEvent.VK_F2) {
            if (gameStatus == 1) { // Game Running
                gameStatus = 0;
            } else if (gameStatus == 0) { // Game Paused
                gameStatus = 1;
            } else if (gameStatus == -1) { // New Game
                O = setObject(randInRage(0, 6));
                nextObj = randInRage(0, 6);
                gameStatus = 1;
            } else { // Game Over

            }
            this.update();
        }
    }// GEN-LAST:event_UserContral

}
