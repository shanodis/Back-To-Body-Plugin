package me.shanodis.BackToBodyPlugin;

import org.bukkit.Location;
import org.bukkit.Material;

public class DangerChecker {
	
	private interface MaterialChecker {
		boolean check(Material[] materials);
	}
	
	private MaterialChecker checkMaterialArray = materials -> {
		for (Material material : materials)
			if (material.equals(Material.LAVA))
				return false;
		return true;
	};
	
	private void fillMaterialArray(Material[] materials, Location location) {
		materials[0] = location.getBlock().getType();
		materials[1] = location.clone().add(0, 1, 0).getBlock().getType();
		materials[2] = location.clone().add(0, 2, 0).getBlock().getType();
	}
	
	public void run(Location playersDeathLocation) {
		Material[] materials = new Material[3];
		
		fillMaterialArray(materials, playersDeathLocation);
		
		while (!checkMaterialArray.check(materials)) {
			playersDeathLocation.add(-1, 0, 0);
			fillMaterialArray(materials, playersDeathLocation);
		}
	}
}
