import java.awt.Point;
import java.util.ArrayList;

/**
* <h1>BattleShip.java</h1>
* The Battleship class represents each battleship and contains
* a couple methods associated with the battleship object as well
* as information regarding it's status
*
* @author  Robert O'Connell & Jacob Frank
* @version 1.0
* @since   2016-03-12
*/
public class Battleship {
   /**
   * live  an arraylist of points representing the ships or spots
   * where ships are alive
   * dead  an arraylist of points representing the ships or spots
   * where ships are hit or innactive
   */
	private ArrayList<Point> live;
	private ArrayList<Point> dead;

   /**
   * A constructor for the Battleship class which takes in all three points
   * of a battleship and where it is placed on the board
   * @param p1  One part of the ship, represented in a coordinate or point
   * @param p2  Second part of the ship, represented in a coordinate or point
   * @param p3  Third part of the ship, represented in a coordinate or point
   */
	public Battleship(Point p1, Point p2, Point p3){
		live = new ArrayList<Point>();
		dead = new ArrayList<Point>();
		live.add(p1);
		live.add(p2);
		live.add(p3);
	}
   /**
   * Another constructor for the battleship class that sets the live variable
   * to the one given in the parameter
   * @param _live  An arraylist point that holds each point for
   * the living ships or ships that haven't sunk
   */
	public Battleship(ArrayList<Point> _live){
		live = _live;
	}
   /**
   * kill is a method that removes a point from the arraylist that holds
   * the points that say where each ship is. 
   * @param place  a Point datatype that says where the part of the ship is
   * that is going to be killed or removed
   * @return Nothing.
   */
	public void kill(Point place){
		System.out.println(place);
		if(live.indexOf(place)!=-1){
			dead.add(place);
			live.remove(live.indexOf(place));
		}
	}
   /**
   * Method to see if the point requested to bomb is still available to bomb
   * @param p  a point
   * @return a boolean value as to if the point is inside the array 
   * of living points
   */
	public boolean hasPoint(Point p){
		return (live.indexOf(p)!=-1);
	}
   /**
   * Method to see if a ship is alive
   * @return a boolean value as to if a ship is still alive
   */
	public boolean isAlive(){
		return live.size()>0;
	}
}
