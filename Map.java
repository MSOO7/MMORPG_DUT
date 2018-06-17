import java.io.*;
import java.util.*;
import java.awt.Point;

/**	Classe Map
 *
 * Classe qui gere tout le jeu, elle fonctionne comme un plateau de
 * jeu de societe autour duquel tout le jeu s'articule
 *
 *	Attributs:
 * 		-boolean map : Tableau boolean , true = Mur false = Sol
 *    -ArrayList<Entites> entites : liste des joueurs,objets,monstres a placé sur la map
 **/


public class Map {
	private boolean[][] map ;
	private ArrayList<Entite> entites;
	private int level = 1;


	/** Constructeur Map
	 *
	 *	int taille : taille de la map a creer(Carre)
	 **/
	public Map(int taille) {
		//initialise les attributs
		this.map = new boolean[taille][taille];
		this.entites = new ArrayList<Entite>();
		//genere la map
		this.generate();
	}

	/**
	 * initialise une partie et commence une partie
	 **/
	public void init(){
		Hero j = new Hero(); //creation d'un joueur
		j.setNom("Aventurier");
		add_E(j); //Ajout du joueur a la liste des entites appartenant a la map
		j.init(); //initialise les attributs du joueur
		for(int i = 0; i < 20; i++){
			random_entite(); // Choisit et place aleatoirement  des entites
		}
		boolean stop = false; //Sert a determiner quand on doit arreter le jeu
		// while(!stop){		  // Tant que l'on ne veut pas quitter
		// 	afficher();		  // Affiche la map
		// 	stop = Action(); // Demande a l'utilisateur l'action souhaité
		// }
	}


	public void ramasser(){
		Entite e;
		for(int i = 0; i < this.entites.size(); i++){ //parcours la liste des entites de la map
			e = this.entites.get(i);
			if(e.getClass().getSuperclass().getName()== "Item"
					&& this.getJ().getX() == e.getX()
						&& this.getJ().getY() == e.getY()){
				//si l'entite est un item ET qu'elle est sur la meme case que le joueur
				if(((Hero)this.getJ()).addI((Item)e)){ //ajoute l'entite a l'inventaire du joueur , renvoie false si il n'y as plus de place
					this.entites.remove(e); // on supprime l'entite de la map pour ne pas qu'elle soit affiché
				}
			}
		}
	}



	/** Mouvement des monstres
	 *
	 *
	 *
	 *
	 *
	 **/
	 public void attaquer(){
		 Monstre mon = new Monstre();
		 mon.setPV(100);

		 for(Monstre[] e: monstre_autour(this.getJ().getX(), this.getJ().getY())){
			 for(Monstre m: e){
				if(m != null){
				 if(m.getPV() < mon.getPV()){
					 mon = m;
				 }
			 	}
			 }
		 }

		 mon.setPV(mon.getPV()-10);
		 if(mon.getPV() < 1) this.entites.remove(mon);
	 }

	public Monstre[][] monstre_autour(int x, int y){
		Monstre[][] tab = new Monstre[3][3];

		for (int i = x-1;i <= x+1; i++){
			for (int j = y-1; j <= y+1; j++){
				if(this.getE(i,j) != null){
					if(this.getE(i,j).toString() == "M" ) tab[i-(x-1)][j-(y-1)] = (Monstre)this.getE(i,j);
				}else{
					tab[i-(x-1)][j-(y-1)] = null;
				}
			}
		}

		return tab;
	}

	public void move_monstre(){
		int lim = 0;
		for(Entite e : this.entites){
			if(e.getClass().getName() == "Monstre"){
				Monstre m = (Monstre)e;
				m.deplacer(this);
				while(this.isNotGround(m.getX(), m.getY()) || this.getNbE(m.getX(),m.getY()) > 1){
					lim ++;
					m.goBack();
					if(lim > 100) break;
					m.deplacer(this);

				}
			}
		}
	}

	/**
	 * Compte le nombre d'entité situé sur une case precise
	 **/
	public int getNbE(int x, int y){
		int i = 0;
		for(Entite e: this.entites){
			if(e.getX() == x && e.getY() == y) i++;
		}

		return i;
	}

	public boolean Action(){
		Scanner sc = new Scanner(System.in);
		int choix=0 , compteur = 0;
		Hero j = (Hero) this.entites.get(0);

		move_monstre();
		do{
			System.out.println("Que voulez vous faire : ");
			System.out.println("1: OUEST\n2: SUD\n3: EST\n5: NORD\n\n4: Inventaire\n7:Passer son tour\n0:Quitter");
			System.out.print("==> ");
			try{
				choix = sc.nextInt();
			}catch(Exception e){
				choix = 0;
			}
		}while(choix < 0 || (choix > 6));
		int dx=0,dy=0;
		switch(choix){
			case 0:
				return true;
			case 1:
				dy--;
				break;
			case 3:
				dy++;
				break;
			case 5:
				dx--;
				break;
			case 2:
				dx++;
				break;
			case 4:
				j.choixInventaire(this);
				break;
			case 6:
				attaquer();
				break;
			case 7:
				break;
			default :
				//erreur a gerer meme si normalement pas de probleme
		}
		if(!this.isNotGround(j.getX()+dx,j.getY()+dy))
			j.deplacement(j.getX()+dx, j.getY()+dy);
		ramasser();     // Detecte si on marche sur un objet et le ramasse automatiquement

		return false;
	}

	public void generateRandom(){
		Random r = new Random();
		r.setSeed(this.level+28);
		Point curs = new Point(0, 0);
		int bloc ;
		for(int i = 0; i < 40; i++){
			bloc = r.nextInt(8)+2;
			curs.move(r.nextInt(getT()-1)+1, r.nextInt(getT()-1)+1);
			for(int j = 0; j < bloc; j++){
				if(r.nextBoolean()){
					curs.translate(r.nextInt(3)-1, 0);
				}else{
					curs.translate(0, r.nextInt(3)-1);
				}
				if((int)curs.getX() >= 0 && (int)curs.getY() >= 0 && (int)curs.getX() < getT() && (int)curs.getY() < getT()){
					this.map[(int)curs.getX()][(int)curs.getY()] = true;
				}
			}
		}

		this.map[1][1] = false;
		this.map[getT()-2][getT()-2] = false;

	}


	public void nextLevel(){
		this.level ++;
	}

	public int getLevel(){
		return this.level;
	}

	public void setLevel(int l){
		this.level = l;
	}

	public void verifLevel(){
		if(this.getJ().getX() == getT()-2 && this.getJ().getY() == getT()-2){
			System.out.println("MMOOUIII?");
			this.nextLevel();
			this.generate();
			Entite e = this.getJ();
			this.entites.removeAll(this.entites);
			this.add_E(e);
			e.setX(1);
			e.setY(1);
			for(int i = 0; i < 20; i++){
				random_entite(); // Choisit et place aleatoirement  des entites
			}
		}
	}

	// public boolean cheminExiste(){
	// 	int[][] m = new Int[getT()][getT()];
	// 	int somme = 0, l,c;
	//
	// 	for(int i = 0; i < getT(); i++){
	// 		for(int j = 0; j < getT(); j++){
	// 			if(this.map[i][j]){
	// 				m[i][j] = 1;
	// 			}else{
	// 				m[i][j] = 0;
	// 			}
	// 		}
	// 	}
	//
	// 	l = 0;
	// 	for(int i = 0; i < getT(); i++){
	// 		c = 0;
	// 		for(int j = 0; j < getT(); j++){
	// 			somme = 0;
	// 			for(int k = 0; k < getT(); k++){
	// 				somme += m[i][k] * m[k][j]
	// 			}
	// 			c++;
	// 			m[l][c] = somme;
	// 		}
	// 		j++;
	// 	}
	//
	// 	if(this.m[)
	// }

	public Entite last(){
		return this.entites.get(this.entites.size()-1);
	}
	public int getT(){
		return this.map.length;
	}
	public boolean isNotGround(int x, int y){
		// System.out.println("MOUI? : "+ this.map[x][y]);
		return this.map[x][y];
	}
	public void add_E(Entite e){
		this.entites.add(e);
	}
	public void add_E(Entite e, int x, int y){
		this.entites.add(e);
		e.setX(x);
		e.setY(y);

		// System.out.println( this.entites.indexOf(e));
		// System.out.println(x+","+y+" : "+e.getX()+","+e.getY());
	}
	public Entite getE(int x, int y){ // Recuperer une entite en fct de ses coordonnes
		Entite e = null;
		for(int i = 0; i < this.entites.size(); i++){
			e = this.entites.get(i);
			if(e.getX() == x && e.getY() == y) return e;
			e = null;
		}
		return e;
		//throw new EntiteInexistante();
	}

	public void random_placement(Entite e){
		int x,y;
		Random r = new Random();


		do{
			x = r.nextInt(getT());
			y = r.nextInt(getT());
		}while(this.getE(x,y) != null || this.isNotGround(x, y));

		e.setX(x);
		e.setY(y);
	}


	public void random_entite(){
		Random r = new Random();
		if (r.nextBoolean())
			this.generateItem();
		else
			this.generateMonstre();
	}

	public void generateItem(){
		Random r = new Random();
		int n = r.nextInt(3);
		Item i = null;
		switch(n){
			case 0:
				i = new Potion();
				break;
			case 1:
				i = new Arme();
				break;
			case 2:
				i = new Armure();
				break;
		}
		random_placement(i);
		entites.add(i);
	}

	public void generateMonstre(){
		Monstre m = new Monstre();
		random_placement(m);
		entites.add(m);
	}


	///////////////////////////////////
	// Fonction specifique a la map //
	/////////////////////////////////

	public void generate() {
		// Random r = new Random();
		// for(int i=0; i < this.map.length; i++){
		// 	for(int j=0; j < this.map.length; j++){
		// 		this.map[i][j] = r.nextInt(70)<1;
		// 	}
		// }
		this.generateRandom();
		this.contour();
	}

	public void contour() {
		for(int i = 0; i < this.map.length; i++) {
			this.map[0][i] = true;
			this.map[i][0] = this.map[i][this.map.length -1] = true; // mur des cotes de la map
			this.map[this.map.length - 1][i] = true;  // mur bas de la map
		}
	}

	public static void clear(){
		for(int i=0;i<40;i++)
		System.out.println("\n" );
	}

	public void afficher(){
		clear();
		System.out.println(this);
	}

	public Hero getJ(){
		return (Hero)this.entites.get(0);
	}


	public String toString(){
		String retour="";
		boolean trouve;
		for(int i=0; i<this.map.length; i++){
			for(int j=0; j<this.map.length; j++){
				if (map[i][j]){
					retour+="#";
				}else{
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
