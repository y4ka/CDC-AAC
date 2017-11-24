package controller;

import java.awt.Point;

import objects.Mission;
import objects.Section;
import objects.Unit;
import parameters.GeneralParameters;
import vue.panelTime.Logger;

public class DeplacementCalculator
{
	public DeplacementCalculator()
	{
		
	}
	
	public void calcNewPosition(Unit unit)
	{
		move(unit);
	}
	
	private void move(Unit unit)
	{
		float xUnit = unit.getX();
		float yUnit = unit.getY();
		float unitSpeed = unit.getSpeed();
		
		//Si l'unit� a une mission et qu'elle n'est pas termin�e, on la bouge en fonction de la mission:
		if (unit.hasMission() && !unit.getMission().isMissionFinished())
		{
			//On r�cup�re sa mission et son objectif en cours:
			Mission mission = unit.getMission();
			Section currentSection = mission.getCurrentSection();
			
			//Si c'est la premi�re fois que l'on traite cette mission, on d�finit l'objectif comme le deuxi�me point de la mission:
			if (currentSection == null)
			{
				Section firstSection = mission.getSections().get(0);
				unit.getMission().setCurrentSection(firstSection);
			}
			
			Point objective = mission.getCurrentSection().getArrival();
			
			//TEST
			
			//On recup�re les coordonn�es de l'objectif:
			double xObjective = objective.getX();
			double yObjective = objective.getY();
			
			//On calcule la distance entre l'unit� et l'objectif:
			double xDistance = xObjective - xUnit;
			double yDistance = yObjective - yUnit;
			double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
			
			double factor = (unitSpeed/10) / distance;
			
			double xSpeed = xDistance * factor;
			double ySpeed = yDistance * factor;
			
			//On ajoute la distance parcourue � la position de l'unit�:
			double newXUnit = xUnit + xSpeed;
			double newYUnit = yUnit + ySpeed;
			
			//On v�rifie que les nouvelles coordonn�es sont l'arriv�e sur l'objectif:
			this.sectionArrival(unit, (float) xObjective, (float) yObjective);
			
			//On met � jour les coordonn�es de l'unit�:
			unit.setX((float) newXUnit);
			unit.setY((float) newYUnit);
			//END TEST
			
			//On calcule les coordonn�es future de l'unit�:
//			float xObjective = (float) objective.getX();
//			float yObjective = (float) objective.getY();
//			
//			float travelTime = 1.0f;
//			float xSpeed = (float) ((xObjective - xUnit) / travelTime);
//			float ySpeed = (float) ((yObjective - yUnit) / travelTime);
//			
//			float factor = (float) (unitSpeed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
//			
//			xSpeed *= factor;
//			ySpeed *= factor;
//			
//			float newXUnit = xUnit+xSpeed;
//			float newYUnit = yUnit+ySpeed;
//			
//			//On v�rifie que les nouvelles coordonn�es sont l'arriv�e sur l'objectif:
//			this.sectionArrival(unit, xObjective, yObjective);
//			
//			//On met � jour les coordonn�es de l'unit�:
//			unit.setX(newXUnit);
//			unit.setY(newYUnit);
		}
	}
	
	private void sectionArrival(Unit unit, float x, float y)
	{
		float precision = GeneralParameters.precisionForEndOfMissionSegments;
		
		if ((Math.abs(x - unit.getX()) < precision) && (Math.abs(y - unit.getY()) < precision))
		{
			//Si on arrive � la fin d'un segment, on met � jour le prochain objectif de l'Unit�:
			unit.getMission().setNextCurrentSection();
		}
	}
	
	public static double calculatePxlDistance(Point departure, Point arrival)
	{
		return Math.sqrt(Math.pow((arrival.getX()-departure.getX()),2)+Math.pow((arrival.getY()-departure.getY()),2));
	}
	
	public static double calculateIRLdistance(double pxlDistance)
	{
		return pxlDistance * GeneralParameters.mapScale;
	}
	
	
	
	
	
	
	
	
//	// Your Variables
//	float startX, startY, endX, endY;
//	float speed = 100;
//	float elapsed = 0.01f;
//
//	// On starting movement
//	float distance = Math.sqrt(Math.pow(endX-startX,2)+Math.pow(endY-startY,2));
//	float directionX = (endX-startX) / distance;
//	float directionY = (endY-startY) / distance;
//	object.X = startX;
//	object.Y = startY;
//	moving = true;
//
//	// On update
//	if(moving == true)
//	{
//	    object.X += directionX * speed * elapsed;
//	    object.Y += directionY * speed * elapsed;
//	    if(Math.sqrt(Math.pow(object.X-startX,2)+Math.pow(object.Y-startY,2)) >= distance)
//	    {
//	        object.X = endX;
//	        object.Y = endY;
//	        moving = false;
//	    }
//	}
	
	
	
	
	
	
//	previousMouseX;
//	previousMouseY;
//
//	differenceX = previousMouseX - X;
//	differenceY = previousMouseY - Y;
//
//	angle = (float)Math.Atan2(differenceY, differenceX) * 180 / Math.PI;
//
//	X += Math.cos(angle * Math.PI/180) * Speed;
//	Y += Math.sin(angle * Math.PI/180) * Speed;
	
}
