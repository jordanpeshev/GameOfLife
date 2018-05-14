package gameoflife;

import gameOfLife.GameOfLife;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class GameOfLifeUI extends JPanel{
    private Cell[][] grid;
    private int squaresize;
    private GameOfLife game;
    public GameOfLifeUI(Cell[][] grid, int squaresize, GameOfLife game){
        this.grid = grid;
        this.squaresize = squaresize;
        this.game = game;
        
        setPreferredSize(new Dimension(grid.length*squaresize, grid[0].length*squaresize));
        this.addMouseListener(new MouseListener() {
                public void mouseReleased(MouseEvent e) {}
                public void mousePressed(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
                public void mouseEntered(MouseEvent e) {}
                public void mouseClicked(MouseEvent e) {
                        toggleGridValue(e.getX(),e.getY());
                }
        });
    }
    protected void toggleGridValue(int x, int y) {
        int i = x/squaresize;
        int j = y/squaresize;
        grid[i][j].swapState();
        repaint();
        game.update(grid);
    }
    public void update(Cell[][] grid){
        this.grid = grid;
        repaint();
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        //draw background
        drawgrid(g2);

        //draw alive cells
        g2.setColor(Color.YELLOW);
        for (int i=0; i<grid.length; i++)
            for (int j=0; j<grid[0].length; j++) 
                if (grid[i][j].state.state) 
                    g2.fill(new Rectangle2D.Double(i*squaresize+1,j*squaresize+1,squaresize-1,squaresize-1));
    }

    public void drawgrid(Graphics2D g2) {
        //fillbackground
        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(getVisibleRect());
        //drawgrid
        g2.setColor(Color.GRAY);
        for (int i=0;i<=grid.length;i++)
            g2.drawLine(i*squaresize, 0, i*squaresize,grid[0].length*squaresize);
        for (int i=0;i<=grid[0].length;i++)
            g2.drawLine(0,i*squaresize,grid.length*squaresize,i*squaresize);
    }
}
