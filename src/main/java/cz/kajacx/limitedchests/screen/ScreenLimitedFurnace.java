package cz.kajacx.limitedchests.screen;

import cz.kajacx.limitedchests.container.ContainerLimitedFurnace;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

//@OnlyIn(Dist.CLIENT)
public class ScreenLimitedFurnace extends AbstractFurnaceScreen<ContainerLimitedFurnace> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft:textures/gui/container/furnace.png");

   public ScreenLimitedFurnace(ContainerLimitedFurnace menu, PlayerInventory inventory, ITextComponent title) {
      super(menu, new FurnaceRecipeGui(), inventory, title, TEXTURE);
   }
}
