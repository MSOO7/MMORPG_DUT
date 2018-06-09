import javax.swing.*;
import java.awt.*;

public class PanCentre extends JPanel{
  private Image[][] carte;

  public PanCentre(int taille){
    this.carte = new Image[taille][taille];
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    for(int i = 0; i < this.carte.length; i++){
      for(int j = 0; j < this.carte.length; j++){
        g.drawImage(new ImageIcon("../Sprite/sol/swamp_0.png").getImage(),j*32,i*32,null);
        g.drawImage(this.carte[i][j],j*32,i*32,null);
      }
    }
  }

  public void setCarte(Image[][] c){
    this.carte = c;
  }
}
