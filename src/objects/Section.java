package objects;

import java.awt.Point;
import java.util.Calendar;
import java.util.Date;

import parameters.GeneralParameters;
import controller.DeplacementCalculator;

public class Section
{
	private Point departure;
	private Point arrival;
	private double distance;
	private Date sectionETA;
	
	public Section(Point departure, Point arrival)
	{
		this.departure = departure;
		this.arrival = arrival;
		this.setDistance(departure, arrival);
	}
	
	public void setDistance(Point departure, Point arrival)
	{
		double pxlDistance = DeplacementCalculator.calculatePxlDistance(departure, arrival);
		double irlDistance = DeplacementCalculator.calculateIRLdistance(pxlDistance);
		
		this.distance = irlDistance;
	}
	
	public int getDuration()
	{
		int numberOfSecondsNeedeed = (int) (distance / GeneralParameters.walkSpeed);
		return numberOfSecondsNeedeed;
	}
	
	public void initSectionETA(Mission mission)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mission.getMissionETA());
		calendar.add(Calendar.SECOND, this.getDuration());
		sectionETA = calendar.getTime();
		mission.setMissionETA(calendar.getTime());
	}
	
	public void update()
	{
		
	}

	public Point getDeparture()
	{
		return departure;
	}

	public void setDeparture(Point departure)
	{
		this.departure = departure;
	}

	public Point getArrival()
	{
		return arrival;
	}

	public void setArrival(Point arrival)
	{
		this.arrival = arrival;
	}

	public double getDistance()
	{
		return distance;
	}

	public void setDistance(double distance)
	{
		this.distance = distance;
	}

	public Date getSectionETA()
	{
		return sectionETA;
	}

	public void setSectionETA(Date sectionETA)
	{
		this.sectionETA = sectionETA;
	}
}
