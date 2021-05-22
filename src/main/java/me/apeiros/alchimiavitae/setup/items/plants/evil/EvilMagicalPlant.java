package me.apeiros.alchimiavitae.setup.items.plants.evil;

import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import me.apeiros.alchimiavitae.setup.AlchimiaVitaeItems;
import me.apeiros.alchimiavitae.utils.AlchimiaVitaeRecipeTypes;
import me.apeiros.alchimiavitae.utils.Categories;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EvilMagicalPlant extends UnplaceableBlock {

    public EvilMagicalPlant() {

        super(Categories.GENERAL, AlchimiaVitaeItems.CONDENSED_SOUL, AlchimiaVitaeRecipeTypes.PLANT_INFUSION_CHAMBER_TYPE, new ItemStack[] {
                new ItemStack(Material.OAK_SAPLING), AlchimiaVitaeItems.CONDENSED_SOUL, null,
                null, null, null,
                null, null, null
        });

    }

}
