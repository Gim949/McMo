package Gim949.mods.McMO;

import net.minecraft.client.gui.Gui;

public class CustomRect extends Gui{
	
	//BETA - DO NOT USE
	
	public void drawCustomRect(int par1, int par2, int par3, int par4, int par6, int par7){
		this.drawRect(par1 + 6, par2 + 6, par3 + 6, par4 + 6, par7);
		this.drawRect(par1, par2, par3, par4, par6);
	}
}
