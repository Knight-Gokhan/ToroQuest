package net.torocraft.toroquest.util.generation;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.torocraft.toroquest.entities.EntityMage;
import net.torocraft.toroquest.generation.BastionsLairGenerator;
import net.torocraft.toroquest.generation.MageTowerGenerator;
import net.torocraft.toroquest.generation.ThroneRoomGenerator;

public class ToroGenCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "tq";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.torogen.usage";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		spawnBastionsLair(sender, sender.getPosition());
	}

	private void spawnBastionsLair(ICommandSender sender, BlockPos pos) {
		new BastionsLairGenerator().generate(sender.getEntityWorld(), sender.getEntityWorld().rand, pos);
	}

	protected void spawnArrayOfTowers(ICommandSender sender) {
		BlockPos pos = sender.getPosition().add(2, -1, 0);

		int floors = 2;
		int radius = 25;
		int floorHieght = 7;

		for (int i = 0; i < 5; i++) {
			spawnTower(floors, radius, floorHieght, sender, pos);
			floors = floors + 2;
			pos = pos.add(radius * 2, 0, 0);
			radius = radius - 4;
		}
	}

	protected void spawnThroneRoom(ICommandSender sender, BlockPos pos) {
		new ThroneRoomGenerator().generate(sender.getEntityWorld(), sender.getEntityWorld().rand, pos);
	}
	
	protected void spawnTower(int floors, int radius, int floorHieght, ICommandSender sender, BlockPos pos) {
		new MageTowerGenerator().generate(floors, radius, floorHieght, sender.getEntityWorld(), sender.getEntityWorld().rand, pos);
	}

	protected void spawnMage(MinecraftServer server, ICommandSender sender) {
		EntityMage e = new EntityMage(server.getEntityWorld());

		int x = sender.getPosition().getX();
		int y = sender.getPosition().getY();
		int z = sender.getPosition().getZ();

		e.setPosition(x, y, z);
		server.getEntityWorld().spawnEntityInWorld(e);
	}

}
