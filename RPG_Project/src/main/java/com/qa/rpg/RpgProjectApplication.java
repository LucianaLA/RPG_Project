package com.qa.rpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.rpg.rest.RPGController;
import com.qa.rpg.service.RPGService;
import com.qa.rpg.service.RPGServiceList;

@SpringBootApplication
public class RpgProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RpgProjectApplication.class, args);
		RPGController controllerBean = context.getBean(RPGController.class);
		System.out.println(controllerBean);
		RPGController myController = new RPGController(new RPGServiceList());
		System.out.println(new RPGServiceList() instanceof RPGService);
	}

}
