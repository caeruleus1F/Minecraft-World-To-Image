# Minecraft-World-To-Image

A Minecraft plug-in that creates a .PNG image of the in-game world. The user specifies the length of one side of the square image, then the plug-in creates a top-down 1 block : 1 pixel image, attempting to match the color of the pixel with the average color of the block. For whatever reason, this plug-in has to be run twice to get a proper image, otherwise the image appears to alternate between vertical lines that are colored or plain white. The amount of time passed since world load before running the plug-in doesn't seem to make any difference.

Example usage:

/im 256

Creates a .PNG image of size 256x256 (or 512x512 :P, it's been six months since I've run this thing) with the center at the origin of the world.
