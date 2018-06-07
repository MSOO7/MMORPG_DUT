import javax.swing.*;
import java.awt.*;

public class Fen extends JFrame{
  private JPanel info;
  private JPanel carte;
  private JPanel inventaire;
  private Map m;

  public Fen(Map m){
    super("MMORPG");
    this.m = m;
    this.setSize(800,800);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  public void init(){
    this.info = new JPanel();
    this.carte = new JPanel(new GridLayout(m.getT(),m.getT(),0,0));
    this.inventaire = new JPanel();
    afficher_map();
    this.add(this.carte,BorderLayout.CENTER);


  }

  public void afficher_map(){
    String[] str = m.toString().split("");

    for(String s: str){
      System.out.print(s);
    }

    for(int i = 0; i < m.getT(); i++){
      if(str[i].equals("#")) ajoutMur();
      if(str[i].equals(" ")) System.out.println("$"); //ajoutSol();
      // else{
      //   int x = i/m.getT();
      //   int y = i%m.getT();
      //   ajoutSol();
      //   //ajoutEntite(m.getE(x,y));
      // }
    }
  }

  public void ajoutMur(){
    JLabel tmp = new JLabel(new ImageIcon("../Sprite/mur.png"));
    tmp.setSize(10, 10);
    this.carte.add(tmp);
    //System.out.println("$");
  }

  public void ajoutSol(){
    JLabel tmp = new JLabel(new ImageIcon("../Sprite/sol.png"));
    tmp.setSize(10, 10);
    this.carte.add(tmp);
  }

  public void ajoutEntite(){

  }
}
