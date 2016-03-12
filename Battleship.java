import java.awt.Point;
import java.util.ArrayList;

public class Battleship {
	private ArrayList<Point> live;
	private ArrayList<Point> dead;

	public Battleship(Point p1, Point p2, Point p3){
		live = new ArrayList<Point>();
		dead = new ArrayList<Point>();
		live.add(p1);
		live.add(p2);
		live.add(p3);
	}
	public Battleship(ArrayList<Point> _live){
		live = _live;
	}

	public void kill(Point place){
		System.out.println(place);
		if(live.indexOf(place)!=-1){
			dead.add(place);
			live.remove(live.indexOf(place));
		}
	}

	public boolean hasPoint(Point p){
		return (live.indexOf(p)!=-1);
	}

	public boolean isAlive(){
		return live.size()>0;
	}
}
