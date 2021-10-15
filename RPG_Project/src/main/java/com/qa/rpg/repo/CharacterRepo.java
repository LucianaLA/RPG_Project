package com.qa.rpg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.rpg.Character;
@Repository
public interface CharacterRepo extends JpaRepository<Character, Integer> {
	
	List<Character> findByName(String name);

}
