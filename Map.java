import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*Creer objet entite avec Coordonnes etc */

public class Map {
	private int taille_map ;
	private char[][] map ;
	private char[] car_perso = { 'O', 'M'}; // incomplet  O= objet  M=monstre
	//penser a remplacer les strings par des objets avec des caracteristiques
	private int nb_MonstresEtObj = 3;



	public Map(int taille) {
		this.taille_map = taille;
		this.map = new char[this.taille_map][this.taille_map]; //Map de taille 15*15
		this.generate();
	}

	//////////////////////////////////
   //// fonction de construction ////
  //////////////////////////////////

	public void generate() {
		for(int i=0; i < this.taille_map; i++){
			for(int j=0; j < this.taille_map; j++){
				this.map[i][j] = ' ';  //on initialise le tableau avec des espaces
			}
		}
		this.contour();
	}

	public void contour() {
		for(int i = 0; i < this.taille_map; i++) {
			this.map[0][i] = '#'; // mur haut de la map
			this.map[i][0] = this.map[i][this.taille_map -1] = '#'; // mur des cotes de la map
			this.map[this.taille_map - 1][i] = '#';  // mur bas de la map
			/**
			 * D'apres ces lignes la map se presentera sous cette forme si la taille == 5:
			 * 		# # # # #
			 * 		#       #
			 * 		#       #		(Sans les espaces)
			 * 		#       #
			 * 		# # # # #
			 */
		}
	}

	

	public void random_placement(char entite){
		int x,y, placement_fait = 0;

		while(placement_fait != 1){
			x =(int) Math.random()* this.taille_map;
			y =(int) Math.random()* this.taille_map;

			placement_fait = this.place(entite, x, y);
		}


	}
	public char random_entite(){
		int indice = (int)Math.random()* this.car_perso.length; // chiffre random [0,car_perso.length[
		return this.car_perso[indice];
	}





	//////////////////////////////////
   //// fonction de manipulation ////
  //////////////////////////////////

	/**
	*	Permet de deplacer le joueur
	*	Retourne 1 si placement effectue
	*   0 sinon
	*/
	public  int place(char entite, int x, int y){
		if(estCase(x, y)){
			if(qui(x,y) != ' '){
				System.out.println("La case est deja occupe");
				return 0;
			}
			this.map[x][y] = entite; // on place l'objet choisi a l'endroit voulu
			return 1;
		}else{
			System.out.println("Coordonnes invalide (deplacement)");
			return 0;
		}
	}

	/**
	* Retourne la contenance d'une case
	*/
	public char qui(int x, int y){
		if(estCase(x,y)){
			return this.map[x][y];
		}
		System.out.println("Coordonnes invalide (qui)");
		return 0;
	}
	
	

	public String toString(){
		String retour="";
		for(int i=0; i<this.taille_map; i++){
			for(int j=0; j<this.taille_map; j++){
				retour += qui(i,j);
			}
			retour += "\n";
		}
		return retour;
	}

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
		
		
	}

	  //////////////////////////////////
	 //// fonction de verification ////
	//////////////////////////////////

	/**
	*	Verifie si a est une coordonnes dans la carte
	*/
	public boolean estValide(int a){
		return (a>=0 && a<this.taille_map);
	}

	/**
	*	Verifie si la case existe
	*/
	public boolean estCase(int x, int y){
		return (estValide(x) && estValide(y));
	}

	//////////////////////////////////
   //// ACCESSEUR \ MUTATEURS    ////
  //////////////////////////////////

  public int getTailleMap(){
	  return this.taille_map;
  }
  public char[][] getMap(){
	  return this.map;
  }
  public void setCase(char s,int i, int j) {
		this.map[i][j]= s;
	}
}
