package llj2003.morehuds.client.gui;

import llj2003.hudapi.exception.KeyNotItemException;
import llj2003.hudapi.exception.NotKeyException;
import llj2003.hudapi.gui.Color;
import llj2003.hudapi.gui.widget.ProgressBar;
import llj2003.hudapi.gui.widget.Image;
import llj2003.hudapi.gui.widget.Panel;
import llj2003.hudapi.util.Region;
import llj2003.hudapi.util.Texture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ItemInfoWidget extends Panel {
    private final Image image;
    private final ProgressBar bar;

    public ItemInfoWidget(Region region) {
        super(region);
        image = new Image(new Region(0, 0, 16, 16), null);
        bar = new ProgressBar(new Region(20, 5, 90, 5),
                Color.fromInt(0xFF16B900), Color.fromInt(0xFF073900), 1.0F);
        add(image);
        add(bar);
    }

    public void setItem(ItemStack item) {
        if (item == null) {
            image.setTexture(null);
            bar.setProgress(0.0F);
            return;
        }
        try {
            image.setTexture(Texture.fromItem(item.getTranslationKey()));
            if (item.getMaxDamage() != 0.0F) {
                bar.setProgress(1.0F - ((float) item.getDamage()) / ((float) item.getMaxDamage()));
            } else {
                bar.setProgress(1.0F);
            }
        } catch (NotKeyException | KeyNotItemException e) {
            e.printStackTrace();
        }
    }
    public static Region getPreferredRegion() {
        return new Region(0, 0, 110, 16);
    }
}
