package com.qa.rpg.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.rpg.Character;
import com.qa.rpg.repo.CharacterRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RPGServiceUnitTest {
	
	@Autowired
	private RPGServiceDB service;
	
	@MockBean
	private CharacterRepo repo;
	
	@Test
	void testGetByName() {
		final String name = "zhongli";
		final List<Character> characters = List.of(new Character(1, name, "geo", "polearm"));
		
		Mockito.when(this.repo.findByName(name)).thenReturn(characters);
		assertThat(this.service.getCharacterByName(name)).isEqualTo(characters);
		Mockito.verify(this.repo, Mockito.times(1)).findByName(name);
	}
	
	@Test
	void testGetById() {
		final Integer Id = 1;
		final Character character = new Character(Id, "zhongli", "geo", "polearm");

		Mockito.when(this.repo.findById(Id)).thenReturn(Optional.of(character));

		assertThat(this.service.getCharacterByIndex(Id)).isEqualTo(character);

		Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
	}
	
	@Test
	void testGetAllCharacters() {
		final List<Character> characters = List.of(new Character(1, "zhongli", "geo", "polearm"),
				new Character(2, "kazuha", "anemo", "sword"));

		Mockito.when(this.repo.findAll()).thenReturn(characters);

		assertThat(this.service.getAllCharacters()).isEqualTo(characters);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testUpdate() {
		final Integer id = 1;
		Character character = new Character(id, "zhongli", "geo", "polearm");
		Optional<Character> optionalCharacter = Optional.of(character);

		Character newCharacter = new Character(id, "kazuha", "anemo", "sword");

		Mockito.when(this.repo.findById(id)).thenReturn(optionalCharacter);
		Mockito.when(this.repo.save(newCharacter)).thenReturn(newCharacter);

		assertThat(this.service.updateCharacter(newCharacter, character.getId())).isEqualTo(newCharacter);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newCharacter);
	}
	
	@Test
	void testCreate() {
		Character newCharacter = new Character(null, "zhongli", "geo", "polearm");
		Character savedCharacter = new Character(1, "zhongli", "geo", "polearm");

		Mockito.when(this.repo.save(newCharacter)).thenReturn(savedCharacter);

		assertThat(this.service.createCharacter(newCharacter)).isEqualTo(savedCharacter);

		Mockito.verify(this.repo, Mockito.times(1)).save(newCharacter);
	}

	@Test
	void testDelete() {
		final Integer id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteCharacter(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

}
