package ykkz000.morehuds.client.gui;

import ykkz000.hudapi.gui.Color;
import ykkz000.hudapi.gui.widget.ProgressBar;
import ykkz000.hudapi.gui.widget.Label;
import ykkz000.hudapi.gui.widget.Panel;
import ykkz000.hudapi.util.Region;
import ykkz000.morehuds.client.util.lang.IdTranslator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

@Environment(EnvType.CLIENT)
public class TargetInfoWidget extends Panel {
    private final Label nameLabel;
    private final Label healthInfoLabel;
    private final ProgressBar healthInfoBar;
    public TargetInfoWidget(Region region) {
        super(region, new Color(0.0F, 0.0F, 0.0F, 0.3F));
        nameLabel = new Label(new Region(5, 1, 60, 9), "没有目标");
        healthInfoLabel = new Label(new Region(5, 11, 60, 9), "");
        healthInfoBar = new ProgressBar(new Region(5, 21, 140, 5),
                Color.fromInt(0xFFB92A00), Color.fromInt(0xFF491000), 1.0F);
        healthInfoLabel.setVisible(false);
        healthInfoBar.setVisible(false);
        add(nameLabel);
        add(healthInfoLabel);
        add(healthInfoBar);
    }
    public void setTarget(HitResult hitResult) {
        if (MinecraftClient.getInstance() == null || MinecraftClient.getInstance().world == null) {
            return;
        }
        switch (hitResult.getType()) {
            case BLOCK -> {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                Block block = MinecraftClient.getInstance().world.getBlockState(blockHitResult.getBlockPos()).getBlock();
                nameLabel.setString(String.format("%s: %s",
                        IdTranslator.translateString("name"), block.getName().getString()));
                healthInfoLabel.setVisible(false);
                healthInfoBar.setVisible(false);
            }
            case ENTITY -> {
                EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                Entity entity = entityHitResult.getEntity();
                nameLabel.setString(String.format("%s: %s",
                        IdTranslator.translateString("name"), entity.getName().getString()));
                if (entity instanceof LivingEntity livingEntity) {
                    healthInfoLabel.setString(String.format("%s: %.1f/%.1f",
                            IdTranslator.translateString("health"),
                            livingEntity.getHealth(), livingEntity.getMaxHealth()));
                    healthInfoBar.setProgress(livingEntity.getHealth() / livingEntity.getMaxHealth());
                    healthInfoLabel.setVisible(true);
                    healthInfoBar.setVisible(true);
                } else {
                    healthInfoLabel.setVisible(false);
                    healthInfoBar.setVisible(false);
                }
            }
            case MISS -> {
                nameLabel.setString(IdTranslator.translateString("no_target"));
                healthInfoLabel.setVisible(false);
                healthInfoBar.setVisible(false);
            }
        }
    }
    public static Region getPreferredRegion() {
        return new Region(0, 0, 150, 30);
    }
}
