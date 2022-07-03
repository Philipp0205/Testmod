package net.fabricmc.example.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.text.Text;

public class ExampleScreen extends CottonClientScreen {

    public ExampleScreen(GuiDescription description) {
        super(description);
    }
}