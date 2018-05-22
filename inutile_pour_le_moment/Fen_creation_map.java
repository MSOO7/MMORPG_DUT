import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Fen_creation_map extends JFrame implements ActionListener{

        private Map m ;

        private JPanel pan_center;
        private JPanel pan_right;
        private JPanel pan_south;

        private Mur_Sol[][] terrain ;
        //private JTextArea center_area;
        //private JLabel sprite_mur = new JLabel();
        //private JLabel sprite_sol = new JLabel();

        private JButton save;
        private JButton load;
        
        private JButton afficher_console;

        public Fen_creation_map(){
            super("Createur de map");
            this.setSize(900, 900);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.init();
            this.setVisible(true);
        }

        public void init(){
            int t;
            do{
                t = Integer.parseInt(JOptionPane.showInputDialog("Veuillez entrer la taille de la map (entre 5 et 100)"));
            }while(t < 5 || t > 100);

            this.m = new Map(t);
            this.center(t);
            this.droite();
            this.sud();
        }
        
        public void center(int t) {
        	this.terrain = new Mur_Sol[this.m.getTailleMap()][this.m.getTailleMap()];
            this.pan_center= new JPanel(new GridLayout(t,t)); // sa fait un smiley ^^
            
            this.load_default();
            this.add(this.pan_center, BorderLayout.CENTER );
        }

        public void load_default(){
            char[][] tmp = this.m.getMap();

            
            for(int i = 0; i < this.m.getTailleMap(); i++){
                for(int j = 0; j < this.m.getTailleMap(); j++){
                	this.terrain[i][j] = new Mur_Sol();
                	this.pan_center.add(this.terrain[i][j]);
                    if(tmp[i][j] == '#' ){
                        this.terrain[i][j].mur();
                    }
                    if(tmp[i][j] == ' '){
                    	 this.terrain[i][j].sol();
                    }
                    //System.out.print(tmp[i][j]);
                    this.terrain[i][j].addActionListener(this);
                }
                //System.out.println();
            }
        }
        
        public void droite() {
        	this.pan_right = new JPanel(new BorderLayout());
        	
        	this.afficher_console = new JButton("Afficher la map dans la console");
        	this.afficher_console.addActionListener(this);
        	
        	this.pan_right.add(this.afficher_console, BorderLayout.CENTER);
        	
        	this.add(this.pan_right, BorderLayout.EAST);
        }
        
        public void sud() {
        	this.pan_south = new JPanel(new FlowLayout());
        	
        	this.save = new JButton("Save");
        	this.load = new JButton("Load");
        	
        	this.save.addActionListener(this);
        	this.load.addActionListener(this);
        	
        	this.pan_south.add(this.save);
        	this.pan_south.add(this.load);        	
        	
        	this.add(this.pan_south,BorderLayout.SOUTH);
        }
        
        public void actualise() {
        	for(int i = 0; i < this.m.getTailleMap(); i++) {
        		for(int j = 0; j < this.m.getTailleMap(); j++) {
        			if(this.m.qui(i,j) == '#' ){
                        this.terrain[i][j].mur();
                    }
                    if(this.m.qui(i,j) == ' '){
                    	 this.terrain[i][j].sol();
                    }
        		}
        	}
        }
        
        
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			
			for(int i = 0; i < this.m.getTailleMap(); i++){
                for(int j = 0; j < this.m.getTailleMap(); j++){
                    if(src == this.terrain[i][j] ){
                        this.terrain[i][j].change();
                        if(this.terrain[i][j].getEstMur()) {
                        	this.m.setCase('#',i,j);
                        }
                        else {
                        	this.m.setCase(' ',i,j);
                        }
                    }
                }
            }
			
			if(src == this.save) {
				String name = JOptionPane.showInputDialog(null, "Nom de la map");
				if(this.m.save(name))
					JOptionPane.showMessageDialog(null, "Sauvegarde faite");
				else
					JOptionPane.showMessageDialog(null, "Echec de la sauvegarde");					
			}
			if(src == this.load) {
				JFileChooser choix = new JFileChooser();
				int retour = choix.showOpenDialog(null);
				if(retour == JFileChooser.APPROVE_OPTION) {
					if(this.m.load(choix.getSelectedFile().getAbsolutePath())) {
						JOptionPane.showMessageDialog(null, "Chargement fait");
						this.actualise();
					}else {
						JOptionPane.showMessageDialog(null, "Echec du chargement");
					}
				}
			}
			if(src == this.afficher_console) {
				System.out.println(this.m);
			}
		}
}



















