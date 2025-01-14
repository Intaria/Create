package com.simibubi.create.compat.jei.category.sequencedAssembly;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedPress;
import com.simibubi.create.compat.jei.category.animations.AnimatedSpout;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import com.simibubi.create.foundation.utility.Lang;

import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;

public abstract class SequencedAssemblySubCategory {

	private final int width;

	public SequencedAssemblySubCategory(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setRecipe(IRecipeLayoutBuilder builder, SequencedRecipe<?> recipe, IFocusGroup focuses, int x) {}

	public abstract void draw(SequencedRecipe<?> recipe, PoseStack ms, double mouseX, double mouseY, int index);

	public static class AssemblyPressing extends SequencedAssemblySubCategory {

		AnimatedPress press;

		public AssemblyPressing() {
			super(25);
			press = new AnimatedPress(false);
		}

		@Override
		public void draw(SequencedRecipe<?> recipe, PoseStack ms, double mouseX, double mouseY, int index) {
			press.offset = index;
			ms.pushPose();
			ms.translate(-5, 50, 0);
			ms.scale(.6f, .6f, .6f);
			press.draw(ms, getWidth() / 2, 0);
			ms.popPose();
		}

	}

	public static class AssemblySpouting extends SequencedAssemblySubCategory {

		AnimatedSpout spout;

		public AssemblySpouting() {
			super(25);
			spout = new AnimatedSpout();
		}

		@Override
		public void setRecipe(IRecipeLayoutBuilder builder, SequencedRecipe<?> recipe, IFocusGroup focuses, int x) {
			FluidIngredient fluidIngredient = recipe.getRecipe()
					.getFluidIngredients()
					.get(0);

			builder
					.addSlot(RecipeIngredientRole.INPUT, x + 4, 15)
					.setBackground(CreateRecipeCategory.getRenderedSlot(), -1, -1)
					.addIngredients(ForgeTypes.FLUID_STACK, CreateRecipeCategory.withImprovedVisibility(fluidIngredient.getMatchingFluidStacks()))
					.addTooltipCallback(CreateRecipeCategory.addFluidTooltip(fluidIngredient.getRequiredAmount()));
		}

		@Override
		public void draw(SequencedRecipe<?> recipe, PoseStack ms, double mouseX, double mouseY, int index) {
			spout.offset = index;
			ms.pushPose();
			ms.translate(-7, 50, 0);
			ms.scale(.75f, .75f, .75f);
			spout.withFluids(recipe.getRecipe()
				.getFluidIngredients()
				.get(0)
				.getMatchingFluidStacks())
				.draw(ms, getWidth() / 2, 0);
			ms.popPose();
		}

	}
}
