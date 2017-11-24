package objects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import parameters.Enums;

public class Mission
{
	private Enums.MissionType missionType;
	private Unit unit;
	private ArrayList<Section> sections = new ArrayList<Section>();
	private Section currentSection;
	private boolean missionFinished = false;
	
	private Date departureDate;
	private Date missionETA;
	
	public Mission(Unit unit)
	{
		this.unit = unit;
	}
	
	public void start()
	{
		departureDate = new Date();
	}
	
	public void update()
	{
		for (Section section : sections)
		{
			section.update();
		}
	}
	
	public void addPoint(int x, int y)
	{
		Point departure = null;
		Point arrival = new Point(x, y);
		
		//Si ce n'est pas la premiere section créée:
		if (!sections.isEmpty())
		{
			//Point depart = point d'arrivée de la section precedente:
			Section previousSection = sections.get(sections.size()-1);
			departure = previousSection.getArrival();
		}
		//Si c'est la première section créée:
		else
		{
			//Point depart = coordonnées unité:
			departure = new Point((int) unit.getX(), (int) unit.getY());
			
			//Heure depart = heure courante:
			missionETA = new Date();
		}
		
		Section newSection = new Section(departure, arrival);
		newSection.initSectionETA(this);
		sections.add(newSection);
	}
		
	public void setNextCurrentSection()
	{	
		int indexOfCurrentSection = sections.indexOf(currentSection);
		
		if (indexOfCurrentSection + 1 >= sections.size())
		{
			//La section courante est la derniere section de la mission:
			missionFinished = true;
		}
		else
		{
			//Si il reste encore un objectif après l'objectif en cours, on le declare nouvel objectif courant:
			currentSection = sections.get(indexOfCurrentSection+1);
		}
	}

	public Enums.MissionType getMissionType()
	{
		return missionType;
	}

	public void setMissionType(Enums.MissionType missionType)
	{
		this.missionType = missionType;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public ArrayList<Section> getSections()
	{
		return sections;
	}

	public void setSections(ArrayList<Section> sections)
	{
		this.sections = sections;
	}

	public Section getCurrentSection()
	{
		return currentSection;
	}

	public void setCurrentSection(Section currentSection)
	{
		this.currentSection = currentSection;
	}

	public boolean isMissionFinished()
	{
		return missionFinished;
	}

	public void setMissionFinished(boolean missionFinished)
	{
		this.missionFinished = missionFinished;
	}

	public Date getDepartureDate()
	{
		return departureDate;
	}

	public void setDepartureDate(Date departureDate)
	{
		this.departureDate = departureDate;
	}

	public Date getMissionETA()
	{
		return missionETA;
	}

	public void setMissionETA(Date missionETA)
	{
		this.missionETA = missionETA;
	}
}

