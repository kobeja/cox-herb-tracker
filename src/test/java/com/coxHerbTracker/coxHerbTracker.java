package com.coxHerbTracker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class coxHerbTracker
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CoxHerbTrackerPlugin.class);
		RuneLite.main(args);
	}
}