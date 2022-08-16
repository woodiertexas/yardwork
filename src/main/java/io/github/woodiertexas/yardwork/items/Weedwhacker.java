package io.github.woodiertexas.yardwork.items;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static io.github.woodiertexas.yardwork.Yardwork.MACHINE_HARVESTABLE;

public class Weedwhacker extends Item implements DyeableItem {
    public Weedwhacker(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        MinecraftClient client = MinecraftClient.getInstance();
        ItemStack stack = user.getStackInHand(hand);

        if (client.crosshairTarget instanceof BlockHitResult blockHitResult) {
            BlockPos position = blockHitResult.getBlockPos();
            if (!user.isSpectator()) {
                for (BlockPos pos : BlockPos.iterate(position.add(-1, 0, -1), position.add(1, 1, 1))) {
                    if (world.getBlockState(pos).isIn(MACHINE_HARVESTABLE)) {
                        world.breakBlock(pos, true, user, 4);
                    }
                }
                return TypedActionResult.pass(stack);
            }
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 999999;
    }
}