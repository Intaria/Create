package com.simibubi.create.foundation.data.recipe;

import java.util.function.UnaryOperator;

import com.google.common.base.Supplier;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.utility.RegisteredObjects;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

public class MechanicalCraftingRecipeGen extends CreateRecipeProvider {

	GeneratedRecipe

	CRUSHING_WHEEL = create(AllBlocks.CRUSHING_WHEEL::get).returns(2)
		.recipe(b -> b.key('P', Ingredient.of(ItemTags.PLANKS))
			.key('S', Ingredient.of(I.stone()))
			.key('A', I.andesite())
			.patternLine(" AAA ")
			.patternLine("AAPAA")
			.patternLine("APSPA")
			.patternLine("AAPAA")
			.patternLine(" AAA ")
			.disallowMirrored()),

		POTATO_CANNON = create(AllItems.POTATO_CANNON::get).returns(1)
			.recipe(b -> b.key('L', I.andesite())
				.key('R', I.precisionMechanism())
				.key('S', AllBlocks.FLUID_PIPE.get())
				.key('C', Ingredient.of(I.copper()))
				.patternLine("LRSSS")
				.patternLine("CC   "))

	;

	public MechanicalCraftingRecipeGen(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
		return new GeneratedRecipeBuilder(result);
	}

	class GeneratedRecipeBuilder {

		private String suffix;
		private Supplier<ItemLike> result;
		private int amount;

		public GeneratedRecipeBuilder(Supplier<ItemLike> result) {
			this.suffix = "";
			this.result = result;
			this.amount = 1;
		}

		GeneratedRecipeBuilder returns(int amount) {
			this.amount = amount;
			return this;
		}

		GeneratedRecipeBuilder withSuffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		GeneratedRecipe recipe(UnaryOperator<MechanicalCraftingRecipeBuilder> builder) {
			return register(consumer -> {
				MechanicalCraftingRecipeBuilder b =
					builder.apply(MechanicalCraftingRecipeBuilder.shapedRecipe(result.get(), amount));
				ResourceLocation location = Create.asResource("mechanical_crafting/" + RegisteredObjects.getKeyOrThrow(result.get()
					.asItem())
					.getPath() + suffix);
				b.build(consumer, location);
			});
		}
	}

	@Override
	public String getName() {
		return "Create's Mechanical Crafting Recipes";
	}

}
