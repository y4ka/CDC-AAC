package objects;

import java.awt.Point;

import parameters.Enums;
import controller.Controller;


public class Unit
{
	private Controller controller;
	
	private float x;
	private float y;
	private Enums.UnitType unitType;
	private Enums.Affiliation affiliation;
	
	private float speed = 0.83f;
	private float speedTmp = 0.00f;
	
	private float moral = 100f;
	private float moralTmp = 0.00f;
	
	private float tiredness = 100f;
	private float tirednessTmp = 0.00f;
	
	private String unitName;
	private Mission mission;
	
	public Unit(float x, float y, Enums.UnitType unitType, Enums.Affiliation affiliation)
	{
		this.x = x;
		this.y = y;
		this.unitType = unitType;
		this.affiliation = affiliation;
		
		this.unitName = "NA";
	}
	
	public void update()
	{
		//On update les variables de l'unité:
		controller.unitVariablesCalculator.calculateVariables(this);
		
		//On update la position de l'unité:
		controller.deplacementCalculator.calcNewPosition(this);
		
		//On update la mission:
		if (hasMission())
		{
			mission.update();
		}
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}
	
	public Point getPoint()
	{
		return new Point((int) x, (int) y);
	}

	public Enums.UnitType getUnitType()
	{
		return unitType;
	}

	public void setUnitType(Enums.UnitType unitType)
	{
		this.unitType = unitType;
	}

	public Enums.Affiliation getAffiliation()
	{
		return affiliation;
	}

	public void setAffiliation(Enums.Affiliation affiliation)
	{
		this.affiliation = affiliation;
	}

	public String getUnitName()
	{
		return unitName;
	}

	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}
	
	public boolean hasMission()
	{
		if (mission == null)
			return false;
		else
			return true;
	}

	public Mission getMission()
	{
		return mission;
	}

	public void setMission(Mission mission)
	{
		this.mission = mission;
	}

	public float getSpeed()
	{
		return speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public float getMoral()
	{
		return moral;
	}

	public void setMoral(float moral)
	{
		this.moral = moral;
	}
	
	public float getSpeedTmp()
	{
		return speedTmp;
	}

	public void setSpeedTmp(float speedTmp)
	{
		this.speedTmp = speedTmp;
	}

	public float getMoralTmp()
	{
		return moralTmp;
	}

	public void setMoralTmp(float moralTmp)
	{
		this.moralTmp = moralTmp;
	}

	public float getTiredness()
	{
		return tiredness;
	}

	public void setTiredness(float tiredness)
	{
		this.tiredness = tiredness;
	}

	public float getTirednessTmp()
	{
		return tirednessTmp;
	}

	public void setTirednessTmp(float tirednessTmp)
	{
		this.tirednessTmp = tirednessTmp;
	}

	public void addController(Controller controller)
	{
		this.controller = controller;
	}
}
