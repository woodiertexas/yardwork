package io.github.woodiertexas.yardwork.items;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
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

        assert player != null;
        if (!player.isSpectator()) {
            if (world.getBlockState(position).isIn(MACHINE_HARVESTABLE)) {
                world.breakBlock(position, true, null, 0);
                return ActionResult.CONSUME;
            }
        }
        return ActionResult.PASS;
    }
}
