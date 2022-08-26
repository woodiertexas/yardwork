package io.github.woodiertexas.yardwork.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static io.github.woodiertexas.yardwork.Yardwork.MACHINE_HARVESTABLE;

public class Weedwhacker extends Item implements DyeableItem {
    public Weedwhacker(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!user.isSpectator() && !world.isClient()) {
            var cast = user.raycast(3.5, 0, false);
            var pos1 = cast.getPos();
            BlockPos position = new BlockPos(pos1);

            for (BlockPos pos2 : BlockPos.iterate(position.add(-1, 0, -1), position.add(1, 1, 1))) {
                if (world.getBlockState(pos2).isIn(MACHINE_HARVESTABLE)) {
                    world.breakBlock(pos2, true, user, 4);
                }
            }
            return TypedActionResult.pass(stack);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 999999;
    }
}