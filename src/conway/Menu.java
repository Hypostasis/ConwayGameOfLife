package conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame
{
    Menu()
    {
        setVisible(true);
        setTitle("MENU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 200);
        setLocationRelativeTo(null);
    }

    void initialize(ActionListener start)
    {
        Button button = new Button("TICK!");
        button.addActionListener(start);
        add(button);
    }

}
