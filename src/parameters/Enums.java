package parameters;

public class Enums
{
	public enum Mode
	{
		SELECTION,
		UNIT_CREATION,
		MISSION_CREATION;
	}
	
	public enum Affiliation 
	{
		  UNKNOWN,
		  FRIEND,
		  NEUTRAL,
		  HOSTILE;	
	}
	
	public enum UnitType
	{
		INFANTRY,
		AIRMOBILE_INFANTRY,
		MECHANIZED_INFANTRY;
	}
	
	public enum MissionType
	{
		RECON,
		ATTACK,
		DEFEND,
		PATROL,
		SEARCH;
	}
	
	public enum ShowMissions 
	{
		ALL,
		ONLY_SELECTED;
	}
	
	public enum AreaType
	{
		FIELD_DIFFICULTY,
		VEGETATION,
		HUMIDITY,
		ROAD,
		RADIO_RANGE,
		NA;
	}
	
	public enum AreaValue
	{
		HIGH,
		MEDIUM,
		LOW,
		NA;
	}
}
