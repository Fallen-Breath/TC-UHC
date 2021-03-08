package cn.topologycraft.uhc.util;

import cn.topologycraft.uhc.GameManager;
import cn.topologycraft.uhc.task.TaskSpawnPlatformProtect;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class SpawnPlatform {
	public static int height = 160;
	private static BlockPos[] hexagonPos;

	public static void generatePlatform(GameManager gameManager, World world) {
		generateHexagon(world, hexagonPos[0], EnumDyeColor.BLUE);
		generateHexagon(world, hexagonPos[1], EnumDyeColor.RED);
		generateHexagon(world, hexagonPos[2], EnumDyeColor.BLUE);
		generateHexagon(world, hexagonPos[3], EnumDyeColor.BLUE);
		generateHexagon(world, hexagonPos[4], EnumDyeColor.BLUE);
		generateHexagon(world, hexagonPos[5], EnumDyeColor.RED);
		generateHexagon(world, hexagonPos[6], EnumDyeColor.RED);
		gameManager.addTask(new TaskSpawnPlatformProtect(gameManager));
	}

	private static void generateHexagon(World world, BlockPos pos, EnumDyeColor color) {
		for (int x = pos.getX() - 5; x <= pos.getX() + 6; x++)
			for (int z = pos.getZ() - 6; z <= pos.getZ() + 6; z++) {
				int dx = Math.min(x - pos.getX() + 5, pos.getX() + 6 - x);
				int dz = Math.min(z - pos.getZ() + 6, pos.getZ() + 6 - z);
				if (dx / 2.0f + dz < 2.1f)
					continue;
				if (Math.abs(x - pos.getX()) < 2 && Math.abs(z - pos.getZ()) < 2) {
					world.setBlockState(new BlockPos(x, pos.getY(), z), Blocks.SEA_LANTERN.getDefaultState(), 2);
				} else
					world.setBlockState(new BlockPos(x, pos.getY(), z),
							Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, color), 2);
				world.setBlockState(new BlockPos(x, pos.getY() + 1, z),
						Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, color), 2);
			}
	}

	public static void generateSafePlatform(World world) {
		IBlockState block = Blocks.BARRIER.getDefaultState();
		for (int x = -25; x <= 25; x++)
			for (int z = -25; z <= 25; z++) {
				if (x == -25 || x == 25 || z == -25 || z == 25)
					for (int y = height + 1; y <= height + 4; y++)
						world.setBlockState(new BlockPos(x, y, z), block, 2);
				if (world.isAirBlock(new BlockPos(x, height, z)))
					world.setBlockState(new BlockPos(x, height, z), block, 2);
			}
	}

	public static void destroyPlatform(World world) {
		for (int x = -30; x <= 30; x++)
			for (int z = -30; z <= 30; z++)
				for (int y = height + 5; y >= height; y--)
					world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 2);
	}

	public static BlockPos getRandomSpawnPosition(Random rand) {
		BlockPos res = hexagonPos[rand.nextInt(hexagonPos.length)];
		return res.add(new BlockPos(rand.nextInt(7) - 3, 0, rand.nextInt(7) - 3));
	}

	static {
		hexagonPos = new BlockPos[7];
		hexagonPos[0] = new BlockPos(0, height, 0);
		hexagonPos[1] = new BlockPos(14, height, 0);
		hexagonPos[2] = new BlockPos(-14, height, 0);
		hexagonPos[3] = new BlockPos(7, height, 12);
		hexagonPos[4] = new BlockPos(7, height, -12);
		hexagonPos[5] = new BlockPos(-7, height, 12);
		hexagonPos[6] = new BlockPos(-7, height, -12);
	}
}
