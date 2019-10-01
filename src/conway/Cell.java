package conway;

import java.awt.*;

class Cell
{
    boolean alive;
    boolean aliveInNextState;
    Button button;
    int x;
    int y;

    Cell(int x, int y)
    {
        alive = false;
        aliveInNextState = false;
        button = null;
        this.x = x;
        this.y = y;
    }
    void makeAlive()
    {
        alive = true;
        button.setBackground(Color.yellow);
    }

    void makeDead()
    {
        alive = false;
        button.setBackground(null);
    }

}