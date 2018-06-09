import java.util.Scanner;

public class Hero extends Personnage{
  private int force;
  private int adresse;
  private int resistance;
  private Item[] inventaire;
  private int curseur=0;
  public static int DEFAULT_NB_DEGRES = 18;

  public Hero(){
    super();
    this.force = 0;
    this.adresse = 0;
    this.resistance = 0;
    this.inventaire = new Item[4];
    init();
  }

  public Hero(int force, int adresse, int resistance, Item[] equipement, int initiative, int attaque, int esquive, int defense, int degats, int pa, int pv, int xp, Item[] inventaire){
    super(initiative,attaque,esquive,defense,degats,pa,pv,xp,equipement);
    this.force = force;
    this.adresse = adresse;
    this.resistance = resistance;
    this.inventaire = inventaire;
  }

  public String getEtat(){
    return "Vous etes "+Personnage.etat[(super.getPV()/2)-1]+"\n";
  }

  public boolean addI(Item ite){
    for(int i = 0; i < inventaire.length; i++){
      if(inventaire[i] == null){
        inventaire[i] = ite;
        return true;
      }
    }
    return false;
  }

  public void afficherInventaire(){
    System.out.println("Inventaire:");
    for(int i = 0; i < this.inventaire.length; i++){
     if( this.inventaire[i] != null)
        if (curseur== i){
            System.out.println(" > "+this.inventaire[i].getNom());
        }else{
          System.out.println(" - "+this.inventaire[i].getNom());
        }
      }
  }

  public void choixInventaire(Map m){
    this.curseur = 0;
    Scanner sc = new Scanner(System.in);
    int choix = 1;
    while(choix != 4){
      afficherInventaire();
      System.out.print("5: HAUT\n2: BAS\n0: Jeter l'objet\n4 pour quitter : ");
      choix = sc.nextInt();
      if (choix == 5 && curseur > 0)this.curseur--;
      if (choix == 2 && curseur < this.inventaire.length-1 && this.inventaire[curseur+1] != null)this.curseur++;
      if (choix == 0 && this.inventaire[this.curseur] != null) jeterI(m);
    }
  }

  public void jeterI(Map m){
    m.add_E(this.inventaire[this.curseur],this.getX(),this.getY());
    removeI(this.curseur);
  }

  public void removeI(int i){
    Item[] inv_tmp = new Item[this.inventaire.length];
    this.inventaire[this.curseur] = null;
    for(int k = 0; k < this.inventaire.length; k++ ){
      if(this.inventaire[k] != null){
        inv_tmp[k] = this.inventaire[k];
      }
    }
    this.inventaire = inv_tmp;

  }

  public void init(){
    // super.init();
    // Scanner sc = new Scanner(System.in);
  	// int tempo;
    // int degresUtilises = 0;
    // String n;
    //
    // System.out.println("Quel est le nom du personnage que vous souhaitez creer? ");
    // sc.nextLine();
    // n = sc.nextLine();
    // super.setNom(n);
    //
  	// if (DEFAULT_NB_DEGRES-degresUtilises>0) {
    // 	System.out.println("Force ? (degres restants : "+(DEFAULT_NB_DEGRES-degresUtilises) + ") : ");
    // 	tempo = sc.nextInt();
    // 	if (tempo <= DEFAULT_NB_DEGRES-degresUtilises) {
    // 		this.setForce(tempo);
    // 	}
    // 	else {
    // 		this.setForce(DEFAULT_NB_DEGRES-degresUtilises);
    // 	}
    // 	degresUtilises+=this.getForce();
    // 	if (DEFAULT_NB_DEGRES-degresUtilises>0) {
	  //   	System.out.println("Adresse ? (degres restants : "+(DEFAULT_NB_DEGRES-degresUtilises) + ") : ");
	  //   	tempo = sc.nextInt();
	  //   	if (tempo <= DEFAULT_NB_DEGRES-degresUtilises) {
	  //   		this.setAdresse(tempo);
	  //   	}
	  //   	else {
	  //   		this.setAdresse(DEFAULT_NB_DEGRES-degresUtilises);
	  //   	}
	  //   	degresUtilises+=this.getAdresse();
	  //   	if (DEFAULT_NB_DEGRES-degresUtilises>0) {
		//     	System.out.println("Resistance ? (degres restants : "+(DEFAULT_NB_DEGRES-degresUtilises) + ") : ");
		//     	tempo = sc.nextInt();
		//     	if (tempo <= DEFAULT_NB_DEGRES-degresUtilises) {
		//     		this.setResistance(tempo);
		//     	}
		//     	else {
		//     		this.setResistance(DEFAULT_NB_DEGRES-degresUtilises);
		//     	}
		//     	degresUtilises+=this.getResistance();
	  //   	}
    // 	}
  	// }
  	// if (DEFAULT_NB_DEGRES-degresUtilises>0) {
  	// 	degresUtilises = 0;
  	// 	System.out.println("Vous devez utiliser les 18 degres, recommencez ");
  	// 	this.init();
  	// }
  	// else {
  	// 	System.out.println("Personnage Créée");
  	// }
    // sc.close();

  }


  public int getForce(){return this.force;}
  public int getAdresse(){return this.adresse;}
  public int getResistance(){return this.resistance;}
  public Item[] getInventaire(){return this.inventaire;}

  public void setForce(int force){this.force = force;}
  public void setAdresse(int adresse){this.adresse = adresse;}
  public void setResistance(int resistance){this.resistance = resistance;}

  public String toString(){
    return "O";
  }
}
