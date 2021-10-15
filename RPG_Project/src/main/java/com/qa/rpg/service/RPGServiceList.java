package com.qa.rpg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qa.rpg.Character;

@Service
public class RPGServiceList implements RPGService {
	
	private List<Character> characters = new ArrayList<>();

	@Override
	public List<Character> getCharacterByName(String name) {
		return this.characters.stream().filter(character -> name.equalsIgnoreCase(character.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public Character getCharacterByIndex(Integer id) {

		return this.characters.get(id);
	}

	@Override
	public List<Character> getAllCharacters() {

		return this.characters;
	}

	@Override
	public Character createCharacter(Character character) {
		System.out.println("CREATED CHARACTER: " + character);
		this.characters.add(character);
		return this.characters.get(this.characters.size() - 1);
	}

	@Override
	public Character updateCharacter(Character character, Integer id) {
		System.out.println("UPDATED CHARACTER: " + character);
		System.out.println("ID: " + id);
		return this.characters.set(id, character);
	}

	@Override
	public boolean deleteCharacter(Integer id) {
		Character toDelete = this.characters.get(id);
		return this.characters.remove(toDelete);
	}

}

