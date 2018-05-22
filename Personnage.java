public class Personnage extends Entite{
	private int initiative;
	private int attaque;
	private int esquive;
	private int defense;
	private int degats;
	private int PA;
	private int PV;
	private int XP;
	private int inventaire[];
	
	public Personnage(){
		super();
		int tab[]= {10,10};
		this.initiative = 10;
		this.attaque = 10;
		this.esquive = 10;
		this.defense = 10;
		this.degats = 10;
		this.PA = 10;
		this.PV = 10;
		this.XP = 10;
		this.inventaire = tab;
	}
	public Personnage(int initiative, int attaque, int esquive, int defense,
			int degats, int pA, int pV, int xP, int[] inventaire) {
		super();
		this.initiative = initiative;
		this.attaque = attaque;
		this.esquive = esquive;
		this.defense = defense;
		this.degats = degats;
		this.PA = pA;
		this.PV = pV;
		this.XP = xP;
		this.inventaire = inventaire;
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

	public int[] getInventaire() {
		return inventaire;
	}

	public void setInventaire(int[] inventaire) {
		this.inventaire = inventaire;
	}

	public String toString(){
		return "P";
	}
}
