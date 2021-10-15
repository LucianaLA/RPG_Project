package com.qa.rpg;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //table
public class Character {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String name;
	private String vision;
	private String weapon;
	
	public Character(Integer id, String name, String vision, String weapon) {
		super();
		this.id = id;
		this.name = name;
		this.vision = vision;
		this.weapon = weapon;
	}

	public Character() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", vision=" + vision + ", weapon=" + weapon + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, vision, weapon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(vision, other.vision)
				&& Objects.equals(weapon, other.weapon);
	}
	
	
	
}
