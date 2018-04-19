/*Creer objet entite avec Coordonnes etc */

public class Map {
	private final int taille_map = 15;
	private String[][] map ;
	private String[] car_perso = { "O", "M"}; // incomplet  O= objet  M=monstre
	//penser a remplacer les strings par des objets avec des caracteristiques
	private int nb_MonstresEtObj = 3;



	public Map() {
		this.map = new String[this.taille_map][this.taille_map]; //Map de taille 15*15
		this.generate();
	}

	//////////////////////////////////
   //// fonction de construction ////
  //////////////////////////////////

	public void generate() {
		for(int i=0; i < this.taille_map; i++){
			for(int j=0; j < this.taille_map; j++){
				this.map[i][j] = " ";  //on initialise le tableau avec des espaces
			}
		}
		this.contour();
		this.mur();
	}

	public void contour() {
		for(int i = 0; i < this.taille_map; i++) {
			this.map[0][i] = "#"; // mur haut de la map
			this.map[i][0] = this.map[i][this.taille_map] = "#"; // mur des cotes de la map
			this.map[this.taille_map - 1][i] = "#";  // mur bas de la map
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

	public void mur(){

	}

	public void random_placement(String entite){
		int x,y, placement_fait = 0;

		while(placement_fait != 1){
			x = Math.random()* this.taille_map;
			y = Math.random()* this.taille_map;

			placement_fait = this.place(entite, x, y);
		}


	}
	public String random_entite(){
		int indice = Math.random()* this.car_perso.length; // chiffre random [0,car_perso.length[
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
	public  int place(String entite, int x, int y){
		if(estCase(x, y)){
			if(this.map[ligne][colonne] != " "){
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
	public String qui(int x, int y){
		if(estCase(x,y)){
			return this.map[x][y];
		}
		System.out.println("Coordonnes invalide (qui)");
		return null;
	}



	  //////////////////////////////////
	 //// fonction de verification ////
	//////////////////////////////////

	/**
	*	Verifie si a est une coordonnes dans la carte
	*/
	public boolean estValide(int a){
		return (a>0 && a<this.taille_map);
	}

	/**
	*	Verifie si la case existe
	*/
	public boolean estCase(int x, int y){
		return (estValide(x) && estValide(y));
	}
}
