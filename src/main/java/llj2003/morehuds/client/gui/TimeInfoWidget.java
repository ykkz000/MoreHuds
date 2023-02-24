package llj2003.morehuds.client.gui;

import llj2003.hudapi.HudApiMain;
import llj2003.hudapi.gui.Color;
import llj2003.hudapi.gui.widget.Label;
import llj2003.hudapi.gui.widget.Panel;
import llj2003.hudapi.util.Region;
import llj2003.morehuds.client.util.lang.IdTranslator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;

import java.util.Date;

@Environment(EnvType.CLIENT)
public class TimeInfoWidget extends Panel {
    private final Label tickInfo;
    private final Label minecraftTimeInfo;
    private final Label realisticTimeInfo;

    public TimeInfoWidget(Region region) {
        super(region, new Color(0.0F, 0.0F, 0.0F, 0.3F));
        tickInfo = new Label(new Region(5, 1, 60, 9), "");
        minecraftTimeInfo = new Label(new Region(5, 11, 60, 9), "");
        realisticTimeInfo = new Label(new Region(5, 21, 60, 9), "");
        add(tickInfo);
        add(minecraftTimeInfo);
        add(realisticTimeInfo);
    }

    @Override
    public void render(MatrixStack matrixStack) {
        if (HudApiMain.client != null && HudApiMain.client.world != null) {
            ClientWorld clientWorld = HudApiMain.client.world;
            tickInfo.setString(String.format("%s: %d", IdTranslator.translateString("game_tick"), clientWorld.getTime()));
            minecraftTimeInfo.setString(String.format("%s: %02d:%02d",
                    IdTranslator.translateString("game_time"),
                    (clientWorld.getTimeOfDay() / 1000 + 6) % 24,
                    (clientWorld.getTimeOfDay() % 1000) * 60 / 1000));
            Date currentDate = new Date();
            realisticTimeInfo.setString(String.format("%s: %tT",
                    IdTranslator.translateString("time"), currentDate));
        }
        super.render(matrixStack);
    }

    public static Region getPreferredRegion() {
        return new Region(0, 120, 120, 31);
    }
}
