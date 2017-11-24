package objects;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import parameters.Enums.AreaType;
import parameters.Enums.AreaValue;

public class Area
{
	private Polygon polygon;
	private AreaType type = AreaType.NA;
	private AreaValue value = AreaValue.NA;
	
	public Area(ArrayList<Point> points)
	{
		int pointsNumber = points.size();
		int[] xPoints = new int[pointsNumber];
		int[] yPoints = new int[pointsNumber];
		
		for (int i = 0 ; i < points.size() ; i++)
		{
			xPoints[i] = (int) points.get(i).getX();
			yPoints[i] = (int) points.get(i).getY();
		}
		
		polygon = new Polygon(xPoints, yPoints, pointsNumber);
	}
	
	public Area(ArrayList<Point> points, AreaType type, AreaValue value)
	{
		this(points);
		this.type = type;
		this.value = value;
	}

	public Polygon getPolygon()
	{
		return polygon;
	}

	public void setPolygon(Polygon polygon)
	{
		this.polygon = polygon;
	}

	public AreaType getType()
	{
		return type;
	}

	public void setType(AreaType type)
	{
		this.type = type;
	}

	public AreaValue getValue()
	{
		return value;
	}

	public void setValue(AreaValue value)
	{
		this.value = value;
	}
}
