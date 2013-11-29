package Gim949.mods.McMO;

import java.net.URI;

import Gim949.mods.McMO.Gui.GuiOverlay;

public class CommunityLinks extends GuiOverlay{
	
	public static void clickLinks(int i, int j, int k){
		//this.drawRect(700 = 2, 37 = 4, 630 = 1, 27 = 3, 0xff000000);
		if(630 < i && 700 > i && 27 < j && 37 > j)
		{
			try
			{
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
				oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {new URI("http://www.minecraft.net/")});
			}
			catch (Throwable throwable)
			{
				throwable.printStackTrace();
			}
		}

		//this.drawRect(720, 52, 630, 40, 0xffaa0000);
		if(630 < i && 720 > i && 40 < j && 52 > j)
		{
			try
			{
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
				oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {new URI("http://www.planetminecraft.com/")});
			}
			catch (Throwable throwable)
			{
				throwable.printStackTrace();
			}
		}
		
		//this.drawRect(720, 67, 630, 54, 0xffaa0000);
		if(630 < i && 720 > i && 54 < j && 67 > j)
		{
			try
			{
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
				oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {new URI("http://www.minecraftforum.net/")});
			}
			catch (Throwable throwable)
			{
				throwable.printStackTrace();
			}
		}
		
		//this.drawRect(720, 80, 630, 69, 0xffaa0000);
		if(630 < i && 720 > i && 69 < j && 80 > j)
		{
			try
			{
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
				oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {new URI("http://www.minecraft.wikia.com/")});
			}
			catch (Throwable throwable)
			{
				throwable.printStackTrace();
			}
		}
	}
}
