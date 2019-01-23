package machinecafe;

import java.util.ArrayList;
import java.util.Scanner;

public class Machine {
	
	private Ingredient[] ingredient;
	private Boisson[] boisson;
	Integer action;
	Scanner sc = new Scanner(System.in);
	
	public Machine(){
		this.ingredient = new Ingredient[4];
		this.ingredient[0] = new Ingredient("Café", 0);
		this.ingredient[1] = new Ingredient("Lait", 0);
		this.ingredient[2] = new Ingredient("Chocolat", 0);
		this.ingredient[3] = new Ingredient("Sucre", 0);
		
		this.boisson = new Boisson[3];
		this.boisson[0] = new Boisson("Thé étrange", 5, 0, 2, 1, 0);
	}
	
	public Ingredient[] getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient[] ingredient) {
		this.ingredient = ingredient;
	}
	
	public Boisson[] getBoisson() {
		return boisson;
	}

	public void setBoisson(Boisson[] boisson) {
		this.boisson = boisson;
	}
	/*
	public static void main(String[] args) {
		
		Ingredient cafe = new Ingredient("Café", 0);
		Ingredient lait = new Ingredient("Lait", 0);
		Ingredient chocolat = new Ingredient("Chocolat", 0);
		Ingredient sucre = new Ingredient("Sucre", 0);
		
		//Boisson a = new Boisson("cafe", 5, 2, 2, 2, 2);
		//Boisson b = new Boisson("chocolat", 5, 2, 2, 2, 2);
		//Boisson c = new Boisson("eau", 5, 2, 2, 2, 2); 
		
		Machine m = new Machine();
		
		
		//m.setBoisson(new Boisson[] {a,b,c});
		m.setBoisson(new Boisson[] {});
		
		m.demarrerMachine();
		
	}*/
	
	public void demarrerMachine(){
		System.out.println("Bienvenue dans notre Machine à café");
		menu();
	}
	
	public void menu(){
		do {
			System.out.println("Veuillez saisir le chiffre de votre action");
			System.out.println("1 : Acheter une boisson");
			System.out.println("2 : Ajouter une boisson");
			System.out.println("3 : Modifier une boisson");
			System.out.println("4 : Supprimer une boisson");
			System.out.println("5 : Ajouter un ingrédient");
			System.out.println("6 : Vérifier le stock des ingrédients");
			System.out.println("7 : Quitter");
						
			action = sc.nextInt();
			switch(action)
			{
				case 1: this.acheterBoisson();
					break;
				case 2: this.ajouterBoisson();
					break;
				case 3: this.modifierBoisson();
					break;
				case 4:	this.supprimerBoisson();
					break;
				case 5: this.ajouterIngredient();
					break;
				case 6: this.verifStockIngredients();
					break;
				case 7: this.sortieMachine();
					break;
				default: System.out.println("Veuillez saisir une valeur correcte");
			}
		} while (action != 7);
	}
	
	public void acheterBoisson(){
		
		do {
			for (int i = 0; i < this.ingredient.length - 1; i++) {
				if(this.boisson[i] != null){
					System.out.println("==========");
					this.boisson[i].getInfos();
					System.out.println("==========");
				}
			}
			
			action = sc.nextInt();
		} while (action < 0 || action > this.ingredient.length - 1);
		
		retourMenu();
	}
	
	public void ajouterBoisson(){
		
		System.out.println("Veuillez saisir le nom de votre boisson");
		sc.nextLine();
		String nom_boisson = sc.nextLine();
		System.out.println("Veuillez saisir le prix de votre boisson");
		int prix = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de café dans votre boisson");
		int unit_cafe = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de lait dans votre boisson");
		int unit_lait = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de sucre dans votre boisson");
		int unit_sucre = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de chocolat dans votre boisson");
		int unit_chocolat = sc.nextInt();
		
		Boisson boisson = new Boisson(nom_boisson, prix, unit_cafe, unit_lait, unit_sucre, unit_chocolat);
		for (int i = 0; i < this.ingredient.length; i++) 
		{
			if(this.boisson[i] == null)
			{
				this.boisson[i] = boisson;
				System.out.println("ajout ok");
				return;
			}
		}
		
		System.out.println("erreur ajout");
			
		retourMenu();
	}
	
	public void modifierBoisson(){
		
		System.out.println("Veuillez saisir le numéro de la boisson que vous souhaitez modifier");
		
		do {
			for (int i = 0; i < this.ingredient.length - 1; i++) {
				if(this.boisson[i] != null){
					System.out.println("\t" + i + " : " + this.boisson[i].getNom());
				}
			}
			
			action = sc.nextInt();
		} while (action < 0 || action > this.ingredient.length - 1);
		
		System.out.println("Veuillez saisir le prix de votre boisson");
		int prix = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de café dans votre boisson");
		int unit_cafe = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de lait dans votre boisson");
		int unit_lait = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de sucre dans votre boisson");
		int unit_sucre = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de chocolat dans votre boisson");
		int unit_chocolat = sc.nextInt();
		
		this.boisson[action].setPrix(prix);
		this.boisson[action].setUnit_cafe(unit_cafe);
		this.boisson[action].setUnit_lait(unit_lait);
		this.boisson[action].setUnit_sucre(unit_sucre);
		this.boisson[action].setUnit_chocolat(unit_chocolat);
		
		System.out.println("Boisson correctement modifiée");
		
		retourMenu();
	}
	
	public void supprimerBoisson(){
		System.out.println("Veuillez saisir le numéro de la boisson que vous souhaiter supprimer");
		
		do {
			for (int i = 0; i < this.ingredient.length - 1; i++) {
				if(this.boisson[i] != null){
					System.out.println("\t" + i + " : " + this.boisson[i].getNom());
				}
			}
			
			action = sc.nextInt();
		} while (action < 0 || action > this.ingredient.length - 1);
		
		this.boisson[action] = null;
		
		retourMenu();
	}
	
	public void ajouterIngredient(){
		do {
			System.out.println("Voici la liste des ingrédients : sélectionner celui que vous souhaitez ajouter");
			System.out.println("1 : Café");
			System.out.println("2 : Lait");
			System.out.println("3 : Chocolat");
			System.out.println("4 : Sucre");

			action = sc.nextInt();
			
		} while (action < 0 || action > ingredient.length - 1);
		
		System.out.println("Veuillez indiquer la quantité que vous souhaitez ajouter");
		int qte = sc.nextInt();
		
		System.out.println(this.ingredient[action-1].getNom() + " - Quantité avant ajout : " + this.ingredient[action-1].getUnit());
		this.ingredient[action-1].ajouterIngredient(qte);
		System.out.println(this.ingredient[action-1].getNom() + " - Quantité après ajout : " + this.ingredient[action-1].getUnit());
		retourMenu();
	}
	
	public void verifStockIngredients(){
		System.out.print("Voici le stock actuel des ingrédients\n");
		for (int i = 0; i <= ingredient.length - 1; i++) {
			System.out.println("\t" + this.ingredient[i].getNom() + " - Quantité : " + this.ingredient[i].getUnit());
		}
		return;
	}
	
	public void sortieMachine(){
		System.out.println("Merci de votre visite, à bientot !");
	}
	
	public void retourMenu(){
		System.out.println("Retour au menu");
	}
	
	public void listeBoisson(){
		int i;
		
		do {
			for (i = 0; i < this.ingredient.length - 1; i++) {
				if(this.boisson[i] != null){
					System.out.println("\t" + i + " : " + this.boisson[i].getNom());
				}
			}
			
			action = sc.nextInt();
		} while (action < 0 || action > this.ingredient.length - 1);
	}

}
