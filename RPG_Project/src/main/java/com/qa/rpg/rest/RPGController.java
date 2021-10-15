package com.qa.rpg.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rpg.service.RPGService;
import com.qa.rpg.Character;

@CrossOrigin
@RestController //make bean
public class RPGController {
	// add service 
	private RPGService service;
	
	public RPGController(RPGService service) {
		super();
		this.service = service;
	}
	
	//requests below
	@GetMapping("/hello")
	public String hello() {
		return "Hi hi please work";
	}
	
	@GetMapping("/getCharacterByName/{name}")
	public List<Character> getCharacterByName(@PathVariable String name) {
		return this.service.getCharacterByName(name);
	}
	
	@GetMapping("/getCharacter/{id}")
	public List<Character> getCharacterByIndex(@PathVariable Integer id) {
		return this.service.getCharacterByIndex(id);
	}
	
	@GetMapping("/getAllCharacters")
	public List<Character> getAllCharacters() {
		return this.service.getAllCharacters();
	}

	@PostMapping("/createCharacter")
	public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
		Character responseBody = this.service.createCharacter(character);
		ResponseEntity<Character> response = new ResponseEntity<Character>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateCharacter/{id}")
	public ResponseEntity<Character> updateCharacter(@RequestBody Character character, @PathVariable Integer id) {
		Character responseBody = this.service.updateCharacter(character, id);
		return new ResponseEntity<Character>(responseBody, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/removeCharacter/{id}")
	public ResponseEntity<?> deleteCharacter(@PathVariable Integer id) {
		boolean deleted = this.service.deleteCharacter(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
