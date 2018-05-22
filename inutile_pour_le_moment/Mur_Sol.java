import java.awt.Color;

import javax.swing.JButton;

public class Mur_Sol extends JButton {
	private boolean estMur ;
	
	public Mur_Sol() {
		super();
		this.estMur = false;
	}
	public boolean getEstMur() {
		return this.estMur;
	}
	public void mur() {
		this.estMur = true;
		this.setBackground(Color.RED);
	}
	public void sol() {
		this.estMur = false;
		this.setBackground(Color.GREEN);
	}
	public void change() {
		if(this.estMur) {
			this.sol();
		}
		else {
			this.mur();
		}
	}

}
