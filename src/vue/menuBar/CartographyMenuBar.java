package vue.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import parameters.ViewParameters;

public class CartographyMenuBar extends JMenuBar implements ActionListener
{
	JMenu menuFile = new JMenu("File");
	JMenu menuView = new JMenu("View");
	
	JMenu menuArea = new JMenu("Areas");
	JCheckBoxMenuItem checkBoxFieldDifficulty = new JCheckBoxMenuItem("Field Difficulty");
	JCheckBoxMenuItem checkBoxVegetation = new JCheckBoxMenuItem("Vegetation");
	JCheckBoxMenuItem checkBoxHumidity = new JCheckBoxMenuItem("Humidity");
	JCheckBoxMenuItem checkBoxRoads = new JCheckBoxMenuItem("Roads");
	JCheckBoxMenuItem checkBoxRadioRange = new JCheckBoxMenuItem("Radio Range");
	
	JMenu menuUnits = new JMenu("Units");
	JCheckBoxMenuItem checkBoxFilledLogo = new JCheckBoxMenuItem("Filled Logo");
	JCheckBoxMenuItem checkBoxDrawETA = new JCheckBoxMenuItem("ETA");
	JCheckBoxMenuItem checkBoxDrawDistance = new JCheckBoxMenuItem("Distance");
	
	public CartographyMenuBar()
	{
		checkBoxFieldDifficulty.addActionListener(this);
		checkBoxVegetation.addActionListener(this);
		checkBoxHumidity.addActionListener(this);
		checkBoxRoads.addActionListener(this);
		checkBoxRadioRange.addActionListener(this);
		
		checkBoxFilledLogo.addActionListener(this);
		checkBoxDrawETA.addActionListener(this);
		checkBoxDrawDistance.addActionListener(this);
		
		menuView.addSeparator();
		menuView.add(menuArea);
		menuArea.add(checkBoxFieldDifficulty);
		menuArea.add(checkBoxHumidity);
		menuArea.add(checkBoxRoads);
		menuArea.add(checkBoxRadioRange);
		
		menuView.add(menuUnits);
		menuUnits.add(checkBoxFilledLogo);
		menuUnits.add(checkBoxDrawETA);
		menuUnits.add(checkBoxDrawDistance);
		
		this.add(menuFile);
		this.add(menuView);
		
		setDefaultChecks();
	}
	
	private void setDefaultChecks()
	{
		if (ViewParameters.drawFieldDifficultyAreas)
			checkBoxFieldDifficulty.setSelected(true);
		if (ViewParameters.drawVegetationAreas)
			checkBoxVegetation.setSelected(true);
		if (ViewParameters.drawHumidityAreas)
			checkBoxHumidity.setSelected(true);
		if (ViewParameters.drawRoadAreas)
			checkBoxRoads.setSelected(true);
		if (ViewParameters.drawRadioRangeAreas)
			checkBoxRadioRange.setSelected(true);
		
		
		if (ViewParameters.filledLogo)
			checkBoxFilledLogo.setSelected(true);
		if (ViewParameters.drawETA)
			checkBoxDrawETA.setSelected(true);
		if (ViewParameters.drawDistances)
			checkBoxDrawDistance.setSelected(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		// ===== AREAS =====
		if (e.getSource().equals(checkBoxFieldDifficulty))
		{
			ViewParameters.drawFieldDifficultyAreas = checkBoxFieldDifficulty.isSelected();
		}
		else if (e.getSource().equals(checkBoxVegetation))
		{
			ViewParameters.drawVegetationAreas = checkBoxVegetation.isSelected();
		}
		else if (e.getSource().equals(checkBoxHumidity))
		{
			ViewParameters.drawHumidityAreas = checkBoxHumidity.isSelected();
		}
		else if (e.getSource().equals(checkBoxRoads))
		{
			ViewParameters.drawRoadAreas = checkBoxRoads.isSelected();
		}
		else if (e.getSource().equals(checkBoxRadioRange))
		{
			ViewParameters.drawRadioRangeAreas = checkBoxRadioRange.isSelected();
		}
		
		
		// ===== UNITS =====
		else if (e.getSource().equals(checkBoxFilledLogo))
		{
			ViewParameters.filledLogo = checkBoxFilledLogo.isSelected();
		}
		else if (e.getSource().equals(checkBoxDrawETA))
		{
			ViewParameters.drawETA = checkBoxDrawETA.isSelected();
		}
		else if (e.getSource().equals(checkBoxDrawDistance))
		{
			ViewParameters.drawDistances = checkBoxDrawDistance.isSelected();
		}
	}
}
