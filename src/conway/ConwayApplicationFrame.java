package conway;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConwayApplicationFrame extends JFrame {
    private static final int boardSize = 16;
    private static final Cell[][] board;
    private static ActionListener actionListener;

    static {
        board = new Cell[boardSize][boardSize];
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Integer i = Integer.valueOf(actionEvent.getActionCommand());
                board[i / boardSize][i % boardSize].alive = !board[i / boardSize][i % boardSize].alive;
                if (board[i / boardSize][i % boardSize].alive) {
                    board[i / boardSize][i % boardSize].button.setBackground(Color.yellow);
                } else board[i / boardSize][i % boardSize].button.setBackground(null);
            }
        };
    }


    private void initialize()
    {
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                board[i][j] = new Cell(i, j);
            }
        }

        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                board[i][j].button = new Button(Integer.toString(i * boardSize + j));
                board[i][j].button.addActionListener(actionListener);
                add(board[i][j].button);
            }
        }
    }
    private static int countNeighbours(Cell cell)
    {
        int neighboursCount = 0;
        int aux = -1;
        for(int i = 0; i < 3; i++)
        {
            if(board[(cell.x - 1 + boardSize) % boardSize][(cell.y + boardSize + aux) % boardSize].alive) neighboursCount++;
            aux++;
        }
        for(int i = 0; i < 3; i++)
        {
            aux--;
            if(board[(cell.x + 1 + boardSize)% boardSize][(cell.y + boardSize + aux) % boardSize].alive) neighboursCount++;
        }
        if(board[cell.x][(cell.y + 1 + boardSize) % boardSize].alive) neighboursCount++;
        if(board[cell.x][(cell.y - 1 + boardSize) % boardSize].alive) neighboursCount++;
        return neighboursCount;
    }

    private static void computeNextState()
    {
        Cell cell;
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                cell = board[i][j];
                int count = countNeighbours(cell);
                if(cell.alive)
                {
                    cell.aliveInNextState = count >= 2 && count <= 3;
                }
                else
                {
                    cell.aliveInNextState = count == 3;
                }
            }
        }
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                cell = board[i][j];
                if(cell.aliveInNextState) cell.makeAlive();
                else cell.makeDead();
            }
        }
    }

    private ConwayApplicationFrame()
    {
        setTitle("Conway");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); //make the window appear at the center of the screen
        setVisible(true);
    }

    private static ActionListener ticker = actionEvent -> computeNextState();

    public static void main(String[] args) {
        ConwayApplicationFrame conwayApplicationFrame = new ConwayApplicationFrame();
        Menu menu = new Menu();
        conwayApplicationFrame.initialize();
        GridLayout gridLayout = new GridLayout(boardSize, boardSize);
        GridLayout gridLayout2 = new GridLayout(1,1);
        conwayApplicationFrame.setLayout(gridLayout);
        menu.setLayout(gridLayout2);
        menu.initialize(ticker);
        conwayApplicationFrame.setSize(501,500);
        conwayApplicationFrame.setSize(500,500);
        menu.setSize(251, 200);
        menu.setSize(250, 200);
    }

}