package ykkz000.morehuds.client.gui;

import ykkz000.hudapi.gui.widget.Panel;
import ykkz000.hudapi.util.Region;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ExtendHudsWidget extends Panel {
    public TargetInfoWidget targetInfoWidget;
    public PlayerInfoWidget playerInfoWidget;
    public TimeInfoWidget timeInfoWidget;

    public ExtendHudsWidget(Region region) {
        super(region);
        targetInfoWidget = new TargetInfoWidget(TargetInfoWidget.getPreferredRegion());
        playerInfoWidget = new PlayerInfoWidget(PlayerInfoWidget.getPreferredRegion());
        timeInfoWidget = new TimeInfoWidget(TimeInfoWidget.getPreferredRegion());
        add(targetInfoWidget);
        add(playerInfoWidget);
        add(timeInfoWidget);
    }

    public void reAdjustRegion() {
        Region region = getRegion();
        region.setX(MinecraftClient.getInstance().getWindow().getScaledWidth() - region.getWidth() - 5);
        setRegion(region);
    }

    public static Region getPreferredRegion() {
        return new Region(0, 20, 150, 0);
    }
}
