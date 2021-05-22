package me.apeiros.alchimiavitae.setup.items.plants.good;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import me.apeiros.alchimiavitae.setup.AlchimiaVitaeItems;
import me.apeiros.alchimiavitae.utils.AlchimiaVitaeRecipeTypes;
import me.apeiros.alchimiavitae.utils.Categories;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GoodMagicalPlant extends UnplaceableBlock {

    public GoodMagicalPlant() {

        super(Categories.GENERAL, AlchimiaVitaeItems.CONDENSED_SOUL, AlchimiaVitaeRecipeTypes.PLANT_INFUSION_CHAMBER_TYPE, new ItemStack[] {
                new ItemStack(Material.OAK_SAPLING), SlimefunItems.MAGIC_LUMP_3, null,
                null, null, null,
                null, null, null
        });

    }

}
