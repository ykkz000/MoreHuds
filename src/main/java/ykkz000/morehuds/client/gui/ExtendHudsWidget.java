package ykkz000.morehuds.client.gui;

import ykkz000.hudapi.gui.widget.Panel;
import ykkz000.hudapi.util.Region;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ExtendHudsWidget extends Panel {
    public TargetInfoWidget targetInfoWidget;
    public ArmorInfoWidget armorInfoWidget;
    public TimeInfoWidget timeInfoWidget;

    public ExtendHudsWidget(Region region) {
        super(region);
        targetInfoWidget = new TargetInfoWidget(TargetInfoWidget.getPreferredRegion());
        armorInfoWidget = new ArmorInfoWidget(ArmorInfoWidget.getPreferredRegion());
        timeInfoWidget = new TimeInfoWidget(TimeInfoWidget.getPreferredRegion());
        add(targetInfoWidget);
        add(armorInfoWidget);
        add(timeInfoWidget);
    }

    public void reAdjustRegion() {
        Region region = getRegion();
        region.setX(MinecraftClient.getInstance().getWindow().getScaledWidth() - region.getWidth() - 5);
        setRegion(region);
    }

    public static Region getPreferredRegion() {
        Region region = ItemInfoWidget.getPreferredRegion();
        return new Region(0, 20, 120, 0);
    }
}
