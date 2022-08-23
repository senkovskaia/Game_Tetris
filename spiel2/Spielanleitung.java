package team19.spiel2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



/**
 * Spielanleitung JPanel (containts JFrame) is created when the user clicks on the button Spielanleitung.
 * Here are the rules of the game and the selected parameters from VorauswahlPanel
 * @author Klavdiia Senkovskaia 
 * 
 */

public class Spielanleitung extends JPanel implements WindowListener {

	boolean open;
	/**
	 * Creates Frame 
	 */
	JFrame Spielanleitung = new JFrame();
	/**
	 * Vorauswahl_Spielfenster by which this Spielanleitung is opened.
	 */
    Vorauswahl_Spielfenster VorauswahlPanel;
    Spielfeld Spielfeldfenster;
    /**
	 * Spielanleitung constructor
	 * 
	 * @param VorauswahlPanel is the instance of Vorauswahl_Spielfenster
	 *              
	 */
	public Spielanleitung (Vorauswahl_Spielfenster VorauswahlPanel) {
		this.VorauswahlPanel = VorauswahlPanel;
     
		/**
		 * Sets size of the Spielanleitung panel and the different parameters
		 */
		setPreferredSize(new Dimension(850, 600));
	
		Spielanleitung.add(this);
	
	    open = true;
		
		Spielanleitung.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Spielanleitung.addWindowListener(this);
		Spielanleitung.pack();
	
	
	
		
		setVisible(true);
		Spielanleitung.setVisible(true);
		
		
		
	
	}   
	    /**
	     *  Creates array list of strings which allows to describe the rules of game
	     *
	     */
	
	     ArrayList<String> list_Txt() {

			String title = ("Spielanleitung: Spiel2 - Puzzles");

			String text1 = ("Darstellung des Spielfensters");
			String text2 = ("");
			String text3 = ("Im Fenster der Groeße " + SizeWindow() + " befindet sich rechts eine abgetrennte Spalte mit der Vorauswahl, Buttons und Score. ");
			String text4 = ("Links von dieser Spalte befindet sich das Spielfeld, oben befindet sich das Gitter. ");
            String text5 = ("Die unten angezeigten Quadrate sind " + ParkPlatz() + " Parktplaetze, außerhalb des Gitters befinden sich noch " + VorauswahlPanel.number_Umrisse + " Umrisse.");
            String text6 = ("Ziel des Spiels");
        	String text7 = ("");
			String text8 = ("Ziel des Spiels ist es, dass fallende Steine den unteren Rand des Gitters nicht beruehren, sonst gilt das Spiel als verloren.");
            String text9 = ("Dies kann verhindert werden, indem die Fallsteine ausgewaehlt und auf einen ausgewaelten Parkplatz bzw. Umriss abgelegt werden.");
			String text10 = ("Ein Umriss kann mit zwei Fallsteinen passgenau ausgefüllt werden kann. ");
			String text11 = ("Die beiden Fallsteine verschwinden, es werden 100 Punkte gutgeschrieben.");
			String text12 = ("Spielverlauf");
			String text13 = ("");
			String text14 = ("In regelmaeßigen Abstaenden alle " + Time_Interval() + " erscheinen " + VorauswahlPanel.amountOfVisibleSteins + " Fallstein(e) bestehend aus " + VorauswahlPanel.k + " Kacheln." );
			String text15 = ("Sie fallen mit der Geschwindigkeit " + VorauswahlPanel.chosen_speed + " ms und sind in " + 	VorauswahlPanel.chosen_color + " Farbe(n) eingefärbt. ");
			String text16 = ("Die Steigerung der Fallgeschwindigkeit  " + Acceleration());
			String text17 = ("Man kann einen Fallstein , einen Umriss oder einen Parkplatz mit der Maus auswaehlen. ");
			String text18 = ("Man bewirkt durch Druecken der Return - Taste das Ablegen des aktiven Fallsteins.");
            String text19 = ("Ein Fallstein kann ausgewaehlt werden, wenn er sich im Gitter oder auf einem Parkplatz befindet.");
			String text20 = ("Es gibt zulaessiges Ablegen eines aktiven Fallsteins mit der Return - Taste : ");
			String text21 = ("vom Gitter auf einen Parkplatz, vom Gitter auf einen Umriss, von einem Parkplatz auf einen anderen Parkplatz,");
			String text22 = ("von einem Parkplatz auf einen Umriss.");
			String text23 = ("Außerdem muss gelten: ein Parkplatz muss leer sein und ein Umriss muss entweder leer sein oder es liegt bereits ein Fallstein darauf.");
			String text24 = ("Mit der Taste V kann man vom Bereich der Umrisse in den Bereich der Parkplaetze wechseln und umgekehrt.");
			String text25 = ("Jeder Parkplatz und jeder Umriss hat eine Nummer. Man muss sich im Bereich der Parkplaetze bzw. der Umrisse befinden (mit der Maus bzw. Taste V),");
			String text26 = ("damit eine Zahl (Taste) druecken und einen entsprechenden Parkplatz bzw. einen entsprechenden Umriss auswaelen kann.");
            String text27 = ("Der aktive Fallstein kann im Gitter mittels Q (E) horizontal (vertikal) gespiegelt werden, mittels Y (C) nach links (rechts) gedreht werden.");
			String text28 = ("und mit den Pfeiltasten (links, unten, rechts) bzw. mit den Tasten A, S, D verschoben werden.");
			String text29 = ("Der aktive Fallstein kann auf dem Parkplatz mittels Q (E) horizontal (vertikal) gespiegelt werden, mittels Y (C) nach links (rechts) gedreht werden.");
			String text30 = ("Man kann das Spiel mit der Taste P pausieren. Das Spiel wird fortgefuehrt, wenn noch einmal die Taste P gedrueckt wird. ");
            String text31 = ("Man kann das Spiel beenden, wenn das Spiel pausiert wird und die Taste Z gedrueckt wird.");
			String text32 = ("Man kann auch den aktiven Fallstein mit der Leertaste schneller fallen lassen, bis er einen anderen Stein oder den Boden des Gitters beruehren.");
			
	       
			
			
		/**
		  *  Creates array list of strings and adds the text to the string
	      */
		 ArrayList<String> txt = new ArrayList<String>();
			txt.add(title); 
		    txt.add(text1);
		    txt.add(text2);
			txt.add(text3);
			txt.add(text4);
			txt.add(text5); 
			txt.add(text6);
		    txt.add(text7);
			txt.add(text8);
			txt.add(text9);
			txt.add(text10); 
			txt.add(text11);
			txt.add(text12);
			txt.add(text13); 
			txt.add(text14);
			txt.add(text15);
			txt.add(text16);
			txt.add(text17);
			txt.add(text18); 
			txt.add(text19);
			txt.add(text20);
			txt.add(text21); 
			txt.add(text22);
			txt.add(text23);
			txt.add(text24);
			txt.add(text25);
			txt.add(text26); 
			txt.add(text27);
			txt.add(text28);
			txt.add(text29);
			txt.add(text30);
			txt.add(text31); 
			txt.add(text32);
			
			return txt;

		}
	
	     /**
	 	 * Draws all strings that are contained in the rules of the game.
	 	 */
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ArrayList<String> list_txt = list_Txt();
			int delta = 15;
			int count = 0;
			for (String txt : list_txt) {
				delta = delta + 15;
				if (count == 0) {
					g.setFont(new Font("Arial", Font.HANGING_BASELINE, 20));
					
				} else if (count == 1 || count == 6 || count == 12) {
					delta =delta + 15;
					g.setFont(new Font("Arial", Font.ITALIC, 17));
				} else {
					g.setFont(new Font("Arial", Font.ITALIC, 12));
				}
				g.drawString(txt, 15, delta);
				count++;
			}
		}
	
		/**
		 * Checks what size of the window is selected in VorauswahlPanel
		 * @return size of the window M
		 */	
	private String SizeWindow() {
			if (VorauswahlPanel.Sizewindow == 0)
				return "S";
	
			if (VorauswahlPanel.Sizewindow == 1)
				return "M";
			if (VorauswahlPanel.Sizewindow == 2)
				return "L";

			    return "M";
		}
		/**
		 * Checks what speed  is selected in VorauswahlPanel
		 * depending on the selected time interval
		 * @return time interval 14 s if speed 700 is selected
		 * @return time interval 20 s if speed 1000 is selected
		 * @return time interval 30 s if speed 1500 is selected
		 * @return time interval 20 s
		 */	
	private String Time_Interval()  {
		if (VorauswahlPanel.chosen_speed == 700)
			return "14 s";
		if (VorauswahlPanel.chosen_speed == 1000)
			return "20 s";
		if (VorauswahlPanel.chosen_speed == 1500)
			return "30 s";
		    return "20 s";
		    
	}
	    /**
	     * Checks what number of the parking space is selected in VorauswahlPanel
	     * depending on the selected Stein size k
	     * @return number of the parking space 2 if k = 3 is selected
	     * @return number of the parking space 4 if k = 4 is selected
	     * @return number of the parking space 9 if k = 5 is selected
	     * @return number of the parking space 4
	     */	
	private String ParkPlatz() {
		
		if(VorauswahlPanel.k == 3) 
			return "2";
		
		if(VorauswahlPanel.k == 4) 
			return "4";
		
		if(VorauswahlPanel.k == 5) 
			return "9";
		
            return "4";
		}
		
	    /**
         * Checks if the acceleration is selected in VorauswahlPanel then
         * @return the string "findet alle 40 ms statt"
         * else
         * @return the string "erhoeht sich nicht"
         */
	        private String Acceleration() {
		if (VorauswahlPanel.acceleration)
			return "findet alle 40 ms statt.";
		else
			return "erhoeht sich nicht. ";
	}

		@Override
	public void windowOpened(WindowEvent e) {
		VorauswahlPanel.pause = true;
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		VorauswahlPanel.pause = false;
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
		
	}

	
	
}
