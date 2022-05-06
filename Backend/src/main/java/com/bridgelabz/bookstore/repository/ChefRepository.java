/** ChefRepository is an interface that extends JpaRepository for CRUD methods and custom finder methods.It will be autowired in ChefController**/

package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Chef;

@Repository
public interface ChefRepository extends JpaRepository<Chef, Long> {

	List<Chef> findByChefDeSemaine(boolean chefDeSemaine); // returns all chiefs

	List<Chef> findByNomContaining(String nom);// returns all chiefs which nom contains imput nom

}

/*
 * We also define custom finder methods: – findByChefDeSemaine(): returns all
 * chefs with chefDeSemaine having value as input chefDeSemaine. –
 * findByNomContaining(): returns all chefs which nom contains input nom.
 * 
 * 
 */