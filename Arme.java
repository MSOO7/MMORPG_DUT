
public class Arme extends Item{
	private int impact;
	private int maniabilite;

	public Arme(){
		super();
		super.setNom("Epee");
		this.impact = 0;
		this.maniabilite = 0;
	}

	public int getImpact(){return this.impact;}
	public int getManiabilite(){return this.maniabilite;}
	public void setImpact(int n){this.impact=n;}
	public void setManiabilite(int n){this.maniabilite=n;}

	public String toString(){
    return "W";
  }
}
