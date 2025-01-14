package com.simibubi.create.foundation.data.recipe;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;

public class EmptyingRecipeGen extends ProcessingRecipeGen {

	/*
	 * potion/water bottles are handled internally
	 */

	GeneratedRecipe

	HONEY_BOTTLE = create("honey_bottle", b -> b.require(Items.HONEY_BOTTLE)
		.output(AllFluids.HONEY.get(), 250)
		.output(Items.GLASS_BOTTLE)),

		FD_MILK = create(Mods.FD.recipeId("milk_bottle"), b -> b.require(Mods.FD, "milk_bottle")
			.output(ForgeMod.MILK.get(), 250)
			.output(Items.GLASS_BOTTLE)
			.whenModLoaded(Mods.FD.getId()))

	;

	public EmptyingRecipeGen(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.EMPTYING;
	}

}
