package controller;

import objects.Area;
import objects.Unit;
import parameters.Enums.AreaValue;
import parameters.GeneralParameters;

public class UnitVariablesCalculator
{
	private Controller controller;
	
	private float moralModificator = 0.0f;
	private float speedModificator = 0.0f;
	
	public UnitVariablesCalculator(Controller controller)
	{
		this.controller = controller;
	}
	
	public void calculateVariables(Unit unit)
	{
		moralModificator = 0.0f;
		speedModificator = 0.0f;
		
		//On calcule les modificateurs liés aux Area:
		this.getAreaInformations(unit);
		
		//On calcule les modificateurs liés aux Variables:
		this.calculateMoral(unit);
		this.calculateSpeed(unit);
		
		//On met à jour les variables de l'Unité:
		this.setValues(unit);
	}
	
	private void getAreaInformations(Unit unit)
	{
		//On parcours toutes les zones existantes:
		for (Area area : controller.getAreas())
		{
			//On regarde si l'unité est présente dans cette zone:
			if (area.getPolygon().contains(unit.getPoint()))
			{
				int valueModificator = 1;
				
				//On récupère le valeur de la zone:
				switch (area.getValue())
				{
					case HIGH:
						valueModificator = 4;
						break;
					case MEDIUM:
						valueModificator = 3;
						break;
					case LOW:
						valueModificator = 2;
						break;
					case NA:
						valueModificator = 1;
						break;
				}
				
				//On récupère le type de la zone:
				switch (area.getType())
				{
					case FIELD_DIFFICULTY:
						speedModificator += valueModificator * 0.075f;
						break;
						
					case HUMIDITY:
						speedModificator += valueModificator * 0.005f;
						break;
						
					case VEGETATION:
						speedModificator += valueModificator * 0.005f;
						break;
						
					case ROAD:
						speedModificator -= valueModificator * 0.075f;
						break;
						
					case NA:
						break;
				}
			}
		}
	}
	
	private void calculateMoral(Unit unit)
	{
		
	}
	
	private void calculateSpeed(Unit unit)
	{
		//Permanent Malus/Bonus
		float currentMoral = unit.getMoral();
		float currentTiredness = unit.getTiredness();
		
		//Temporaire Malus/Bonus
		unit.setSpeedTmp(speedModificator);
		
		//Solution temporaire:
		unit.setSpeed(GeneralParameters.walkSpeed - speedModificator);
	}
	
	private void setValues(Unit unit)
	{
		unit.setMoralTmp(moralModificator);
		unit.setSpeedTmp(speedModificator);
	}
}
