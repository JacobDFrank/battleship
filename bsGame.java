import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

public class bsGame extends JFrame{

	public String phase = "1move";
	public JButton reset = null;
	public JLabel statusLine = null;
	public JLabel score = null;
	public ArrayList<JButton> squares = new ArrayList<JButton>();
	private JLabel title = null;

	ArrayList<Battleship> p1Ships = new ArrayList<Battleship>();
	ArrayList<Battleship> p2Ships = new ArrayList<Battleship>();

	Color[] p1Turn = new Color[64];
	Color[] p2Turn = new Color[64];

	public final Color WATER = new Color(62,140,232);
	public final Color HIT = new Color(240,48,28);
	public final Color MISS = Color.WHITE;

	public int p1Wins = 0;
	public int p2Wins = 0;


	public bsGame(){

		setupGame();	

		setSize(1000,1000);

		JPanel statusBar = new JPanel(new GridLayout(1,4));
		statusBar.add(reset = new JButton("reset"));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				setupGame();
				loadColor(1);
				statusLine.setText("Player 1's turn");
				
			}
		});
		statusBar.add(score = new JLabel("      Wins: P1: 0 P2: 0"));
		statusBar.add(title = new JLabel("Welcome to battleship!"));
		statusBar.add(statusLine = new JLabel("Player 1's turn"));
		statusLine.setFont(statusLine.getFont().deriveFont(15f));
		title.setFont(title.getFont().deriveFont(15f));
		add(statusBar, BorderLayout.NORTH);

		eventHandler evt = new eventHandler(squares,phase);

		JPanel grid = new JPanel(new GridLayout(8,8));
		JButton nButton;
		for(int i=1;i<9;i++){
			for(int j=1;j<9;j++){
				squares.add(nButton = new JButton(j+", " + i));
				nButton.setBackground(WATER);
				nButton.setFont(nButton.getFont().deriveFont(20f));
				//nButton.setContentAreaFilled(true);
				nButton.setBorderPainted(false);
				nButton.setOpaque(true);
				grid.add(squares.get(squares.size()-1));
				nButton.addActionListener(evt);
			}
		}
		add(grid, BorderLayout.CENTER);
	}
	public int randInt(int low, int high){
		return (int)(Math.random()*(high-low)+low);
	}
	public void setupGame(){

		phase = "1move";

		for(int i=0;i<64;i++){
			p1Turn[i]=WATER;
			p2Turn[i]=WATER;
		}
		p1Ships = new ArrayList<Battleship>();
		p2Ships = new ArrayList<Battleship>();

		int xpos = randInt(1,7);
		int ypos = randInt(1,4);
		p1Ships.add(new Battleship(new Point(xpos,ypos),
					new Point(xpos+1,ypos),
					new Point(xpos+2,ypos)));
		System.out.println(xpos+" "+ypos);
		xpos = randInt(1,7);
		p1Ships.add(new Battleship(new Point(xpos,4),
					new Point(xpos,5),
					new Point(xpos,6)));
		System.out.println("Vertical at X: "+ xpos);
		xpos = randInt(1,6);
		ypos = randInt(7,9);
		p1Ships.add(new Battleship(new Point(xpos,ypos),
					new Point(xpos+1,ypos),
					new Point(xpos+2,ypos)));
		System.out.println(xpos+" "+ypos);


		xpos = randInt(1,7);
		ypos = randInt(1,4);
		p2Ships.add(new Battleship(new Point(xpos,ypos),
					new Point(xpos+1,ypos),
					new Point(xpos+2,ypos)));
		System.out.println(xpos+" "+ypos);
		xpos = randInt(1,7);
		p2Ships.add(new Battleship(new Point(xpos,4),
					new Point(xpos,5),
					new Point(xpos,6)));
		System.out.println("Vertical at X: "+ xpos);
		xpos = randInt(1,6);
		ypos = randInt(7,9);
		p2Ships.add(new Battleship(new Point(xpos,ypos),
					new Point(xpos+1,ypos),
					new Point(xpos+2,ypos)));
		System.out.println(xpos+" "+ypos);


	}
	public void loadColor(int p){
		Color[] colors = null;
		if(p==1){colors=p1Turn;}
		if(p==2){colors=p2Turn;}
		for(int i=0;i<64;i++){
			squares.get(i).setBackground(colors[i]);
		}
	}
	public void writeColor(int p){
		System.out.println("Writing colors of player " + p);
		Color[] colors = null;
		if(p==1){colors=p1Turn;}
		if(p==2){colors=p2Turn;}
		for(int i=0;i<64;i++){
			colors[i]=squares.get(i).getBackground();
		}
	}
	public void updateScoreboard(){
		score.setText("      Wins: P1:"+p1Wins+" P2: "+p2Wins);
	}


	public static void main(String[] args){
		bsGame mainWindow = new bsGame();
		mainWindow.setTitle("Miniproject: Battleship by Robert and Jacob");
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected class eventHandler implements ActionListener {

		ArrayList<JButton> squares;
		String phase;

		public eventHandler(ArrayList<JButton> _squares, String _phase){
			squares = _squares;
			phase = _phase;
		}
		public void actionPerformed(ActionEvent e){

			JButton evtSquare = null;
			for(int i=0;i<squares.size();i++){
				if(squares.get(i)==e.getSource()){
					evtSquare = squares.get(i);
					//squares.get(i).setEnabled(false);
				}
			}
			boolean hit=false;
			boolean hasShip=false;
			boolean reset = false;

			if(phase == "1move" && evtSquare.getBackground()==WATER){
				System.out.println("Got click from player 1");
				Point target = toPoint(evtSquare.getText());
				for(int i=0;i<p2Ships.size();i++){
					if(p2Ships.get(i).hasPoint(target)){
						p2Ships.get(i).kill(target);
						evtSquare.setBackground(HIT);
						if(p2Ships.get(i).isAlive()){
							JOptionPane.showMessageDialog(null,"Hit!");
						}
						else{
							JOptionPane.showMessageDialog(null,"Hit! You sunk my battleship!");
						}
						hit = true;
						hasShip = false;
						for(int j=0;j<p2Ships.size();j++){
							if(p2Ships.get(j).isAlive()){
								hasShip=true;
							}
						}
						if(!hasShip){
							JOptionPane.showMessageDialog(null,"Player one wins!");
							reset = true;
						}
					}
				}
				if(!hit){
					evtSquare.setBackground(MISS);
					JOptionPane.showMessageDialog(null,"Miss!");
				}
				if(!reset){
					writeColor(1);
					loadColor(2);
					phase="2move";
					statusLine.setText("Player 2's turn");
				}
				if(reset){
					setupGame();
					loadColor(1);//Doesn't matter, they're now identical
					statusLine.setText("Player 1's turn");
					p1Wins++;
					updateScoreboard();

				}
								
			}
			else if(phase == "2move" && evtSquare.getBackground()==WATER){
				System.out.println("Got click from player 2");
				Point target = toPoint(evtSquare.getText());
				for(int i=0;i<p1Ships.size();i++){
					if(p1Ships.get(i).hasPoint(target)){
						p1Ships.get(i).kill(target);
						evtSquare.setBackground(HIT);
						if(p1Ships.get(i).isAlive()){
							JOptionPane.showMessageDialog(null,"Hit!");
						}
						else{
							JOptionPane.showMessageDialog(null,"Hit! You sunk my battleship!");
						}
						hasShip = false;
						for(int j=0;j<p1Ships.size();j++){
							if(p1Ships.get(j).isAlive()){
								hasShip=true;
							}
						}
						if(!hasShip){
							JOptionPane.showMessageDialog(null,"Player two wins!");
							reset = true;
						}
						hit = true;
					}
				}
				if(!hit){
					evtSquare.setBackground(MISS);
					JOptionPane.showMessageDialog(null,"Miss!");
				}
				if(!reset){
					writeColor(2);
					loadColor(1);
					phase="1move";
					statusLine.setText("Player 1's turn");
				}
				if(reset){
					setupGame();
					loadColor(1);//Doesn't matter, they're now identical
					statusLine.setText("Player 1's turn");
					p2Wins++;
					updateScoreboard();
				}
			}
			System.out.println(e.getActionCommand());
		}
		public Point toPoint(String s){
			System.out.println("Converting string " + s); 
			int x = Integer.parseInt(s.substring(0,1));
			int y = Integer.parseInt(s.substring(3,4));
			return new Point(x,y);
		}

	}
		
}
