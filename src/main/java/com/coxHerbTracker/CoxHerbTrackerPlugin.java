package com.coxHerbTracker;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "Cox Herb Tracker",
		description = "Tracks herbs farmed in the Chamber of Xeric farming room",
		tags = {"chamber", "xeric", "herb", "tracker"}
)
public class CoxHerbTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ItemManager itemManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CoxHerbTrackerOverlay overlay;

	@Inject
	private CoxHerbTrackerConfig config;

	@Getter
	private int herbsFarmed;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		log.info("Cox Herb Tracker started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.info("Cox Herb Tracker stopped!");
	}

	@Provides
	CoxHerbTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CoxHerbTrackerConfig.class);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOGGED_IN)
		{
			herbsFarmed = 0;
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (isInFarmingRoom())
		{
			herbsFarmed += countHerbsInInventory();
		}
	}

	private boolean isInFarmingRoom()
	{
		//TODO: find out how to check if player is in a farming room
		return true;
	}

	private int countHerbsInInventory()
	{
		//TODO: find buchu ID and replace value here
		int herbItemId = 0;
		ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);

		if (inventory != null)
		{
			Item[] items = inventory.getItems();
			int count = 0;

			for (Item item : items)
			{
				if (item.getId() == herbItemId)
				{
					count += item.getQuantity();
				}
			}

			return count;
		}

		return 0;
	}

}
