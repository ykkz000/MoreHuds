package llj2003.morehuds.client.gui;

import llj2003.hudapi.HudApiMain;
import llj2003.hudapi.gui.widget.Panel;
import llj2003.hudapi.util.Region;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

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
        region.setX(HudApiMain.client.getWindow().getScaledWidth() - region.getWidth() - 5);
        setRegion(region);
    }

    public static Region getPreferredRegion() {
        Region region = ItemInfoWidget.getPreferredRegion();
        return new Region(0, 20, 120, 0);
    }
}
