package vue.panelTime;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Logger
{
	private static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	public static void log(String txt)
	{
		for (JLabel label : labels)
		{
			label.setText(txt);
		}
	}
	
	public static void addSource(JLabel source)
	{
		labels.add(source);
	}
}
