import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Battleship extends JFrame{

    public JLabel statusLine = null;
    public JLabel score = null;
    public ArrayList<JButton> squares = new ArrayList<JButton>();
    private JLabel title = null;

    public Battleship(){
        setSize(1000,1000);

        JPanel statusBar = new JPanel(new GridLayout(1,3));
        statusBar.add(score = new JLabel(""));
        statusBar.add(title = new JLabel("Welcome to battleship!"));
        statusBar.add(statusLine = new JLabel("Player 1, place your battleships"));
        statusLine.setFont(statusLine.getFont().deriveFont(20f));
        title.setFont(title.getFont().deriveFont(20f));
        add(statusBar, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(8,8));
        for(int i=1;i<9;i++){
            for(int j=1;j<9;j++){
                squares.add(new JButton(i+", " + j));
                grid.add(squares.get(squares.size()-1));
            }
        }
        add(grid, BorderLayout.CENTER);



    }

    public static void main(String[] args){
        Battleship mainWindow = new Battleship();
        mainWindow.setTitle("Battleship game");
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
