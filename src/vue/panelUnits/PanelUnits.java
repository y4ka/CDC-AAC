package vue.panelUnits;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import objects.ImageLoader;
import parameters.Enums;
import controller.Controller;


public class PanelUnits extends JPanel implements ActionListener
{
	private Controller controller;
	private ImageLoader imageLoader = new ImageLoader();
	
	private JButton infantry = new JButton();
	private JButton airmobileInfantry = new JButton();
	private JButton mechanizedInfantry = new JButton();
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	
	private ButtonGroup affiliationRadioGroup = new ButtonGroup();
	private JRadioButton radioFriend = new JRadioButton("FRIEND");
	private JRadioButton radioHostile = new JRadioButton("HOSTILE");
	
	public PanelUnits()
	{
		this.setLayout(new GridLayout(0,1));
		this.setBackground(Color.GRAY);
		
		//Add icon to JButtons:
		infantry.setIcon(new ImageIcon("Infantry.png"));
		airmobileInfantry.setIcon(new ImageIcon("airmobileInfantry.png"));
		mechanizedInfantry.setIcon(new ImageIcon("mechanizedInfantry.png"));
		
		//Add components to panel:
		this.add(infantry);
		this.add(airmobileInfantry);
		this.add(mechanizedInfantry);
		this.add(radioFriend);
		this.add(radioHostile);
		
		//Add radioButton group and listener:
		this.affiliationRadioGroup.add(radioFriend);
		this.affiliationRadioGroup.add(radioHostile);
		this.radioFriend.setSelected(true);
		radioFriend.addActionListener(this);
		radioHostile.addActionListener(this);
		
		//Add button listener:
		this.infantry.addActionListener(this);
		this.airmobileInfantry.addActionListener(this);
		this.mechanizedInfantry.addActionListener(this);
		
		//Save all buttons in a List for various treatments:
		this.buttonList.add(infantry);
		this.buttonList.add(airmobileInfantry);
		this.buttonList.add(mechanizedInfantry);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//On désactive les autres boutons de création d'unité
		//disableOtherButtons(e);
		
		//On passe le controller en mode création d'unité:
		controller.setControllerMode(Enums.Mode.UNIT_CREATION);
		
		if (e.getSource().equals(infantry))
			controller.setSelectedUnitType(Enums.UnitType.INFANTRY);
		else if (e.getSource().equals(airmobileInfantry))
			controller.setSelectedUnitType(Enums.UnitType.AIRMOBILE_INFANTRY);
		else if (e.getSource().equals(mechanizedInfantry))
			controller.setSelectedUnitType(Enums.UnitType.MECHANIZED_INFANTRY);
		else if (e.getSource().equals(radioFriend))
		{
			controller.setSelectedAffiliation(Enums.Affiliation.FRIEND);
			updateButtonImages();
		}
		else if (e.getSource().equals(radioHostile))
		{
			controller.setSelectedAffiliation(Enums.Affiliation.HOSTILE);
			updateButtonImages();
		}
	}
	
	private void updateButtonImages()
	{
		infantry.setIcon(new ImageIcon(imageLoader.getUnitImage(Enums.UnitType.INFANTRY, controller.getSelectedAffiliation())));
		airmobileInfantry.setIcon(new ImageIcon(imageLoader.getUnitImage(Enums.UnitType.AIRMOBILE_INFANTRY, controller.getSelectedAffiliation())));
		mechanizedInfantry.setIcon(new ImageIcon(imageLoader.getUnitImage(Enums.UnitType.MECHANIZED_INFANTRY, controller.getSelectedAffiliation())));
	}
	
	private void disableOtherButtons(ActionEvent e)
	{
		for (JButton unitButtons : buttonList)
		{
			if (!unitButtons.equals(e.getSource()))
			{
				unitButtons.setEnabled(false);
			}
		}
	}
	
	public void addController(Controller controller)
	{
		this.controller = controller;
	}
}
