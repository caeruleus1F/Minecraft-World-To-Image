package com.gbates31.imagemaker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class CopyOfImageMaker extends BukkitRunnable {

	World world = null;
	int DIMENSION;
	
	public CopyOfImageMaker(World world, int dimension) {
		this.DIMENSION = dimension;
		this.world = world;
	}
	/*
	public void registerBlocks() {
		int color = 0;
		BlockData AIR = new BlockData(Material.AIR, 0);

		color = (43 << 16) | (43 << 8) | 43;
		BlockData BEDROCK = new BlockData(Material.BEDROCK, color);
		
		color = (66 << 16) | (66 << 8) | 66;
		BlockData COBBLESTONE = new BlockData(Material.COBBLESTONE, color);
		
		color = (60 << 16) | (75 << 8) | 60;
		BlockData MOSSY_COBBLESTONE = new BlockData(Material.MOSSY_COBBLESTONE, color);
		
		color = (83 << 16) | (59 << 8) | 40;
		BlockData DIRT = new BlockData(Material.DIRT, color);
		
		color = (77 << 16) | (55 << 8) | 38;
		BlockData COARSE_DIRT = new BlockData(Material.DIRT, color);
		
		color = (78 << 16) | (77 << 8) | 76;
		BlockData GRAVEL = new BlockData(Material.GRAVEL, color);
		
		color = (85 << 16) | (116 << 8) | 162;
		BlockData ICE = new BlockData(Material.ICE, color);
		
		color = (100 << 16) | (118 << 8) | 149;
		BlockData PACKED_ICE = new BlockData(Material.PACKED_ICE, color);
		
		color = (151 << 16) | (154 << 8) | 157;
		BlockData MOB_SPAWNER = new BlockData(Material.MOB_SPAWNER, color);
		
		color = (141 << 16) | (135 << 8) | 103;
		BlockData SAND = new BlockData(Material.SAND, color);
		
		color = (108 << 16) | (56 << 8) | 21;
		BlockData RED_SAND = new BlockData(Material.SAND, color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
		
		color = ( << 16) | ( << 8) | ;
		BlockData  = new BlockData(Material., color);
	}
	*/
	
	public void createImage() {
		/**
		 * Stores the top-most block for each X-Z coordinate of a specified 
		 * range in the Minecraft world into a 2-dimensional array.  
		 */
		Block[][] blocks = new Block[DIMENSION][DIMENSION];
		final int STARTING_X = -DIMENSION / 2;
		final int STARTING_Y = 128;
		final int STARTING_Z = -DIMENSION / 2;
		//final int ENDING_X = DIMENSION / 2;
		final int ENDING_Y = 0;
		//final int ENDING_Z = DIMENSION / 2;
		final Material AIR = Material.AIR;
		
		try {
			for (int z = 0; z < DIMENSION; ++z) {
				for (int x = 0; x < DIMENSION; ++x) {
					for (int y = STARTING_Y; y > ENDING_Y; --y) {
						Block blockAt = world.getBlockAt(x + STARTING_X, y, z + STARTING_Z);
						if (blockAt.getType() == AIR) {
							
						}
						else {
							blocks[x][z] = blockAt;
							y = ENDING_Y;
						}
					}
				}
			} // end of nested for loops
			
			/**
			 * Checks each item in the array for duplicates.
			 * All unique block names get put into a list.
			 * This will be used later to determine the individual
			 * colors used in the bitmap so each block has a unique
			 * color associated with it.
			 */
			List<String> listOfMaterials = new ArrayList<String>();
			
			for (int i = 0; i < DIMENSION; ++i) {
				for (int j = 0; j < DIMENSION; ++j) {
					String materialName = blocks[i][j].getType().toString();
					if (!listOfMaterials.contains(materialName)) {
						listOfMaterials.add(materialName);
					}
				}
			}
			
			/**
			 * Outputting each unique block into the Bukkit console window.
			 */
			for (int i = 0; i < listOfMaterials.size(); ++i) {
				Main.c(listOfMaterials.get(i));
			}
			
			/**
			 * Tallying up each individual block to determine which
			 * blocks are the most frequent. This can allow for a
			 * more optimized if/else structure that orders conditions
			 * based on the likelihood that they will be found true,
			 * leading to higher efficiency.
			 */
			int grass = 0;
			int air = 0;
			int stone = 0;
			int dirt = 0;
			int stationary_water = 0;
			int water_lily = 0;
			int long_grass = 0;
			int double_plant = 0;
			int vine = 0;
			int leaves = 0;
			int snow = 0;
			int red_rose = 0;
			int yellow_flower = 0;
			int sand = 0;
			int gravel = 0;
			int coal_ore = 0;
			int sugar_cane_block = 0;
			int water = 0;
			int ice = 0;
			int pumpkin = 0;
			int stationary_lava = 0;
			int lava = 0;
			int monster_eggs = 0;
			int leaves_2 = 0;
			int sandstone = 0;
			int cactus = 0;
			int dead_bush = 0;
			int iron_ore = 0;
			int wood = 0;
			int web = 0;
			int log_2 = 0;
			int huge_mushroom_1 = 0;
			int huge_mushroom_2 = 0;
			int clay = 0;
			int sandstone_stairs = 0;
			int melon_block = 0;
			int step = 0;
			int cocoa = 0;
			int mossy_cobblestone = 0;
			int cobblestone_stairs = 0;
			int cobblestone = 0;
			int fence = 0;
			int log = 0;
			int spruce_wood_stairs = 0;
			int crops = 0;
			int soil = 0;
			int carrot = 0;
			int potato = 0;
			int wood_stairs = 0;
			int double_step = 0;
			int wool = 0;
			int torch = 0;
			int ladder = 0;
			int hard_clay = 0;
			int stained_clay = 0;
			int red_sandstone = 0;
			int gold_ore = 0;
			int rails = 0;
			int unknown = 0;
			double total = 0;
			
			List BlockList = new ArrayList<BlockData>();
			BufferedImage bi = new BufferedImage(DIMENSION, DIMENSION, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < DIMENSION; ++i) {
				for (int j = 0; j < DIMENSION; ++j) {
					int color = 0;
					final Material currentBlock = blocks[i][j].getType();

					if (currentBlock == Material.GRASS) {
						color = (69 << 16) | (110 << 8) | 51;
						
						++grass;
						++total;
					}
					else if (currentBlock == Material.STATIONARY_WATER) {
						color = ColorDependentDepth(world, blocks[i][j]);
						++stationary_water;
						++total;
					}
					else if (currentBlock == Material.SNOW) {
						color = (240 << 16) | (251 << 8) | 251;
						++snow;
						++total;
					}
					else if (currentBlock == Material.SAND) {
						color = (220 << 16) | (212 << 8) | 161;
						++sand;
						++total;
					}
					else if (currentBlock == Material.LEAVES) {
						color = (39 << 16) | (78 << 8) | 22;
						++leaves;
						++total;
					}
					else if (currentBlock == Material.ICE) {
						color = (162 << 16) | (191 << 8) | 241;
						++ice;
						++total;
					}
					else if (currentBlock == Material.LONG_GRASS) {
						color = (103 << 16) | (117 << 8) | 53;
						++long_grass;
						++total;
					}
					else if (currentBlock == Material.STONE) {
						color = (125 << 16) | (125 << 8) | 125;
						++stone;
						++total;
					}
					else if (currentBlock == Material.DIRT) {
						color = (134 << 16) | (96 << 8) | 66;
						++dirt;
						++total;
					}
					else if (currentBlock == Material.AIR) {
						color = (255 << 16) | (255 << 8) | 255;
						++air;
						++total;
					}
					else if (currentBlock == Material.WATER_LILY) {
						color = (64 << 16) | (105 << 8) | 42;
						++water_lily;
						++total;
					}
					else if (currentBlock == Material.DOUBLE_PLANT) {
						color = (159 << 16) | (147 << 8) | 171;
						++double_plant;
						++total;
					}
					else if (currentBlock == Material.VINE) {
						color = (0 << 16) | (120 << 8) | 8;
						++vine;
						++total;
					}
					else if (currentBlock == Material.RED_ROSE) {
						color = (143 << 16) | (24 << 8) | 7;
						++red_rose;
						++total;
					}
					else if (currentBlock == Material.YELLOW_FLOWER) {
						color = (203 << 16) | (210 << 8) | 1;
						++yellow_flower;
						++total;
					}
					else if (currentBlock == Material.GRAVEL) {
						color = (126 << 16) | (124 << 8) | 122;
						++gravel;
						++total;
					}
					else if (currentBlock == Material.COAL_ORE) {
						color = (26 << 16) | (22 << 8) | 22;
						++coal_ore;
						++total;
					}
					else if (currentBlock == Material.SUGAR_CANE_BLOCK) {
						color = (150 << 16) | (193 << 8) | 103;
						++sugar_cane_block;
						++total;
					}
					else if (currentBlock == Material.WATER) {
						color = ColorDependentDepth(world, blocks[i][j]);
						++water;
						++total;
					}
					else if (currentBlock == Material.PUMPKIN) {
						color = (190 << 16) | (119 << 8) | 21;
						++pumpkin;
						++total;
					}
					else if (currentBlock == Material.STATIONARY_LAVA) {
						color = (208 << 16) | (96 << 8) | 23;
						++stationary_lava;
						++total;
					}
					else if (currentBlock == Material.LAVA) {
						color = (208 << 16) | (96 << 8) | 23;
						++lava;
						++total;
					}
					else if (currentBlock == Material.MONSTER_EGGS) {
						color = (255 << 16) | (82 << 8) | 252;
						++monster_eggs;
						++total;
					}
					else if (currentBlock == Material.LEAVES_2) {
						color = (39 << 16) | (78 << 8) | 22;
						++leaves_2;
						++total;
					}
					else if (currentBlock == Material.SANDSTONE) {
						color = (217 << 16) | (209 << 8) | 157;
						++sandstone;
						++total;
					}
					else if (currentBlock == Material.CACTUS) {
						color = (15 << 16) | (112 << 8) | 27;
						++cactus;
						++total;
					}
					else if (currentBlock == Material.DEAD_BUSH) {
						color = (122 << 16) | (78 << 8) | 24;
						++dead_bush;
						++total;
					}
					else if (currentBlock == Material.IRON_ORE) {
						color = (191 << 16) | (150 << 8) | 94;
						++iron_ore;
						++total;
					}
					else if (currentBlock == Material.WOOD) {
						color = (86 << 16) | (68 << 8) | 26;
						++wood;
						++total;
					}
					else if (currentBlock == Material.WEB) {
						color = (222 << 16) | (222 << 8) | 222;
						++web;
						++total;
					}
					else if (currentBlock == Material.LOG_2) {
						color = (86 << 16) | (68 << 8) | 26;
						++log_2;
						++total;
					}
					else if (currentBlock == Material.HUGE_MUSHROOM_1) {
						color = (141 << 16) | (106 << 8) | 83;
						++huge_mushroom_1;
						++total;
					}
					else if (currentBlock == Material.HUGE_MUSHROOM_2) {
						color = (184 << 16) | (47 << 8) | 45;
						++huge_mushroom_2;
						++total;
					}
					else if (currentBlock == Material.CLAY) {
						color = (159 << 16) | (165 << 8) | 178;
						++clay;
						++total;
					}
					else if (currentBlock == Material.SANDSTONE_STAIRS) {
						color = (214 << 16) | (206 << 8) | 153;
						++sandstone_stairs;
						++total;
					}
					else if (currentBlock == Material.MELON_BLOCK) {
						color = (143 << 16) | (147 << 8) | 35;
						++melon_block;
						++total;
					}
					else if (currentBlock == Material.STEP) {
						color = (67 << 16) | (44 << 8) | 23;
						++step;
						++total;
					}
					else if (currentBlock == Material.COCOA) {
						color = (142 << 16) | (103 << 8) | 73;
						++cocoa;
						++total;
					}
					else if (currentBlock == Material.MOSSY_COBBLESTONE) {
						color = (98 << 16) | (120 << 8) | 98;
						++mossy_cobblestone;
						++total;
					}
					else if (currentBlock == Material.COBBLESTONE_STAIRS) {
						color = (123 << 16) | (123 << 8) | 123;
						++cobblestone_stairs;
						++total;
					}
					else if (currentBlock == Material.COBBLESTONE) {
						color = (123 << 16) | (123 << 8) | 123;
						++cobblestone;
						++total;
					}
					else if (currentBlock == Material.FENCE) {
						color = (151 << 16) | (108 << 8) | 75;
						++fence;
						++total;
					}
					else if (currentBlock == Material.LOG) {
						color = (86 << 16) | (68 << 8) | 26;
						++log;
						++total;
					}
					else if (currentBlock == Material.SPRUCE_WOOD_STAIRS) {
						color = (90 << 16) | (72 << 8) | 48;
						++spruce_wood_stairs;
						++total;
					}
					else if (currentBlock == Material.CROPS) {
						color = (50 << 16) | (128 << 8) | 8;
						++crops;
						++total;
					}
					else if (currentBlock == Material.SOIL) {
						color = (111 << 16) | (73 << 8) | 44;
						++soil;
						++total;
					}
					else if (currentBlock == Material.CARROT) {
						color = (46 << 16) | (109 << 8) | 16;
						++carrot;
						++total;
					}
					else if (currentBlock == Material.POTATO) {
						color = (64 << 16) | (170 << 8) | 73;
						++potato;
						++total;
					}
					else if (currentBlock == Material.WOOD_STAIRS) {
						color = (154 << 16) | (125 << 8) | 77;
						++wood_stairs;
						++total;
					}
					else if (currentBlock == Material.DOUBLE_STEP) {
						color = (167 << 16) | (167 << 8) | 167;
						++double_step;
						++total;
					}
					else if (currentBlock == Material.WOOL) {
						color = (167 << 16) | (167 << 8) | 167;
						++wool;
						++total;
					}
					else if (currentBlock == Material.TORCH) {
						color = (136 << 16) | (111 << 8) | 61;
						++torch;
						++total;
					}
					else if (currentBlock == Material.LADDER) {
						color = (121 << 16) | (95 << 8) | 53;
						++ladder;
						++total;
					}
					else if (currentBlock == Material.HARD_CLAY) {
						color = (77 << 16) | (51 << 8) | 36;
						++hard_clay;
						++total;
					}
					else if (currentBlock == Material.STAINED_CLAY) {
						color = (150 << 16) | (92 << 8) | 66;
						++stained_clay;
						++total;
					}
					else if (currentBlock == Material.RED_SANDSTONE) {
						color = (166 << 16) | (85 << 8) | 30;
						++red_sandstone;
						++total;
					}
					else if (currentBlock == Material.GOLD_ORE) {
						color = (254 << 16) | (246 << 8) | 83;
						++gold_ore;
						++total;
					}
					else if (currentBlock == Material.RAILS) {
						color = (254 << 16) | (246 << 8) | 83;
						++rails;
						++total;
					}
					else {
						++unknown;
						++total;
					}
					
					bi.setRGB(i, j, color);
				}
			} // end of nested for loops

			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(4);
			Main.c("Staitonary water: " + nf.format(100 * stationary_water / total));
			Main.c("snow: " + nf.format(100 * snow / total));
			Main.c("Grass: " + nf.format(100 * grass / total));
			Main.c("sand: " + nf.format(100 * sand / total));
			Main.c("ice: " + nf.format(100 * ice / total));
			Main.c("leaves: " + nf.format(100 * leaves / total));
			Main.c("Dirt: " + nf.format(100 * dirt / total));
			Main.c("Stone: " + nf.format(100 * stone / total));
			Main.c("Air: " + nf.format(100 * air / total));
			Main.c("Water lily: " + nf.format(100 * water_lily / total));
			Main.c("long_grass: " + nf.format(100 * long_grass / total));
			Main.c("double_plant: " + nf.format(100 * double_plant / total));
			Main.c("vine: " + nf.format(100 * vine / total));
			Main.c("red_rose: " + nf.format(100 * red_rose / total));
			Main.c("yellow_flower: " + nf.format(100 * yellow_flower / total));
			Main.c("gravel: " + nf.format(100 * gravel / total));
			Main.c("coal_ore: " + nf.format(100 * coal_ore / total));
			Main.c("sugar_cane_block: " + nf.format(100 * sugar_cane_block / total));
			Main.c("water: " + nf.format(100 * water / total));
			Main.c("pumpkin: " + nf.format(100 * pumpkin / total));
			Main.c("stationary_lava: " + nf.format(100 * stationary_lava / total));
			Main.c("lava: " + nf.format(100 * lava / total));
			Main.c("monster_eggs: " + nf.format(100 * monster_eggs / total));
			Main.c("leaves_2: " + nf.format(100 * leaves_2 / total));
			Main.c("sandstone: " + nf.format(100 * sandstone / total));
			Main.c("cactus: " + nf.format(100 * cactus / total));
			Main.c("dead_bush: " + nf.format(100 * dead_bush / total));
			Main.c("iron_ore: " + nf.format(100 * iron_ore / total));
			Main.c("wood: " + nf.format(100 * wood / total));
			Main.c("web: " + nf.format(100 * web / total));
			Main.c("log_2: " + nf.format(100 * log_2 / total));
			Main.c("huge_mushroom_1: " + nf.format(100 * huge_mushroom_1 / total));
			Main.c("huge_mushroom_2: " + nf.format(100 * huge_mushroom_2 / total));
			Main.c("clay: " + nf.format(100 * clay / total));
			Main.c("sandstone_stairs: " + nf.format(100 * sandstone_stairs / total));
			Main.c("melon_block: " + nf.format(100 * melon_block / total));
			Main.c("step: " + nf.format(100 * step / total));
			Main.c("cocoa: " + nf.format(100 * cocoa / total));
			Main.c("mossy_cobblestone: " + nf.format(100 * mossy_cobblestone / total));
			Main.c("cobblestone_stairs: " + nf.format(100 * cobblestone_stairs / total));
			Main.c("cobblestone: " + nf.format(100 * cobblestone / total));
			Main.c("fence: " + nf.format(100 * fence / total));
			Main.c("log: " + nf.format(100 * log / total));
			Main.c("spruce_wood_stairs: " + nf.format(100 * spruce_wood_stairs / total));
			Main.c("crops: " + nf.format(100 * crops / total));
			Main.c("soil: " + nf.format(100 * soil / total));
			Main.c("carrot: " + nf.format(100 * carrot / total));
			Main.c("potato: " + nf.format(100 * potato / total));
			Main.c("wood_stairs: " + nf.format(100 * wood_stairs / total));
			Main.c("double_step: " + nf.format(100 * double_step / total));
			Main.c("wool: " + nf.format(100 * wool / total));
			Main.c("torch: " + nf.format(100 * torch / total));
			Main.c("ladder: " + nf.format(100 * ladder / total));
			Main.c("hard_clay: " + nf.format(100 * hard_clay / total));
			Main.c("stained_clay: " + nf.format(100 * stained_clay / total));
			Main.c("red_sandstone: " + nf.format(100 * red_sandstone / total));
			Main.c("gold_ore: " + nf.format(100 * gold_ore / total));
			Main.c("rails: " + nf.format(100 * rails / total));
			Main.c("unknown: " + nf.format(100 * unknown / total));
			
			File file = new File("img.png");
			ImageIO.write(bi, "PNG", file);
			Main.c("Complete");
		} catch (Exception ex) {
			Main.c(ex.toString());
		}
	} // end of createImage method
	private int ColorDependentDepth(World world, Block waterBlock) {
		int color = 0;
		final int height = waterBlock.getY();
		double heightOfNonWaterBlock = 0;
		int red = 0;
		int green = 0;
		int blue = 0;
		final int x = waterBlock.getX();
		final int z = waterBlock.getZ();
		
		for (int y = height - 1; y > 0; --y) {
			Block temp = world.getBlockAt(x, y, z);
			if (temp.getType() != Material.STATIONARY_WATER && temp.getType() != Material.WATER) {
				heightOfNonWaterBlock = y;
				break;
			}
		}
		if (heightOfNonWaterBlock > 63) {
			color = (48 << 16) | (71 << 8) | 244;
		}
		else if (heightOfNonWaterBlock > 31) {
			red = (int) (24 * Math.sin((2 * heightOfNonWaterBlock * Math.PI / 63) + (Math.PI / 2)) + 24);
			green = (int) (28 * Math.sin((2 * heightOfNonWaterBlock * Math.PI / 63) + (Math.PI / 2)) + 42);
			blue = (int) (60 * Math.sin((2 * heightOfNonWaterBlock * Math.PI / 63) + (Math.PI / 2)) + 184);
			color = ( red << 16) | (green << 8) | blue;
		}
		else {
			color = (10 << 16) | (14 << 8) | 124;
		}
		
		return color;
	}

	public void run() {
		createImage();
	}
}
