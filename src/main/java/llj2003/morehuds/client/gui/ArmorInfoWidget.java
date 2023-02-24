package llj2003.morehuds.client.gui;

import llj2003.hudapi.gui.Color;
import llj2003.hudapi.gui.widget.Panel;
import llj2003.hudapi.util.Region;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ArmorInfoWidget extends Panel {
    private final ItemInfoWidget[] itemInfoWidget;
    public ArmorInfoWidget(Region region) {
        super(region, new Color(0.0F, 0.0F, 0.0F, 0.3F));
        itemInfoWidget = new ItemInfoWidget[4];
        for (int i = 0; i < itemInfoWidget.length; i++) {
            Region regionItemInfoWidget = ItemInfoWidget.getPreferredRegion();
            regionItemInfoWidget.setX(5);
            regionItemInfoWidget.setY(i * (1 + regionItemInfoWidget.getHeight()));
            itemInfoWidget[i] = new ItemInfoWidget(regionItemInfoWidget);
            add(itemInfoWidget[i]);
        }
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
        Region region = ItemInfoWidget.getPreferredRegion();
        return new Region(0, 40, 120, 70);
    }
}
