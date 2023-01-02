package io.github.woodiertexas.yardwork;

import io.github.woodiertexas.yardwork.items.Weedwhacker;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.client.itemgroup.CreativeGuiExtensions;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Yardwork implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Yardwork");

	public static final TagKey<Block> MACHINE_HARVESTABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier("yardwork", "machine_harvestable"));
	public static final TagKey<Block> NON_MACHINE_HARVESTABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier("yardwork", "non_machine_harvestable"));
	public static SpecialRecipeSerializer<WeedwhackerRecipe> WEEDWHACKER_RECIPE;
	public static final Weedwhacker WEEDWHACKER = new Weedwhacker(new QuiltItemSettings().maxCount(1));
	
	@Override
	public void onInitialize(ModContainer mod) {
		Registry.register(Registries.ITEM, new Identifier("yardwork", "weedwhacker"), WEEDWHACKER);
		Registry.register(Registries.RECIPE_SERIALIZER, new Identifier("yardwork", "special_craft_weedwhacker_recipe"), new SpecialRecipeSerializer<>(WeedwhackerRecipe::new));

		RecipeManagerHelper.registerStaticRecipe(
				VanillaRecipeBuilders.shapedRecipe(
						"B R",
								" I ",
								"S  ")
						.output(new ItemStack(WEEDWHACKER))
						.ingredient('B', Items.STONE_BUTTON)
						.ingredient('R', Items.REDSTONE)
						.ingredient('I', Items.IRON_INGOT)
						.ingredient('S', Items.STRING)
						.build(new Identifier("yardwork", "weedwhacker"), "")
		);
	}
}
