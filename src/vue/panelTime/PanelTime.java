package vue.panelTime;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class PanelTime extends JPanel
{
	private Controller controller;
	
	private JLabel labelTime = new JLabel("Time");
	private JLabel labelStatus = new JLabel("Status");
	
	public PanelTime()
	{
		this.add(labelTime);
		this.add(labelStatus);
		Logger.addSource(labelStatus);
	}
	
	public void addController(Controller controller)
	{
		this.controller = controller;
	}
	
	public void update()
	{
		//On met à jour l'heure:
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("h:mm:ss");
		labelTime.setText(formater.format(date));
	}
}
