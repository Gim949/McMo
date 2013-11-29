package Gim949.mods.McMO.Gui.Button;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiRectButton extends GuiButton{

	public GuiRectButton(int par1, int par2, int par3, int par4, int par5, String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
	}
	
	public int getHoverState(boolean par1) 
	{
		byte b0 = 1;

		if (!this.enabled)
		{
			b0 = 0;
		}
		else if (par1) 
		{
			b0 = 2;
		}

		return b0;
	}

	public void drawButton(Minecraft par1Minecraft, int par2, int par3) 
	{
		if (this.drawButton) {
			FontRenderer fontrenderer = par1Minecraft.fontRenderer;
			this.field_82253_i = par2 >= this.xPosition / 1.1 && par3 >= this.yPosition / 1.1 && par2 < this.xPosition + (this.width - 8) / 1.1 && par3 < this.yPosition + (this.height - 8) / 2;
			
			int k = this.getHoverState(field_82253_i);
			int i = this.getHoverState(field_82253_i);
			
			k = 0x80000000;
			
			if(!this.enabled){
				k = 0x00000000;
			}else if(this.field_82253_i){
				k = 0x80A3A1A1;
			}
			
			i = 0x80ffffff;
			
			if(!this.enabled){
				i = 0x00000000;
			}else if(this.field_82253_i){
				i = 0x80C5C5C5;
			}
			
			this.drawBorderRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, k, i);
			
//			this.mouseDragged(par1Minecraft, par2, par3);
			
			int l = 14737632;

			if (!this.enabled) {
				l = -6250336;
			}
			else if (this.field_82253_i) {
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}
	
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        return this.enabled && this.drawButton && par2 >= this.xPosition / 1.1 && par3 >= this.yPosition / 1.1 && par2 < this.xPosition + (this.width - 8) / 1.1 && par3 < this.yPosition + (this.height - 8) / 2;
    }
	
	public static void drawBorderRect(int par1, int par2, int par3, int par4, int par5, int par6)
	{
		drawRect(par1, par2, par3, par4, par5);
		
		drawRect(par1 - 1, par2 - 2, par3 + 1, par2, par6);
		drawRect(par1 - 1, par4, par3 + 1, par4 + 2, par6);
		drawRect(par1 - 2, par2 - 1, par1, par4 + 1, par6);
		drawRect(par3, par2 - 1, par3 + 2, par4 + 1, par6);
	}
}
