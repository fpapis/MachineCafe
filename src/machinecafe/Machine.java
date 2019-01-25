package machinecafe;

import java.util.InputMismatchException;
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
		this.ingredient[2] = new Ingredient("Sucre", 0);
		this.ingredient[3] = new Ingredient("Chocolat", 0);
		
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
			
			//action = sc.nextInt();
			saisie();

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
		int monnaie;
		
		listeBoisson("Veuillez saisir le numéro de la boisson que vous souhaitez acheter");
		
		System.out.println("Veuillez saisir votre monnaie");
		monnaie = sc.nextInt();
		
		if (monnaie < this.boisson[action].getPrix()) {
			System.out.println("Vous n'avez pas assez de monnaie, vente refusée !");
			retourMenu();
		}
		
		if (this.ingredient[0].getUnit() < this.boisson[action].getUnit_cafe()) {
			System.out.println("Il n'y a pas assez de " + this.ingredient[0].getNom() + " dans la machine");
			retourMenu();
		}
		
		if (this.ingredient[1].getUnit() < this.boisson[action].getUnit_lait()) {
			System.out.println("Il n'y a pas assez de " + this.ingredient[1].getNom() + " dans la machine");
			retourMenu();
		}
		
		if (this.ingredient[2].getUnit() < this.boisson[action].getUnit_sucre()) {
			System.out.println("Il n'y a pas assez de " + this.ingredient[2].getNom() + " dans la machine");
			retourMenu();
		}
		
		if (this.ingredient[3].getUnit() < this.boisson[action].getUnit_chocolat()) {
			System.out.println("Il n'y a pas assez de " + this.ingredient[3].getNom() + " dans la machine");
			retourMenu();
		}
		
		this.ingredient[0].setUnit(this.ingredient[0].getUnit() - this.boisson[action].getUnit_cafe());
		
		this.ingredient[1].setUnit(this.ingredient[1].getUnit() - this.boisson[action].getUnit_lait());
		
		this.ingredient[2].setUnit(this.ingredient[2].getUnit() - this.boisson[action].getUnit_sucre());
		
		this.ingredient[3].setUnit(this.ingredient[3].getUnit() - this.boisson[action].getUnit_chocolat());
		
		double rendu = monnaie - this.boisson[action].getPrix();
		
		if (rendu > 0) {
			System.out.println("Pensez à récupérer votre monnaie : " + rendu);
		}
	
		System.out.println("Votre boisson est prête, merci de votre achat !");
		
		retourMenu();
	}
	
	public void ajouterBoisson(){
		int prix = 0;
		int unit_cafe = 0;
		int unit_lait = 0;
		int unit_sucre = 0;
		int unit_chocolat = 0;
		
		for (int i = 0; i < this.boisson.length; i++) 
		{
			if(this.boisson[i] == null)
			{
				System.out.println("Veuillez saisir le nom de votre boisson");
				sc.nextLine();
				String nom_boisson = sc.nextLine();
				System.out.println("Veuillez saisir le prix de votre boisson");
				prix = sc.nextInt();
				System.out.println("Veuillez saisir la quantité de café dans votre boisson");
				unit_cafe = sc.nextInt();
				System.out.println("Veuillez saisir la quantité de lait dans votre boisson");
				unit_lait = sc.nextInt();
				System.out.println("Veuillez saisir la quantité de sucre dans votre boisson");
				unit_sucre = sc.nextInt();
				System.out.println("Veuillez saisir la quantité de chocolat dans votre boisson");
				unit_chocolat = sc.nextInt();
				
				Boisson boisson = new Boisson(nom_boisson, prix, unit_cafe, unit_lait, unit_sucre, unit_chocolat);
				
				this.boisson[i] = boisson;
				System.out.println("Votre boisson a bien été ajoutée.");
				return;
			}
		}
		
		System.out.println("Ajout impossible !");
		retourMenu();
		
	}
	
	public void modifierBoisson(){
		int prix = 0;
		int unit_cafe = 0;
		int unit_lait = 0;
		int unit_sucre = 0;
		int unit_chocolat = 0;
		
		listeBoisson("Veuillez saisir le numéro de la boisson que vous souhaitez modifier");
		
		System.out.println("Veuillez saisir le prix de votre boisson");
		prix = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de café dans votre boisson");
		unit_cafe = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de lait dans votre boisson");
		unit_lait = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de sucre dans votre boisson");
		unit_sucre = sc.nextInt();
		System.out.println("Veuillez saisir la quantité de chocolat dans votre boisson");
		unit_chocolat = sc.nextInt();

		this.boisson[action].setPrix(prix);
		this.boisson[action].setUnit_cafe(unit_cafe);
		this.boisson[action].setUnit_lait(unit_lait);
		this.boisson[action].setUnit_sucre(unit_sucre);
		this.boisson[action].setUnit_chocolat(unit_chocolat);
		
		System.out.println("Boisson correctement modifiée");
		
		retourMenu();
	}
	
	public void supprimerBoisson(){
		
		listeBoisson("Veuillez saisir le numéro de la boisson que vous souhaiter supprimer");
		
		this.boisson[action] = null;
		
		System.out.println("La boisson a été supprimée");
		
		retourMenu();
	}
	
	public void ajouterIngredient(){
		int qte = 0;
		
		listeIngredient();
		
		System.out.println("Veuillez indiquer la quantité de " + this.ingredient[action].getNom() + " que vous souhaitez ajouter");
		
		//qte = sc.nextInt();
		
		
		
		System.out.println(this.ingredient[action].getNom() + " - Quantité avant ajout : " + this.ingredient[action].getUnit());
	
		this.ingredient[action].ajouterIngredient(qte);
		
		System.out.println(this.ingredient[action].getNom() + " - Quantité après ajout : " + this.ingredient[action].getUnit());
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
		menu();
	}

	public void listeBoisson(String txt){
		int i;
		
		if(this.boisson[0] != null) {
			System.out.println(txt);
			do {
				for (i = 0; i < this.boisson.length - 1; i++) {
					if(this.boisson[i] != null){
						System.out.println("\t" + i + " : " + this.boisson[i].getNom());
					}
				}
				
				saisie();
				
			} while (action < 0 || action > this.boisson.length - 1);
		}
		else {
			System.out.println("Il n'y a aucune boisson dans la machine");
			retourMenu();
		}
	}
	
	public void listeIngredient() {
	int i;
		
		if(this.ingredient[0] != null) {
			do {
				for (i = 0; i < this.ingredient.length; i++) {
					if(this.ingredient[i] != null){
						System.out.println("\t" + i + " : " + this.ingredient[i].getNom());
					}
				}
				
				saisie();
				
			} while (action < 0 || action > this.ingredient.length - 1);
		}
		else {
			System.out.println("Il n'y a aucun ingredient dans la machine");
			retourMenu();
		}
	}

	public void saisie() {	
		if (sc.hasNextInt()) 
		{
			action = sc.nextInt();
		}
		else 
		{
			System.out.println("Vous devez saisir une valeur numérique");
			sc.nextLine();
			retourMenu();
		}
	}
	
	public Integer verif(Integer val) {
		try {
			val = sc.nextInt();
		} catch (InputMismatchException e) {
			saisie();
		}
		return val;
	}

}
