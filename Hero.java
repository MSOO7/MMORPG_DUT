import java.util.Scanner;

public class Hero extends Personnage{
  private int force;
  private int adresse;
  private int resistance;
  private Item[] inventaire;
  public static int DEFAULT_NB_DEGRES = 18;

  public Hero(){
    super();
    this.force = 0;
    this.adresse = 0;
    this.resistance = 0;
    this.inventaire = new Item[10];
    init();
  }

  public Hero(int force, int adresse, int resistance, Item[] equipement, int initiative, int attaque, int esquive, int defense, int degats, int pa, int pv, int xp, Item[] inventaire){
    super(initiative,attaque,esquive,defense,degats,pa,pv,xp,equipement);
    this.force = force;
    this.adresse = adresse;
    this.resistance = resistance;
    this.inventaire = inventaire;
  }

  public void afficherInventaire(){
    Scanner sc = new Scanner(System.in);
    int choix = 1;
    System.out.println("Inventaire:");
    for(Item i: this.inventaire){
      if( i != null)
        System.out.println("- "+i);
    }
    while(choix != 0){
      System.out.print("0 pour quitter : ");
      choix = sc.nextInt();
    }
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

  public void setForce(int force){this.force = force;}
  public void setAdresse(int adresse){this.adresse = adresse;}
  public void setResistance(int resistance){this.resistance = resistance;}

  public String toString(){
    return "O";
  }
}
