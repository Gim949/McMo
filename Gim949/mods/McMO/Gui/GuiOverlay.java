package Gim949.mods.McMO.Gui;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumOS;
import net.minecraft.util.Util;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import Gim949.mods.McMO.CommunityLinks;
import Gim949.mods.McMO.Friend;
import Gim949.mods.McMO.Gui.Button.GuiRectButton;

public class GuiOverlay extends GuiScreen{

	int colorWhite = 0xffffff;
	
	GuiRectButton rectbuttonAdd, rectbuttonRemove, rectButtonClear;
	
	public void initGui() 
    {
    	this.buttonList.add(new GuiRectButton(1, 301, 375, 110, 19, ("View Achievements")));
    	this.buttonList.add(new GuiRectButton(2, 501, 375, 160, 19, ("View Screenshots Folder")));
    	
    	/*this.buttonList.add(rectbuttonAdd = new GuiRectButton(3, 104, 352, 73, 19, ("Add Friend")));
    	this.buttonList.add(rectbuttonRemove = new GuiRectButton(4, 186, 352, 81, 19, ("Remove Friend")));
    	this.buttonList.add(rectButtonClear = new GuiRectButton(5, 104, 378, 163, 19, ("Clear Friends List")));*/
    	
    	if(!(mc.getSession().getSessionID() == null)) 
    	{
    		rectbuttonAdd.drawButton = false;
    		rectbuttonRemove.drawButton = false;
    		rectButtonClear.drawButton = false;
    	}
    }
	
	protected void actionPerformed(GuiButton par1GuiButton) 
	{
		switch (par1GuiButton.id)
        {
            case 1:
                this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
                break;
            
            case 2:
            	File file1 = new File(this.mc.mcDataDir + "/screenshots");
                String s = file1.getAbsolutePath();

                if (Util.getOSType() == EnumOS.MACOS)
                {
                    try
                    {
                        this.mc.getLogAgent().logInfo(s);
                        Runtime.getRuntime().exec(new String[] {"/usr/bin/open", s});
                        return;
                    }
                    catch (IOException ioexception)
                    {
                        ioexception.printStackTrace();
                    }
                }
                else if (Util.getOSType() == EnumOS.WINDOWS)
                {
                    String s1 = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[] {s});

                    try
                    {
                        Runtime.getRuntime().exec(s1);
                        return;
                    }
                    catch (IOException ioexception1)
                    {
                        ioexception1.printStackTrace();
                    }
                }

                boolean flag = false;

                try
                {
                    Class oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {file1.toURI()});
                }
                catch (Throwable throwable)
                {
                    throwable.printStackTrace();
                    flag = true;
                }

                if (flag)
                {
                    this.mc.getLogAgent().logInfo("Opening via system class!");
                    Sys.openURL("file://" + s);
                }
                break;
             
            case 3:
            	this.mc.displayGuiScreen(new GuiFriendAdd());
            	break;
            	
            case 4:
            	mc.displayGuiScreen(new GuiFriendRemove());
            	break;
            	
            case 5:
            	try
            	{
            		if(!(Friend.getManager().friendsList.size() == 0))
            		{
            			Friend.getManager().friendsList.clear();
                		this.addMessage("Your friends list is now empty.");
            		}else
            		{
            			this.addMessage("Your friends list is already empty.");
            		}
            	}
            	catch(Exception e) {
            		
            		this.addMessage("Unable to clear your friends list!");
            	}
            	
            	this.mc.displayGuiScreen(null);
            	break;
        }
	}
	
	public void drawScreen(int par1, int par2, float par3)
    {
		this.drawBackground();
		
		Date date = new Date();
		String time = date.toString();
		
		//Test DrawRect
//		this.drawRect(420, 310, 270, 210, 0x70000000);
		
		//Menus
		GuiRectButton.drawBorderRect(440, 380, 270, 250, 0x70000000, 0x70ffffff); // Achievements
		GuiRectButton.drawBorderRect(635, 380, 456, 250, 0x70000000, 0x70ffffff); // Screenshots
		GuiRectButton.drawBorderRect(92, 380, 254, 250, 0x70000000, 0x70ffffff); // Friends
		
		//Boxes
		this.drawOutlineRect(1, 1, 240, 120, 0x77ffffff);
		this.drawOutlineRect(720, 1, 600, 100, 0x77ffffff);
		
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		
		this.fontRenderer.drawStringWithShadow(time, 7, 7, colorWhite);
		this.fontRenderer.drawStringWithShadow("Minecraft", 415, 7, colorWhite);
		
		//Menu Titles
		this.fontRenderer.drawString("Achievements", 189, 176, colorWhite);
		this.fontRenderer.drawString("Screenshots", 316, 176, colorWhite);
		this.fontRenderer.drawString("Friends", 74, 176, colorWhite);
		
		GL11.glScalef(0.625F, 0.625F, 0.625F);
		this.drawLink("Minecraft.net", 670, 30);
		this.drawLink("PlanetMinecraft", 670, 45);
		this.drawLink("Minecraft Forums", 670, 60);
		this.drawLink("Minecraft Wiki", 670, 75);
		
		int AchievementTotal = AchievementList.achievementList.size();
		this.fontRenderer.drawString("Total Achievements: " + AchievementTotal, 299, 306, colorWhite);
		
		this.fontRenderer.drawString("Press F2, in game", 503, 306, colorWhite);
		this.fontRenderer.drawString("to take a screenshot.", 503, 316, colorWhite);
		
		//****************************************************************************************************
		this.fontRenderer.drawString("LOL I'm working on this part", 122, 366, colorWhite);
		//****************************************************************************************************
		
		int i = Friend.getManager().friendsList.size();
		this.fontRenderer.drawString(i == 0 ? "Forever alone..." : "Amount of Friends: " + i, 118, 306, colorWhite);
		
		//Stats
		GuiMenuStats stat = new GuiMenuStats();
		this.drawStat("Username: " + stat.getUsername(), 15, 30);
		this.drawStat("Minecraft Version: " + stat.getMinecraftVersion(), 15, 45);
		this.drawStat("Resource Pack: " + stat.getTexturePack(), 15, 60);
		this.drawStat("World Name: " + stat.getWorldName(), 15, 75);
		this.drawStat("Difficulty: " + stat.getDifficult(), 15, 90);
		this.drawStat("Seed: " + stat.getSeed(), 15, 105);
		
		super.drawScreen(par1, par2, par3);
    }
	
	public void mouseClicked(int i, int j, int k)
    {
		CommunityLinks.clickLinks(i, j, k);
		
		super.mouseClicked(i, j, k);
    }
	
	public boolean doesGuiPauseGame()
    {
        return true;
    }
	
	public void drawLink(String par1, int par2, int par3)
	{
		this.drawString(this.fontRenderer, EnumChatFormatting.UNDERLINE + par1, par2, par3, colorWhite);
	}
	
	public void drawStat(String string, int par, int var)
	{
		this.drawString(fontRenderer, EnumChatFormatting.ITALIC + string, par, var, colorWhite);
	}
	
	public static void addMessage(String message)
	{
		Minecraft.getMinecraft().thePlayer.addChatMessage("\2471[\247bMcMo\2471]\247f " + message);
	}
	
	public void drawBackground()
	{
		this.drawGradientRect(0, 0, this.width, this.height, 0x00000000, 0xff000000);
	}
	
	public void drawOutlineRect(int par1, int par2, int par3, int par4, int par5)
	{
		drawRect(par1 - 1, par2 - 2, par3 + 1, par2, par5);
		drawRect(par1 - 1, par4, par3 + 1, par4 + 2, par5);
		drawRect(par1 - 2, par2 - 1, par1, par4 + 1, par5);
		drawRect(par3, par2 - 1, par3 + 2, par4 + 1, par5);
	}
}
