package com.gbates31.imagemaker;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class BlockData extends MaterialData {

	int RGB = 0;
	int count = 0;
	
	public BlockData(Material type, int color) {
		super(type);
	}
	
	public int getRGB() {
		return RGB;
	}
	
	public int getCount() {
		return count;
	}
	
	public void increment() {
		++count;
	}

}
