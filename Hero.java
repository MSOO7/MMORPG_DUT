public class Hero extends Personnage{
  private int force;
  private int adresse;
  private int resistance;
  private Item[] inventaire;

  public Hero(){
    super();
    this.force = 0;
    this.adresse = 0;
    this.resistance = 0;
    this.inventaire = new Item[10];
  }

  public String toString(){
    return "1";
  }
}
