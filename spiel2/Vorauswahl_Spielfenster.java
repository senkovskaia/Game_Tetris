
package team19.spiel2;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import team19.enumeration.Enumeration;
import team19.enumeration.Vorauswahl;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 * 
 * Vorauswahl_Spielfenster JPanel is created when the user starts Spiel 2.
 * Vorauswahl_Spielfenster is displayed on the right side of the Spielfeld.
 * Class Vorauswahl_Spielfenster implements ActionListener library.
 * This JPanel is subdivided on to 5 sub-panels (panel_1, panel_2, panel_3, panel_4, panel_5). 
 * In these sub-panels user can select options for the Spiel2: 
 * 1) the number of squares in Stein: 3, 4 ,5 
 * 2) the number of colors in which Steine are painted
 * 3) the number of displayed Steine: 1, 2, 3, 4, 5 
 * 4) the number of Umrisse: 2, 3, 4, 5, 6, 7, 8, 9 
 * 5) the speed of Stein: langsam (update per 1500 ms), mittel (update per 1000 ms), schnell (update per 700 ms)
 * 6) the acceleration with which Steine fall: ja or nein
 * 7) the window size: S, M, L
 * 8) the rules of game 
 * 
 * @author Klavdiia Senkovskaia 
 */

public class Vorauswahl_Spielfenster extends JPanel implements ActionListener {
	 
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates array list of booleans which set correct enumeration of Steins
	 */
	public ArrayList<boolean[]> right_enumeration;	
	public int 	amountOfSteins=0;
	int k = 4;
	int acceleration_constant = 30;
	int Sizewindow = 1;
	int park_platz = 4;
	/**
	 * Creates JButtons btn_game_intstruction - the rules of game, btn_start - starts the game with chosen
	 * parameters, Button1, Button2, Button3 - the window size (S, M, L) 
	 */
	public JButton btn_game_intstruction;
	public JButton btn_start;
	public JButton Button1,Button2,Button3;
	
	/**
	 * This boolean checks whether k is selected
	 */
	boolean k_selected = false;	
	
	boolean pause = false;
	Spielanleitung Spielanleitung;
	/**
	 * Creates JLabels for 5 sub-panels
	 */
	JLabel scoreLabel; 
	JLabel l_size_fenster;
	JLabel l_color;
	JLabel l_size;
	JLabel l_number;
	JLabel l_number_outline;
	JLabel l_speed;
	JLabel l_speed_increase;
	JLabel l_warnung;
	/**
	 * Creates JComboBox for the drop down menu
	 */
	JComboBox<String> cb_color;
	JComboBox<String> cb_size_stone;
	JComboBox<String> cb_number_stone;
	JComboBox<String> cb_number_outlines;
	JComboBox<String> cb_speed;
	JComboBox<String> cb_speed_increase;
	
	public final Spielfeld spielfeld;
	/**
	 * Creates the object of enumeration class
	 */
	Enumeration steinEnum;
	/**
	 * Creates array list of booleans which set correct enumeration of Steins
	 */
	public ArrayList<boolean[]> right_enum;
	int amountOfVisibleSteins;
	int chosen_color;
	int number_Umrisse;
	int chosen_speed;
	boolean acceleration;
	/**
	 * This message appears in pop-up window if the game over
	 */
	String message;
	boolean GameCycle = false;
	boolean restart = false;
	
	private GUI_Spiel_2 mother;
	public int squaresz;

	boolean steigerung;
	Timer timer;
	Timer timer_warn;
	int warning_period = 1500;
	
	/**
	 * Vorauswahl_Spielfenster constructor
	 * 
	 * @param GUI_Spiel is the instance of GUI_Spiel_2 (left JPanel) (so we can use
	 *               the methods of GUI_Spiel2)
	 * @param mother is the instance of Spielfeld (JFrame) (connecting
	 *               Vorauswahl_Spielfenster and Spielfeld)
	 */
	public Vorauswahl_Spielfenster(GUI_Spiel_2 GUI_Spiel, Spielfeld spielfeld){ 
		/**
		 * Sets size of the Vorauswahl_Spielfenster panel
		 */
		setPreferredSize(new Dimension(160,52*squaresz));
		
		this.spielfeld = spielfeld;	
		this.mother = GUI_Spiel;
		
		/**
		 * Makes a GridLayout to structure the big and small panels
		 */
		this.setLayout(new GridLayout(5,1));
		/**
		 * Sets the GridBagLayout for panels 1, 2, 3, 4, 5
		 * 
		 */
		JPanel panel_1 = new  JPanel(new GridBagLayout());
		JPanel panel_2 = new JPanel(new GridBagLayout());
		JPanel panel_3 = new JPanel(new GridBagLayout());
		JPanel panel_4 = new JPanel(new GridBagLayout());
		JPanel panel_5 = new JPanel(new GridBagLayout());
		/**
		 * Specify constraints c
		 * These constrains will be used for the sub-panels 1, 2, 3, 4, 5
		 * 4 and for the buttons btn_rnd_choice, btn_start
		 */
		GridBagConstraints c = new GridBagConstraints();
		/**
		 * Adds sub-panels to the main panel Vorauswahl_Spielfenster
		 */
		this.add(panel_1);
		this.add(panel_2);
		this.add(panel_3);
		this.add(panel_4);
		this.add(panel_5);
		/**
		 * Sets background colors for the sub-panels
		 */
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_5.setBackground(Color.LIGHT_GRAY);
		l_warnung = new JLabel("Unzulaessige Stelle");
		l_warnung.setForeground(Color.LIGHT_GRAY);               	//Color.LIGHT_GRAY
		l_warnung.setFont(new Font("Arial", Font.BOLD, 13));
		/**
		 * Creates button for the rules of game, start button (btn_start)
		 * and buttons the window size 
		 * Adds an ActionListener to Button1, Button2, Button3
		 * 
		 */
		btn_game_intstruction = new JButton("Spielanleitung");
		btn_start = new JButton("Start");
		Button1 = new JButton("S");
		Button2 = new JButton("M");
		Button3 = new JButton("L");
		
		Button1.addActionListener(new ActionListener() {
			@Override		public void actionPerformed(ActionEvent e) {
				int prev_squaresz = mother.squaresz;
				mother.squaresz = 10;
				mother.refreshSize(1, prev_squaresz);
				Sizewindow = 0;
			}
		});
		
		Button2.addActionListener(new ActionListener() {
			@Override		public void actionPerformed(ActionEvent e) {
				int prev_squaresz = mother.squaresz;
				mother.squaresz = 12;
				mother.refreshSize(2, prev_squaresz);
				Sizewindow = 1;
			}
		});
		
		Button3.addActionListener(new ActionListener() {
			@Override		public void actionPerformed(ActionEvent e) {
				int prev_squaresz = mother.squaresz;
				mother.squaresz = 15;
				mother.refreshSize(3, prev_squaresz);
				Sizewindow = 2;
			}
		});
	
		/**
		 * 
		 * Adds an ActionListener to btn_start, btn_game_instruction
		 * and sets focusable
		 */
		btn_start.setFocusable(false);
		btn_game_intstruction.setFocusable(false);
		Button1.setFocusable(false);
		Button2.setFocusable(false);
		Button3.setFocusable(false);
		
		btn_game_intstruction.addActionListener(this);
		btn_start.addActionListener(this);
		Button1.addActionListener(this);
		Button2.addActionListener(this);
		Button3.addActionListener(this);
		
		/**
		 * Creates labels to the panels 1, 2, 3, 4 and 5
		 */
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
		l_size_fenster = new JLabel("Größe:");
		l_color = new JLabel("Farbe der Fallsteine:");
		l_size = new JLabel("Größe der Fallsteine:");
		l_number = new JLabel("Anzahl der Fallsteine:");
		l_number_outline = new JLabel("Anzahl der Umrisse:");
		l_speed = new JLabel("Fallgeschwindigkeit:");
		l_speed_increase = new JLabel("Steigerung:");
		
		/**
		 * Creates list of all possible parameters in Vorauswahl_Spielfenster. Creates a
		 * JComboBoxs with this list, adds an ActionListener to the JComboBox
		 * 
		 */
		String[] choices_stone = {"3", "4" , "5"};
		cb_size_stone = new JComboBox<String>(choices_stone);
		cb_size_stone.setSelectedItem("4");
		cb_size_stone.addActionListener(this);
		cb_size_stone.setFocusable(false);
		
		String[] choices_color = {"1", "2", "3", "4" , "5"};
		cb_color = new JComboBox<String>(choices_color);
		cb_color.setSelectedItem("4");
		cb_color.addActionListener(this);
		cb_color.setFocusable(false);
		
		
		String[] choices_number_stone = {"1", "2", "3", "4" , "5"};
		cb_number_stone = new JComboBox<String>(choices_number_stone);
		cb_number_stone.setSelectedItem("4");
		cb_number_stone.addActionListener(this);
		cb_number_stone.setFocusable(false);
	
		String[] choices_number_Umrisse = {"2", "3", "4" , "5", "6", "7", "8", "9"};
		cb_number_outlines = new JComboBox<String>(choices_number_Umrisse);
		cb_number_outlines.setSelectedItem("8");
		cb_number_outlines.addActionListener(this);
		cb_number_outlines.setFocusable(false);
		
		String[] choices_speed = {"langsam (1500 ms)", "mittel (1000 ms)" , "schnell (700 ms)"};
		cb_speed = new JComboBox<String>(choices_speed);
		cb_speed.setSelectedIndex(1);
		cb_speed.addActionListener(this);
		cb_speed.setFocusable(false);
		
		String[] choices_speed_increase = {"ja", "nein"};
		cb_speed_increase = new JComboBox<String>(choices_speed_increase);
		cb_speed_increase.setSelectedItem("nein");
		cb_speed_increase.addActionListener(this);
		cb_speed_increase.setFocusable(false);
		
		
	   
		/**
		 * Specifies constraints to the labels , buttons, JComboboxes
		 * adds all these items into the corresponding panels 1, 2, 3, 4, 5
		 */
		
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 0, 5);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    panel_1.add(l_warnung,c);
	    c.gridy = 1;
		panel_1.add(scoreLabel,c);
		c.gridy = 2;
		panel_2.add(l_size, c);
		c.gridy = 4;
	    panel_2.add(cb_size_stone, c);
	    c.gridy = 5;
		panel_2.add(l_color,c);
		c.gridy = 6;
		panel_2.add(cb_color, c);
		
		c.gridy = 0;
		
		panel_3.add(l_number,c);
		c.gridy = 1;
		panel_3.add(cb_number_stone, c);
		c.gridy = 2;
		panel_3.add(l_number_outline,c);
		c.gridy = 3;
		panel_3.add(cb_number_outlines, c);
		c.gridy = 0;
		panel_4.add(l_speed,c);
		c.gridy = 1;
		panel_4.add(cb_speed, c);
		c.gridy = 2;
		panel_4.add(l_speed_increase,c);
		c.gridy = 3; 
		panel_4.add(cb_speed_increase,c);
		c.gridy = 0;
		c.gridx = 0;
		
		
		panel_5.add(l_size_fenster,c);
	    c.gridy = 1;
	    c.gridx = 0; 
		panel_5.add(Button1,c);
	    c.gridy = 1;
	    c.gridx = 1; 
	    panel_5.add(Button2,c);
	    c.gridy = 1;
		c.gridx = 2;
		panel_5.add(Button3,c);
	
	
		c.gridx = 0;
		c.gridwidth =3;
		c.gridy = 2;
		panel_5.add(btn_game_intstruction, c);
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		panel_5.add(btn_start, c);
	
	}
	
	public void start_count() {
		
		if (GameCycle) { 
			timer = new Timer(0, new ActionListener() { //chosen_speed
				
				@Override
				public void actionPerformed(ActionEvent e) {
					newscore(spielfeld.score);
					if (spielfeld.pause == true) {
						Button1.setEnabled(true);
						Button2.setEnabled(true);
						Button3.setEnabled(true);
						repaint();
					}
					else {
						setButtonsEnabled(false);
						repaint();
					}
					
					if(spielfeld.warnung) {
						ShowWarnung(warning_period);
						repaint();	
					}
					if(spielfeld.exit) {
						setVisible(false);
						
					}
				}
				});
			timer.setDelay(chosen_speed);
			timer.setRepeats(true);
			timer.start();
			restart = true;
		}
	}
		
	
	public void ShowWarnung(int time) {
		
		if (timer_warn!=null) {
			timer_warn.stop();
			timer_warn = null;
			l_warnung.setForeground(Color.LIGHT_GRAY);
			spielfeld.warnung = false;
		}
		else {
			timer_warn = new Timer(time, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				 	l_warnung.setForeground(Color.RED); 
				}
			});
			//timer.setDelay(time);
			timer_warn.setRepeats(false);
			timer_warn.start();
		}
	 }
		
	
	public void newscore(int score) {
		scoreLabel.setText("Score: " + String.valueOf(score));	
		if(spielfeld.warnung) {
			l_warnung.setForeground(Color.RED); 
		}
	}
	
	
	public void setButtonsEnabled(boolean bool) {
		btn_start.setEnabled(bool);
		cb_size_stone.setEnabled(bool);
		cb_color.setEnabled(bool);
		cb_number_stone.setEnabled(bool);
		cb_number_outlines.setEnabled(bool);
		cb_speed.setEnabled(bool);
		cb_speed_increase.setEnabled(bool);
		Button1.setEnabled(bool);
		Button2.setEnabled(bool);
		Button3.setEnabled(bool);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		k = Integer.parseInt((String) cb_size_stone.getSelectedItem());
		chosen_color = Integer.parseInt((String) cb_color.getSelectedItem());
		
		
		int speed_index = cb_speed.getSelectedIndex();
		if(speed_index == 0) {
			chosen_speed = 1500;
		}
		else if(speed_index == 1) {
			chosen_speed = 1000;
		}
		else {chosen_speed = 700;};
		
	    //System.out.println("chosen_speed =" + chosen_speed);
	  	
	    int speed_increase_index = cb_speed_increase.getSelectedIndex();
	    
	    //System.out.println("speed_increase_index = " + speed_increase_index);
	    
	    if(speed_increase_index == 0) { 
	    	acceleration = true;
		}
	    else {acceleration = false;}
	      
	    amountOfVisibleSteins = Integer.parseInt((String) cb_number_stone.getSelectedItem());
	    
	    number_Umrisse = Integer.parseInt((String) cb_number_outlines.getSelectedItem());
	
	if (e.getSource() == btn_start) {
		
		setButtonsEnabled(false);
		if (spielfeld.stein != null) {
			spielfeld.stein = null;
		}
		
		
			
		steinEnum = new Enumeration(k);	 
		boolean standardmenge = false;
		boolean sortiert = false;
		right_enum = steinEnum.getRightEnum(sortiert, standardmenge); 
																	  
		
		spielfeld.chosen_color = chosen_color;
		spielfeld.setSpeed(chosen_speed);
		spielfeld.acceleration = acceleration;
		spielfeld.initPanel();
		spielfeld.setSteins(right_enum, k, amountOfVisibleSteins);
		spielfeld.setUmrisse(right_enum, number_Umrisse);
	
		GameCycle = true;
		start_count();
	
	
	}
	
	if (e.getSource() == Button1) {
		Sizewindow = 0;
	}
	if (e.getSource() == Button2) {
		Sizewindow = 1;
	}	
	if (e.getSource() == Button3) {
		Sizewindow = 2;
		
	}

	if (e.getSource() == btn_game_intstruction) {// && GameCycle
			Spielanleitung = new Spielanleitung(this);
			if(GameCycle) {
			    spielfeld.pause = true;
			    spielfeld.pause();
				spielfeld.requestFocus();
			}
		
	}}}
	 





	


	
	

	



