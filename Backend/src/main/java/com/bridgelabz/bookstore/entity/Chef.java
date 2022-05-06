/** Chef data model class corresponds to entity and table chefs. **/

package com.bridgelabz.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // it indicates that the class is a persistent Java class
@Table(name = "chefs") // @Table annotation provides the table that maps this entity
public class Chef {
	@Id // it's for the primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue annotation is used to define generation strategy
													// for the primary key.GenerationType.AUTO means Auto Increment
													// field
	private long chef_id;

	@Column(name = "nom") // @Column used to define the column in database that maps annotated field
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "origine")
	private String origine;
	/*
	 * @Column(name = "age") private int age;
	 */

	@Column(name = "specialite")
	private String specialite;

	@Column(name = "photo")
	private String photo;

	@Column(name = "ChefDeSemaine")
	private boolean chefDeSemaine;

	public Chef() {

	}

	public Chef(String nom, String prenom, String specialite, String origine, String photo, boolean chefDeSemaine) {

		this.nom = nom;
		this.prenom = prenom;
		this.origine = origine;
		// this.age = age;
		this.specialite = specialite;
		this.photo = photo;
		this.chefDeSemaine = chefDeSemaine;

	}

	public long getChef_id() {
		return chef_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	/**
	 * public int getAge() { return age; }
	 * 
	 * public void setAge(int age) { this.age = age; }
	 * 
	 **/
	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public boolean isChefDeSemaine() {
		return chefDeSemaine;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setChefDeSemaine(boolean ischefDeSemaine) {
		this.chefDeSemaine = ischefDeSemaine;
	}

	@Override
	public String toString() {
		return "Chef [chef_id=" + chef_id + ", nom=" + nom + ", prenom=" + prenom + ", origine=" + origine
				+ ", specialite=" + specialite + ", photo=" + photo + ", chefDeSemaine=" + chefDeSemaine + "]";
	}

}
