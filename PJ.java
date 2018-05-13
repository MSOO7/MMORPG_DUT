/**
 *
 * @author Steven Le Moine
 * @version Beta (1.0)
 *
 */

/*
 * A faire : rajouter dans le toString les nouvelles capacités (de initiative a degats)
 */

import java.util.Scanner;


public class PJ {
    private int[]caracteristique = new int[8];
    private String[] carac= {"Force","Adresse","Resistance","Initiative","Attaque","Esquive","Defense","Degats"};
    public static final int DEFAULT_NB_DEGRES = 18;
    private int degresUtilises;
    private String name_PJ;

    public PJ() {
        for (int i =0; i<3; i++) {
        	this.caracteristique[i]=0;
        }
        this.degresUtilises = 0;
        this.init();
    }

  public String getName(){
    return this.name_PJ;
  }


 public int getForce() {

        return this.caracteristique[0];
    }


    public void setForce(int value) {

        this.caracteristique[0] = value;
    }


    public void setAdresse(int value) {

        this.caracteristique[1] = value;
    }


    public int getAdresse() {

        return this.caracteristique[1];
    }


    public int getResistance() {

        return this.caracteristique[2];
    }


    public void setResistance(int value) {

        this.caracteristique[2] = value;
    }

 public int getInitiative() {

        return this.caracteristique[3];
    }


    public void setInitiative(int value) {

        this.caracteristique[3] = value;
    }

 public int getAttaque() {

        return this.caracteristique[4];
    }


    public void setAttaque(int value) {

        this.caracteristique[4] = value;
    }

 public int getEsquive() {

        return this.caracteristique[5];
    }


    public void setEsquive(int value) {

        this.caracteristique[5] = value;
    }

 public int getDefense() {

        return this.caracteristique[6];
    }


    public void setDefense(int value) {

        this.caracteristique[6] = value;
    }

 public int getDegats() {

        return this.caracteristique[7];
    }

 public void setDegats(int value) {

        this.caracteristique[7] = value;
    }
 public void setDegresUtilises(int value) {

        this.degresUtilises = value;
    }


public int getDegresUtilises() {

        return this.degresUtilises;
    }

public void init() {
    	Scanner sc = new Scanner(System.in);
    	int tempo;

      System.out.println("Quel est le nom du personnage que vous souhaitez creer? ");
      this.name_PJ = sc.nextLine();

    	if (PJ.DEFAULT_NB_DEGRES-this.degresUtilises>0) {
	    	System.out.println("Force ? (degres restants : "+(PJ.DEFAULT_NB_DEGRES-this.degresUtilises) + ") : ");
	    	tempo = sc.nextInt();
	    	if (tempo <= PJ.DEFAULT_NB_DEGRES-this.degresUtilises) {
	    		this.setForce(tempo);
	    	}
	    	else {
	    		this.setForce(PJ.DEFAULT_NB_DEGRES-this.degresUtilises);
	    	}
	    	this.degresUtilises+=this.getForce();
	    	if (PJ.DEFAULT_NB_DEGRES-this.degresUtilises>0) {
		    	System.out.println("Adresse ? (degres restants : "+(PJ.DEFAULT_NB_DEGRES-this.degresUtilises) + ") : ");
		    	tempo = sc.nextInt();
		    	if (tempo <= PJ.DEFAULT_NB_DEGRES-this.degresUtilises) {
		    		this.setAdresse(tempo);
		    	}
		    	else {
		    		this.setAdresse(PJ.DEFAULT_NB_DEGRES-this.degresUtilises);
		    	}
		    	this.degresUtilises+=this.getAdresse();
		    	if (PJ.DEFAULT_NB_DEGRES-this.degresUtilises>0) {
			    	System.out.println("Resistance ? (degres restants : "+(PJ.DEFAULT_NB_DEGRES-this.degresUtilises) + ") : ");
			    	tempo = sc.nextInt();
			    	if (tempo <= PJ.DEFAULT_NB_DEGRES-this.degresUtilises) {
			    		this.setResistance(tempo);
			    	}
			    	else {
			    		this.setResistance(PJ.DEFAULT_NB_DEGRES-this.degresUtilises);
			    	}
			    	this.degresUtilises+=this.getResistance();
		    	}
	    	}
    	}
    	if (PJ.DEFAULT_NB_DEGRES-this.degresUtilises>0) {
    		this.degresUtilises = 0;
    		System.out.println("Vous devez utiliser les 18 degres, recommencez ");
    		this.init();
    	}
    	else {
    		System.out.println("Personnage Créée");
        	sc.close();
    	}

    }

    public String toString() {
    	return "[Force : " + statToString(0) +"\nAdresse : " + statToString(1) +"\nResistance : "+statToString(2)+"]";
    }

    private String statToString (int attribut){
    	String ret;
    	int partie_r;
    	int partie_rest;
    	//Force
    	partie_r = this.caracteristique[attribut]/3;
    	partie_rest = this.caracteristique[attribut]-(partie_r*3);
    	if (partie_rest!=0) {
    		ret = ""+partie_r+"D+"+partie_rest;
    	}
    	else
    		ret = ""+partie_r+"D";

    	return ret;
    }

    private int[] stringToStat(String a) {
    	//"aD+Y"
    	int [] res = new int[2];
    	res[0] = Integer.parseInt(a.substring(0, 1));
    	res[1] = Integer.parseInt(a.substring(3,4));
    	return res;
    }

    public int tirageDes() {
    	return (int)( Math.random()*( 6 - 1 + 1 ) ) + 1;
    }

    public int calculScore(int attribut) {

    	//fragmentation stat
    	int []res = this.stringToStat(this.statToString(attribut));
    	int prems = 0;
    	for (int i = 0; i<res[0];i++) {
    		prems+=tirageDes();
    	}
    	prems+=res[1];
    	return prems;
    }
}
