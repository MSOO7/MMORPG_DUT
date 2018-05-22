import java.util.Scanner;

public class Client{

  public static void main(String [] args){
    Scanner sc = new Scanner(System.in);
    int choix = 0;

    System.out.println("+----------------------------------------------------------------+");
    System.out.println("|                             MMORPG                             |");
    System.out.println("+----------------------------------------------------------------+");
    System.out.println("|                                                                |");
    System.out.println("|   [1]>Creer un personnage                                      |");
    System.out.println("|   [2]>Charger un personnage                                    |");
    System.out.println("|   [3]>Supprimer un personnage                                  |");
    System.out.println("|                                                                |");
    System.out.println("+----------------------------------------------------------------+");

    while(choix < 1 || choix > 3){
      System.out.print("    [Votre choix]> ");
      choix = sc.nextInt();
    }

    if(choix == 1){
      PJ p = new PJ();
      System.out.println("\n\n");
      Client.play(p);
    }
  }

  public static void play(PJ p){
    System.out.println("Vous vous reveillez au milieu d'une foret adossé a un arbre, au loin resonne les gazouillements des oiseaux.");
    System.out.println("Vous decidez d'eteindre votre feu de camp et de preparer votre materiel pour une nouvelle journée dans le donjon de l'oncle Sam.");
    System.out.println("Quelques foulées plus tard, vous arrivez devant l'entrée d'une grotte. Bizarre d'en trouver une en pleine foret, Surtout sans montagne autour.");
    System.out.println("Un homme approche.... ");
    System.out.println(">>Hey "+p.getName()+" , Sa fait longtemps qu'on t'as pas vu, Ptiot.");
    System.out.println("De retour a creil pour chasser du gobelins?");
    System.out.println(".................");
    System.out.println(">> J'avais oublié que la parlotte c'est pas trop ton truc Ptiot. Bon jte laisse faire ton boulot");
    System.out.println("Vous avez reussi a faire fuire le fermier sauvage.");
    System.out.println("Sans attendre vous courez et entré dans la grotte. Une odeur malefique s'echappe de celui-ci , Un mauvais presage.......... ");
    System.out.println("\n\n");
    Map m = new Map(30);
    m.load("../dft");
    m.init_monstre_objet(30);
    m.place('1',5,0);
    System.out.println("\n\n");
    System.out.println(m);

  }

  public static void clear(){
    try{
      Runtime r = Runtime.getRuntime();
      Process proc = r.exec("clear"); // Pour windows changer clear par cls
      proc.waitFor();
    }catch(Exception e){
      System.out.println("Erreur pour lancement de commande Clear");

    }
  }
}
