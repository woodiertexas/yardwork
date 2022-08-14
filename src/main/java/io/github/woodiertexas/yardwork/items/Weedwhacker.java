package io.github.woodiertexas.yardwork.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static io.github.woodiertexas.yardwork.Yardwork.MACHINE_HARVESTABLE;

public class Weedwhacker extends Item {
    public Weedwhacker(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos position = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        double randomX = Math.random();
        double randomY = Math.random();
        assert player != null;
        if (!player.isSpectator()) {
            for (BlockPos pos : BlockPos.iterate(position.add(-randomX, 0, -randomY), position.add(randomX, 1, randomY))) {
                if (world.getBlockState(pos).isIn(MACHINE_HARVESTABLE)) {
                    world.breakBlock(pos, true, player, 4);
                }
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }
}