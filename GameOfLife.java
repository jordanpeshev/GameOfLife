package gameOfLife;

import gameoflife.AliveState;
import gameoflife.Cell;
import gameoflife.DeadState;
import gameoflife.GameOfLifeUI;

public class GameOfLife{
    public GameOfLifeUI panel;
    private Cell[][] grid;
    //private int squaresize;
	
    public GameOfLife(int numbrows, int numbcols, int squaresize) {

        // init grid
        grid = new Cell[numbcols][numbrows];
        for (int i=0; i<numbcols; i++)
                for (int j=0; j<numbrows; j++)
                        grid[i][j] = new Cell(new DeadState());
        // choose some initial configuration
        // multiple inits might interfere with the shape behavior
        initGlider();
        //initSmallExploder();
        //initTumbler();
        
       panel = new GameOfLifeUI(grid, squaresize, this);
        //this.squaresize = squaresize;
        /* setPreferredSize(new Dimension(numbcols*squaresize,numbrows*squaresize));

        this.addMouseListener(new MouseListener() {
                public void mouseReleased(MouseEvent e) {}
                public void mousePressed(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
                public void mouseEntered(MouseEvent e) {}
                public void mouseClicked(MouseEvent e) {
                        toggleGridValue(e.getX(),e.getY());
                }
        }); */
    }

	/* protected void toggleGridValue(int x, int y) {
		int i = x/squaresize;
		int j = y/squaresize;
		grid[i][j] = !grid[i][j];
		repaint();
	} */
    
    public void update(Cell[][] grid){
        this.grid = grid;
    }
    
    public void tick() {
        this.advance();
        panel.update(grid);
    }

    private void advance() {
        Cell[][] newgrid = new Cell[grid.length][grid[0].length];

        for (int i=0; i<grid.length; i++)
                for (int j=0; j<grid[0].length; j++)
                        newgrid[i][j] = new Cell(new DeadState());

        for (int i=0; i<grid.length; i++)
            for (int j=0; j<grid[0].length; j++) 
                if ((grid[i][j].state.state) && (nbrOfNeighbors(i,j) < 2))
                    newgrid[i][j] = new Cell(new DeadState());
                else if ((grid[i][j].state.state) && (2 <= nbrOfNeighbors(i,j)) && (nbrOfNeighbors(i,j) <= 3))
                    newgrid[i][j] = new Cell(new AliveState());
                else if ((grid[i][j].state.state) && (3 < nbrOfNeighbors(i,j)))
                    newgrid[i][j] = new Cell(new DeadState());
                else if ((!grid[i][j].state.state) && (nbrOfNeighbors(i,j) == 3))
                    newgrid[i][j] = new Cell(new AliveState());

        grid = newgrid;
        
    }

    private int nbrOfNeighbors(int x, int y) {
        int result = 0;
        if ((0 <= x-1) && (0 <= y-1) && (grid[x-1][y-1]).state.state) result++;
        if ((0 <= x-1) && (grid[x-1][y].state.state)) result++;
        if ((0 <= x-1) && (y+1 < grid[0].length) && (grid[x-1][y+1].state.state)) result++;
        if ((0 <= y-1) && (grid[x][y-1].state.state)) result++;
        if ((y+1 < grid[0].length) && (grid[x][y+1].state.state)) result++;
        if ((x+1 < grid.length) && (0 <= y-1) && (grid[x+1][y-1].state.state)) result++;
        if ((x+1 < grid.length) && (grid[x+1][y].state.state)) result++;
        if ((x+1 < grid.length) && (y+1 < grid[0].length) && (grid[x+1][y+1].state.state)) result++;
        return result;
    }

    private void initSmallExploder() {
        grid[30][31] = new Cell(new AliveState());
        grid[30][32] = new Cell(new AliveState());
        grid[31][30] = new Cell(new AliveState());
        grid[31][31] = new Cell(new AliveState());
        grid[31][33] = new Cell(new AliveState());
        grid[32][31] = new Cell(new AliveState());
        grid[32][32] = new Cell(new AliveState());
    }

    private void initGlider() {
        grid[21][20] = new Cell(new AliveState());
        grid[22][21] = new Cell(new AliveState());
        grid[22][22] = new Cell(new AliveState());
        grid[21][22] = new Cell(new AliveState());
        grid[20][22] = new Cell(new AliveState());
    }
	
    private void initTumbler() {
        grid[30][23] = new Cell(new AliveState());
        grid[30][24] = new Cell(new AliveState());
        grid[30][25] = new Cell(new AliveState());
        grid[31][20] = new Cell(new AliveState());
        grid[31][21] = new Cell(new AliveState());
        grid[31][25] = new Cell(new AliveState());
        grid[32][20] = new Cell(new AliveState());
        grid[32][21] = new Cell(new AliveState());
        grid[32][22] = new Cell(new AliveState());
        grid[32][23] = new Cell(new AliveState());
        grid[32][24] = new Cell(new AliveState());
        grid[34][20] = new Cell(new AliveState());
        grid[34][21] = new Cell(new AliveState());
        grid[34][22] = new Cell(new AliveState());
        grid[34][23] = new Cell(new AliveState());
        grid[34][24] = new Cell(new AliveState());
        grid[35][20] = new Cell(new AliveState());
        grid[35][21] = new Cell(new AliveState());
        grid[35][25] = new Cell(new AliveState());
        grid[36][23] = new Cell(new AliveState());
        grid[36][24] = new Cell(new AliveState());
        grid[36][25] = new Cell(new AliveState());
    }
	/*
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		//draw background
		drawgrid(g2);
		
		//draw alive cells
		g2.setColor(Color.YELLOW);
		for (int i=0; i<grid.length; i++)
			for (int j=0; j<grid[0].length; j++) 
				if (grid[i][j]) 
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
	*/	
}

