package net.fabricmc.example.gui;


import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class DrawerBlockScreen extends CottonInventoryScreen<DrawerBlockGuiDescription> {
    public DrawerBlockScreen(DrawerBlockGuiDescription gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}
