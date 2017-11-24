package vue.panelMissions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import parameters.Enums;
import parameters.Enums.MissionType;
import parameters.Enums.Mode;
import controller.Controller;

public class PanelMissions extends JPanel implements ActionListener
{
	private Controller controller;
	private JButton createMission = new JButton("Create");
	private JButton saveMission = new JButton("Save");
	private JButton deleteMission = new JButton("Delete");
	
	private JComboBox<Enums.MissionType> comboMissionType = new JComboBox<Enums.MissionType>(Enums.MissionType.values());
	
	public PanelMissions()
	{
		this.setLayout(new GridLayout(0,1));
		//this.setLayout(new BoxLayout(this, 1));
		
		createMission.addActionListener(this);
		saveMission.addActionListener(this);
		deleteMission.addActionListener(this);
		
		this.add(createMission);
		this.add(comboMissionType);
		this.add(saveMission);
		this.add(deleteMission);
	}
	
	public void addController(Controller controller)
	{
		this.controller = controller;
	}
	
	public void update()
	{
		//Si on est entrain de créer une mission on active le bouton pour sauvegarder:
		if (controller.getControllerMode().equals(Mode.MISSION_CREATION))
		{
			saveMission.setEnabled(true);
			deleteMission.setEnabled(false);
		}
		else
		{
			saveMission.setEnabled(false);
			deleteMission.setEnabled(false);
		}
		
		//Si aucune unité n'est selectionnée:
		if (controller.getSelectedUnit() == null)
		{
			//On ne peut pas créer de mission:
			createMission.setEnabled(false);
			
			//On ne peut pas supprimer de mission:
			deleteMission.setEnabled(false);
		}
		
		//Si une unité est sélectionnée:
		else
		{
			//On peut créer une mission:
			createMission.setEnabled(true);
			
			//Si elle a déjà une mission on peut la supprimer:
			if (controller.getSelectedUnit().hasMission())
			{
				deleteMission.setEnabled(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if (e.getSource().equals(createMission))
		{
			controller.setControllerMode(Mode.MISSION_CREATION);
		}
		else if (e.getSource().equals(saveMission))
		{
			//On récupère le type de la mission:
			Enums.MissionType missionType = (MissionType) comboMissionType.getSelectedItem();
			
			controller.saveCurrentMission(missionType);
			controller.setControllerMode(Mode.SELECTION);
		}
		else if (e.getSource().equals(deleteMission))
		{
			controller.getSelectedUnit().setMission(null);
		}
	}
}
