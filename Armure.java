public class Armure extends Item{
  private int solidite;
  private double etat;

  public Armure(){
      super();
      super.setNom("Armure");
      this.etat = 0;
      this.solidite = 0;
  }

  public String toString(){
    return "A";
  }
}
