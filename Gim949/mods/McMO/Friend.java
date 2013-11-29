package Gim949.mods.McMO;

import java.util.ArrayList;
import net.minecraft.util.StringUtils;

public class Friend {
	
	String name;
	
	static Manager manager;
	static Friend friend;
	
	public Friend(String name)
	{
		this.name = name;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public static Manager getManager()
	{
		if(manager == null) 
		{
			manager = new Manager();
		}
		
		return manager;
	}
	
	public static class Manager 
	{
		public static ArrayList<Friend> friendsList = new ArrayList<Friend>();
		
		public void addFriend(String name)
		{
			friendsList.add(new Friend(name));
		}
		
		public void removeFriend(String name)
		{
			for(Friend friend : friendsList)
			{
				friendsList.remove(friend);
				break;
			}
		}
		
		public boolean isFriend(String name)
		{
			boolean isFriend = false;
			
			for(Friend friend : friendsList)
			{
				if(friend.getName().equalsIgnoreCase(StringUtils.stripControlCodes(name)))
				{
					isFriend = true;
					break;
				}
			}
			
			return isFriend;
		}
	}
}
