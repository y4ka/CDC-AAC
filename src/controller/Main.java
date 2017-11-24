package controller;

import vue.MainFrame;

public class Main
{
	public static void main (String [] args)
	{
		MainFrame mainFrame = new MainFrame();
		Controller controller = new Controller();
		
		mainFrame.addController(controller);
		
		Clock clock = new Clock();
		clock.addClockListener(mainFrame);
		clock.addClockListener(controller);
		clock.launch();
	}
}
