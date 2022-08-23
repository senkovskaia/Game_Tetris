package team19.spiel2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import team19.highscore.HighscoreFenster;
import team19.main.gui_Startfenster;
import javax.swing.Timer;
import team19.main.gui_Startfenster;

/**
 * GUI_Spiel_2 is the frame 
 * 
 * @author Klavdiia Senkovskaia
 *
 */
public class GUI_Spiel_2 extends JFrame{  
	 
	private static final long serialVersionUID = 1L;	
		public Spielfeld SpielfeldlPanel;
		public Vorauswahl_Spielfenster VorauswahlPanel;
		private JSplitPane splitpane;
		private final double verteilungsverhaeltnis = 1D;
		
		public final gui_Startfenster startfenster;

        public int Sizewindow;
		public int squaresz=12;
	    public int score;

		
		public GUI_Spiel_2() {
			this(null);
		}
			
		public GUI_Spiel_2(gui_Startfenster sf) {
	
			startfenster = sf;
			setSize(new Dimension(58*squaresz+163,54*squaresz+2));	
			setLocationRelativeTo(null);
			setResizable(false);

			initSplitPane();
			initPanels();
	
			setVisible(true);
			SpielfeldlPanel.requestFocus();
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent windowEvent) {
					score = SpielfeldlPanel.score;
					HighscoreFenster.updateScore("resources/spiel2.txt", score);
					startfenster.setVisible(true);
				}
			});
		} 
		
		private void initSplitPane() {
			splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitpane.setResizeWeight(1.0); 
			splitpane.setDividerSize(0);
			splitpane.setBorder(null);
			
			add(splitpane); 
			
		    splitpane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
		    		BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder())));
			
		}
		
		private void initPanels() {
		
			SpielfeldlPanel = new Spielfeld(this);
			splitpane.setLeftComponent(SpielfeldlPanel);
			
			VorauswahlPanel = new Vorauswahl_Spielfenster(this, SpielfeldlPanel);
			VorauswahlPanel.setSize(new Dimension(160,52*squaresz));
			splitpane.setRightComponent(VorauswahlPanel);
		
		}
		
		public void refreshSize(int s, int prev_squaresz) {
			
			
			switch (s) {
			case 1: setSize(new Dimension(58*squaresz+167,60*squaresz)); // =58*10 +167, 60*10                58*squaresz+167,55*squaresz
			        Sizewindow = 0;
					break;
			case 2: setSize(new Dimension(58*squaresz+163,54*squaresz+2));
			        Sizewindow = 1;
					break;
			case 3: setSize(new Dimension(58*squaresz+130,53*squaresz+5)); //58*squaresz+157,53*squaresz+5
			        Sizewindow = 2;
					break;
			}
		
			    setLocationRelativeTo(null);
				revalidate();
				SpielfeldlPanel.squaresz = squaresz;
				SpielfeldlPanel.resize(prev_squaresz);  
				SpielfeldlPanel.repaint();
				SpielfeldlPanel.requestFocus(); 
		}
		
		public void windowClosing() {	
				setVisible(false);
				startfenster.setVisible(true);
			//	if(VorauswahlPanel.Spielanleitung.open) {
			//		VorauswahlPanel.Spielanleitung.setVisible(false);
			//	}				
				dispose();
		}

}
