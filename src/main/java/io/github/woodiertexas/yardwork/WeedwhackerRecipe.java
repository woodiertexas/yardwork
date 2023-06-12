package io.github.woodiertexas.yardwork;

import com.google.common.collect.Lists;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.CraftingCategory;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

import static io.github.woodiertexas.yardwork.Yardwork.WEEDWHACKER_RECIPE;

public class WeedwhackerRecipe extends SpecialCraftingRecipe {
    public WeedwhackerRecipe(Identifier identifier, CraftingCategory theCategory) {
        super(identifier, theCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        ItemStack itemStack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();

        for(int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack2 = inventory.getStack(i);
            if (!itemStack2.isEmpty() || !(itemStack2.getItem() instanceof DyeItem)) {
                return false;
            }

            if (itemStack2.getItem() instanceof DyeableItem) {
                if (!itemStack.isEmpty()) {
                    return false;
                }

                itemStack = itemStack2;
            }

            list.add(itemStack2);
        }

        return !itemStack.isEmpty() && !list.isEmpty();
    }

	@Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        List<DyeItem> list = Lists.newArrayList();
        ItemStack itemStack = ItemStack.EMPTY;

        for(int i = 0; i < inventory.size(); ++i) {
            Item item = null;
            ItemStack itemStack2 = inventory.getStack(i);

            if (!itemStack2.isEmpty()) {
                item = itemStack2.getItem();
            }

            if (item instanceof DyeableItem) {
                if (!itemStack.isEmpty()) {
                    return ItemStack.EMPTY;
                }

                itemStack = itemStack2.copy();
            } else {
                if (!(item instanceof DyeItem)) {
                    return ItemStack.EMPTY;
                }

                list.add((DyeItem) item);
            }
        }
        return !itemStack.isEmpty() && !list.isEmpty() ? DyeableItem.blendAndSetColor(itemStack, list) : ItemStack.EMPTY;
    }

	@Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WEEDWHACKER_RECIPE;
    }
}
