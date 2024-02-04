package com.coxHerbTracker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

public class CoxHerbTrackerOverlay extends Overlay
{
    private final Client client;
    private final CoxHerbTrackerPlugin plugin;

    @Inject
    private CoxHerbTrackerOverlay(Client client, CoxHerbTrackerPlugin plugin)
    {
        this.client = client;
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        int herbsFarmed = plugin.getHerbsFarmed();

        if (herbsFarmed > 0)
        {
            String text = "Herbs: " + herbsFarmed;
            int textX = 10; // Adjust as needed
            int textY = 30; // Adjust as needed

            graphics.setColor(Color.WHITE);
            graphics.drawString(text, textX, textY);
        }

        return null;
    }
}
