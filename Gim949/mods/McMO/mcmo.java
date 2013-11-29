package Gim949.mods.McMO;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import Gim949.mods.McMO.Gui.GuiOverlay;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class mcmo{
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		boolean[] repeat = {false};
		
		KeyBinding[] keyToggle1 = {new KeyBinding("Overlay menu Toggle 1", Keyboard.KEY_LSHIFT)};
		KeyBindingRegistry.registerKeyBinding(new T1(keyToggle1, repeat));
		
		KeyBinding[] keyToggle2 = {new KeyBinding("Overlay menu Toggle 2", Keyboard.KEY_TAB)};
		KeyBindingRegistry.registerKeyBinding(new T2(keyToggle2, repeat));
	}
	
	public static class T1 extends KeyHandler{

		public static boolean keyPressed = false;
		private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);
        
		public T1(KeyBinding[] keyBindings, boolean[] repeatings) 
		{
			super(keyBindings, repeatings);
		}

		@Override
		public String getLabel() 
		{
			return "Overlay Menu Key 1";
		}

		@Override
		public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) 
		{
			keyPressed = true;
			
			//No code required: Specific order is LSHIFT + TAB
		}

		@Override
		public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) 
		{
			keyPressed = false;
		}

		@Override
		public EnumSet<TickType> ticks() 
		{
			return tickTypes;
		}
	}
	
	public static class T2 extends KeyHandler{

		public static boolean keyPressed = false;
		private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);
        
		public T2(KeyBinding[] keyBindings, boolean[] repeatings) 
		{
			super(keyBindings, repeatings);
		}

		@Override
		public String getLabel() 
		{
			return "Overlay Menu Key 2";
		}

		@Override
		public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) 
		{
			keyPressed = true;
			if(T1.keyPressed){
				
				Minecraft.getMinecraft().displayGuiScreen(new GuiOverlay());
				
				//System.out.println("Success");
			}
		}

		@Override
		public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) 
		{
			keyPressed = false;
		}

		@Override
		public EnumSet<TickType> ticks() 
		{
			return tickTypes;
		}
	}
}
