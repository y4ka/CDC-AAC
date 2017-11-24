package controller;

import java.awt.Point;
import java.util.ArrayList;

import objects.Area;
import objects.Mission;
import objects.Unit;
import parameters.Enums;
import parameters.Enums.Affiliation;
import parameters.Enums.AreaType;
import parameters.Enums.AreaValue;
import parameters.Enums.MissionType;
import parameters.Enums.Mode;
import parameters.Enums.UnitType;

public class Controller implements ClockListener
{
	private Enums.Mode controllerMode = Mode.SELECTION;
	private Enums.Affiliation selectedAffiliation = Affiliation.FRIEND;
	private Enums.UnitType selectedUnitType = UnitType.INFANTRY;
	
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private Unit selectedUnit = null;
	private Unit lastSelectedUnit = null;
	
	private Mission currentMission;
	
	public DeplacementCalculator deplacementCalculator = new DeplacementCalculator();
	public UnitVariablesCalculator unitVariablesCalculator = new UnitVariablesCalculator(this);
	
	private ArrayList<Area> areas = new ArrayList<Area>();
	
	public Controller()
	{
		createNewArea();
	}
	
	public void clicOnMap(int x, int y)
	{
		if (controllerMode.equals(Mode.UNIT_CREATION))
		{
			this.createNewUnit(x, y);
		}
		else if (controllerMode.equals(Mode.MISSION_CREATION))
		{
			this.createNewMission(x, y);
		}
		else
		{
			this.selectionOnMap(x, y);
		}
	}
	
	private void createNewUnit(int x, int y)
	{
		Unit newUnit = new Unit(x, y, selectedUnitType, selectedAffiliation);
		newUnit.addController(this);
		this.units.add(newUnit);
		controllerMode = Mode.SELECTION;
	}
	
	private void createNewMission(int x, int y)
	{
		if (selectedUnit != null)
		{
			if (currentMission == null)
			{
				System.out.println("CREATION DE LA MISSION");
				currentMission = new Mission(selectedUnit);
			}
			currentMission.addPoint(x, y);
			System.out.println("AJOUT D'UN POINT A LA MISSION");
		}
		else
		{
			controllerMode = Mode.SELECTION;
			System.out.println("SELECT A UNIT FIRST");
		}
	}
	
	private void createNewArea()
	{
		//ROADS
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(638,222));
		points.add(new Point(692,211));
		points.add(new Point(838,233));
		points.add(new Point(850,343));
		points.add(new Point(862,342));
		points.add(new Point(851,225));
		points.add(new Point(692,200));
		points.add(new Point(633,214));
		
		this.areas.add(new Area(points, AreaType.ROAD, AreaValue.HIGH));
		
		//FIELD DIFFICULY
		ArrayList<Point> points2 = new ArrayList<Point>();
		points2.add(new Point(696,212));
		points2.add(new Point(753,223));
		points2.add(new Point(773,335));
		points2.add(new Point(706,328));
		this.areas.add(new Area(points2, AreaType.FIELD_DIFFICULTY, AreaValue.HIGH));
		
		ArrayList<Point> points3 = new ArrayList<Point>();
		points3.add(new Point(760,226));
		points3.add(new Point(779,332));
		points3.add(new Point(832,342));
		points3.add(new Point(821,231));
		this.areas.add(new Area(points3, AreaType.FIELD_DIFFICULTY, AreaValue.MEDIUM));
		
		ArrayList<Point> points4 = new ArrayList<Point>();
		points4.add(new Point(664,219));
		points4.add(new Point(637,297));
		points4.add(new Point(698,324));
		points4.add(new Point(691,212));
		this.areas.add(new Area(points4, AreaType.FIELD_DIFFICULTY, AreaValue.LOW));	
		
		//HUMIDITY
		ArrayList<Point> points5 = new ArrayList<Point>();
		points5.add(new Point(567,537));
		points5.add(new Point(582,532));
		points5.add(new Point(598,538));
		points5.add(new Point(598,550));
		points5.add(new Point(579,577));
		points5.add(new Point(567,573));
		points5.add(new Point(566,552));
		this.areas.add(new Area(points5, AreaType.HUMIDITY, AreaValue.HIGH));
		
	}
	
	public void saveCurrentMission(MissionType missionType)
	{
		if (currentMission != null && selectedUnit != null)
		{
			//On met à jour le type de la mission:
			currentMission.setMissionType(missionType);
			
			//On lance la mission:
			currentMission.start();
			
			//On affecte la mission crée à l'unité selectionnée:
			selectedUnit.setMission(currentMission);
			
			//On vide la currentMission:
			currentMission = null;
			System.out.println("SAUVEGARDE DE LA MISSION");
		}
	}
	
	private void selectionOnMap(int x, int y)
	{
		selectedUnit = coordIsNearAUnit(x, y);
	}
	
	private Unit coordIsNearAUnit(int x, int y)
	{
		int minimumDistanceForSelection = 30;
		Unit nearestUnit = null;
		int nearestUnitDistance = minimumDistanceForSelection;
		
		for (Unit unit : units)
		{
			float xUnit = unit.getX();
			float yUnit = unit.getY();
			
			float distance = (float) Math.sqrt(sqr(y - yUnit) + sqr(x - xUnit));
			
			if (distance < nearestUnitDistance)
			{
				nearestUnit = unit;
			}
		}
		
		return nearestUnit;
	}
	
	private double sqr(double a) 
	{
		return a*a;
	}

	@Override
	public void update()
	{
		lastSelectedUnit = selectedUnit;
		
		for (Unit unit : units)
		{
			unit.update();
		}
	}
	
	// ===== GETTERS / SETTERS =====
	
	public ArrayList<Unit> getUnits()
	{
		return units;
	}

	public Enums.Mode getControllerMode()
	{
		return controllerMode;
	}

	public void setControllerMode(Enums.Mode controllerMode)
	{
		this.controllerMode = controllerMode;
	}

	public void setUnits(ArrayList<Unit> units)
	{
		this.units = units;
	}

	public Enums.Affiliation getSelectedAffiliation()
	{
		return selectedAffiliation;
	}

	public void setSelectedAffiliation(Enums.Affiliation selectedAffiliation)
	{
		this.selectedAffiliation = selectedAffiliation;
	}

	public Enums.UnitType getSelectedUnitType()
	{
		return selectedUnitType;
	}

	public void setSelectedUnitType(Enums.UnitType selectedUnitType)
	{
		this.selectedUnitType = selectedUnitType;
	}

	public Unit getSelectedUnit()
	{
		return selectedUnit;
	}

	public void setSelectedUnit(Unit selectedUnit)
	{
		this.selectedUnit = selectedUnit;
	}

	public Unit getLastSelectedUnit()
	{
		return lastSelectedUnit;
	}

	public void setLastSelectedUnit(Unit lastSelectedUnit)
	{
		this.lastSelectedUnit = lastSelectedUnit;
	}

	public Mission getCurrentMission()
	{
		return currentMission;
	}

	public void setCurrentMission(Mission currentMission)
	{
		this.currentMission = currentMission;
	}

	public ArrayList<Area> getAreas()
	{
		return areas;
	}

	public void setAreas(ArrayList<Area> areas)
	{
		this.areas = areas;
	}
}
