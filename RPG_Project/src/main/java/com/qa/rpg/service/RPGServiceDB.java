package com.qa.rpg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.rpg.Character;
import com.qa.rpg.repo.CharacterRepo;


@Primary
@Service
public class RPGServiceDB implements RPGService{
	
	private CharacterRepo repo;
	
	public RPGServiceDB(CharacterRepo repo) {
		super();
		this.repo = repo;
	}
	
	@Override
	public List<Character> getCharacterByName(String name) {
		return this.repo.findByName(name);
	}
	
	@Override
	public Character getCharacterByIndex(Integer id) {
		return this.repo.findById(id);
	}
	
	@Override
	public List<Character> getAllCharacters() {
		return this.repo.findAll();
	}

	@Override
	public Character createCharacter(Character character) {
		return this.repo.save(character);
	}

	@Override
	public Character updateCharacter(Character character, Integer id) {
		Optional<Character> optionalCharacter = this.repo.findById(id);
		Character toUpdate = optionalCharacter.get();
		toUpdate.setName(character.getName());
		toUpdate.setVision(character.getVision());
		toUpdate.setWeapon(character.getWeapon());
		return this.repo.save(toUpdate);
	}

	@Override
	public boolean deleteCharacter(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
	
}
