import java.util.Random;


public class Entite {
	private int x;
	private int y;
	private String nom;

	public Entite(){
		this.x=1;
		this.y=1;
	}

	public Entite(int x, int y, String nom) {
		this.x = x;
		this.y = y;
		this.nom = nom;
	}

	public void initRandom(int max){
		Random r = new Random();
		this.x = r.nextInt(max-2)+1;
		this.y = r.nextInt(max-2)+1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String toString(){
		return "E";
	}
}
