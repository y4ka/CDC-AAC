package controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Clock
{
	private Timer timer = new Timer();
	private TimerTask task;
	private ArrayList<ClockListener> clockListeners = new ArrayList<ClockListener>();
	private int ticksFromBeginning = 0;
	
	public Clock()
	{
		task = new TimerTask()
		{
			@Override
			public void run() 
			{
				for (ClockListener clockListener : clockListeners)
				{
					ticksFromBeginning++;
					clockListener.update();
				}
			}
		};
	}
	
	public void launch()
	{
		timer.scheduleAtFixedRate(task, 0, 100);
	}
	
	public void addClockListener(ClockListener clockListener)
	{
		clockListeners.add(clockListener);
	}
}
