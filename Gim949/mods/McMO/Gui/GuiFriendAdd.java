package Gim949.mods.McMO.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import org.lwjgl.input.Keyboard;

import Gim949.mods.McMO.Friend;

public class GuiFriendAdd extends GuiScreen{
	
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
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, ("Add Friend")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
        this.textField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 116, 200, 20);
        this.textField.setMaxStringLength(128);
        this.textField.setFocused(true);
        ((GuiButton)this.buttonList.get(0)).enabled = this.textField.getText().length() > 0 && this.textField.getText().split(":").length > 0;
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
                	Friend.getManager().addFriend(s);
                	
                	GuiOverlay.addMessage(s + " was added as your friend.");
                }
                 
                mc.displayGuiScreen(null);
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

    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.textField.mouseClicked(par1, par2, par3);
    }

    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, ("Add Friend"), this.width / 2, 20, 16777215);
        this.drawString(this.fontRenderer, ("Enter the username of the person you want to add to your Friends List"), this.width / 2 - 150, 100, 10526880);
        this.textField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}
