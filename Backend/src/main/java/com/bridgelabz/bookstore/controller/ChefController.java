
/** ChefController is a RestController which has request mapping methods for RESTful requests such:getAllChefs,createChef,updateChef,deleteChef,findByChefDeSemaine...**/
//controller provides APIs for creating ,retrieving,updating ,deleting and finding chiefs

package com.bridgelabz.bookstore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Chef;
import com.bridgelabz.bookstore.repository.ChefRepository;

@CrossOrigin(origins = "http://localhost:4200") // for configuring allowed origins
@RestController // used to define a controller and to indicate that the return value of the
				// methods should be bound to the web response body
@RequestMapping("/api") // declares that all Apis' url in the controller will start with /api
public class ChefController {
	@Autowired // to inject ChefRepository bean to local variable
	ChefRepository chefRepository;

	@GetMapping("/chefs")
	public ResponseEntity<List<Chef>> getAllChefs(@RequestParam(required = false) String nom) {
		try {
			List<Chef> chefs = new ArrayList<Chef>();
			if (nom == null)
				chefRepository.findAll().forEach(chefs::add);
			else
				chefRepository.findByNomContaining(nom).forEach(chefs::add);
			if (chefs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(chefs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("chefs/{chef_id}")
	public ResponseEntity<Chef> getChefById(@PathVariable("chef_id") long chef_id) {
		Optional<Chef> chefData = chefRepository.findById(chef_id);
		if (chefData.isPresent()) {
			return new ResponseEntity<>(chefData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/chefs")
	public ResponseEntity<Chef> createChef(@RequestBody Chef chef) {
		try {
			Chef _chef = chefRepository.save(new Chef(chef.getNom(), chef.getPrenom(), chef.getSpecialite(),
					chef.getOrigine(), chef.getPhoto(), false));
			return new ResponseEntity<>(_chef, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/chefs/{chef_id}")
	public ResponseEntity<Chef> updateChef(@PathVariable("chef_id") long chef_id, @RequestBody Chef chef) {
		Optional<Chef> chefData = chefRepository.findById(chef_id);
		if (chefData.isPresent()) {
			Chef _chef = chefData.get();
			_chef.setNom(chef.getNom());
			_chef.setPrenom(chef.getPrenom());
			_chef.setOrigine(chef.getOrigine());
			// _chef.setAge(chef.getAge());
			_chef.setSpecialite(chef.getSpecialite());
			_chef.setChefDeSemaine(chef.isChefDeSemaine());
			return new ResponseEntity<>(chefRepository.save(_chef), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/chefs/{chef_id}")
	public ResponseEntity<HttpStatus> deleteChef(@PathVariable("chef_id") long chef_id) {
		try {
			chefRepository.deleteById(chef_id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/chefs")
	public ResponseEntity<HttpStatus> deleteAllChefs() {
		try {
			chefRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/chefs/ChefDeSemaine")
	public ResponseEntity<List<Chef>> findByChefDeSemaine() {
		try {
			List<Chef> chefs = chefRepository.findByChefDeSemaine(true);
			if (chefs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(chefs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
