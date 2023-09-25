package com.simibubi.create.infrastructure.ponder.scenes;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsBlockEntity;
import com.simibubi.create.content.contraptions.actors.psi.PortableItemInterfaceBlockEntity;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlockEntity;
import com.simibubi.create.content.contraptions.chassis.LinearChassisBlock;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.PonderPalette;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.element.ParrotElement;
import com.simibubi.create.foundation.ponder.element.ParrotElement.FlappyPose;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;
import com.simibubi.create.foundation.utility.Pointing;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class MovementActorScenes {

	public static void psiTransfer(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("portable_storage_interface", "Contraption Storage Exchange");
		scene.configureBasePlate(0, 0, 6);
		scene.scaleSceneView(0.95f);
		scene.setSceneOffsetY(-1);
		scene.world.showSection(util.select.layer(0), Direction.UP);
		scene.idle(5);

		BlockPos bearing = util.grid.at(5, 1, 2);
		scene.world.showSection(util.select.position(bearing), Direction.DOWN);
		scene.idle(5);
		ElementLink<WorldSectionElement> contraption =
			scene.world.showIndependentSection(util.select.fromTo(5, 2, 2, 6, 3, 2), Direction.DOWN);
		scene.world.configureCenterOfRotation(contraption, util.vector.centerOf(bearing));
		scene.idle(10);
		scene.world.rotateBearing(bearing, 360, 70);
		scene.world.rotateSection(contraption, 0, 360, 0, 70);
		scene.overlay.showText(60)
			.pointAt(util.vector.topOf(bearing.above(2)))
			.colored(PonderPalette.RED)
			.placeNearTarget()
			.attachKeyFrame()
			.text("Moving inventories can be tricky to access with automation.");

		scene.idle(70);
		BlockPos psi = util.grid.at(4, 2, 2);
		scene.world.showSectionAndMerge(util.select.position(psi), Direction.EAST, contraption);
		scene.idle(13);
		scene.effects.superGlue(psi, Direction.EAST, true);

		scene.overlay.showText(80)
			.pointAt(util.vector.topOf(psi))
			.colored(PonderPalette.GREEN)
			.placeNearTarget()
			.attachKeyFrame()
			.text("This component can interact with storage without the need to stop the contraption.");
		scene.idle(90);

		BlockPos psi2 = psi.west(2);
		scene.world.showSection(util.select.position(psi2), Direction.DOWN);
		scene.overlay.showSelectionWithText(util.select.position(psi.west()), 50)
			.colored(PonderPalette.RED)
			.placeNearTarget()
			.attachKeyFrame()
			.text("Place a second one with a gap of 1 or 2 blocks inbetween");
		scene.idle(55);

		scene.world.rotateBearing(bearing, 360, 60);
		scene.world.rotateSection(contraption, 0, 360, 0, 60);
		scene.idle(20);

		scene.overlay.showText(40)
			.placeNearTarget()
			.pointAt(util.vector.of(3, 3, 2.5))
			.text("Whenever they pass by each other, they will engage in a connection");
		scene.idle(38);

		Selection both = util.select.fromTo(2, 2, 2, 4, 2, 2);
		Class<PortableItemInterfaceBlockEntity> psiClass = PortableItemInterfaceBlockEntity.class;

		scene.world.modifyBlockEntityNBT(both, psiClass, nbt -> {
			nbt.putFloat("Distance", 1);
			nbt.putFloat("Timer", 12);
		});

		scene.idle(17);
		scene.overlay.showOutline(PonderPalette.GREEN, psi, util.select.fromTo(5, 3, 2, 6, 3, 2), 80);
		scene.idle(10);

		scene.overlay.showSelectionWithText(util.select.position(psi2), 70)
			.placeNearTarget()
			.colored(PonderPalette.GREEN)
			.attachKeyFrame()
			.text("While engaged, the stationary interface will represent ALL inventories on the contraption");

		scene.idle(80);

		BlockPos hopper = util.grid.at(2, 3, 2);
		scene.world.showSection(util.select.position(hopper), Direction.DOWN);
		scene.overlay.showText(70)
			.placeNearTarget()
			.pointAt(util.vector.topOf(hopper))
			.attachKeyFrame()
			.text("Items can now be inserted...");

		ItemStack itemStack = new ItemStack(Items.COPPER_INGOT);
		Vec3 entitySpawn = util.vector.topOf(hopper.above(3));

		ElementLink<EntityElement> entity1 =
			scene.world.createItemEntity(entitySpawn, util.vector.of(0, 0.2, 0), itemStack);
		scene.idle(10);
		ElementLink<EntityElement> entity2 =
			scene.world.createItemEntity(entitySpawn, util.vector.of(0, 0.2, 0), itemStack);
		scene.idle(10);
		scene.world.modifyEntity(entity1, Entity::discard);
		scene.idle(10);
		scene.world.modifyEntity(entity2, Entity::discard);

		scene.overlay
			.showControls(new InputWindowElement(util.vector.topOf(5, 3, 2), Pointing.DOWN).withItem(itemStack), 40);

		scene.idle(30);
		scene.world.hideSection(util.select.position(hopper), Direction.UP);
		scene.idle(15);

		BlockPos beltPos = util.grid.at(1, 1, 2);
		scene.world.showSection(util.select.fromTo(0, 1, 0, 1, 2, 6), Direction.DOWN);
		scene.idle(10);
		scene.world.createItemOnBelt(beltPos, Direction.EAST, itemStack.copy());
		scene.overlay.showText(40)
			.placeNearTarget()
			.pointAt(util.vector.topOf(beltPos.above()))
			.text("...or extracted from the contraption");
		scene.idle(15);
		scene.world.createItemOnBelt(beltPos, Direction.EAST, itemStack);

		scene.idle(20);
		scene.world.modifyEntities(ItemEntity.class, Entity::discard);
		scene.idle(15);
		scene.world.modifyEntities(ItemEntity.class, Entity::discard);

		scene.overlay.showText(120)
			.placeNearTarget()
			.pointAt(util.vector.topOf(psi2))
			.text("After no items have been exchanged for a while, the contraption will continue on its way");
		scene.world.modifyBlockEntityNBT(both, psiClass, nbt -> nbt.putFloat("Timer", 2));

		scene.idle(15);
		scene.markAsFinished();
		scene.world.rotateBearing(bearing, 270, 120);
		scene.world.rotateSection(contraption, 0, 270, 0, 120);
	}

	public static void psiRedstone(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("portable_storage_interface_redstone", "Redstone Control");
		scene.configureBasePlate(0, 0, 5);
		scene.setSceneOffsetY(-1);

		Class<PortableStorageInterfaceBlockEntity> psiClass = PortableStorageInterfaceBlockEntity.class;
		Selection psis = util.select.fromTo(1, 1, 3, 1, 3, 3);
		scene.world.modifyBlockEntityNBT(psis, psiClass, nbt -> {
			nbt.putFloat("Distance", 1);
			nbt.putFloat("Timer", 12);
		});

		scene.world.showSection(util.select.layer(0), Direction.UP);
		scene.idle(5);
		scene.world.showSection(util.select.layer(1), Direction.DOWN);
		scene.idle(5);

		ElementLink<WorldSectionElement> contraption =
			scene.world.showIndependentSection(util.select.layersFrom(2), Direction.DOWN);
		BlockPos bearing = util.grid.at(3, 1, 3);
		scene.world.configureCenterOfRotation(contraption, util.vector.topOf(bearing));
		scene.idle(20);
		scene.world.modifyBlockEntityNBT(psis, psiClass, nbt -> nbt.putFloat("Timer", 2));
		scene.world.rotateBearing(bearing, 360 * 3 + 270, 240 + 60);
		scene.world.rotateSection(contraption, 0, 360 * 3 + 270, 0, 240 + 60);
		scene.idle(20);

		scene.world.toggleRedstonePower(util.select.fromTo(1, 1, 1, 1, 1, 2));
		scene.effects.indicateRedstone(util.grid.at(1, 1, 1));

		scene.idle(10);

		scene.overlay.showSelectionWithText(util.select.position(1, 1, 3), 120)
			.colored(PonderPalette.RED)
			.text("Redstone power will prevent the stationary interface from engaging");

		scene.idle(20);
		scene.markAsFinished();
	}
}
