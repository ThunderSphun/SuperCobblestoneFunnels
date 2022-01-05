package gd.rf.acro.scf;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class SCF implements ModInitializer {
	public static String[] ORES = {};
	public static String[] WEIGHTS = {};

	public static final ItemGroup TAB = FabricItemGroupBuilder.build(
			new Identifier("scf", "tab"),
			() -> new ItemStack(SCF.COBBLESTONE_FUNNEL_BLOCK));

	public static Block COBBLESTONE_FUNNEL_BLOCK;
	public static Block COPPER_FUNNEL_BLOCK;
	public static Block BRONZE_FUNNEL_BLOCK;
	public static Block IRON_FUNNEL_BLOCK;
	public static Block GOLD_FUNNEL_BLOCK;
	public static Block DIAMOND_FUNNEL_BLOCK;
	public static Block NETHERITE_FUNNEL_BLOCK;

	@Override
	public void onInitialize() {
		ConfigUtils.checkConfigs();
		OreManager.makeOreTable();

		COBBLESTONE_FUNNEL_BLOCK = registerFunnel("cobblestone_funnel", "cobblelvl", "cobblespeed");
		COPPER_FUNNEL_BLOCK = registerFunnel("copper_funnel", "copperlvl", "copperspeed");
		BRONZE_FUNNEL_BLOCK = registerFunnel("bronze_funnel", "bronzelvl", "bronzespeed");
		IRON_FUNNEL_BLOCK = registerFunnel("iron_funnel", "ironlvl", "ironspeed");
		GOLD_FUNNEL_BLOCK = registerFunnel("gold_funnel", "goldlvl", "goldspeed");
		DIAMOND_FUNNEL_BLOCK = registerFunnel("diamond_funnel", "diamondlvl", "diamondspeed");
		NETHERITE_FUNNEL_BLOCK = registerFunnel("netherite_funnel", "netheritelvl", "netheritespeed");
	}

	private FunnelBlock registerFunnel(String id, String level, String speed) {
		FunnelBlock block = Registry.register(Registry.BLOCK,
						new Identifier("scf", id),
						new FunnelBlock(
								AbstractBlock.Settings
										.of(Material.STONE)
										.ticksRandomly()
										.strength(1.5f, 0),
								ConfigUtils.config.get(level), ConfigUtils.config.get(speed)));

		Registry.register(Registry.ITEM,
				new Identifier("scf", id),
				new BlockItem(block, new Item.Settings().group(TAB)));

		return block;
	}
}
