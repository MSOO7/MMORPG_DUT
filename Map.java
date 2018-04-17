
public class Map {
	private final int taille_map = 15;
	private String[][] map ;



	public Map() {
		this.map = new String[this.taille_map][this.taille_map]; //Map de taille 15*15
		this.generate();
	}

	public void generate() {
		this.contour();
		this.generate_interne() // test
	}

	public void contour() {
		for(int i = 0; i < this.taille_map; i++) {
			this.map[0][i] = "*"; // mur haut de la map
			this.map[i][0] = this.map[i][this.taille_map] = "*"; // mur des cotes de la map
			this.map[this.taille_map - 1][i] = "*";  // mur bas de la map
			/**
			 * D'apres ces lignes la map se presentera sous cette forme si la taille == 5:
			 * 		* * * * *
			 * 		*       *
			 * 		*       *		(Sans les espaces)
			 * 		*       *
			 * 		* * * * *
			 */
		}
	}
}
