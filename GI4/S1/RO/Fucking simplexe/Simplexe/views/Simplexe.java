package views;

import java.io.*;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Vector;
import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.event.*;
import java.awt.print.PrinterException;

import util.URessource;

@SuppressWarnings("unused")

/**
 * Fenêtre principale de l'application.
 */

public class Simplexe extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Mode courant
	 **/
	private int currentMode ;
	
	/**
	 * Models logiques de cette composante physique.
	 **/
	private models.Simplexe simplex;	
	
	/**
	 * ToolBar
	 **/
	JMenuBar barMenu;
	JMenu fileMenu,editionMenu,helpMenu;
	JMenuItem aboutItem,exitItem;
	JMenuItem resetConfigItem,applyItem;
	JMenuItem cancelItem,runItem,resetItem;
	JMenuItem firstItem,previousItem,playItem,nextItem,lastItem;
	JMenuItem clearItem,printItem;
	JMenuItem loadItem;
	
	/** 
	 * Panneau de configuration 
	 **/
	JPanel configPanel ;
	JLabel variableLabel,constraintLabel;
	JSlider variableSlider,constraintSlider;
	JLabel functionNameLabel, varLabel; //  identifiants de la function objective et des variables.
	JTextField functionText,variableText;// valeur des identifiants de la function objective et des variables.
	JButton resetConfigButton,applyButton;
	
	/**
	 *  Panneau d'édition.
	 **/
	JPanel editionPanel;
	JTable table;
	JButton cancelButton,runButton,resetButton;
	JButton firstButton,previousButton,playButton,nextButton,lastButton;
	
	/**
	 * Panneau des traces d'exécution.
	 **/
	JPanel outputPanel;
	JTextArea output;
	JButton clearButton, printButton;
	
	/**
	 * StatusBar
	 **/	
	 JLabel statusBar;
	 
	 /**
	  *<p>Booléen permettant de détecter quel est l'état du bouton playButton.
	  *Si il est en stop (par défaut) ou dans l'état play.
	  */
	 private boolean play = false;
	
	 /**
	  * <p> Variable permettant de sélectionner resp. la ligne, la colonne et l'elt pivot
	  */
	 private int selectedRow=-1,selectedColumn=-1,select=0 ;
	 
	 /**
	  * Thread de simulation du simplexe.
	  */
	 private SimplexeThread thread=null;
	 private boolean execution = false ;
	 
	/**
	 * Construit la fenêtre principale de l'application.
	 * @throws IOException
	 **/
	
	
	public Simplexe() throws IOException {
	
		this.setTitle("Méthode du Simplexe sur Probléme de PL sous forme Standard");		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(URessource.WHITE_COLOR);		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(250,30,700,730);
		this.setLocation(250, 30);
		this.setIconImage(URessource.createIcon("logo.png","ressource").getImage());
		this.setResizable(false);
	
		table = new JTable();
		simplex = new models.Simplexe(null,0,0);
		buildMenu();
		buildStatusBar();
		builMainPanel();
		updateInterface(URessource.CONFIGURATION_MODE);
		//this.pack();
	}
	
	
	/**
	 * Construit le menu.
	 */
	private void buildMenu() {
		
		barMenu = new JMenuBar();		
		
		fileMenu = new JMenu("Fichier");
		fileMenu.setMnemonic('F');
		//fileMenu.setAccelerator(KeyStroke.getKeyStroke("control "+'F'));
		
		aboutItem = new JMenuItem("A Propos.",URessource.createIcon("about-ico.png","ressource"));		//
		aboutItem.setDisabledIcon(URessource.createIcon("about2-ico.png","ressource"));
		//aboutItem.setEnabled(false);
		aboutItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aboutItemAction(e);
			}
		});
		fileMenu.add(aboutItem);
		
		fileMenu.addSeparator();
		
		exitItem = new JMenuItem("Quitter",URessource.createIcon("exit-ico.png","ressource"));
		exitItem.setDisabledIcon(URessource.createIcon("exit2-ico.png","ressource"));
		//exitItem.setEnabled(false);
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitItemAction(e);
			}
		});
		fileMenu.add(exitItem);
		barMenu.add(fileMenu);
		
		editionMenu = new JMenu("Edition");
		editionMenu.setMnemonic('E');
		
		resetConfigItem = new JMenuItem("Réinitialiser les paramétres",URessource.createIcon("reset-ico.png","ressource"));
		resetConfigItem.setDisabledIcon(URessource.createIcon("reset2-ico.png","ressource"));
		//resetConfigItem.setEnabled(false);
		resetConfigItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetConfigAction(e);
			}
		});
		editionMenu.add(resetConfigItem);
		
		applyItem = new JMenuItem("Editer la matrice",URessource.createIcon("edit-ico.png","ressource"));
		applyItem.setDisabledIcon(URessource.createIcon("edit2-ico.png","ressource"));
		//applyItem.setEnabled(false);
		applyItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				applyAction(e);
			}
		});
		editionMenu.add(applyItem);
		
		editionMenu.addSeparator();
		
		cancelItem = new JMenuItem("Paramétrer la matrice",URessource.createIcon("cancel-ico.png","ressource"));
		cancelItem.setDisabledIcon(URessource.createIcon("cancel2-ico.png","ressource"));
		//cancelItem.setEnabled(false);
		cancelItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cancelAction(e);
			}
		});
		editionMenu.add(cancelItem);
						
		runItem = new JMenuItem("Simulation",URessource.createIcon("run-ico.png","ressource"));
		runItem.setDisabledIcon(URessource.createIcon("run2-ico.png","ressource"));
		//runItem.setEnabled(false);
		runItem.setToolTipText("Ouvre l'espace de simulation.");
		runItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				runAction(e);
			}
		});
		editionMenu.add(runItem);
		
		resetItem = new JMenuItem("Réinitialiser la matrice",URessource.createIcon("reset-ico.png","ressource"));
		resetItem.setDisabledIcon(URessource.createIcon("reset2-ico.png","ressource"));
		//resetItem.setEnabled(false);
		resetItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetAction(e);
			}
		});
		editionMenu.add(resetItem);
		
		editionMenu.addSeparator();
		
		firstItem = new JMenuItem("Simuler Premiére Itéraion",URessource.createIcon("first-ico.png","ressource"));
		firstItem.setDisabledIcon(URessource.createIcon("first2-ico.png","ressource"));
		//firstItem.setEnabled(false);
		firstItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				firstAction(e);
			}
		});
		editionMenu.add(firstItem);
		
		previousItem = new JMenuItem("Simuler Itéraion Précédente",URessource.createIcon("previous-ico.png","ressource"));
		previousItem.setDisabledIcon(URessource.createIcon("previous2-ico.png","ressource"));
		//previousItem.setEnabled(false);
		previousItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				previousAction(e);
			}
		});
		editionMenu.add(previousItem);
		
		playItem = new JMenuItem("Play/Stop Simulation ",URessource.createIcon("play-ico.png","ressource"));
		playItem.setDisabledIcon(URessource.createIcon("play2-ico.png","ressource"));
		//playItem.setEnabled(false);
		playItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				playAction(e);
			}
		});
		editionMenu.add(playItem);
		
		nextItem = new JMenuItem("Simuler Itéraion Suivante",URessource.createIcon("next-ico.png","ressource"));
		nextItem.setDisabledIcon(URessource.createIcon("next2-ico.png","ressource"));
		//nextItem.setEnabled(false);
		nextItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextAction(e);
			}
		});
		editionMenu.add(nextItem);
		
		lastItem = new JMenuItem("Simuler Derniére Itéraion",URessource.createIcon("last-ico.png","ressource"));
		lastItem.setDisabledIcon(URessource.createIcon("last2-ico.png","ressource"));
		//lastItem.setEnabled(false);
		lastItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lastAction(e);
			}
		});
		editionMenu.add(lastItem);
		
		editionMenu.addSeparator();
		
		clearItem = new JMenuItem("Effacer les Traces d'Exécution",URessource.createIcon("reset-ico.png","ressource"));
		clearItem.setDisabledIcon(URessource.createIcon("reset2-ico.png","ressource"));
		//clearItem.setEnabled(false);
		clearItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearAction(e);
			}
		});
		editionMenu.add(clearItem);
		
		printItem = new JMenuItem("Imprimer les Traces d'Exécution",URessource.createIcon("print-ico.png","ressource"));
		printItem.setDisabledIcon(URessource.createIcon("print2-ico.png","ressource"));
		//printItem.setEnabled(false);
		printItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				printAction(e);
			}
		});
		editionMenu.add(printItem);
		
		barMenu.add(editionMenu);
		
		
		helpMenu = new JMenu("Aide");
		helpMenu.setMnemonic('A');
		
		loadItem = new JMenuItem ("Charger Exemple Test",URessource.createIcon("help-ico.png","ressource"));
		loadItem.setDisabledIcon(URessource.createIcon("help2-ico.png","ressource"));
		//loadItem.setEnabled(false);
		loadItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadAction(e);
			}
		});
		helpMenu.add(loadItem);
		barMenu.add(helpMenu);
		
		this.setJMenuBar(barMenu);
	}
	
	
	/**
	 * Construit la barre des tâches.
	 **/
	private void buildStatusBar() {
		statusBar = new JLabel(URessource.CONFIGURATION_MESSAGE);		
		this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	}
	
	/**
	 * Construit le panneau central de l'application.
	 */
	private void builMainPanel() {
		
		buildConfigPanel();
		buildEditionPanel();
		buildOutputPanel();
		
		//configPanel.setSize(700, 100);
		this.getContentPane().add(configPanel,BorderLayout.NORTH);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(editionPanel,BorderLayout.CENTER);
		panel.add(outputPanel,BorderLayout.SOUTH);
		this.getContentPane().add(panel,BorderLayout.CENTER);
		
	}
	
	/**
	 * Construit le Panneau de configuration.
	 **/
	private void buildConfigPanel() {		
		
		functionNameLabel = new JLabel("Fonction Objective");				
		functionText = new JTextField();		
		functionText.setToolTipText("Représente l'indentifiant qui sera utilisée pour la fonction Objective.");
		functionText.addFocusListener(new FocusAdapter(){					
			public void focusLost(FocusEvent e) {		
				if(currentMode==URessource.CONFIGURATION_MODE) createTable();
			}
			
		});
		
		varLabel = new JLabel("Identifiant Variable");		
		variableText = new JTextField();		
		variableText.setToolTipText("Représente l'indentifiant qui sera utilisée pour construire les identifiants des Variables.");
		variableText.addFocusListener(new FocusAdapter(){					
			public void focusLost(FocusEvent e) {		
				if(currentMode==URessource.CONFIGURATION_MODE) createTable();
			}
			
		});
		
		variableLabel = new JLabel("Nombre de Variables");		
		variableSlider = new JSlider(1,10);
		variableSlider.setPaintTicks(true);
		variableSlider.setMajorTickSpacing(1);
		variableSlider.setMinorTickSpacing(1);		
		variableSlider.setPaintLabels(true);
		
		variableSlider.addChangeListener(new ChangeListener(){		
			public void stateChanged(ChangeEvent e) {
				sliderAction(e,"Nombre de Variables");				
			}
		});
		
		constraintLabel = new JLabel("Nombre de Contraintes");		
		constraintSlider = new JSlider(1,10);
		constraintSlider.setPaintTicks(true);
		constraintSlider.setMajorTickSpacing(1);
		constraintSlider.setMinorTickSpacing(1);		
		constraintSlider.setPaintLabels(true);
		
		constraintSlider.addChangeListener(new ChangeListener(){		
			public void stateChanged(ChangeEvent e) {
				sliderAction(e,"Nombre de Contraintes");				
			}
		});
		
		resetConfigButton = new JButton();
		resetConfigButton.setFont(new Font("Times New Roman", 0, 14));
		resetConfigButton.setIcon(URessource.createIcon("reset.png","ressource"));
		resetConfigButton.setRolloverEnabled(true);
		resetConfigButton.setRolloverIcon(URessource.createIcon("reset3.png","ressource"));
		resetConfigButton.setDisabledIcon(URessource.createIcon("reset2.png","ressource"));
		resetConfigButton.setContentAreaFilled(false);
		resetConfigButton.setMargin(new Insets(0,0,0,0));
		resetConfigButton.setBorderPainted(false);
		resetConfigButton.setToolTipText("Réinitialiser les paramétres");
		resetConfigButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				resetConfigAction(e);
			}
		});
		
		applyButton = new JButton();
		applyButton.setFont(new Font("Times New Roman", 0, 14));
		applyButton.setIcon(URessource.createIcon("edit.png","ressource"));
		applyButton.setRolloverEnabled(true);
		applyButton.setRolloverIcon(URessource.createIcon("edit3.png","ressource"));
		applyButton.setDisabledIcon(URessource.createIcon("edit2.png","ressource"));
		applyButton.setContentAreaFilled(false);
		applyButton.setMargin(new Insets(0,0,0,0));
		applyButton.setBorderPainted(false);
		applyButton.setToolTipText("Editer la matrice");
		applyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				applyAction(e);
			}
		});		
		
		resetConfigAction(null);
		
		this.configPanel = new JPanel(new BorderLayout());		
		configPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," Configuration "));
				
		JPanel panel;
		JPanel pan,pan1,pan2;
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT,35,5));
		//functionNameLabel.setPreferredSize(new Dimension(150,20));
		panel.add(functionNameLabel);
		functionText.setPreferredSize(new Dimension(500,20));
		panel.add(functionText);
		configPanel.add(panel,BorderLayout.NORTH);
		
		pan = new JPanel(new BorderLayout());
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT,35,5));
		//varLabel.setPreferredSize(new Dimension(150,20));
		panel.add(varLabel);
		variableText.setPreferredSize(new Dimension(500,20));
		panel.add(variableText);
		pan.add(panel,BorderLayout.NORTH);
		
		pan1 = new JPanel(new BorderLayout());
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT,25,5));
		//variableLabel.setPreferredSize(new Dimension(150,20));
		panel.add(variableLabel);
		//variableSlider.setPreferredSize(new Dimension(500,80));
		panel.add(variableSlider);
		pan1.add(panel,BorderLayout.NORTH);		
		
		pan2 = new JPanel(new BorderLayout());
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));
		//constraintLabel.setPreferredSize(new Dimension(150,20));
		panel.add(constraintLabel);
		panel.add(constraintSlider);
		pan2.add(panel,BorderLayout.NORTH);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,5));		
		panel.add(resetConfigButton);
		panel.add(applyButton);
		
		pan2.add(panel,BorderLayout.SOUTH);
		pan1.add(pan2,BorderLayout.SOUTH);		
		pan.add(pan1,BorderLayout.SOUTH);
		configPanel.add(pan,BorderLayout.SOUTH);				
	}
	
	
	/**
	 * Affecte tous les champs contenus dans le panneau de configuration à enabled
	 * @param enabled Booléen indiquant l'état des composants de configurationPanel
	 */
	private void setEnableToConfigPanel(boolean enabled){
		functionText.setEnabled(enabled);
		variableText.setEnabled(enabled);
		variableSlider.setEnabled(enabled);
		constraintSlider.setEnabled(enabled);
		resetConfigButton.setEnabled(enabled);
		applyButton.setEnabled(enabled);
	}
	
	/**
	 * Construit le Panneau d'édition et de Simulation.
	 **/
	private void buildEditionPanel() {
		this.editionPanel = new JPanel(new BorderLayout());
		editionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tableau Simplexe"));		
		createTable();
		JScrollPane scrollPane = new JScrollPane(table);
		editionPanel.add(scrollPane,BorderLayout.CENTER);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,25,5));
		
		cancelButton = new JButton();
		cancelButton.setFont(new Font("Times New Roman", 0, 14));
		cancelButton.setIcon(URessource.createIcon("cancel.png","ressource"));
		cancelButton.setRolloverEnabled(true);
		cancelButton.setRolloverIcon(URessource.createIcon("cancel3.png","ressource"));
		cancelButton.setDisabledIcon(URessource.createIcon("cancel2.png","ressource"));
		cancelButton.setContentAreaFilled(false);
		cancelButton.setMargin(new Insets(0,0,0,0));
		cancelButton.setBorderPainted(false);
		cancelButton.setToolTipText("Retour au mode précédent.");
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cancelAction(e);
			}
		});
		panel.add(cancelButton);
		
		runButton = new JButton();
		runButton.setFont(new Font("Times New Roman", 0, 14));
		runButton.setIcon(URessource.createIcon("run.png","ressource"));
		runButton.setRolloverEnabled(true);
		runButton.setRolloverIcon(URessource.createIcon("run2.png","ressource"));
		runButton.setDisabledIcon(URessource.createIcon("run3.png","ressource"));
		runButton.setContentAreaFilled(false);
		runButton.setMargin(new Insets(0,0,0,0));
		runButton.setBorderPainted(false);
		runButton.setToolTipText("Aller au mode Simulation.");
		runButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				runAction(e);
			}
		});
		panel.add(runButton);
		
		resetButton = new JButton();
		resetButton.setFont(new Font("Times New Roman", 0, 14));
		resetButton.setIcon(URessource.createIcon("reset.png","ressource"));
		resetButton.setRolloverEnabled(true);
		resetButton.setRolloverIcon(URessource.createIcon("reset3.png","ressource"));
		resetButton.setDisabledIcon(URessource.createIcon("reset2.png","ressource"));
		resetButton.setContentAreaFilled(false);
		resetButton.setMargin(new Insets(0,0,0,0));
		resetButton.setBorderPainted(false);
		resetButton.setToolTipText("Réinitialiser la matrice.");
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				resetAction(e);
			}
		});
		panel.add(resetButton);
		
		firstButton = new JButton();
		firstButton.setFont(new Font("Times New Roman", 0, 14));
		firstButton.setIcon(URessource.createIcon("first2.png","ressource"));
		firstButton.setRolloverEnabled(true);
		firstButton.setRolloverIcon(URessource.createIcon("first.png","ressource"));
		firstButton.setDisabledIcon(URessource.createIcon("first3.png","ressource"));
		firstButton.setContentAreaFilled(false);
		firstButton.setMargin(new Insets(0,0,0,0));
		firstButton.setBorderPainted(false);
		firstButton.setToolTipText("Simuler la premiére Itération.");
		firstButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				firstAction(e);
			}
		});
		panel.add(firstButton);
		
		previousButton = new JButton();
		previousButton.setFont(new Font("Times New Roman", 0, 14));
		previousButton.setIcon(URessource.createIcon("previous2.png","ressource"));
		previousButton.setRolloverEnabled(true);
		previousButton.setRolloverIcon(URessource.createIcon("previous.png","ressource"));
		previousButton.setDisabledIcon(URessource.createIcon("previous3.png","ressource"));
		previousButton.setContentAreaFilled(false);
		previousButton.setMargin(new Insets(0,0,0,0));
		previousButton.setBorderPainted(false);
		previousButton.setToolTipText("Simuler l'itération précédente.");
		previousButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				previousAction(e);
			}
		});
		panel.add(previousButton);
		
		playButton = new JButton();
		playButton.setFont(new Font("Times New Roman", 0, 14));
		playButton.setIcon(URessource.createIcon("play.png","ressource"));
		playButton.setRolloverEnabled(true);
		playButton.setRolloverIcon(URessource.createIcon("play2.png","ressource"));
		playButton.setDisabledIcon(URessource.createIcon("play3.png","ressource"));
		playButton.setContentAreaFilled(false);
		playButton.setMargin(new Insets(0,0,0,0));
		playButton.setBorderPainted(false);
		playButton.setToolTipText("Simuler l'algorithme du simplexe.");
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				playAction(e);
			}
		});
		panel.add(playButton);
		
		nextButton = new JButton();
		nextButton.setFont(new Font("Times New Roman", 0, 14));
		nextButton.setIcon(URessource.createIcon("next2.png","ressource"));
		nextButton.setRolloverEnabled(true);
		nextButton.setRolloverIcon(URessource.createIcon("next.png","ressource"));
		nextButton.setDisabledIcon(URessource.createIcon("next3.png","ressource"));
		nextButton.setContentAreaFilled(false);
		nextButton.setMargin(new Insets(0,0,0,0));
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText("Simuler l'itération suivante.");
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				nextAction(e);
			}
		});
		panel.add(nextButton);
		
		lastButton = new JButton();
		lastButton.setFont(new Font("Times New Roman", 0, 14));
		lastButton.setIcon(URessource.createIcon("last2.png","ressource"));
		lastButton.setRolloverEnabled(true);
		lastButton.setRolloverIcon(URessource.createIcon("last.png","ressource"));
		lastButton.setDisabledIcon(URessource.createIcon("last3.png","ressource"));
		lastButton.setContentAreaFilled(false);
		lastButton.setMargin(new Insets(0,0,0,0));
		lastButton.setBorderPainted(false);
		lastButton.setToolTipText("Simuler la derniére itération.");
		lastButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				lastAction(e);
			}
		});
		panel.add(lastButton);
		
		editionPanel.add(panel,BorderLayout.SOUTH);
		
	}
	
	/**
	 * Affecte tous les champs contenus dans le panneau d'édition à enabled
	 * @param enabled Booléen indiquant l'état des composants d' editionPanel
	 */
	private void setEnableToEditionPanel(boolean enabled){
		table.setEnabled(enabled);
		cancelButton.setEnabled(enabled);
		runButton.setEnabled(enabled);
		resetButton.setEnabled(enabled);
		firstButton.setEnabled(enabled);
		previousButton.setEnabled(enabled);
		playButton.setEnabled(enabled);
		nextButton.setEnabled(enabled);
		lastButton.setEnabled(enabled);
	}	
	
		
	/**
	 * Construit le Panneau des sorties.
	 **/
	private void buildOutputPanel(){
		
		outputPanel = new JPanel(new BorderLayout());
		outputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Traces Execution"));

		this.output = new JTextArea();
		output.setRows(5);		
		JScrollPane scrollPane = new JScrollPane(output);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				adjustScrollBar(e);				
			}			
		});
		outputPanel.add(scrollPane,BorderLayout.CENTER);
		
				
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));
		
		clearButton = new JButton();
		clearButton.setFont(new Font("Times New Roman", 0, 14));
		clearButton.setIcon(URessource.createIcon("reset-ico.png","ressource"));
		clearButton.setRolloverEnabled(true);
		clearButton.setRolloverIcon(URessource.createIcon("reset3-ico.png","ressource"));
		clearButton.setDisabledIcon(URessource.createIcon("reset2-ico.png","ressource"));
		clearButton.setContentAreaFilled(false);
		clearButton.setMargin(new Insets(0,0,0,0));
		clearButton.setBorderPainted(false);
		clearButton.setToolTipText("Effacer les Traces d'exécution.");
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				clearAction(e);
			}
		});
		panel.add(clearButton);
		
		printButton = new JButton();
		printButton.setFont(new Font("Times New Roman", 0, 14));
		printButton.setIcon(URessource.createIcon("print-ico.png","ressource"));
		printButton.setRolloverEnabled(true);
		printButton.setRolloverIcon(URessource.createIcon("print3-ico.png","ressource"));
		printButton.setDisabledIcon(URessource.createIcon("print2-ico.png","ressource"));
		printButton.setContentAreaFilled(false);
		printButton.setMargin(new Insets(0,0,0,0));
		printButton.setBorderPainted(false);
		printButton.setToolTipText("Imprimer les Traces d'exécution.");
		printButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				printAction(e);
			}
		});
		panel.add(printButton);
		
		outputPanel.add(panel,BorderLayout.NORTH);
		
		//this.outputPanel.setBackground(Color.CYAN);
	}
	
	private void setEnableToOutputPanel(boolean enabled){
	
		this.output.setEnabled(enabled);
		this.clearButton.setEnabled(enabled);
		this.printButton.setEnabled(enabled);
	}
	
	/**
	 * Met à jour l'interface de l'application selon le mode. 
	 * @param mode Entier représente le mode dont on doit définir l'interface.
	 */
	private void updateInterface(int mode){
		
		switch(mode){
			case URessource.CONFIGURATION_MODE : //resetConfigItem.setEnabled(true);
												 applyItem.setEnabled(true);
												 cancelItem.setEnabled(false);												 
												 runItem.setEnabled(false);
												 resetItem.setEnabled(false);
												 firstItem.setEnabled(false);
												 previousItem.setEnabled(false);
												 playItem.setEnabled(false);
												 nextItem.setEnabled(false);
												 lastItem.setEnabled(false);
												 clearItem.setEnabled(false);
												 printItem.setEnabled(false);
												 loadItem.setEnabled(true);
												 setEnableToConfigPanel(true);
												 setEnableToEditionPanel(false);												 
												 runButton.setVisible(true);
												 //resetButton.setVisible(true);
												 firstButton.setVisible(false);
												 previousButton.setVisible(false);
												 playButton.setVisible(false);
												 nextButton.setVisible(false);
												 lastButton.setVisible(false);
												 setEnableToOutputPanel(false);
												 currentMode = URessource.CONFIGURATION_MODE;
												 setMessage(URessource.CONFIGURATION_MESSAGE);
												 break;
			case URessource.EDITION_MODE 	   : resetConfigItem.setEnabled(false);
												 applyItem.setEnabled(false);
												 cancelItem.setEnabled(true);												 
												 runItem.setEnabled(true);												 
												 firstItem.setEnabled(false);
												 previousItem.setEnabled(false);
												 playItem.setEnabled(false);
												 nextItem.setEnabled(false);
												 lastItem.setEnabled(false);
												 resetItem.setEnabled(true);
												 clearItem.setEnabled(false);
												 printItem.setEnabled(false);
												 loadItem.setEnabled(false);
												 setEnableToConfigPanel(false);
												 setEnableToEditionPanel(true);
												 runButton.setVisible(true);
												 //resetButton.setVisible(true);
												 firstButton.setVisible(false);
												 previousButton.setVisible(false);
												 playButton.setVisible(false);
												 nextButton.setVisible(false);
												 lastButton.setVisible(false);
												 setEnableToOutputPanel(false);
												 currentMode = URessource.EDITION_MODE;
												 setMessage(URessource.EDITION_MESSAGE);
												 break;
			case URessource.SIMULATION_MODE    : resetConfigItem.setEnabled(false);
												 applyItem.setEnabled(false);
												 cancelItem.setEnabled(true);												 
												 runItem.setEnabled(false);												 
												 firstItem.setEnabled(true);
												 previousItem.setEnabled(true);
												 playItem.setEnabled(true);
												 nextItem.setEnabled(true);
												 lastItem.setEnabled(true);
												 resetItem.setEnabled(false);
												 clearItem.setEnabled(true);
												 printItem.setEnabled(true);
												 loadItem.setEnabled(false);
												 setEnableToConfigPanel(false);
												 setEnableToEditionPanel(true);
												 table.setEnabled(false);
												 runButton.setVisible(false);
												 //resetButton.setVisible(false);
												 firstButton.setVisible(true);
												 previousButton.setVisible(true);
												 playButton.setVisible(true);
												 nextButton.setVisible(true);
												 lastButton.setVisible(true);
												 setEnableToOutputPanel(true);
												 currentMode = URessource.SIMULATION_MODE;
												 setMessage(URessource.SIMULATION_MESSAGE);
				 								 break;		
		}
		
	}
	
	/**
	 * 
	 * @param msg Message à affecter à la barre des tâches.
	 **/
	private void setMessage(String msg) {
		statusBar.setText(msg);
	}
	
	/**
	 * Action de l'Item about du menu file.
	 * @param e ActionEvent
	 */
	private void aboutItemAction(ActionEvent e) {		
		JOptionPane.showMessageDialog(this,"Application de l'algorithme du Simplexe sur les Problémes \nde Programmation Linéaires sous forme Standard.", "A Propos",JOptionPane.INFORMATION_MESSAGE);		
	}	
	
	
	/**
	 * Action de l'Item exit du menu file.
	 * @param e ActionEvent.
	 */
	private void exitItemAction(ActionEvent e){	System.exit(0);	}
	
	
	private void  sliderAction(ChangeEvent e,String msg){
		JSlider s = (JSlider) e.getSource();
		this.setMessage(URessource.CONFIGURATION_MESSAGE+" : "+msg+" = "+s.getValue());
		createTable();
	}
	
		/**
	 * Action de réinitialisation des tailles des matrices au taille par défaut.
	 * @param e ActionEvent.
	 */
	private void resetConfigAction(ActionEvent e){
		functionText.setText("Z");
		variableText.setText("x");
		variableSlider.setValue(2);
		constraintSlider.setValue(3);
	}
	
	/**
	 * Action d'application et d' édition de la matrice.
	 * @param e ActionEvent.
	 */
	private void applyAction(ActionEvent e){		
		updateInterface(URessource.EDITION_MODE);
	}
	
	/**
	 * <p>Action de destruction des valeurs du panneau d'édition/simulation
	 * et de remise à zéro de l'interface. On doit aller en mode configuration.
	 * @param e ActionEvent.
	 */
	private void cancelAction(ActionEvent e){		
		
		if(this.currentMode == URessource.SIMULATION_MODE){			
			updateInterface(URessource.EDITION_MODE);
			stopExecution();
		}else{
			table.clearSelection();
			updateInterface(URessource.CONFIGURATION_MODE);
		}
		
	}
	
	/**
	 * <p> Lance le panneau de simulation en affichant l'iteration zéro,
	 * à savoir la modélisation du Probléme.
	 * @param e ActionEvent
	 */
	private void runAction(ActionEvent e){
		models.Matrix matrix = new models.Matrix(table.getRowCount(),table.getColumnCount());
		
		try{		
			int i,j;			
			matrix.setElementAt(0,0, new models.Element(0,0,Double.parseDouble(table.getValueAt(0,0).toString().trim())));
			for(i=1;i<table.getRowCount();i++) {
				for(j=1;j<(table.getColumnCount()-1);j++)
					matrix.setElementAt(i,j, new models.Element(i,j,Double.parseDouble(table.getValueAt(i,j).toString().trim())));
				
			}
			
			for(j=1;j<table.getColumnCount()-1;j++)
				matrix.setElementAt(0,j, new models.Element(0,j,Double.parseDouble(table.getValueAt(0,j).toString().trim()),table.getColumnName(j)));
			
			matrix.setElementAt(0,table.getColumnCount()-1, new models.Element(0,table.getColumnCount()-1,Double.parseDouble(table.getValueAt(0,0).toString().trim()),(String)table.getValueAt(0,table.getColumnCount()-1)));
			j= table.getColumnCount()-1;
			for(i=1;i<table.getRowCount();i++)
				matrix.setElementAt(i,j, new models.Element(i,j,Double.parseDouble(table.getValueAt(i,0).toString().trim()),(String)table.getValueAt(i,j)));
			
			for(i=0;i<table.getRowCount();i++)
				matrix.setElementAt(i,0, new models.Element(i,0,Double.parseDouble(table.getValueAt(i,0).toString().trim())));
			
			simplex.reinit(matrix, variableSlider.getValue() , constraintSlider.getValue());
			simplex.execute();			
			simplex.setCurrent(0);		
			updateInterface(URessource.SIMULATION_MODE);
		}catch(Exception err){
			matrix.clear();
			matrix = null;
			simplex.reinit(null,0, 0);
			JOptionPane.showMessageDialog(this,"Une des valeurs saisies n'est pas un nombre.\n Vérifiez que les nombres sont écrits correctement selon vos paramétres locaux.\nSoit 0.25 soit 0,25.","Attention!!", JOptionPane.WARNING_MESSAGE);			
			return;
		}
	}
			
	/**
	 * <p> Crée un table de taible 1+constraintSlider.getValue() x constraintSlider.getValue()+variableSlider.getValue()+2
	 */
	
	private void createTable() {
			
		//variableSlider.setValue(10);
		//constraintSlider.setValue(10);
		String[] columnNames = new String[2+variableSlider.getValue()+constraintSlider.getValue()];
		columnNames[0] = new String(URessource.RIGHT_MEMBER);
		columnNames[1+variableSlider.getValue()+constraintSlider.getValue()] = new String(URessource.BASIS_VARIABLES+" ");
		
		String idVar = variableText.getText();
		int i,j,k;
		
		for(i=1;i<=(variableSlider.getValue()+constraintSlider.getValue());i++)
			columnNames[i] = new String(idVar+i);			
		
		SimplexeTableModel model = new SimplexeTableModel(); 
		model.setColumnIdentifiers(columnNames);
		
		int count = 1+variableSlider.getValue()+constraintSlider.getValue();
		k = variableSlider.getValue()+1;
		
		for(i=0;i<=constraintSlider.getValue();i++){
			Vector <Object> row = new Vector<Object>();
			for(j=0;j<count;j++){
				//row.add("0");
				if(i!=0 && ( (j-variableSlider.getValue())==i ) ) row.add("1");
				else row.add("0");
			}
			if(i==0) row.add(functionText.getText());
			else row.add(columnNames[k++]);
			model.addRow(row);
		}
		table.setModel(model);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		this.selectedColumn = -1 ;
		this.selectedRow = -1 ;
		table.setDefaultRenderer(Object.class, new SimplexeTableRender());
	}
	
	
	/**
	 * <p>Réinitialise le tableau d'édition et de simulation au valeur vide
	 * et cache les boutons de navigation dans l'exécution, i.e les boutons
	 * first,previous,play,next,last. et les remplace par le bouton run.
	 * On part en mode edition de la matrice.
	 * @param e ActionEvent e
	 */
	private void resetAction(ActionEvent e){
		
		
		if(this.currentMode== URessource.SIMULATION_MODE) 
			stopExecution();
		else{
			int i,j;
			int row = constraintSlider.getValue();
			int column = variableSlider.getValue()+constraintSlider.getValue();
			for(i=0;i<=row;i++){
				for(j=0;j<=column;j++){
					if(i!=0 && ( (j-variableSlider.getValue())==i )) table.setValueAt(1, i, j);
					else table.setValueAt(0, i, j);
				}
			}
		}
		
		
	}
	
	/**
	 * Action de simulation de la premiére Itération
	 * @param e ActionEvent.
	 */
	private void firstAction(ActionEvent e){		
			simuleIteration(0);		
	}
		
	/**
	 * Action de simulation de l'iteration précédent l'itération courante. 
	 * @param e ActionEvent
	 */
	private void previousAction(ActionEvent e) {
		
		if(!simplex.isFirstIteration()){
			simplex.previousIteration();
			simuleIteration(simplex.getCurrentIteration().getIterationNumber()-1);
		}
		
	}
	
	/**
	 * Action de simulation de l'algorithme du simplexe depuis la premiére Itération
	 * à la derniére.
	 * @param e ActionEvent
	 * @throws InterruptedException 
	 */
	private void playAction(ActionEvent e) {	    
		
		setStatus(!play) ;		
		
		if(play) 
			simuleIteration(-1);
		else{			
			if(thread!=null){
				thread.interrupt();
				thread = null;
			}			
		}
	}
	
	private void setStatus(boolean play) {
		
		this.select = 0;
		this.selectedRow = -1;
		this.selectedColumn = -1;
		table.repaint();
		
		this.play = play;
		
		if(play) {
			playButton.setIcon(URessource.createIcon("stop.png","ressource"));
			playButton.setRolloverIcon(URessource.createIcon("stop2.png","ressource"));
			firstButton.setEnabled(false);
			previousButton.setEnabled(false);
			nextButton.setEnabled(false);
			lastButton.setEnabled(false);			
		}else{
			playButton.setIcon(URessource.createIcon("play.png","ressource"));
			playButton.setRolloverIcon(URessource.createIcon("play2.png","ressource"));
			firstButton.setEnabled(true);
			previousButton.setEnabled(true);
			nextButton.setEnabled(true);
			lastButton.setEnabled(true);			
		}
	}
	
	/**
	 * Action de simulation de l'itération suivant l'itération courante.
	 * @param e ActionEvent
	 */
	private void nextAction(ActionEvent e) {
	
		if(!simplex.isLastIteration()){
			simplex.nextIteration();
			simuleIteration(simplex.getCurrentIteration().getIterationNumber()-1);
		}
		
	}
	
	/**
	 * Action de simùulation de la derniére itération.
	 * @param e ActionEvent.
	 */
	private void lastAction(ActionEvent e) {
		simuleIteration(simplex.getIterationCount()-1);
	}
	
	/**
	 * Charge les données d'un exemple.
	 * @param e ActionEvent
	 */
	private void loadAction(ActionEvent e) {	
				
		functionText.setText("Z");
		variableText.setText("x");
		variableSlider.setValue(2);
		constraintSlider.setValue(3);
		createTable();
		table.setValueAt("0", 0, 0);
		table.setValueAt("-3", 0, 1);
		table.setValueAt("-5", 0, 2);
		table.setValueAt("0", 0, 3);
		table.setValueAt("0", 0, 4);
		table.setValueAt("0", 0, 5);
		table.setValueAt("4", 1, 0);
		table.setValueAt("1", 1, 1);
		table.setValueAt("0", 1, 2);
		table.setValueAt("1", 1, 3);
		table.setValueAt("0", 1, 4);
		table.setValueAt("0", 1, 5);
		table.setValueAt("12", 2, 0);
		table.setValueAt("0", 2, 1);
		table.setValueAt("2", 2, 2);
		table.setValueAt("0", 2, 3);
		table.setValueAt("1", 2, 4);
		table.setValueAt("0", 2, 5);
		table.setValueAt("18", 3, 0);
		table.setValueAt("3", 3, 1);
		table.setValueAt("2", 3, 2);
		table.setValueAt("0", 3, 3);
		table.setValueAt("0", 3, 4);
		table.setValueAt("1", 3, 5);
		this.currentMode = URessource.SIMULATION_MODE;	
		runAction(null);
		
	}
	
	/**
	 * Efface la zone d'affichage des traces d'exécution.
	 * @param e ActionEvent.
	 */
	private void clearAction(ActionEvent e) {
		output.setText("");
	}
	
	/**
	 * Lance l'impression de la zone d'affichage des traces d'exécution.
	 * @param e ActionEvent.
	 */
	private void printAction(ActionEvent e){
		
		try {
			this.output.print(new MessageFormat("Algorithme du Simplexe"), new MessageFormat("Algorithme du Simplexe de G.Dantzig"));
		} catch (PrinterException e1) {
			JOptionPane.showMessageDialog(this, "Un Probléme est survenu lors de la tentive d'impression. Vérifiez qu'une imprimente est bien connectée à cette machine.","Attention!!",JOptionPane.WARNING_MESSAGE);
			return;
		}	    
		
	}	
	
	/**
	 * Recharge la matrice Initiale dans le tableau et efface la sortie.
	 * @param matrix
	 * @param rowB
	 * @param rowE
	 * @param columnB
	 * @param columnE
	 */
	private void loadMatrix(models.Matrix matrix,int rowB,int rowE,int columnB,int columnE){
		
		boolean load = (matrix!=null) && rowB>=0 && rowE<matrix.getRowCount() && columnB>=0 && columnE<matrix.getColumnCount() ;
		
		if(load){
			int i,j;
			DecimalFormat nombre = new DecimalFormat("0.00");
			for(i=rowB;i<=rowE;i++){			
				for(j=columnB;j<=columnE;j++){					
					if(j!=(matrix.getColumnCount()-1)){
						if(this.currentMode==URessource.SIMULATION_MODE)
							table.setValueAt(""+nombre.format(matrix.getValueAt(i, j)), i, j);
						else
							table.setValueAt(""+matrix.getValueAt(i, j), i, j);
					}else{
						table.setValueAt(""+matrix.getElementAt(i, j).getName(), i, j);
					}
				}
			}
		}else JOptionPane.showMessageDialog(this,"Load ERREUR. Valeur load ="+load,"Attention!!",JOptionPane.WARNING_MESSAGE);
		
	}
	
	/**
	 * Charge le contenu de la matrix de la ligne rowB à la ligne rowE et de la colonne columnB à celle d'index columnE,
	 * avec un temps de time millisecondes, entre les charges. Elle commence par la ligne row.
	 * 
	 * @param matrix  Matrix dont le contenu doit être chargé.
	 * @param rowB Index de la premiére ligne.
	 * @param rowE Index de la derniére ligne.
	 * @param columnB  Index de la premiére colonne.
	 * @param columnE  Index de la derniére colonne.
	 * @param time Temps entre les charges des cellules
	 * @param row Index de la ligne par laquelle on va commencer le remplissage.
	 * @throws InterruptedException 
	 */
	private void loadMatrix(models.Matrix matrix,int rowB,int rowE,int columnB,int columnE,long time,int row) throws InterruptedException{
		
		boolean load = (matrix!=null) && rowB>=0 && rowE<matrix.getRowCount() && columnB>=0 && columnE<matrix.getColumnCount() && time>=0 ;
		load = load && row >= (-1) && row < matrix.getRowCount(); 
		if(load) {
			int i,j;
			DecimalFormat nombre = new DecimalFormat("0.00");
			if(row!=-1){
				output.setText(output.getText()+"\n--- Calcul de la ligne Pivot.\n");
				for(j=columnB;j<=columnE;j++){					
					this.select = 3;
					this.selectedRow = row ;
					this.selectedColumn = j;
					table.repaint();
					Thread.sleep(time);
					if(j!=(matrix.getColumnCount()-1))
						table.setValueAt(""+nombre.format(matrix.getValueAt(row, j)), row, j);
					else
						table.setValueAt(""+matrix.getElementAt(row, j).getName(), row, j);
					Thread.sleep(time);
					this.select = 0;
					this.selectedRow = -1 ;
					this.selectedColumn = -1;
					table.repaint();
					Thread.sleep(time);
				}				
			}
			
			for(i=rowB;i<=rowE;i++){
				if(i==row) continue ;
				output.setText(output.getText()+"\n--- Calcul de la nouvelle ligne "+(i+1)+".\n");
				for(j=columnB;j<=columnE;j++){
					this.select = 3;
					this.selectedRow = i ;
					this.selectedColumn = j;
					table.repaint();
					Thread.sleep(time);
					if(j!=(matrix.getColumnCount()-1))
						table.setValueAt(""+nombre.format(matrix.getValueAt(i, j)), i, j);
					else
						table.setValueAt(""+matrix.getElementAt(i, j).getName(), i, j);
					Thread.sleep(time);
					this.select = 0;
					this.selectedRow = -1 ;
					this.selectedColumn = -1;
					table.repaint();
					Thread.sleep(time);
				}
			}
		}else JOptionPane.showMessageDialog(this,"PB survenu lors du chargement de la matrice.","Attention!!",JOptionPane.WARNING_MESSAGE);		
	}
	
	
	/**
	 * Simule la sélection de la ligne pivot, de la colonne pivot et de la valeur du pivot
	 * @param rowPivot  Index de la ligne à séléctionner.
	 * @param columnPivot Index de la colonne à sélectionner.
	 * @param time : Temps entre les sélections.
	 * @throws InterruptedException 
	 */
	private void repaintTable(int rowPivot,int columnPivot,long time) throws InterruptedException {
		
		/* Sélection de la colonne pivot */
		this.select = 1 ;
		this.selectedColumn = columnPivot ;
		table.repaint();
		output.setText(output.getText()+"\n--- Sélection de la colonne "+(columnPivot+1)+" comme colonne pivot\n");
		Thread.sleep(time);
		
		/* Sélection de la ligne pivot */
		this.selectedRow = rowPivot ;
		table.repaint();
		output.setText(output.getText()+"\n--- Sélection de la ligne "+(rowPivot+1)+" comme ligne pivot\n");
		Thread.sleep(time);
		
		/* Sélection de la cellule pivot */
		this.select = 2 ;
		table.repaint();
		output.setText(output.getText()+"\n--- Coordonnée de l'élément pivot ("+(rowPivot+1)+","+(columnPivot+1)+"). \n");
		Thread.sleep(time);
		
		/* Remise à zéro */
		this.select = 0 ;
		this.selectedRow = -1 ;
		this.selectedColumn = -1 ;
		table.repaint();
		
	}
	
	/**
	 * Redéfinition de la classe DefaultTableModel, tel que la derniére colonne ne puisse pas
	 * être éditable.
	 **/
	
	class SimplexeTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isCellEditable(int ligne, int colonne){
			return colonne!=(1+variableSlider.getValue()+constraintSlider.getValue()) ;
		}
	}
	
	/**
	 * Redéfinition du Rendu des cellules du Tableau du Simplexe en fonction de 
	 * la ligne pivot et de la colonne pivot.
	 **/
	class  SimplexeTableRender  extends DefaultTableCellRenderer{

		private static final long serialVersionUID = 1L;
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){			
			this.setValue(value);
			setBackground(Color.WHITE);
			if(select==1 && row==selectedRow)this.setBackground(Color.ORANGE);
			if(select==1 && column==selectedColumn) this.setBackground(Color.ORANGE);
			if(select==2 &&row==selectedRow && column==selectedColumn) this.setBackground(Color.CYAN);
			if(select==3 &&row==selectedRow && column==selectedColumn) this.setBackground(Color.GREEN);
			return this;
		}		
		
	}

	/**
	 * Simule l'itération d'index iteration.
	 * Si -1 simule toutes les itérations.
	 * @param iteration Index de l'itération à simuler.
	 */
	private void simuleIteration(int iteration){
	
		if(thread!=null){
			thread.interrupt();
			thread = null;
		}		
		thread = new SimplexeThread(iteration);
		thread.start();
		this.execution = true;
	}
	
	
	private void stopExecution() {
		
		if(thread!=null){
			thread.interrupt();
			thread = null;
			setStatus(false);			
		}	
		execution = false;
		simplex.setCurrent(0);
		models.Matrix matrix = simplex.getCurrentIteration().getInitialMatrix();		
		/*try {*/
			loadMatrix(matrix,0,matrix.getRowCount()-1,0,matrix.getColumnCount()-1);
		//} catch (InterruptedException e) {}		
		clearAction(null);
	
	}

	class SimplexeThread extends Thread {
		
		int index,count;
		long time = 500 ;
		
		public SimplexeThread(int iteration){
		
			if(iteration==-1){
				index = 0 ;
				count = simplex.getIterationCount()-1;
			}else{
				index = iteration ;
				count = iteration;
			}
				
		}
		
		public void run() {		
			try{
				
				int i;
				String message = "";				
				simplex.setCurrent(index);				
				models.SimplexeIteration iteration = simplex.getCurrentIteration();
				models.Matrix matrix = iteration.getInitialMatrix();
				loadMatrix(matrix,0,matrix.getRowCount()-1,0,matrix.getColumnCount()-1);				
				clearAction(null);	
				output.setText("\n                        --- Execution de la méthode du simplexe ---                        \n\n");
				
				for(i=index;i<=count;i++){				   
					Thread.sleep(time+300);
					repaintTable(iteration.getPivot().getRow(),iteration.getPivot().getColumn(),time+400);
					Thread.sleep(time);
					matrix = iteration.getResultMatrix();
					output.setText(output.getText()+"\n--- Calcul des nouvelles Valeurs \n");
					loadMatrix(matrix,0,matrix.getRowCount()-1,0,matrix.getColumnCount()-1,time,iteration.getPivot().getRow());
					output.setText(output.getText()+iteration.toString());
					if(i!=count){
						simplex.nextIteration();
						iteration = simplex.getCurrentIteration();						
					}
				}
				
				if(iteration.isOptimalSolution()) message+="   Solution Optimale atteinte.\n";
				else message+="   Solution non Optimale.\n";
				
				if(iteration.isInfiniteLoop()) message+="   Boucle infinie détectée.\n";
				
				if(iteration.isUnlimitedFunction()) message+=" Fonction Objective illimitée.\n";
				
				JOptionPane.showMessageDialog(null, message, "Résumé Execution",JOptionPane.INFORMATION_MESSAGE);
				setStatus(false);
				execution = false;				
			}catch(InterruptedException e){
				output.setText(output.getText()+"Execution Interrompu");
				JOptionPane.showMessageDialog(null,"\n                        Execution Interrompu                        \n\n","Information",JOptionPane.INFORMATION_MESSAGE);				
			}
		}
	}
	
	private void adjustScrollBar(AdjustmentEvent e){
		JScrollBar scrollPane = (JScrollBar)e.getSource();
		if(execution ){			
			scrollPane.setValue(scrollPane.getMaximum());			
		}
		scrollPane.setValueIsAdjusting(true);
	}
	
	/*public static void main(String args[]) throws IOException{
		new Simplexe().setVisible(true);
	}*/

}
