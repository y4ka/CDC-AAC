package vue.panelDetails;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objects.Unit;
import controller.Controller;

public class PanelDetails extends JPanel implements ActionListener
{
	private Controller controller;
	private JLabel unitType = new JLabel();
	private JLabel unitAffiliation = new JLabel();
	private JLabel unitLocation = new JLabel();
	private JTextField unitName = new JTextField();
	private JLabel unitSpeed = new JLabel();
	private JLabel unitMoral = new JLabel();
	private JButton saveButton = new JButton("Save");
	
	private ArrayList<JComponent> components = new ArrayList<JComponent>();
	
	public PanelDetails()
	{
		this.setLayout(new GridLayout(3, 0));
		this.setBackground(Color.GRAY);
		
		saveButton.addActionListener(this);
		
		this.add(unitType);
		this.add(unitAffiliation);
		this.add(unitLocation);
		this.add(unitName);
		this.add(unitSpeed);
		this.add(unitMoral);
		this.add(saveButton);
		
		components.add(unitType);
		components.add(unitAffiliation);
		components.add(unitLocation);
		components.add(unitName);
		components.add(unitSpeed);
		components.add(unitMoral);
		components.add(saveButton);
	}
	
	public void addController(Controller controller)
	{
		this.controller = controller;
	}
	
	public void update()
	{
		Unit selectedUnit = controller.getSelectedUnit();
		Unit lastSelectedUnit = controller.getLastSelectedUnit();
		
		//Si on a aucune unitee selectionee:
		if (selectedUnit == null)
		{
			unitType.setText("");
			unitAffiliation.setText("");
			unitLocation.setText("");
			unitName.setText("");
			unitSpeed.setText("");
			unitMoral.setText("");
			
			for (JComponent component : components)
			{
				component.setVisible(false);
			}
		}
		else
		{
			//Si on a une unitee selectionnee et qu'elle est differente de celle du tick precedent:
			if (selectedUnit != lastSelectedUnit)
			{
				//On met à jour l'ensemble des données:
				unitType.setText(selectedUnit.getUnitType().toString());
				unitAffiliation.setText(selectedUnit.getAffiliation().toString());
				unitLocation.setText(selectedUnit.getX()+","+selectedUnit.getY());
				unitName.setText(selectedUnit.getUnitName());
				unitSpeed.setText("Speed: "+selectedUnit.getSpeed()+" ("+selectedUnit.getSpeedTmp()+")");
				unitMoral.setText("Moral: "+selectedUnit.getMoral()+" ("+selectedUnit.getMoralTmp()+")");
				
				
				for (JComponent component : components)
				{
					component.setVisible(true);
				}
			}
			//Si on a une unitee selectionne mais que c'est la meme que le tick precedent:
			else
			{
				//On ne met à jour que les données pouvant suceptibles d'être modifiées en temps réel:
				unitLocation.setText(selectedUnit.getX()+","+selectedUnit.getY());
				unitSpeed.setText("Speed: "+selectedUnit.getSpeed()+" ("+selectedUnit.getSpeedTmp()+")");
				unitMoral.setText("Moral: "+selectedUnit.getMoral()+" ("+selectedUnit.getMoralTmp()+")");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(saveButton))
		{
			controller.getSelectedUnit().setUnitName(unitName.getText());
		}
	}
}
