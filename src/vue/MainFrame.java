package vue;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JFrame;

import parameters.Enums;
import parameters.Enums.Mode;
import vue.menuBar.CartographyMenuBar;
import vue.panelCartography.PanelCartography;
import vue.panelDetails.PanelDetails;
import vue.panelMissions.PanelMissions;
import vue.panelTime.PanelTime;
import vue.panelUnits.PanelUnits;
import controller.ClockListener;
import controller.Controller;


public class MainFrame extends JFrame implements ClockListener
{
	private Controller controller;
	private PanelCartography panelCartography = new PanelCartography();
	private PanelUnits panelUnits = new PanelUnits();
	private PanelDetails panelDetails = new PanelDetails();
	private PanelMissions panelMissions = new PanelMissions();
	private PanelTime panelTime = new PanelTime();
	private CartographyMenuBar cartographyMenuBar = new CartographyMenuBar();
	
	public MainFrame()
	{
		this.setTitle("CDC AAC");
		this.setSize(850,750);	
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.add(panelCartography, BorderLayout.CENTER);
		this.add(panelUnits, BorderLayout.EAST);
		this.add(panelDetails, BorderLayout.SOUTH);
		this.add(panelMissions, BorderLayout.WEST);
		this.add(panelTime, BorderLayout.NORTH);
		
		this.setJMenuBar(cartographyMenuBar);
	}
	
	public void addController(Controller controller)
	{
		this.controller = controller;
		panelCartography.addController(controller);
		panelUnits.addController(controller);
		panelDetails.addController(controller);
		panelMissions.addController(controller);
		panelTime.addController(controller);
	}

	@Override
	public void update()
	{
		//On met à jour le curseur:
		this.updateCursor();
		
		//On rafraichit la cartographie:
		panelCartography.repaint();
		this.repaint();
		
		//On met à jour le panelDetails:
		panelDetails.update();
		
		//On met à jour le panelMission:
		panelMissions.update();
		
		//On met à jour le panelTime:
		panelTime.update();
	}
	
	private void updateCursor()
	{
		Enums.Mode controllerMode = controller.getControllerMode();
		
		if (controllerMode.equals(Mode.UNIT_CREATION) || controllerMode.equals(Mode.MISSION_CREATION))
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
