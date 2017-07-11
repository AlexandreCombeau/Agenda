package stats;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Cette classe doit permettre de switcher en les deux panels de stats avec un card layout
 * 
 * Faut juste faire en sorte de convertir PanelStats en JPanel sans que cela cause de bug afin de pouvoir l'ajouter au cardLayout
 * @author Alexandre
 *
 */
public class FrameStats extends JFrame { 
	public final static String PRESENTATION = "1";
	public final static String CREATION = "2";
	public FrameStats() {

		this.setVisible(true);
		this.setSize(500, 500);
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new CardLayout());

		PanelStats ps = new PanelStats();
		PanelConfigStats pcs = new PanelConfigStats();
	
		//panel.add(ps, PRESENTATION);
		panel.add(pcs, CREATION);

		((CardLayout)panel.getLayout()).show(panel, CREATION);
	}

}
