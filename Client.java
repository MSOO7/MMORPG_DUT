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
      Client.play(p);
    }
  }

  public static void play(PJ p){
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    System.out.println(" Vous vous reveillez au milieu d'une foret adossé a un arbre, au loin resonne les gazouillements des oiseaux.\n   Vous decidez d'eteindre votre feu de camp et de preparer votre materiel pour une nouvelle journée dans le donjon de l'oncle Sam.\n Quelques foulées plus tard, vous arrivez devant l'entrée d'une grotte. Bizarre d'en trouver une en pleine foret, Surtout sans montagne autour.\n Un homme approche.....\n >>Hey "+p.getName()+" , Sa fait longtemps qu'on t'as pas vu, Ptiot.\n De retour a creil pour chasser du gobelins?\n ................. \n >>Dis donc j'avais oublié que la parlotte c'est pas ton fort, Ptiot. Toi c'est plus le Claquo. Allez jte laisse faire ton boulot le Ptiot.\n\n Vous avez reussi a faire fuire le fermier sauvage.\n Sans attendre vous courez et entré dans la grotte. Une odeur malefique s'echappe de celui-ci , Un mauvais presage..........  ");

    Map m = new Map(30);
    m.load("dft");
    m.init_monstre_objet(6);
    m.place('1',5,0);
    System.out.println(m);

  }
}
