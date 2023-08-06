package ykkz000.morehuds.client;

import ykkz000.hudapi.HudApiMain;
import ykkz000.hudapi.gui.widget.Panel;
import ykkz000.morehuds.client.gui.ExtendHudsWidget;
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
    public static final Panel MAIN_FRAME = HudApiMain.allocateFrame();
    @Override
    public void onInitializeClient() {
        MAIN_FRAME.add(EXTEND_HUDS_WIDGET);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null) {
                LOGGER.info("client == null");
            } else {
                EXTEND_HUDS_WIDGET.targetInfoWidget.setTarget(client.crosshairTarget);
                if (client.player == null) {
                    LOGGER.info("client.player == null");
                } else {
                    EXTEND_HUDS_WIDGET.playerInfoWidget.setPlayerPosition(client.player.getX(), client.player.getY(), client.player.getZ());
                    EXTEND_HUDS_WIDGET.playerInfoWidget.setArmorItems(client.player.getArmorItems());
                }
                EXTEND_HUDS_WIDGET.reAdjustRegion();
            }
            if (KEY_BINDING_F4.wasPressed()) {
                MAIN_FRAME.setVisible(!MAIN_FRAME.isVisible());
                LOGGER.info("MAIN_FRAME.isVisible() == " + MAIN_FRAME.isVisible());
            }
        });
    }
}
