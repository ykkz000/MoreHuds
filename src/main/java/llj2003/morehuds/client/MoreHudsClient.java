package llj2003.morehuds.client;

import llj2003.hudapi.HudApiMain;
import llj2003.morehuds.client.gui.ExtendHudsWidget;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class MoreHudsClient implements ClientModInitializer {
    public static final String MOD_ID = "morehuds";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ExtendHudsWidget EXTEND_HUDS_WIDGET = new ExtendHudsWidget(ExtendHudsWidget.getPreferredRegion());
    public static final KeyBinding KEY_BINDING_F4 = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(String.format("key.%s.visibility", MOD_ID),
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_F4,
                    String.format("category.%s.base", MOD_ID)));
    @Override
    public void onInitializeClient() {
        HudApiMain.MAIN_PANEL.add(EXTEND_HUDS_WIDGET);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null) {
                LOGGER.info("client == null");
            } else {
                EXTEND_HUDS_WIDGET.targetInfoWidget.setTarget(client.crosshairTarget);
                if (client.player == null) {
                    LOGGER.info("client.player == null");
                } else {
                    EXTEND_HUDS_WIDGET.armorInfoWidget.setArmorItems(client.player.getArmorItems());
                }
                EXTEND_HUDS_WIDGET.reAdjustRegion();
            }
            if (KEY_BINDING_F4.wasPressed()) {
                EXTEND_HUDS_WIDGET.setVisible(!EXTEND_HUDS_WIDGET.isVisible());
                LOGGER.info("EXTEND_HUDS_WIDGET.isVisible() == " + EXTEND_HUDS_WIDGET.isVisible());
            }
        });
    }
}
