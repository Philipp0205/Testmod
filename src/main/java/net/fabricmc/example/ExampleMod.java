package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.blockentities.DrawerBlockEntity;
import net.fabricmc.example.blocks.DrawerBlock;
import net.fabricmc.example.gui.DrawerBlockScreen;
import net.fabricmc.example.gui.DrawerBlockGuiDescription;
import net.fabricmc.example.items.CustomItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");


    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("tutorial", "general"),
            () -> new ItemStack(Blocks.COBBLESTONE));

    public static final Item CUSTOM_ITEM = new CustomItem(new FabricItemSettings().group(ExampleMod.ITEM_GROUP));
    public static final DrawerBlock DRAWER_BLOCK = new DrawerBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final ScreenHandlerType<? extends DrawerBlockGuiDescription> SCREEN_HANDLER_TYPE =
            ScreenHandlerRegistry.registerSimple(new Identifier("example", "drawer"), (syncId, inventory) -> new DrawerBlockGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));

    public static BlockEntityType<DrawerBlockEntity> DRAWER_BLOCK_ENTITY;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        Registry.register(Registry.ITEM, new Identifier("tutorial", "custom_item"), CUSTOM_ITEM);
        // Drawer Block
        DRAWER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "tutorial:drawer_block_entity", FabricBlockEntityTypeBuilder.create(DrawerBlockEntity::new, DRAWER_BLOCK).build(null));

        Registry.register(Registry.BLOCK, new Identifier("tutorial", "drawer_block"), DRAWER_BLOCK);

        Registry.register(Registry.ITEM, new Identifier("tutorial", "drawer_item"),
                new BlockItem(DRAWER_BLOCK, new FabricItemSettings().group(ExampleMod.ITEM_GROUP)));


        ScreenRegistry.<DrawerBlockGuiDescription, DrawerBlockScreen>register(ExampleMod.SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new DrawerBlockScreen(gui, inventory.player, title));


    }
}
