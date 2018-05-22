import java.util.Random;


public class Main {

	public static void main(String[] args) {
		Map m = new Map(30);
		int tab[]={10,10};
		m.add_E(new Personnage());
		random_placement(m.last(),m);
		m.add_E(new Item());
		random_placement(m.last(),m);
		System.out.println(m);
	}
	
	public static void random_placement(Entite entite, Map m){
		int x,y;
		Random r = new Random();

		do{
			x = r.nextInt(m.getT());
			y = r.nextInt(m.getT());
		}
		while(m.isNotGround(x, y));
		entite.setX(x);
		entite.setY(y);
	}
}
