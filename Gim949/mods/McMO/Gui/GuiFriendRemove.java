package Gim949.mods.McMO.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import org.lwjgl.input.Keyboard;

import Gim949.mods.McMO.Friend;
import Gim949.mods.McMO.Gui.Button.GuiRectButton;

public class GuiFriendRemove extends GuiScreen{

	private GuiScreen guiScreen;
	private GuiTextField textField;

	public void updateScreen()
	{
		this.textField.updateCursorCounter();
	}

	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiRectButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, 200, 90, ("Remove Friend")));
		this.buttonList.add(new GuiRectButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 90, I18n.getString("gui.cancel")));
		this.textField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 116, 200, 20);
		this.textField.setMaxStringLength(128);
		this.textField.setFocused(true);
		((GuiRectButton)this.buttonList.get(0)).enabled = this.textField.getText().length() > 0 && this.textField.getText().split(":").length > 0;
	}


	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if (par1GuiButton.enabled)
		{
			if (par1GuiButton.id == 1)
			{
				this.mc.displayGuiScreen(null);
			}
			else if (par1GuiButton.id == 0)
			{
				String s = this.textField.getText();
				
				if(!Friend.getManager().isFriend(s))
				{
					Friend.getManager().isFriend(s);
					Friend.getManager().removeFriend(s);
					
					GuiOverlay.addMessage(s + " was removed from your friends list");
				}
				
				mc.displayGuiScreen(new GuiOverlay());
			}
		}
	}

	protected void keyTyped(char par1, int par2)
	{
		if (this.textField.textboxKeyTyped(par1, par2))
		{
			((GuiButton)this.buttonList.get(0)).enabled = this.textField.getText().length() > 0 && this.textField.getText().split(":").length > 0;
		}
		else if (par2 == 28 || par2 == 156)
		{
			this.actionPerformed((GuiButton)this.buttonList.get(0));
		}
	}

	public void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		this.textField.mouseClicked(par1, par2, par3);
	}

	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawGradientRect(0, 0, this.width, this.height, 0x00000000, 0xff000000);
		this.drawCenteredString(this.fontRenderer, ("Remove Friend"), this.width / 2, 20, 16777215);
		this.drawString(this.fontRenderer, ("Enter the username of the person you want to remove from your Friends List"), this.width / 2 - 150, 100, 10526880);
		this.textField.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}
}
