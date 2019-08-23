package org.GladiusPvP.factionsessentials.integration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FactionsFolder 
{
	public static JSONObject factionsJSON = getFactionsFile("factions.json");
	public static JSONObject playersJSON = getFactionsFile("players.json");
	public static JSONObject boardJSON = getFactionsFile("board.json");
	public static JSONObject confJSON = getFactionsFile("conf.json");
	public static JSONObject tagsJSON = getFactionsFile("tags.json");
		
	//
	// Gets players.json File
	//
	public static JSONObject getFactionsFile(String name)
	{
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("plugins//factions//" + name))
		{
			return (JSONObject) jsonParser.parse(reader);
		}
		catch(IOException | ParseException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	//
	// Gets "factionID" Entry in players.json File
	//
	@SuppressWarnings("rawtypes")
	public static String getFactionID(Player player)
	{
		JSONObject file = FactionsFolder.playersJSON;
		
		Map playerInfo = (Map) file.get(player.getUniqueId().toString());
		
		String factionID = playerInfo.get("factionId").toString();
		
		return factionID;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void debug()
	{
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("plugins//factions//factions.json"))
		{
			JSONObject file = (JSONObject) jsonParser.parse(reader);
			Map factionID = (Map) file.get("4");
			
			Iterator<Map.Entry> itr1 = factionID.entrySet().iterator();

			while(itr1.hasNext())
			{
				Map.Entry pair = itr1.next();
				Bukkit.getPlayer("ReachCarter").sendMessage(pair.getKey() + " " + pair.getValue());
			}
			
			Bukkit.getPlayer("ReachCarter").sendMessage();
		} catch(ParseException | IOException e)
		{
			e.printStackTrace();
		}
	}
}
