package vue.panelCartography;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.Controller;


public class PanelCartographyMouseListener implements MouseListener
{
	private Controller controller;

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		System.out.println(e.getX()+","+e.getY());
		
		//On envoie au controller les coordonnées selectionnées:
		controller.clicOnMap(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	public void addController(Controller controller)
	{
		this.controller = controller;
	}
}
