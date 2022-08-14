package io.github.woodiertexas.yardwork;

import io.github.woodiertexas.yardwork.items.Weedwhacker;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Yardwork implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Yardwork");

	public static final TagKey<Block> MACHINE_HARVESTABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier("yardwork", "machine_harvestable"));
	public static final Weedwhacker WEEDWHACKER = new Weedwhacker(new QuiltItemSettings().group(ItemGroup.MISC));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		Registry.register(Registry.ITEM, new Identifier("yardwork", "weedwhacker"), WEEDWHACKER);


	}
}
