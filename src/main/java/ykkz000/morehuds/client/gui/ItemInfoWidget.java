package ykkz000.morehuds.client.gui;

import ykkz000.hudapi.gui.Color;
import ykkz000.hudapi.gui.widget.ItemDisplay;
import ykkz000.hudapi.gui.widget.Panel;
import ykkz000.hudapi.gui.widget.ProgressBar;
import ykkz000.hudapi.util.Region;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ItemInfoWidget extends Panel {
    private final ItemDisplay itemDisplay;
    private final ProgressBar bar;

    public ItemInfoWidget(Region region) {
        super(region);
        itemDisplay = new ItemDisplay(new Region(0, 0, 16, 16), null);
        bar = new ProgressBar(new Region(20, 5, 120, 5),
                Color.fromInt(0xFF16B900), Color.fromInt(0xFF073900), 1.0F);
        add(itemDisplay);
        add(bar);
    }

    public void setItem(ItemStack item) {
        itemDisplay.setItemStack(item);
        if (item == null) {
            bar.setProgress(1.0F);
            return;
        }
        if (item.getMaxDamage() != 0.0F) {
            bar.setProgress(1.0F - ((float) item.getDamage()) / ((float) item.getMaxDamage()));
        } else {
            bar.setProgress(1.0F);
        }
    }

    public static Region getPreferredRegion() {
        return new Region(0, 0, 140, 16);
    }
}
