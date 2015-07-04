package com.gbates31.imagemaker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class ImageMaker {

	World world = null;
	int DIMENSION;
	
	public ImageMaker(World world, int dimension) {
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
			
			BufferedImage bi = new BufferedImage(DIMENSION, DIMENSION, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < DIMENSION; ++i) {
				for (int j = 0; j < DIMENSION; ++j) {
					int color = 0;
					final Material currentBlock = blocks[i][j].getType();

					if (currentBlock == Material.GRASS) {
						color = (69 << 16) | (110 << 8) | 51;
					}
					else if (currentBlock == Material.STATIONARY_WATER) {
						color = ColorDependentDepth(world, blocks[i][j]);
					}
					else if (currentBlock == Material.SNOW) {
						color = (240 << 16) | (251 << 8) | 251;
					}
					else if (currentBlock == Material.SAND) {
						color = (220 << 16) | (212 << 8) | 161;
					}
					else if (currentBlock == Material.LEAVES) {
						color = (39 << 16) | (78 << 8) | 22;
					}
					else if (currentBlock == Material.ICE) {
						color = (162 << 16) | (191 << 8) | 241;
					}
					else if (currentBlock == Material.LONG_GRASS) {
						color = (103 << 16) | (117 << 8) | 53;
					}
					else if (currentBlock == Material.STONE) {
						color = (125 << 16) | (125 << 8) | 125;
					}
					else if (currentBlock == Material.DIRT) {
						color = (134 << 16) | (96 << 8) | 66;
					}
					else if (currentBlock == Material.PACKED_ICE) {
						color = (161 << 16) | (190 << 8) | 240;
					}
					else if (currentBlock == Material.AIR) {
						color = (255 << 16) | (255 << 8) | 255;
					}
					else if (currentBlock == Material.WATER_LILY) {
						color = (64 << 16) | (105 << 8) | 42;
					}
					else if (currentBlock == Material.DOUBLE_PLANT) {
						color = (159 << 16) | (147 << 8) | 171;
					}
					else if (currentBlock == Material.VINE) {
						color = (0 << 16) | (120 << 8) | 8;
					}
					else if (currentBlock == Material.RED_ROSE) {
						color = (143 << 16) | (24 << 8) | 7;
					}
					else if (currentBlock == Material.YELLOW_FLOWER) {
						color = (203 << 16) | (210 << 8) | 1;
					}
					else if (currentBlock == Material.GRAVEL) {
						color = (126 << 16) | (124 << 8) | 122;
					}
					else if (currentBlock == Material.COAL_ORE) {
						color = (26 << 16) | (22 << 8) | 22;
					}
					else if (currentBlock == Material.SUGAR_CANE_BLOCK) {
						color = (150 << 16) | (193 << 8) | 103;
					}
					else if (currentBlock == Material.WATER) {
						color = ColorDependentDepth(world, blocks[i][j]);
					}
					else if (currentBlock == Material.PUMPKIN) {
						color = (190 << 16) | (119 << 8) | 21;
					}
					else if (currentBlock == Material.STATIONARY_LAVA) {
						color = (208 << 16) | (96 << 8) | 23;
					}
					else if (currentBlock == Material.LAVA) {
						color = (208 << 16) | (96 << 8) | 23;
					}
					else if (currentBlock == Material.MONSTER_EGGS) {
						color = (255 << 16) | (82 << 8) | 252;
					}
					else if (currentBlock == Material.LEAVES_2) {
						color = (39 << 16) | (78 << 8) | 22;
					}
					else if (currentBlock == Material.SANDSTONE) {
						color = (217 << 16) | (209 << 8) | 157;
					}
					else if (currentBlock == Material.CACTUS) {
						color = (15 << 16) | (112 << 8) | 27;
					}
					else if (currentBlock == Material.DEAD_BUSH) {
						color = (122 << 16) | (78 << 8) | 24;
					}
					else if (currentBlock == Material.IRON_ORE) {
						color = (191 << 16) | (150 << 8) | 94;
					}
					else if (currentBlock == Material.WOOD) {
						color = (86 << 16) | (68 << 8) | 26;
					}
					else if (currentBlock == Material.WEB) {
						color = (222 << 16) | (222 << 8) | 222;
					}
					else if (currentBlock == Material.LOG_2) {
						color = (86 << 16) | (68 << 8) | 26;
					}
					else if (currentBlock == Material.HUGE_MUSHROOM_1) {
						color = (141 << 16) | (106 << 8) | 83;
					}
					else if (currentBlock == Material.HUGE_MUSHROOM_2) {
						color = (184 << 16) | (47 << 8) | 45;
					}
					else if (currentBlock == Material.CLAY) {
						color = (159 << 16) | (165 << 8) | 178;
					}
					else if (currentBlock == Material.SANDSTONE_STAIRS) {
						color = (214 << 16) | (206 << 8) | 153;
					}
					else if (currentBlock == Material.MELON_BLOCK) {
						color = (143 << 16) | (147 << 8) | 35;
					}
					else if (currentBlock == Material.STEP) {
						color = (67 << 16) | (44 << 8) | 23;
					}
					else if (currentBlock == Material.COCOA) {
						color = (142 << 16) | (103 << 8) | 73;
					}
					else if (currentBlock == Material.MOSSY_COBBLESTONE) {
						color = (98 << 16) | (120 << 8) | 98;
					}
					else if (currentBlock == Material.COBBLESTONE_STAIRS) {
						color = (123 << 16) | (123 << 8) | 123;
					}
					else if (currentBlock == Material.COBBLESTONE) {
						color = (123 << 16) | (123 << 8) | 123;
					}
					else if (currentBlock == Material.FENCE) {
						color = (151 << 16) | (108 << 8) | 75;
					}
					else if (currentBlock == Material.LOG) {
						color = (86 << 16) | (68 << 8) | 26;
					}
					else if (currentBlock == Material.SPRUCE_WOOD_STAIRS) {
						color = (90 << 16) | (72 << 8) | 48;
					}
					else if (currentBlock == Material.CROPS) {
						color = (50 << 16) | (128 << 8) | 8;
					}
					else if (currentBlock == Material.SOIL) {
						color = (111 << 16) | (73 << 8) | 44;
					}
					else if (currentBlock == Material.CARROT) {
						color = (46 << 16) | (109 << 8) | 16;
					}
					else if (currentBlock == Material.POTATO) {
						color = (64 << 16) | (170 << 8) | 73;
					}
					else if (currentBlock == Material.WOOD_STAIRS) {
						color = (154 << 16) | (125 << 8) | 77;
					}
					else if (currentBlock == Material.DOUBLE_STEP) {
						color = (167 << 16) | (167 << 8) | 167;
					}
					else if (currentBlock == Material.WOOL) {
						color = (167 << 16) | (167 << 8) | 167;
					}
					else if (currentBlock == Material.TORCH) {
						color = (136 << 16) | (111 << 8) | 61;
					}
					else if (currentBlock == Material.LADDER) {
						color = (121 << 16) | (95 << 8) | 53;
					}
					else if (currentBlock == Material.HARD_CLAY) {
						color = (77 << 16) | (51 << 8) | 36;
					}
					else if (currentBlock == Material.STAINED_CLAY) {
						color = (150 << 16) | (92 << 8) | 66;
					}
					else if (currentBlock == Material.RED_SANDSTONE) {
						color = (166 << 16) | (85 << 8) | 30;
					}
					else if (currentBlock == Material.GOLD_ORE) {
						color = (254 << 16) | (246 << 8) | 83;
					}
					else if (currentBlock == Material.RAILS) {
						color = (254 << 16) | (246 << 8) | 83;
					}
					
					bi.setRGB(i, j, color);
				}
			} // end of nested for loops
			
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
}
