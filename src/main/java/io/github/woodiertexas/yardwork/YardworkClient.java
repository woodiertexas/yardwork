package io.github.woodiertexas.yardwork;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import static io.github.woodiertexas.yardwork.Yardwork.WEEDWHACKER;

public class YardworkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem)stack.getItem()).getColor(stack), WEEDWHACKER);

		ModelPredicateProviderRegistry.register(WEEDWHACKER, new Identifier("run"), (itemStack, world, entity, junk) -> {
			if (entity == null) {
				return 0.0f;
			}
			return entity.getActiveItem() != itemStack ? 0.0f : (itemStack.getMaxUseTime());
		});

		ModelPredicateProviderRegistry.register(WEEDWHACKER, new Identifier("active"), (itemStack, world, entity, junk) -> {
			if (entity == null) {
				return 0.0f;
			}
			return entity.isUsingItem() && entity.getActiveItem() != itemStack ? 1.0f : 0.0f;
		});
	}
}
