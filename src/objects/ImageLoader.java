package objects;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import parameters.Enums;
import parameters.ViewParameters;

public class ImageLoader
{
	public ImageLoader()
	{

	}

	public Image getUnitImage(Enums.UnitType unitType, Enums.Affiliation affiliation)
	{
		String fileName = "";
		Image unitImage;
		try
		{
			if (unitType.equals(Enums.UnitType.AIRMOBILE_INFANTRY))
				fileName = "airmobileInfantry";
			else if (unitType.equals(Enums.UnitType.MECHANIZED_INFANTRY))
				fileName = "mechanizedInfantry";
			else
				fileName = "Infantry";
			
			//On ajoute le suffixe FILLED si besoin:
			if (ViewParameters.filledLogo)
			{
				fileName += "_FILLED";
			}
			
			//On ajoute le suffixe OPFOR si besoin:
			if (affiliation.equals(Enums.Affiliation.HOSTILE))
			{
				fileName += "_OPFOR";
			}
			
			//On ajoute l'extention du fichier:
			fileName += ".png";
			
			//On récupère l'image pour l'afficher:
			unitImage = ImageIO.read(new File(fileName));
			
			return unitImage;

		} catch (IOException e)
		{
			System.err.println("Error while loading Unit image.");
			e.printStackTrace();
			return null;
		}
	}
}
