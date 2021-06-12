package me.apeiros.alchimiavitae.setup;

import io.github.mooy1.infinitylib.recipes.inputs.MultiInput;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import io.github.thebusybiscuit.slimefun4.core.services.CustomTextureService;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import me.apeiros.alchimiavitae.AlchimiaVitae;
import me.apeiros.alchimiavitae.listeners.EntityDeathListener;
import me.apeiros.alchimiavitae.listeners.infusion.*;
import me.apeiros.alchimiavitae.setup.items.crafters.AltarOfInfusion;
import me.apeiros.alchimiavitae.setup.items.crafters.DivineAltar;
import me.apeiros.alchimiavitae.setup.items.crafters.OrnateCauldron;
import me.apeiros.alchimiavitae.setup.items.electric.EXPCrystallizer;
import me.apeiros.alchimiavitae.setup.items.electric.PlantInfusionChamber;
import me.apeiros.alchimiavitae.setup.items.general.*;
import me.apeiros.alchimiavitae.setup.items.plants.EvilEssence;
import me.apeiros.alchimiavitae.setup.items.plants.EvilMagicPlant;
import me.apeiros.alchimiavitae.setup.items.plants.GoodEssence;
import me.apeiros.alchimiavitae.setup.items.plants.GoodMagicPlant;
import me.apeiros.alchimiavitae.setup.items.potions.BenevolentBrew;
import me.apeiros.alchimiavitae.setup.items.potions.MalevolentConcoction;
import me.apeiros.alchimiavitae.setup.items.potions.PotionOfOsmosis;
import me.apeiros.alchimiavitae.utils.Categories;
import me.apeiros.alchimiavitae.utils.RecipeTypes;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class Setup {

    private static final NamespacedKey axeInfusionDestructiveCrits = new NamespacedKey(AlchimiaVitae.i(), "infusion_destructivecrits");
    private static final NamespacedKey axeInfusionPhantomCrits = new NamespacedKey(AlchimiaVitae.i(), "infusion_phantomcrits");
    private static final NamespacedKey chestplateInfusionTotemBattery = new NamespacedKey(AlchimiaVitae.i(), "infusion_totemstorage");
    private static final NamespacedKey bowInfusionTrueAim = new NamespacedKey(AlchimiaVitae.i(), "infusion_trueaim");
    private static final NamespacedKey bowInfusionForceful = new NamespacedKey(AlchimiaVitae.i(), "infusion_forceful");
    private static final NamespacedKey bowInfusionVolatile = new NamespacedKey(AlchimiaVitae.i(), "infusion_volatile");
    private static final NamespacedKey bowInfusionHealing = new NamespacedKey(AlchimiaVitae.i(), "infusion_healing");
    private static final NamespacedKey hoeInfusionAutoReplant = new NamespacedKey(AlchimiaVitae.i(), "infusion_autoreplant");

    public static void setup(AlchimiaVitae p) {
        // Category
        Categories.MAIN.register(p);

        // Items
        new SoulCollector(Categories.GENERAL).register(p);
        new CondensedSoul(Categories.GENERAL).register(p);
        new PlantInfusionChamber(Categories.GENERAL).register(p);
        new GoodMagicPlant(Categories.GENERAL).register(p);
        new EvilMagicPlant(Categories.GENERAL).register(p);
        new GoodEssence(Categories.GENERAL).register(p);
        new EvilEssence(Categories.GENERAL).register(p);
        new EXPCrystallizer(Categories.GENERAL).register(p);
        new EXPCrystal(Categories.GENERAL).register(p);
        new Illumium(Categories.GENERAL).register(p);
        new Darksteel(Categories.GENERAL).register(p);

        // Divine Altar
        new DivineAltar(Categories.GENERAL).register(p);
        setupDivineAltar(p);

        // Items cont.d
        new MoltenMysteryMetal(Categories.GENERAL).register(p);
        new MysteryMetal(Categories.GENERAL).register(p);

        // Ornate Cauldron
        new OrnateCauldron(Categories.GENERAL).register(p);
        setupOrnateCauldron();

        // Items cont.d
        new PotionOfOsmosis(Categories.GENERAL).register(p);
        new BenevolentBrew(Categories.GENERAL, p).register(p);
        new MalevolentConcoction(Categories.GENERAL, p).register(p);

        // Altar of Infusion
        new AltarOfInfusion(Categories.INFUSIONS).register(p);
        setupInfusionAltar(p);

        // Listeners
        new EntityDeathListener(p);
        new InfusionAxeAttackListener(p);
        new InfusionBowShootListener(p);
        new InfusionHoeReapListener(p);
        new InfusionTotemRightClickListener(p);
        new InfusionTotemStorageDeathListener(p);

        // Researches
        setupResearches(p);
    }

    public static void setupResearches(AlchimiaVitae p) {
        new Research(new NamespacedKey(p, "soul"), 131072,
                "Breaking the cycle of life and death", 25)
                .addItems(Items.CONDENSED_SOUL, Items.SOUL_COLLECTOR)
                .register();

        new Research(new NamespacedKey(p, "magic_plants"), 131073,
                "Two polar opposites", 30)
                .addItems(Items.PLANT_INFUSION_CHAMBER, Items.GOOD_MAGIC_PLANT, Items.EVIL_MAGIC_PLANT)
                .register();

        new Research(new NamespacedKey(p, "magic_essence"), 131074,
                "Grinding it down", 10)
                .addItems(Items.GOOD_ESSENCE, Items.EVIL_ESSENCE)
                .register();

        new Research(new NamespacedKey(p, "exp_crystals"), 131075,
                "Crystalline experience", 21)
                .addItems(Items.EXP_CRYSTALLIZER, Items.EXP_CRYSTAL)
                .register();

        new Research(new NamespacedKey(p, "magic_steel"), 131076,
                "Mystical metals", 16)
                .addItems(Items.DARKSTEEL, Items.ILLUMIUM)
                .register();

        new Research(new NamespacedKey(p, "divine_altar"), 131077,
                "The long-lost cousin of the Ancient Altar", 45)
                .addItems(Items.DIVINE_ALTAR)
                .register();

        new Research(new NamespacedKey(p, "metal_amalgamation"), 131078,
                "An amalgamation of metallic substances", 19)
                .addItems(Items.MOLTEN_MYSTERY_METAL, Items.MYSTERY_METAL)
                .register();

        new Research(new NamespacedKey(p, "ornate_cauldron"), 131079,
                "A contraption to brew advanced potions", 35)
                .addItems(Items.ORNATE_CAULDRON)
                .register();

        new Research(new NamespacedKey(p, "potion_of_osmosis"), 131080,
                "Osmosis and absorption", 30)
                .addItems(Items.POTION_OF_OSMOSIS)
                .register();

        new Research(new NamespacedKey(p, "benevolent_brew"), 131081,
                "A blessing from Gaia herself", 35)
                .addItems(Items.BENEVOLENT_BREW)
                .register();

        new Research(new NamespacedKey(p, "malevolent_concoction"), 131082,
                "A substance with a slightly corrupted tinge", 35)
                .addItems(Items.MALEVOLENT_CONCOCTION)
                .register();

        new Research(new NamespacedKey(p, "altar_of_infusion"), 131083,
                "The ultimate altar to energize your items", 30)
                .addItems(Items.ALTAR_OF_INFUSION)
                .register();
    }

    private static void setupDivineAltar(AlchimiaVitae p) {
        // Get config values
        boolean reinforcedTransmutationEnabled = p.getConfig().getBoolean("options.transmutations.reinforced-transmutation");
        boolean hardenedTransmutationEnabled = p.getConfig().getBoolean("options.transmutations.hardened-transmutation");
        boolean steelTransmutationEnabled = p.getConfig().getBoolean("options.transmutations.steel-transmutation");
        boolean damascusTransmutationEnabled = p.getConfig().getBoolean("options.transmutations.damascus-transmutation");
        boolean compressedCarbonTransmutationEnabled = p.getConfig().getBoolean("options.transmutations.compressed-carbon-transmutation");
        boolean useSlimefunItemCustomModelData = p.getConfig().getBoolean("options.transmutations.use-same-custommodeldata");

        // ItemStack and texture service
        SlimefunItemStack item;
        CustomTextureService cts = SlimefunPlugin.getItemTextureService();

        // Add transmutations
        if (reinforcedTransmutationEnabled) {
            DivineAltar.RECIPES.put(new MultiInput(new ItemStack[] {
                    null, SlimefunItems.DAMASCUS_STEEL_INGOT, null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.DAMASCUS_STEEL_INGOT, null
            }), new SlimefunItemStack(SlimefunItems.REINFORCED_ALLOY_INGOT, 2));

            item = new SlimefunItemStack("AV_REINFORCED_ALLOY_INGOT", Material.IRON_INGOT, "&b&lReinforced Alloy Ingot");

            if (useSlimefunItemCustomModelData) {
                item.setCustomModel(cts.getModelData("REINFORCED_ALLOY_INGOT"));
                cts.setTexture(item, "AV_REINFORCED_ALLOY_INGOT");
            }

            new SlimefunItem(Categories.ALTAR_RECIPES, item, RecipeTypes.DIVINE_ALTAR_TYPE, new ItemStack[] {
                    null, SlimefunItems.DAMASCUS_STEEL_INGOT, null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.DAMASCUS_STEEL_INGOT, null
            }, new SlimefunItemStack(item, 2)).register(p);
        }

        if (hardenedTransmutationEnabled) {
            DivineAltar.RECIPES.put(new MultiInput(new ItemStack[] {
                    null, SlimefunItems.STEEL_INGOT, null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.STEEL_INGOT, null
            }), new SlimefunItemStack(SlimefunItems.HARDENED_METAL_INGOT, 2));

            item = new SlimefunItemStack("AV_HARDENED_METAL_INGOT", Material.IRON_INGOT, "&b&lHardened Metal");

            if (useSlimefunItemCustomModelData) {
                item.setCustomModel(cts.getModelData("HARDENED_METAL_INGOT"));
                cts.setTexture(item, "AV_HARDENED_METAL_INGOT");
            }

            new SlimefunItem(Categories.ALTAR_RECIPES, item, RecipeTypes.DIVINE_ALTAR_TYPE, new ItemStack[] {
                    null, SlimefunItems.STEEL_INGOT, null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.STEEL_INGOT, null
            }, new SlimefunItemStack(item, 2)).register(p);
        }

        if (steelTransmutationEnabled) {
            DivineAltar.RECIPES.put(new MultiInput(new ItemStack[] {
                    null, new ItemStack(Material.IRON_BLOCK), null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.CARBON, null
            }), new SlimefunItemStack(SlimefunItems.STEEL_INGOT, 8));

            item = new SlimefunItemStack("AV_STEEL_INGOT", Material.IRON_INGOT, "&bSteel Ingot");

            if (useSlimefunItemCustomModelData) {
                item.setCustomModel(cts.getModelData("STEEL_INGOT"));
                cts.setTexture(item, "AV_STEEL_INGOT");
            }

            new SlimefunItem(Categories.ALTAR_RECIPES, item, RecipeTypes.DIVINE_ALTAR_TYPE, new ItemStack[] {
                    null, new ItemStack(Material.IRON_BLOCK), null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.CARBON, null
            }, new SlimefunItemStack(item, 8)).register(p);
        }

        if (damascusTransmutationEnabled) {
            DivineAltar.RECIPES.put(new MultiInput(new ItemStack[] {
                    null, new ItemStack(Material.IRON_BLOCK), null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.COMPRESSED_CARBON, null
            }), new SlimefunItemStack(SlimefunItems.DAMASCUS_STEEL_INGOT, 8));

            item = new SlimefunItemStack("AV_DAMASCUS_STEEL_INGOT", Material.IRON_INGOT, "&bDamascus Steel Ingot");

            if (useSlimefunItemCustomModelData) {
                item.setCustomModel(cts.getModelData("DAMASCUS_STEEL_INGOT"));
                cts.setTexture(item, "AV_DAMASCUS_STEEL_INGOT");
            }

            new SlimefunItem(Categories.ALTAR_RECIPES, item, RecipeTypes.DIVINE_ALTAR_TYPE, new ItemStack[] {
                    null, new ItemStack(Material.IRON_BLOCK), null,
                    Items.DARKSTEEL, Items.MYSTERY_METAL, Items.ILLUMIUM,
                    null, SlimefunItems.COMPRESSED_CARBON, null
            }, new SlimefunItemStack(item, 8)).register(p);
        }

        if (compressedCarbonTransmutationEnabled) {
            DivineAltar.RECIPES.put(new MultiInput(new ItemStack[] {
                    new ItemStack(Material.COAL), new ItemStack(Material.COOKED_BEEF), new ItemStack(Material.COAL),
                    new ItemStack(Material.OAK_LEAVES), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.KELP),
                    new ItemStack(Material.COAL), new ItemStack(Material.ROTTEN_FLESH), new ItemStack(Material.COAL)
            }), SlimefunItems.COMPRESSED_CARBON);

            item = new SlimefunItemStack("AV_COMPRESSED_CARBON", HeadTexture.COMPRESSED_CARBON, "&cCompressed Carbon");

            if (useSlimefunItemCustomModelData) {
                item.setCustomModel(cts.getModelData("COMPRESSED_CARBON"));
                cts.setTexture(item, "AV_COMPRESSED_CARBON");
            }

            new SlimefunItem(Categories.ALTAR_RECIPES, item, RecipeTypes.DIVINE_ALTAR_TYPE, new ItemStack[] {
                    new ItemStack(Material.COAL), new ItemStack(Material.COOKED_BEEF), new ItemStack(Material.COAL),
                    new ItemStack(Material.OAK_LEAVES), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.KELP),
                    new ItemStack(Material.COAL), new ItemStack(Material.ROTTEN_FLESH), new ItemStack(Material.COAL)
            }, item).register(p);
        }

        // Add normal recipes to recipe map
        DivineAltar.RECIPES.put(new MultiInput(new ItemStack[] {
                Items.EXP_CRYSTAL, Items.ILLUMIUM, Items.EXP_CRYSTAL,
                Items.DARKSTEEL, new ItemStack(Material.LAVA_BUCKET), Items.DARKSTEEL,
                Items.EXP_CRYSTAL, Items.ILLUMIUM, Items.EXP_CRYSTAL
        }), Items.MOLTEN_MYSTERY_METAL);

        DivineAltar.RECIPES.put(new MultiInput(new ItemStack[] {
                Items.EXP_CRYSTAL, SlimefunItems.AUTO_BREWER, Items.EXP_CRYSTAL,
                Items.DARKSTEEL, Items.DIVINE_ALTAR, Items.ILLUMIUM,
                SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.FLUID_PUMP, SlimefunItems.BLISTERING_INGOT_3
        }), Items.ORNATE_CAULDRON);

        DivineAltar.RECIPES.put(new MultiInput(new ItemStack[]{
                Items.EXP_CRYSTAL, SlimefunItems.WITHER_PROOF_GLASS, Items.EXP_CRYSTAL,
                SlimefunItems.REINFORCED_PLATE, new ItemStack(Material.BEACON), SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.BLISTERING_INGOT_3, Items.DIVINE_ALTAR, SlimefunItems.BLISTERING_INGOT_3
        }), Items.ALTAR_OF_INFUSION);
    }

    private static void setupOrnateCauldron() {
        // Add recipes to recipe map
        OrnateCauldron.RECIPES.put(new MultiInput(new ItemStack[] {
                Items.EXP_CRYSTAL, new ItemStack(Material.NETHERITE_BLOCK), Items.EXP_CRYSTAL,
                Items.EVIL_ESSENCE, new ItemStack(Material.DRAGON_BREATH), Items.GOOD_ESSENCE,
                Items.DARKSTEEL, new ItemStack(Material.LAVA_BUCKET), Items.ILLUMIUM
        }), Items.POTION_OF_OSMOSIS);

        OrnateCauldron.RECIPES.put(new MultiInput(new ItemStack[] {
                Items.EXP_CRYSTAL, new ItemStack(Material.LILAC), new ItemStack(Material.CORNFLOWER),
                Items.GOOD_ESSENCE, new ItemStack(Material.HONEY_BOTTLE), new ItemStack(Material.TOTEM_OF_UNDYING),
                Items.ILLUMIUM, new ItemStack(Material.LILY_OF_THE_VALLEY), new ItemStack(Material.POPPY)
        }), Items.BENEVOLENT_BREW);
    }

    private static void setupInfusionAltar(AlchimiaVitae p) {
        // Get config values
        boolean destructiveCritsEnabled = p.getConfig().getBoolean("options.infusions.infusion-destructivecrits");
        boolean phantomCritsEnabled = p.getConfig().getBoolean("options.infusions.infusion-phantomcrits");
        boolean trueAimEnabled = p.getConfig().getBoolean("options.infusions.infusion-trueaim");
        boolean forcefulEnabled = p.getConfig().getBoolean("options.infusions.infusion-forceful");
        boolean volatileEnabled = p.getConfig().getBoolean("options.infusions.infusion-volatile");
        boolean healingEnabled = p.getConfig().getBoolean("options.infusions.infusion-healing");
        boolean autoReplantEnabled = p.getConfig().getBoolean("options.infusions.infusion-autoreplant");
        boolean totemStorageEnabled = p.getConfig().getBoolean("options.infusions.infusion-totemstorage");

        // Get recipes from config
        String[] strDestructiveCritsRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);
        String[] strPhantomCritsRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);
        String[] strTrueAimRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);
        String[] strForcefulRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);
        String[] strVolatileRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);
        String[] strHealingRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);
        String[] strAutoReplantRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);
        String[] strTotemStorageRecipe = p.getConfig().getStringList("options.infusions.destructivecrits-recipe").toArray(new String[0]);

        // Convert recipes into ItemStack arrays
        ItemStack[] itemDestructiveCritsRecipe = new ItemStack[8];
        ItemStack[] itemPhantomCritsRecipe = new ItemStack[8];
        ItemStack[] itemTrueAimRecipe = new ItemStack[8];
        ItemStack[] itemForcefulRecipe = new ItemStack[8];
        ItemStack[] itemVolatileRecipe = new ItemStack[8];
        ItemStack[] itemHealingRecipe = new ItemStack[8];
        ItemStack[] itemAutoReplantRecipe = new ItemStack[8];
        ItemStack[] itemTotemStorageRecipe = new ItemStack[8];

        assignInfusionRecipes(strDestructiveCritsRecipe, itemDestructiveCritsRecipe, p);
        assignInfusionRecipes(strPhantomCritsRecipe, itemPhantomCritsRecipe, p);
        assignInfusionRecipes(strTrueAimRecipe, itemTrueAimRecipe, p);
        assignInfusionRecipes(strForcefulRecipe, itemForcefulRecipe, p);
        assignInfusionRecipes(strVolatileRecipe, itemVolatileRecipe, p);
        assignInfusionRecipes(strHealingRecipe, itemHealingRecipe, p);
        assignInfusionRecipes(strAutoReplantRecipe, itemAutoReplantRecipe, p);
        assignInfusionRecipes(strTotemStorageRecipe, itemTotemStorageRecipe, p);

        // ItemStacks
        CustomItem validInfuseAxe = new CustomItem(Material.DIAMOND_AXE, "&b&lA valid axe to infuse", "&aa &6gold&a, &firon&a, &bdiamond&a,", "&aor &cnetherite &aaxe will do");
        CustomItem validInfuseChestplate = new CustomItem(Material.DIAMOND_CHESTPLATE, "&b&lA valid chestplate to infuse", "&aa &6gold&a, &firon&a, &bdiamond&a,", "&aor &cnetherite &achestplate will do");
        CustomItem validInfuseBow = new CustomItem(Material.BOW, "&b&lA valid bow to infuse", "&aA bow or crossbow will do");
        CustomItem validInfuseHoe = new CustomItem(Material.DIAMOND_HOE, "&b&lA valid hoe to infuse", "&aa &6gold&a, &firon&a, &bdiamond&a,", "&aor &cnetherite &ahoe will do");
        SlimefunItemStack item;

        // Register Infusions
        if (destructiveCritsEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemDestructiveCritsRecipe), axeInfusionDestructiveCrits);

            item = new SlimefunItemStack("AV_DESTRUCTIVE_CRITS_INFUSION", Material.TNT, "&c&lDestructive Criticals",
                    "&41/20 chance to give opponent Mining Fatigue III for 8 seconds on crit",
                    "&41/5 chance to give opponent Slowness I for 15 seconds on crit",
                    "&41/5 chance to give opponent Weakness I for 15 seconds on crit",
                    "&4Deals 0-5 extra damage to opponent's armor on crit");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemDestructiveCritsRecipe[0], itemDestructiveCritsRecipe[1], itemDestructiveCritsRecipe[2],
                    itemDestructiveCritsRecipe[3], validInfuseAxe, itemDestructiveCritsRecipe[4],
                    itemDestructiveCritsRecipe[5], itemDestructiveCritsRecipe[6], itemDestructiveCritsRecipe[7]
            }, item).register(p);
        }

        if (phantomCritsEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemPhantomCritsRecipe), axeInfusionPhantomCrits);

            item = new SlimefunItemStack("AV_PHANTOM_CRITS_INFUSION", Material.PHANTOM_MEMBRANE, "&bPhantom Criticals",
                    "&a1/4 chance to deal (your attack damage to the power of 1.15",
                    "&amultiplied by 5/8) extra damage on a crit, bypassing armor");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemPhantomCritsRecipe[0], itemPhantomCritsRecipe[1], itemPhantomCritsRecipe[2],
                    itemPhantomCritsRecipe[3], validInfuseAxe, itemPhantomCritsRecipe[4],
                    itemPhantomCritsRecipe[5], itemPhantomCritsRecipe[6], itemPhantomCritsRecipe[7]
            }, item).register(p);
        }

        if (trueAimEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemTrueAimRecipe), bowInfusionTrueAim);

            item = new SlimefunItemStack("AV_TRUE_AIM_INFUSION", Material.SHULKER_SHELL, "&dTrue Aim",
                    "&5Partially using the levitation charm", "&5Shulkers use to terminate their victims,",
                    "&5a bow infused with this magic can fire", "&5arrows that are not affected by gravity");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemTrueAimRecipe[0], itemTrueAimRecipe[1], itemTrueAimRecipe[2],
                    itemTrueAimRecipe[3], validInfuseBow, itemTrueAimRecipe[4],
                    itemTrueAimRecipe[5], itemTrueAimRecipe[6], itemTrueAimRecipe[7]
            }, item).register(p);
        }

        if (forcefulEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemForcefulRecipe), bowInfusionForceful);

            item = new SlimefunItemStack("AV_FORCEFUL_INFUSION", Material.PISTON, "&2Forceful",
                    "&aThis infusion uses mechanical", "&adevices and electromagnets to accelerate",
                    "&aprojectiles to blistering speeds", "&aArrows will travel 2x farther and faster");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemForcefulRecipe[0], itemForcefulRecipe[1], itemForcefulRecipe[2],
                    itemForcefulRecipe[3], validInfuseBow, itemForcefulRecipe[4],
                    itemForcefulRecipe[5], itemForcefulRecipe[6], itemForcefulRecipe[7]
            }, item).register(p);
        }

        if (volatileEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemVolatileRecipe), bowInfusionVolatile);

            item = new SlimefunItemStack("AV_VOLATILE_INFUSION", Material.FIRE_CHARGE, "&4&lVolatility",
                    "&cThis extremely dangerous infusion creates", "&cspheres made of pure superheated lava,",
                    "&cdelivering a mini-inferno to the target", "&41/7 chance to fire a large fireball",
                    "&46/7 chance to fire a small fireball");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemVolatileRecipe[0], itemVolatileRecipe[1], itemVolatileRecipe[2],
                    itemVolatileRecipe[3], validInfuseBow, itemVolatileRecipe[4],
                    itemVolatileRecipe[5], itemVolatileRecipe[6], itemVolatileRecipe[7]
            }, item).register(p);
        }

        if (healingEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemHealingRecipe), bowInfusionHealing);

            item = new SlimefunItemStack("AV_HEALING_INFUSION", Material.REDSTONE, "&cHealing",
                    "&cThis infusion will heal hit entities", " &cand recover their &4health", "" +
                    "&aHeals for the same amount that a bow shot would damage");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemHealingRecipe[0], itemHealingRecipe[1], itemHealingRecipe[2],
                    itemHealingRecipe[3], validInfuseBow, itemHealingRecipe[4],
                    itemHealingRecipe[5], itemHealingRecipe[6], itemHealingRecipe[7]
            }, item).register(p);
        }

        if (autoReplantEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemAutoReplantRecipe), hoeInfusionAutoReplant);

            item = new SlimefunItemStack("AV_AUTO_REPLANT_INFUSION", Material.WHEAT, "&aAutomatic Re-plant",
                    "&2Any fully-grown crops broken",
                    "&2with a hoe infused with this", "&2will &aautomatically &2be replanted");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemAutoReplantRecipe[0], itemAutoReplantRecipe[1], itemAutoReplantRecipe[2],
                    itemAutoReplantRecipe[3], validInfuseHoe, itemAutoReplantRecipe[4],
                    itemAutoReplantRecipe[5], itemAutoReplantRecipe[6], itemAutoReplantRecipe[7]
            }, item).register(p);
        }

        if (totemStorageEnabled) {
            AltarOfInfusion.RECIPES.put(new MultiInput(itemTotemStorageRecipe), chestplateInfusionTotemBattery);

            item = new SlimefunItemStack("AV_TOTEM_BATTERY_INFUSION", Material.TOTEM_OF_UNDYING, "&6&lTotem Battery",
                    "&eA built-in pocket dimension that holds the energy", "&eof up to 8 Totems of Undying",
                    "&6Store a Totem in this apparatus", "&6by &e&lShift-Right-Clicking &6with a Totem in the hand",
                    "&6while the infused chestplate is worn");

            new SlimefunItem(Categories.INFUSIONS, item, RecipeTypes.INFUSION_ALTAR_TYPE, new ItemStack[] {
                    itemTotemStorageRecipe[0], itemTotemStorageRecipe[1], itemTotemStorageRecipe[2],
                    itemTotemStorageRecipe[3], validInfuseChestplate, itemTotemStorageRecipe[4],
                    itemTotemStorageRecipe[5], itemTotemStorageRecipe[6], itemTotemStorageRecipe[7]
            }, item).register(p);
        }
    }

    private static void assignInfusionRecipes(String[] ids, ItemStack[] recipe, AlchimiaVitae p) {
        for (int i = 0; i < 8; i++) {
            if (SlimefunItem.getByID(ids[i]) != null) {
                recipe[i] = SlimefunItem.getByID(ids[i]).getItem();
            } else if (Material.getMaterial(ids[i]) != null) {
                recipe[i] = new ItemStack(Material.getMaterial(ids[i]));
            } else {
                p.getLogger().log(Level.WARNING, "Misconfigured item id: \"" + ids[i] + "\" (item " + i + ")");
                recipe[i] = SlimefunItems.DEBUG_FISH;
            }
        }
    }
}
