package Gim949.mods.McMO.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class GuiMenuStats {
	
	protected Minecraft mc;
	
	public GuiMenuStats()
	{
		this.mc = Minecraft.getMinecraft();
	}
	
	public String getUsername()
	{
		return mc.thePlayer.username;
	}
	
	public String getMinecraftVersion()
	{
		return "1.6.4";
	}
	
	public String getTexturePack()
	{
		return mc.gameSettings.skin;
	}
	
	public String getWorldName()
	{
		if(mc.isSingleplayer())
		{
			WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
			WorldInfo worldinfo = worldserver.getWorldInfo();

			if(MinecraftServer.getServer().worldServers == null)
			{
				return null;
			}

			return (worldinfo.getWorldName());
		}
		
		return "null";
	}
	
	public String getDifficult()
	{
		int x = mc.gameSettings.difficulty;
		String s = Integer.toString(x);
		
		if(x == 0){
			s = "Peacefully";
			return s;
		}
		
		if(x == 1){
			s = "Easy";
			return s;
		}
		
		if(x == 2){
			s = "Normal";
			return s;
		}
		
		if(x == 3){
			s = "Hard";
			return s;
		}
		
		return s;
	}
	
	public String getSeed()
	{
		long l = mc.thePlayer.worldObj.getSeed();
		String s = Long.toString(l);
		
		return s;
	}
}
