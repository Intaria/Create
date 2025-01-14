package com.simibubi.create.content.decoration.palettes;

import static com.simibubi.create.Create.REGISTRATE;
import static com.simibubi.create.foundation.data.WindowGen.customWindowBlock;
import static com.simibubi.create.foundation.data.WindowGen.customWindowPane;
import static com.simibubi.create.foundation.data.WindowGen.framedGlass;
import static com.simibubi.create.foundation.data.WindowGen.framedGlassPane;
import static com.simibubi.create.foundation.data.WindowGen.woodenWindowBlock;
import static com.simibubi.create.foundation.data.WindowGen.woodenWindowPane;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.connected.HorizontalCTBehaviour;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.WindowGen;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.Tags;

public class AllPaletteBlocks {

	static {
		REGISTRATE.creativeModeTab(() -> AllCreativeModeTabs.PALETTES_CREATIVE_TAB);
	}

	// Windows and Glass

	public static final BlockEntry<WindowBlock> OAK_WINDOW = woodenWindowBlock(WoodType.OAK, Blocks.OAK_PLANKS),
		SPRUCE_WINDOW = woodenWindowBlock(WoodType.SPRUCE, Blocks.SPRUCE_PLANKS),
		BIRCH_WINDOW = woodenWindowBlock(WoodType.BIRCH, Blocks.BIRCH_PLANKS, () -> RenderType::translucent, true),
		JUNGLE_WINDOW = woodenWindowBlock(WoodType.JUNGLE, Blocks.JUNGLE_PLANKS),
		ACACIA_WINDOW = woodenWindowBlock(WoodType.ACACIA, Blocks.ACACIA_PLANKS),
		DARK_OAK_WINDOW = woodenWindowBlock(WoodType.DARK_OAK, Blocks.DARK_OAK_PLANKS),
		MANGROVE_WINDOW = woodenWindowBlock(WoodType.MANGROVE, Blocks.MANGROVE_PLANKS),
		CRIMSON_WINDOW = woodenWindowBlock(WoodType.CRIMSON, Blocks.CRIMSON_PLANKS),
		WARPED_WINDOW = woodenWindowBlock(WoodType.WARPED, Blocks.WARPED_PLANKS),
		ORNATE_IRON_WINDOW =
			customWindowBlock("ornate_iron_window", () -> Items.IRON_NUGGET, () -> AllSpriteShifts.ORNATE_IRON_WINDOW,
				() -> RenderType::cutout, false, () -> MaterialColor.TERRACOTTA_LIGHT_GRAY);

	public static final BlockEntry<ConnectedGlassPaneBlock> OAK_WINDOW_PANE =
		woodenWindowPane(WoodType.OAK, OAK_WINDOW),
		SPRUCE_WINDOW_PANE = woodenWindowPane(WoodType.SPRUCE, SPRUCE_WINDOW),
		BIRCH_WINDOW_PANE = woodenWindowPane(WoodType.BIRCH, BIRCH_WINDOW, () -> RenderType::translucent),
		JUNGLE_WINDOW_PANE = woodenWindowPane(WoodType.JUNGLE, JUNGLE_WINDOW),
		ACACIA_WINDOW_PANE = woodenWindowPane(WoodType.ACACIA, ACACIA_WINDOW),
		DARK_OAK_WINDOW_PANE = woodenWindowPane(WoodType.DARK_OAK, DARK_OAK_WINDOW),
		MANGROVE_WINDOW_PANE = woodenWindowPane(WoodType.MANGROVE, MANGROVE_WINDOW),
		CRIMSON_WINDOW_PANE = woodenWindowPane(WoodType.CRIMSON, CRIMSON_WINDOW),
		WARPED_WINDOW_PANE = woodenWindowPane(WoodType.WARPED, WARPED_WINDOW),
		ORNATE_IRON_WINDOW_PANE = customWindowPane("ornate_iron_window", ORNATE_IRON_WINDOW,
			() -> AllSpriteShifts.ORNATE_IRON_WINDOW, () -> RenderType::cutoutMipped);

	static {
		AllPaletteStoneTypes.register(REGISTRATE);
	}

	public static void register() {}

}
