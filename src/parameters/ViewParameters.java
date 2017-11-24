package parameters;

import java.awt.Color;

import parameters.Enums.ShowMissions;

public class ViewParameters
{
	public static int xLogoUnit = 15;
	public static int yLogoUnit = 10;
	public static Color colorSelectionRectangle = Color.YELLOW;
	public static Color colorUnitNameLabel = Color.WHITE;
	public static Color colorDistanceValues = Color.GREEN;
	public static Color colorETAs = Color.ORANGE;
	public static int digitNumberForDistances = 0;
	public static boolean filledLogo = false;
	public static ShowMissions showMissions = ShowMissions.ONLY_SELECTED;
	public static boolean drawETA = false;
	public static boolean drawDistances = false;
	
	// ===== AREAS =====
	public static boolean drawAreas = true;
	public static boolean drawFieldDifficultyAreas = false;
	public static boolean drawVegetationAreas = false;
	public static boolean drawHumidityAreas = false;
	public static boolean drawRoadAreas = false;
	public static boolean drawRadioRangeAreas = false;
	public static boolean drawNAAreas = false;
	
	public static float areaTransparency = 0.3f;
	public static boolean areaFilled = true;
	public static boolean areaContoured = true;
	public static boolean areaTypeLabel = true;
	public static boolean areaValueLabel = true;
		
	public static Color colorFieldDifficultyArea = new Color(0.8f,0.5f,0.2f,ViewParameters.areaTransparency);
	public static Color colorVegetationArea = new Color(0.3f,0.6f,0.1f,ViewParameters.areaTransparency);
	public static Color colorHumidityArea = new Color(0.3f,0.5f,0.9f,ViewParameters.areaTransparency);
	public static Color colorRoadArea = new Color(0.3f,0.3f,0.4f,ViewParameters.areaTransparency);
	public static Color colorRadioRangeArea = new Color(0.3f,0.1f,0.5f,ViewParameters.areaTransparency);
	public static Color colorNAArea = Color.BLACK;
}
