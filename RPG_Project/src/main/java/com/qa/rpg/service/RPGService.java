package com.qa.rpg.service;

import java.util.List;

import com.qa.rpg.Character;

public interface RPGService {
	
	public List<Character> getCharacterByName(String name);
	public Character getCharacterByIndex(Integer id);
	public List<Character> getAllCharacters();
	public Character createCharacter(Character character);
	public Character updateCharacter(Character character, Integer id);
	public boolean deleteCharacter(Integer id);
}