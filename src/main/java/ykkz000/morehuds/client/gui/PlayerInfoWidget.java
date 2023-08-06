package ykkz000.morehuds.client.gui;

import ykkz000.hudapi.gui.Color;
import ykkz000.hudapi.gui.widget.Label;
import ykkz000.hudapi.gui.widget.Panel;
import ykkz000.hudapi.util.Region;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class PlayerInfoWidget extends Panel {

    private final Label playerPositionInfo;
    private final ItemInfoWidget[] itemInfoWidget;
    public PlayerInfoWidget(Region region) {
        super(region, new Color(0.0F, 0.0F, 0.0F, 0.3F));
        playerPositionInfo = new Label(new Region(5, 1, 60, 9), "X:\tY:\tZ:\t");
        add(playerPositionInfo);
        itemInfoWidget = new ItemInfoWidget[4];
        for (int i = 0; i < itemInfoWidget.length; i++) {
            Region regionItemInfoWidget = ItemInfoWidget.getPreferredRegion();
            regionItemInfoWidget.setX(5);
            regionItemInfoWidget.setY(i * (1 + regionItemInfoWidget.getHeight()) + 10);
            itemInfoWidget[i] = new ItemInfoWidget(regionItemInfoWidget);
            add(itemInfoWidget[i]);
        }
    }
    public void setPlayerPosition(double X, double Y, double Z) {
        playerPositionInfo.setString(String.format("X:%.2f Y:%.2f Z:%.2f", X, Y, Z));
    }
    public void setArmorItems(Iterable<ItemStack> itemStack) {
        int i = itemInfoWidget.length - 1;
        if (itemStack != null) {
            for (ItemStack itemStack1 : itemStack) {
                itemInfoWidget[i].setItem(itemStack1);
                i--;
            }
        }
    }
    public static Region getPreferredRegion() {
        return new Region(0, 40, 150, 80);
    }
}
