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
		
		//Si l'unité a une mission et qu'elle n'est pas terminée, on la bouge en fonction de la mission:
		if (unit.hasMission() && !unit.getMission().isMissionFinished())
		{
			//On récupère sa mission et son objectif en cours:
			Mission mission = unit.getMission();
			Section currentSection = mission.getCurrentSection();
			
			//Si c'est la première fois que l'on traite cette mission, on définit l'objectif comme le deuxième point de la mission:
			if (currentSection == null)
			{
				Section firstSection = mission.getSections().get(0);
				unit.getMission().setCurrentSection(firstSection);
			}
			
			Point objective = mission.getCurrentSection().getArrival();
			
			//TEST
			
			//On recupère les coordonnées de l'objectif:
			double xObjective = objective.getX();
			double yObjective = objective.getY();
			
			//On calcule la distance entre l'unité et l'objectif:
			double xDistance = xObjective - xUnit;
			double yDistance = yObjective - yUnit;
			double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
			
			double factor = (unitSpeed/10) / distance;
			
			double xSpeed = xDistance * factor;
			double ySpeed = yDistance * factor;
			
			//On ajoute la distance parcourue à la position de l'unité:
			double newXUnit = xUnit + xSpeed;
			double newYUnit = yUnit + ySpeed;
			
			//On vérifie que les nouvelles coordonnées sont l'arrivée sur l'objectif:
			this.sectionArrival(unit, (float) xObjective, (float) yObjective);
			
			//On met à jour les coordonnées de l'unité:
			unit.setX((float) newXUnit);
			unit.setY((float) newYUnit);
			//END TEST
			
			//On calcule les coordonnées future de l'unité:
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
//			//On vérifie que les nouvelles coordonnées sont l'arrivée sur l'objectif:
//			this.sectionArrival(unit, xObjective, yObjective);
//			
//			//On met à jour les coordonnées de l'unité:
//			unit.setX(newXUnit);
//			unit.setY(newYUnit);
		}
	}
	
	private void sectionArrival(Unit unit, float x, float y)
	{
		float precision = GeneralParameters.precisionForEndOfMissionSegments;
		
		if ((Math.abs(x - unit.getX()) < precision) && (Math.abs(y - unit.getY()) < precision))
		{
			//Si on arrive à la fin d'un segment, on met à jour le prochain objectif de l'Unité:
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
