import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

/**
* <h1>bsGame.java: BattleShip Game main java file</h1>
* The bsGame program implements an application that
* attempts to replicate the game of battleship. The game
* starts with ships randomly assigned to the field and then
* lets the players choose a box on the field to send a bomb to.
* The game continues the same way a normal battleship game does
* except with only three ships per player's side of three length
* each.
*
* @author  Robert O'Connell & Jacob Frank
* @version 1.0
* @since   2016-03-12
*/
public class bsGame extends JFrame{
   /**
   * phase  a string that tells the program which phase of the game we are in
   * reset  java button that resets the game
   * statusLine  label that updates the player as to which turn it is
   * score  label that updates how many wins each player has
   * squares  arraylist of buttons for each spot on the grid
   * title  label that welcomes the player to the game
   */
	public String phase = "1move";
	public JButton reset = null;
	public JLabel statusLine = null;
	public JLabel score = null;
	public ArrayList<JButton> squares = new ArrayList<JButton>();
	private JLabel title = null;

   /**
   * p1Ships  arraylist of object Battleship for player one
   * p2Ships  arraylist of object Battleship for player two
   */
	ArrayList<Battleship> p1Ships = new ArrayList<Battleship>();
	ArrayList<Battleship> p2Ships = new ArrayList<Battleship>();
   /**
   * p1Turn  2d array colors for the player one's turn
   * p2Turn  2d array colors for the player two's turn 
   */
	Color[] p1Turn = new Color[64];
	Color[] p2Turn = new Color[64];
   /**
   * WATER  color for the water
   * HIT  color for a hit space
   * MISS  color for a missed spot
   */
	public final Color WATER = new Color(62,140,232);
	public final Color HIT = new Color(225,48,28);
	public final Color MISS = Color.WHITE;  
   /**
   * HITTEXT  string for message displayed when a spot is hit
   * MISSTEXT string for message displayed when a player misses
   * SUNKTEXT string for message displayed when a player sinks
   * the other player's ship
   */
   public final String HITTEXT = "<html>Hit!<br><br>Switch players</html>";
   public final String MISSTEXT = "<html>Miss!<br><br>Switch players</html>";
   public final String SUNKTEXT = "<html>Hit!<br>You sunk my battleship!<br>Switch players</html>";
   /**
   * p1Wins  integer for how many wins player 1 has
   * p2Wins  integer for how many wins player 2 has
   */
	public int p1Wins = 0;
	public int p2Wins = 0;

   /**
   * A default constructor used to call the setupGame method and create
   * the main gui for the game itself.
   */
	public bsGame(){

		setupGame();	

		setSize(1000,1000);
      /**
      * statusBar  the top panel created to show information about the game
      */
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
      /**
      * evt  an object of the event handler class with parameters
      * squares and phase 
      */
		eventHandler evt = new eventHandler(squares,phase);
      /**
      * grid  the panel gridlayout for all the buttons and spots in the game
      */
		JPanel grid = new JPanel(new GridLayout(8,8));
      /**
      * nButton  the java button that will be placed on the grid 
      */
		JButton nButton;
		for(int i=1;i<9;i++){
			for(int j=1;j<9;j++){
				squares.add(nButton = new JButton(j+", " + i));
				nButton.setBackground(WATER);
				nButton.setFont(nButton.getFont().deriveFont(20f));
				nButton.setBorderPainted(false);
				nButton.setOpaque(true);
				grid.add(squares.get(squares.size()-1));
				nButton.addActionListener(evt);
			}
		}
		add(grid, BorderLayout.CENTER);
	}
   /**
   * This method is used to create two integers for
   * the randomly placed ships
   * @param low  This is the first paramter to randInt method
   * @param high  This is the second paramter to randInt method
   * @return int This returns sum of numA and numB.
   */
	public int randInt(int low, int high){
		return (int)(Math.random()*(high-low)+low);
	}
   /**
   * Adds every ship to the grid and adds color to every position
   * as well as add ships to the the player's arrays
   * @return Nothing.
   */
	public void setupGame(){

		phase = "1move";

		for(int i=0;i<64;i++){
			p1Turn[i]=WATER;
			p2Turn[i]=WATER;
		}
		p1Ships = new ArrayList<Battleship>();
		p2Ships = new ArrayList<Battleship>();
      /**
      *  xpos  the x position for a ship on a Point
      *  ypos  the y position for a ship on a Point
      */
		int xpos = randInt(1,7);
		int ypos = randInt(1,4);
      /**
      * <b>Note:</b> The following lines add new points and positions for each
      * ship to each player's array
      */
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
   /**
   * Loads all the colors to the background of each button or square in the
   * arrays
   * @param p  parameter for loadColor that tells the method which set of
   * colors to load
   * @return Nothing.
   */
	public void loadColor(int p){
      /**
      * colors  A 2d array of colors of data type color
      */
		Color[] colors = null;
		if(p==1){colors=p1Turn;}
		if(p==2){colors=p2Turn;}
		for(int i=0;i<64;i++){
			squares.get(i).setBackground(colors[i]);
		}
	}
   /**
   * Loads all the colors to the background of each button or square in the
   * arrays
   * @param p  parameter for writeColor that tells the method which set of
   * colors to write to the colors array
   * @return Nothing.
   */
	public void writeColor(int p){
		System.out.println("Writing colors of player " + p);
		Color[] colors = null;
		if(p==1){colors=p1Turn;}
		if(p==2){colors=p2Turn;}
		for(int i=0;i<64;i++){
			colors[i]=squares.get(i).getBackground();
		}
	}
   /**
   * Updates the scoreboard with the new amount of wins
   */
	public void updateScoreboard(){
		score.setText("      Wins: P1:"+p1Wins+" P2: "+p2Wins);
	}
   /**
   * This is the main method which creates the main window that
   * the game is viewed in
   * @param args Unused.
   * @return Nothing.
   */
	public static void main(String[] args){
      /**
      * mainWindow  starts the program and title it mainWindow for the game
      */
		bsGame mainWindow = new bsGame();
		mainWindow.setTitle("Mini-Project: Battleship by Robert and Jacob");
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
   /**
   * .inner class that handles all the events and actions that takes
   * place 
   */
	protected class eventHandler implements ActionListener {
      /**
      * squares  an arraylist of java buttons for the game
      * phase  a string that tells the methods which phase the game is on
      */
		ArrayList<JButton> squares;
		String phase;
      /**
      * Constructor for the eventHandler inner class which initializes the
      * values used for the class
      * @param _squares  parameter for eventHandler constructor that is an arraylist of squares
      * @param _phase  parameter for eventHandler constructor that is a string for each phase
      */
		public eventHandler(ArrayList<JButton> _squares, String _phase){
			squares = _squares;
			phase = _phase;
		}
      /**
      * A method for all the events that occur as the game goes on
      * @param e  ActionEvent 
      * @return Nothing.
      */
		public void actionPerformed(ActionEvent e){
			JButton evtSquare = null;
			for(int i=0;i<squares.size();i++){
				if(squares.get(i)==e.getSource()){
					evtSquare = squares.get(i);
				}
			}
         /**
         * hit  a boolean variable to tell if the ship is hit
         * hasShip  a boolean variable to tell if the ship is there
         * reset  a boolean variable that tells if the game should be reset
         */
			boolean hit=false;
			boolean hasShip=false;
			boolean reset = false;

			if(phase == "1move" && evtSquare.getBackground()==WATER){
				System.out.println("Got click from player 1");
            /**
            * target  the point value coordinate for the target
            */
				Point target = toPoint(evtSquare.getText());
				for(int i=0;i<p2Ships.size();i++){
					if(p2Ships.get(i).hasPoint(target)){
						p2Ships.get(i).kill(target);
						evtSquare.setBackground(HIT);
						if(p2Ships.get(i).isAlive()){
							JOptionPane.showMessageDialog(null, HITTEXT);
						}
						else{
							JOptionPane.showMessageDialog(null, SUNKTEXT);
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
					JOptionPane.showMessageDialog(null, MISSTEXT);
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
            /**
            * target for the 2nd player and what square they pick
            */
				Point target = toPoint(evtSquare.getText());
				for(int i=0;i<p1Ships.size();i++){
					if(p1Ships.get(i).hasPoint(target)){
						p1Ships.get(i).kill(target);
						evtSquare.setBackground(HIT);
						if(p1Ships.get(i).isAlive()){
							JOptionPane.showMessageDialog(null, HITTEXT);
						}
						else{
							JOptionPane.showMessageDialog(null, SUNKTEXT);
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
					JOptionPane.showMessageDialog(null, MISSTEXT);
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
      /**
      * A method that returns a point based on the string of points given
      * @param s  parameter that contains each point in a string
      * @return returns a point or coordinate
      */
		public Point toPoint(String s){
			System.out.println("Converting string " + s); 
         /**
         * x  the x coordinate parsed from the given string
         * y  the y coordinate parsed from the given string
         */
			int x = Integer.parseInt(s.substring(0,1));
			int y = Integer.parseInt(s.substring(3,4));
			return new Point(x,y);
		}

	}
		
}
