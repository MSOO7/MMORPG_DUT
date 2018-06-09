import java.io.*;
import java.util.*;

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
		random_placement(j); //On place aleatoirement le joueur
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

	public void move_monstre(){
		for(Entite e : this.entites){
			if(e.getClass().getName() == "Monstre"){
				Monstre m = (Monstre)e;
				m.deplacer((Hero)this.getJ());
				while(this.isNotGround(m.getX(), m.getY())){
					m.goBack();
					m.deplacer((Hero)this.getJ());
				};
			}
		}
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
		}while(choix < 0 || (choix > 5 && choix != 7));
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
		}
		return e;
		//throw new EntiteInexistante();
	}

	public void random_placement(Entite e){
		do{
			e.initRandom(this.getT());
		}while(this.isNotGround(e.getX(), e.getY()));
	}


	public void random_entite(){
		Random r = new Random();
		if (r.nextInt(2)==0)
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
		Random r = new Random();
		for(int i=0; i < this.map.length; i++){
			for(int j=0; j < this.map.length; j++){
				this.map[i][j] = r.nextInt(70)<1;
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
