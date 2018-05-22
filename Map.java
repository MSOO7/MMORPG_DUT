import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Map {
	private boolean[][] map ;
	private ArrayList<Entite> entites;
	
	public Map(int taille) {
		this.map = new boolean[taille][taille];
		this.entites = new ArrayList<Entite>();
		this.generate();
		
	}
	
	public Entite last(){
		return this.entites.get(this.entites.size()-1);
	}
	public int getT(){
		return this.map.length;
	}
	public boolean isNotGround(int x, int y){
		return this.map[x][y];
	}
	public void add_E(Entite e){
		this.entites.add(e);
	}

	//////////////////////////////////
   //// fonction de construction ////
  //////////////////////////////////

	public void generate() {
		for(int i=0; i < this.map.length; i++){
			for(int j=0; j < this.map.length; j++){
				this.map[i][j] = false;
			}
		}
		this.contour();
	}

	public void contour() {
		for(int i = 0; i < this.map.length; i++) {
			this.map[0][i] = true;
			this.map[i][0] = this.map[i][this.map.length -1] = true; // mur des cotes de la map
			this.map[this.map.length - 1][i] = true;  // mur bas de la map
		}
	}

	//------------------------------------------------------------------------------------
	public void random_entite(){
		Random r = new Random();
		if (r.nextInt(1)==0)
			this.generateItem();
		else
			this.generateMonstre();
	}

	public void generateItem(){
		
	}
	
	public void generateMonstre(){
		
	}

	public String toString(){
		String retour="";
		boolean trouve;
		for(int i=0; i<this.map.length; i++){
			for(int j=0; j<this.map.length; j++){
				if (map[i][j])
					retour+="#";
				else{
					trouve = false;
					for (int e=0;e<entites.size();e++){
						if (this.entites.get(e).getX()==i && this.entites.get(e).getY()==j){
							retour+=this.entites.get(e).toString();
							trouve = true;
							break;
						}
					}
				if(!trouve)
					retour+=" ";
				}
			}
			retour += "\n";
		}
		return retour;
	/*	for (int i=0;i<map.length;i++){
			for (int j=0;j<map.length;j++){
				retour += map[i][j];
			}
		}
		return retour;
	*/
	}
/*	
	public boolean save(String nom) {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(
									new File(nom))));
			dos.writeInt(this.getTailleMap());
			for(int i = 0; i < this.getTailleMap(); i++) {
				for(int j = 0; j < this.getTailleMap(); j++) {
					dos.writeChar(this.map[i][j]);
				}
			}
			return true;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(dos != null)
					dos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}


	}
	public boolean load(String nom) {
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(
					new BufferedInputStream(
							new FileInputStream(
									new File(nom))));
			if(this.taille_map !=  dis.readInt()) {
				System.out.println("Les cartes n'ont pas la meme taille");;
			}
			else {
				for(int i = 0; i < this.getTailleMap(); i++) {
					for(int j = 0; j < this.getTailleMap(); j++) {
						this.map[i][j] = dis.readChar();
					}
				}
				return true;
			}
			return false;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(dis != null)
					dis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
 */
}
