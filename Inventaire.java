import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Inventaire extends JTabbedPane{
  private Item[] inv;
  private JPanel[] onglet;

  public Inventaire(Item[] inventaire){
    super(JTabbedPane.LEFT);
    this.inv = inventaire;
    this.onglet = new JPanel[this.inv.length];
  }

  public void actualise(){
    this.removeAll();

    for(int i = 0; i < this.onglet.length; i++){
      if(this.inv[i] != null){
        this.onglet[i] = new JPanel();
        // this.onglet[]
        this.add(this.inv[i].getClass().getName(), this.onglet[i]);
        if(this.inv[i].toString().equals("P")) this.setIconAt(i,new ImageIcon(Sprite.potion));
        if(this.inv[i].toString().equals("W")) this.setIconAt(i,new ImageIcon(Sprite.arme));
        if(this.inv[i].toString().equals("A")) this.setIconAt(i,new ImageIcon(Sprite.armure));
      }
    }
  }



}
