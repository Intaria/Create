package com.simibubi.create.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CEquipment extends ConfigBase {

	public final ConfigInt placementAssistRange = i(12, 3, "placementAssistRange", Comments.placementRange);
	public final ConfigInt toolboxRange = i(10, 1, "toolboxRange", Comments.toolboxRange);
	public final ConfigInt airInBacktank = i(900, 1, "airInBacktank", Comments.maxAirInBacktank);
	public final ConfigInt enchantedBacktankCapacity = i(300, 1, "enchantedBacktankCapacity", Comments.enchantedBacktankCapacity);

	@Override
	public String getName() {
		return "equipment";
	}

	private static class Comments {
		static String maxAirInBacktank =
			"The Maximum volume of Air that can be stored in a backtank = Seconds of underwater breathing";
		static String enchantedBacktankCapacity =
			"The volume of Air added by each level of the backtanks Capacity Enchantment";
		static String placementRange =
			"The Maximum Distance a Block placed by Create's placement assist will have to its interaction point.";
		static String toolboxRange =
			"The Maximum Distance at which a Toolbox can interact with Players' Inventories.";
	}

}
