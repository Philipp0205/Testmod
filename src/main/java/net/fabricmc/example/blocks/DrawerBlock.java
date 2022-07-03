package net.fabricmc.example.blocks;

import net.fabricmc.example.blockentities.DrawerBlockEntity;
import net.fabricmc.example.gui.ExampleGui;
import net.fabricmc.example.gui.ExampleGuiDescription;
import net.fabricmc.example.gui.ExampleScreen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawerBlock extends Block implements BlockEntityProvider {

    public static final Logger LOGGER = LoggerFactory.getLogger(DrawerBlock.class.getName());

    public DrawerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        LOGGER.debug("drawer used");
                player.sendMessage(Text.literal("drawer used "), false);

        MinecraftClient.getInstance().setScreen(new ExampleScreen(new ExampleGui()));

        return ActionResult.SUCCESS;
gT
//            DrawerBlockEntity blockEntity = (DrawerBlockEntity) world.getBlockEntity(pos);
//
//            if (!player.getStackInHand(hand).isEmpty()) {
//                player.sendMessage(Text.literal("player empty handed"), false);
//                // Check what is the first open slot and put an item from the player's hand there
//                assert blockEntity != null;
//                if (blockEntity.getStack(0).isEmpty()) {
//                    // Put the stack the player is holding into the inventory
//                    blockEntity.setStack(0, player.getStackInHand(hand).copy());
//                    // Remove the stack from the player's hand
//                    player.getStackInHand(hand).setCount(0);
//                } else if (blockEntity.getStack(1).isEmpty()) {
//                    blockEntity.setStack(1, player.getStackInHand(hand).copy());
//                    player.getStackInHand(hand).setCount(0);
//                } else {
//                    // If the inventory is full we'll print it's contents
//                    System.out.println("The first slot holds "
//                            + blockEntity.getStack(0) + " and the second slot holds " + blockEntity.getStack(1));
//                }
//            } else {
//                // If the player is not holding anything we'll get give him the items in the block entity one by one
//
//                // Find the first slot that has an item and give it to the player
//                if (!blockEntity.getStack(1).isEmpty()) {
//                    // Give the player the stack in the inventory
//                    player.getInventory().offerOrDrop(blockEntity.getStack(1));
//                    // Remove the stack from the inventory
//                    blockEntity.removeStack(1);
//                } else if (!blockEntity.getStack(0).isEmpty()) {
//                    player.getInventory().offerOrDrop(blockEntity.getStack(0));
//                    blockEntity.removeStack(0);
//                }
//            }
//        }
//        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DrawerBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BlockEntityProvider.super.getTicker(world, state, type);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getGameEventListener(ServerWorld world, T blockEntity) {
        return BlockEntityProvider.super.getGameEventListener(world, blockEntity);
    }


}
