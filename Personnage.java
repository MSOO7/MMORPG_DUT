import java.util.Scanner;
import java.util.Random;

public class Personnage extends Entite{
	private int initiative;
	private int attaque;
	private int esquive;
	private int defense;
	private int degats;
	private int PA;
	private int PV;
	private int XP;
	private Item[] equipement;

	public static final String[] etat = {"Mort", "Inconscient", "Gravement blessé", "legerement blessé", "En forme"};

	public Personnage(){
		super();
		this.initiative = 10;
		this.attaque = 10;
		this.esquive = 10;
		this.defense = 10;
		this.degats = 10;
		this.PA = 5;
		this.PV = 100;
		this.XP = 0;
		this.equipement = new Item[6];
	}
	public Personnage(int initiative, int attaque, int esquive, int defense,
			int degats, int pA, int pV, int xP, Item[] equipement) {
		super();
		this.initiative = initiative;
		this.attaque = attaque;
		this.esquive = esquive;
		this.defense = defense;
		this.degats = degats;
		this.PA = pA;
		this.PV = pV;
		this.XP = xP;
		this.equipement = equipement;
	}

	public void init(){
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		sc.close();
	}
	// public save(String chemin){
	//
	// }

	public int attaque(){
		Random r = new Random();
		int nbD = this.attaque, degats = 0;

		for(Item i: this.equipement){
			if(i != null){
				if(i.toString().equals("W")){
					nbD += ((Arme)i).getImpact();
				}
			}
		}

		degats += nbD%3;
		nbD = (nbD/3);

		for(int i = 0; i < nbD; i++){
			degats += r.nextInt(6)+1;
		}

		return degats;

	}

	public void deplacement(int x, int y){
		if(this.PA > 0){
			super.setX(x);
			super.setY(y);
			this.PA--;
		}
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getEsquive() {
		return esquive;
	}

	public void setEsquive(int esquive) {
		this.esquive = esquive;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getPA() {
		return PA;
	}

	public void setPA(int pA) {
		PA = pA;
	}

	public int getPV() {
		return PV;
	}

	public void setPV(int pV) {
		PV = pV;
	}

	public int getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public Item[] getEquipement() {
		return this.equipement;
	}

	public void setEquipement(Item[] inventaire) {
		this.equipement = inventaire;
	}

	public String toString(){
		return "P";
	}
}
