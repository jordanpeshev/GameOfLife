package gameoflife;

public class Cell {
    public CellState state;
    public Cell(CellState state){
        this.state = state;
    }
    public void swapState(){
        if(state.state == true){
            state = new DeadState();
        }
        else{
            state = new AliveState();
        }
    }
}
