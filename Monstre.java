import java.util.Random;

public class Monstre extends Personnage{

  private int last_x;
  private int last_y;
  private final int champ_vision = 3;

  public Monstre(){
    super();
  }

  public void deplacer(Map m){
    Random r = new Random();
    int dx = this.getX(), dy = this.getY();

    Hero h = m.getJ();

    this.last_x = dx;
    this.last_y = dy;

    double distance = Math.sqrt((Math.abs(dx-h.getX())+Math.abs(dy-h.getY())));
    if ((distance <this.champ_vision)){
      if (r.nextBoolean()){
        if(dx-h.getX()<0) {
          dx++;
        }else if(dx-h.getX()>0){
          dx--;
        }else if(dy-h.getY()<0){
          dy++;
        }else if(dy-h.getY()>0){
          dy--;
        }}else{
        if(dy-h.getY()<0) {
          dy++;
        }else if(dy-h.getY()>0){
          dy--;
        }else if(dx-h.getX()<0){
          dx++;
        }else if(dx-h.getX()>0){
          dx--;
        }
      }
    }else{
      switch(r.nextInt(4)){
        case 0:
  				dy--;
  				break;
  			case 1:
  				dy++;
  				break;
  			case 2:
  				dx--;
  				break;
  			case 3:
  				dx++;
  				break;
      }
    }
    if(!(h.getX() == dx && h.getY() == dy) && m.getNbE(dx, dy) == 0 ) super.deplacement(dx,dy);
  }

  public void goBack(){
    super.deplacement(this.last_x, this.last_y);
    super.setPA(super.getPA()+1);
  }

  public String toString(){
    return "M";
  }
}
