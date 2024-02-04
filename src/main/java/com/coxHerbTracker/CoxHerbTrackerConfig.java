package com.coxHerbTracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface CoxHerbTrackerConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Type Here",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}
}
