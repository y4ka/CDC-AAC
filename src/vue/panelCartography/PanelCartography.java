package vue.panelCartography;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import objects.Area;
import objects.ImageLoader;
import objects.Mission;
import objects.Section;
import objects.Unit;
import parameters.Enums;
import parameters.Enums.AreaValue;
import parameters.ViewParameters;
import parameters.Enums.AreaType;
import parameters.Enums.ShowMissions;
import controller.Controller;
import controller.DeplacementCalculator;

public class PanelCartography extends JPanel
{
	private ImageLoader imageLoader = new ImageLoader();
	private PanelCartographyMouseListener mouseListener = new PanelCartographyMouseListener();
	private Image cartoBackground;
	private Controller controller;

	public PanelCartography()
	{
		try
		{
			cartoBackground = ImageIO.read(new File("map.png"));
		} 
		catch (IOException ex)
		{
			System.err.println("Error while loading background cartography.");
		}
		
		this.addMouseListener(mouseListener);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(cartoBackground, 0, 0, null);
		
		drawAreas(g);
		drawUnits(g);
		drawSelectionRectangle(g);
		drawMissions(g);
	}
	
	private void drawAreas(Graphics g)
	{
		//On parcours toutes les zones:
		for (Area area : controller.getAreas())
		{
			//On vérifie si on doit afficher ce type de zone:
			if (drawAreaEnabled(area.getType()))
			{
				if (ViewParameters.areaContoured)
				{
					g.setColor(Color.RED);
					g.drawPolygon(area.getPolygon());
				}

				if (ViewParameters.areaFilled)
				{
					Color zoneColor = getAreaColor(area.getType());
					g.setColor(zoneColor);
					g.fillPolygon(area.getPolygon());
				}

				String areaLabel = "";
				if (ViewParameters.areaTypeLabel && area.getType() != AreaType.NA)
				{
					areaLabel += area.getType();
				}

				if (ViewParameters.areaValueLabel && area.getValue() != AreaValue.NA)
				{
					areaLabel += " / " + area.getValue();
				}

				g.setColor(Color.BLACK);
				g.drawString(areaLabel, (int) area.getPolygon().getBounds()
						.getCenterX(), (int) area.getPolygon().getBounds()
						.getCenterY());
			}
		}
	}
	
	private void drawUnits(Graphics g)
	{
		for (Unit unitToDraw : controller.getUnits())
		{
			drawUnitsTypeLogo(g, unitToDraw);
			drawUnitsLabel(g, unitToDraw);
		}
	}
	
	private void drawUnitsTypeLogo(Graphics g, Unit unit)
	{
		Image da = imageLoader.getUnitImage(unit.getUnitType(), unit.getAffiliation());
		g.drawImage(da, (int)unit.getX()-ViewParameters.xLogoUnit, (int)unit.getY()-ViewParameters.yLogoUnit, 30, 20, this);
	}
	
	private void drawUnitsLabel(Graphics g, Unit unit)
	{
		String unitName = unit.getUnitName();
		int stringLength = unitName.length();
		
		if (!unitName.equals("NA"))
		{
			g.setColor(ViewParameters.colorUnitNameLabel);
			g.drawString(unit.getUnitName(), (int)unit.getX()-ViewParameters.xLogoUnit-(stringLength), (int)unit.getY()-ViewParameters.yLogoUnit-2);
		}
	}
	
	private void drawSelectionRectangle(Graphics g)
	{
		Unit selectedUnit = controller.getSelectedUnit();
		
		if (selectedUnit != null)
		{
			g.setColor(ViewParameters.colorSelectionRectangle);
			g.drawRect((int)selectedUnit.getX()-ViewParameters.xLogoUnit-2, (int)selectedUnit.getY()-ViewParameters.yLogoUnit-2, 34, 24);
		}
	}
	
	private void drawMissions(Graphics g)
	{
		if (controller.getCurrentMission() != null)
		{
			//Si une mission est en cours de création, on l'affiche:
			g.setColor(Color.YELLOW);
			drawMission(g, controller.getCurrentMission());
		}
		else
		{
			if (ViewParameters.showMissions.equals(ShowMissions.ALL))
			{
				//On affiche toutes les missions de toutes les unités même non selectionnées:
				for (Unit unit : controller.getUnits())
				{
					drawMission(g, unit.getMission());
				}
			}
			else if (ViewParameters.showMissions.equals(ShowMissions.ONLY_SELECTED))
			{
				//Si aucune mission est en cours de création, on affiche les missions de l'unité selectionnée:
				if (controller.getSelectedUnit() != null)
				{
					drawMission(g, controller.getSelectedUnit().getMission());
				}
			}
		}
	}
	
	private void drawMission(Graphics g, Mission mission)
	{
		if(mission != null)
		{
			ArrayList<Section> sections = mission.getSections();
			int xLastPoint = 0;
			int yLastPoint = 0;
			
			for (Section section : sections)
			{
				//On dessine chaque Section:
				Point departure = section.getDeparture();
				Point arrival = section.getArrival();
				
				g.drawRect((int)departure.getX()-2, (int)departure.getY()-2, 4, 4);
				g.drawRect((int)arrival.getX()-2, (int)arrival.getY()-2, 4, 4);
				g.drawLine((int)departure.getX(), (int)departure.getY(), (int)arrival.getX(), (int)arrival.getY());
				
				//On affiche les distance de chaque Section:
				this.drawDistances(g, section);
				
				//On affiche les ETA de chaque Section:
				this.drawETA(g, section);
				
				//On récupère les coordonnées du dernier point de la mission (pour afficher le type a coté du dernier point):
				xLastPoint = (int) arrival.getX();
				yLastPoint = (int) arrival.getY();
			}
			
			//On affiche le Type de la Mission:
			if (mission.getMissionType() != null)
			{
				g.drawString(mission.getMissionType().toString(), xLastPoint + 10, yLastPoint + 10);
			}
		}
	}
	
	private void drawDistances(Graphics g, Section section)
	{
		if (ViewParameters.drawDistances)
		{
			double irlDistance = section.getDistance();
			
			DecimalFormat f = new DecimalFormat();
			f.setMaximumFractionDigits(ViewParameters.digitNumberForDistances);
			String distanceToDraw = ""+f.format(irlDistance);
			
			Color previousColor = g.getColor();
			g.setColor(ViewParameters.colorDistanceValues);
			g.drawString(distanceToDraw, (int) section.getArrival().getX() - 10, (int) section.getArrival().getY() - 4);
			g.setColor(previousColor);
		}
	}
	
	private void drawETA(Graphics g, Section section)
	{
		if (ViewParameters.drawETA)
		{
			Date eta = section.getSectionETA();
			SimpleDateFormat formater = new SimpleDateFormat("h:mm:ss");
			String etaToDraw = formater.format(eta);
			
			Color previousColor = g.getColor();
			g.setColor(ViewParameters.colorETAs);
			g.drawString(etaToDraw, (int) section.getArrival().getX() - 10, (int) section.getArrival().getY() - 14);
			g.setColor(previousColor);
		}
	}
	
	private Color getAreaColor(Enums.AreaType areaType)
	{
		switch(areaType)
		{
			case FIELD_DIFFICULTY:
				return ViewParameters.colorFieldDifficultyArea;
			case HUMIDITY:
				return ViewParameters.colorHumidityArea;
				
			case VEGETATION:
				return ViewParameters.colorVegetationArea;
				
			case ROAD:
				return ViewParameters.colorRoadArea;
				
			case RADIO_RANGE:
				return ViewParameters.colorRadioRangeArea;
				
			case NA:
				return ViewParameters.colorNAArea;
				
			default:
				return ViewParameters.colorNAArea;
		}
	}
	
	private boolean drawAreaEnabled(AreaType areaType)
	{
		if (ViewParameters.drawAreas)
		{
			switch(areaType)
			{
				case FIELD_DIFFICULTY:
					return ViewParameters.drawFieldDifficultyAreas;
				case HUMIDITY:
					return ViewParameters.drawHumidityAreas;
					
				case VEGETATION:
					return ViewParameters.drawVegetationAreas;
					
				case ROAD:
					return ViewParameters.drawRoadAreas;
					
				case RADIO_RANGE:
					return ViewParameters.drawRadioRangeAreas;
					
				case NA:
					return ViewParameters.drawNAAreas;
					
				default:
					return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	public void addController(Controller controller)
	{
		this.controller = controller;
		this.mouseListener.addController(controller);
	}
}
