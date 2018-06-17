import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Fen extends JFrame{
  private JPanel est;

  private JTextPane info;
  private PanCentre carte;
  private Inventaire inventaire;

  private Map m;

  private Image[][] carte_img;

  private Evenement e;

  public Fen(Map m){
    super("MMORPG");
    this.m = m;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    this.setSize((int)screen.getWidth(),(int)screen.getHeight());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  public void init(){
    e = new Evenement();
    est = new JPanel(new BorderLayout());
    this.add(est,BorderLayout.EAST);
    info();
    carte();
    inventaire();
    afficher_map();
    this.setFocusable(true);
    this.addKeyListener(e);

  }

  public void carte(){
    this.carte = new PanCentre(m.getT());
    this.carte_img = new Image[m.getT()][m.getT()];

    this.add(this.carte,BorderLayout.CENTER);
  }

  public void info(){
    this.info = new JTextPane();
    this.est.add(this.info, BorderLayout.CENTER);
  }

  public void inventaire(){
    this.inventaire = new Inventaire(m.getJ().getInventaire());

    this.est.add(this.inventaire, BorderLayout.EAST);
  }

  public void afficher_map(){
    this.carte_img = null;
    this.carte_img = new Image[m.getT()][m.getT()];

    String[] str = m.toString().split("\n");

    String[][] s = new String[m.getT()][m.getT()];

    for(int i = 0; i < m.getT(); i++){
      s[i] = str[i].split("");
    }
    for(int i = 0; i < m.getT(); i++){
      for(int j = 0; j < m.getT(); j++){
        System.out.print(s[i][j]);
        if(s[i][j].equals("")) s[i][j] =  null;
        else if(s[i][j].equals("#")) addImg(Sprite.mur);
        else if(s[i][j].equals(" ")) addImg(Sprite.sol);
        else{
          // ajoutSol();
          ajoutEntite(m.getE(i,j));
        }
      }
      System.out.println();
    }
    carte.setCarte(this.carte_img);
    this.inventaire.actualise();
    actualise_info();
    // repaint();
  }

  public void actualise_info(){
    String old = this.info.getText();

    String etat = m.getJ().getEtat();

    this.info.setText("Bienvenue dans le donjon:\n\t-"+m.getJ().getNom());
  }

  public void addImg(String s){
    Image tmp = new ImageIcon(s).getImage();

    for(int i = 0; i < m.getT(); i++){
      for(int j = 0; j < m.getT(); j++){
        if(this.carte_img[i][j] == null){
          this.carte_img[i][j] = tmp;
          return;
        }
      }
    }
  }

  public void ajoutEntite(Entite e){
    String s = e.toString();

    if(s.equals("M")) addImg(Sprite.monstre);
    if(s.equals("P")) addImg(Sprite.potion);
    if(s.equals("A")) addImg(Sprite.armure);
    if(s.equals("W")) addImg(Sprite.arme);
    if(s.equals("O")) addImg(Sprite.player);
  }

  public class Evenement implements KeyListener{
    public Evenement(){}

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e){

      int src = e.getKeyCode();
      int dx = m.getJ().getX(), dy = m.getJ().getY();

      System.out.println(src);

      if(src == KeyEvent.VK_LEFT) dy--;
      if(src == KeyEvent.VK_RIGHT) dy++;
      if(src == KeyEvent.VK_UP) dx--;
      if(src == KeyEvent.VK_DOWN) dx++;
      if(src == KeyEvent.VK_SPACE) m.attaquer();

      if(!m.isNotGround(dx, dy)){
        if(!(m.getNbE(dx,dy) > 0)){
          m.getJ().deplacement(dx,dy);
        }else{
          if(m.getE(dx,dy).toString().equals("W") ||
              m.getE(dx,dy).toString().equals("A") ||
                m.getE(dx,dy).toString().equals("P")) m.getJ().deplacement(dx,dy);
        }
      }
      m.ramasser();
      m.verifLevel();
      m.move_monstre();
      afficher_map();
      repaint();
      requestFocus();
    }
  }

  public static void main(String[] args){

    Map m = new Map(32);
    m.init();
    Fen f = new Fen(m);
  }
}
