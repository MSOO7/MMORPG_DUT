import java.util.Random;


public class Main {

	public static void main(String[] args) {
		Map m = new Map(30);
		int tab[]={10,10};
		m.add_E(new Hero());
		m.random_placement(m.last());
		m.add_E(new Item());
		m.random_placement(m.last());
		System.out.println(m);
		m.moveHero();
		System.out.println(m);
	}


}
