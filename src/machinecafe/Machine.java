package machinecafe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Machine {
	
	private Ingredient[] ingredient;
	private Boisson[] boisson;
	private int nbBoisson = -1;
	Integer action;
	Scanner sc = new Scanner(System.in);
	
	public Machine(){
		this.ingredient = new Ingredient[4];
		this.ingredient[0] = new Ingredient("Caf�", 0);
		this.ingredient[1] = new Ingredient("Lait", 0);
		this.ingredient[2] = new Ingredient("Sucre", 0);
		this.ingredient[3] = new Ingredient("Chocolat", 0);
		
		this.boisson = new Boisson[3];
		this.boisson[0] = new Boisson("Th� �trange", 5, 0, 2, 1, 0);
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
		System.out.println("Bienvenue dans notre Machine � caf�");
		menu();
	}
	
	public void menu(){		
		do {
			System.out.println("Veuillez saisir le chiffre de votre action");
			System.out.println("1 : Acheter une boisson");
			System.out.println("2 : Ajouter une boisson");
			System.out.println("3 : Modifier une boisson");
			System.out.println("4 : Supprimer une boisson");
			System.out.println("5 : Ajouter un ingr�dient");
			System.out.println("6 : V�rifier le stock des ingr�dients");
			System.out.println("7 : Consulter la recette des boissons");
			System.out.println("8 : Quitter");
			
			action = saisie();

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
				case 7: this.consulterRecette();
					break;
				case 8: this.sortieMachine();
					break;
			}
		} while (action != 8);
	}
	
	public void acheterBoisson(){
		int monnaie;
		double rendu;
		
		listeBoisson("Veuillez saisir le num�ro de la boisson que vous souhaitez acheter");
		
		System.out.println("Veuillez saisir votre monnaie");
		monnaie = saisie();
		
		if (monnaie < this.boisson[action].getPrix()) {
			System.out.println("Vous n'avez pas assez de monnaie, vente refus�e !");
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
		
		rendu = monnaie - this.boisson[action].getPrix();
		
		if (rendu > 0) {
			System.out.println("Pensez � r�cup�rer votre monnaie : " + rendu);
		}
		
		System.out.println("Votre boisson est en cours de pr�paration, veuillez patienter ...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println("Votre boisson est pr�te, merci de votre achat !");
		
		retourMenu();
	}
	
	public void ajouterBoisson(){
		int[] infos;
		
		for (int i = 0; i < this.boisson.length; i++) 
		{
			if(this.boisson[i] == null)
			{
				System.out.println("Veuillez saisir le nom de votre boisson");
				sc.nextLine();
				String nom_boisson = sc.nextLine();
				infos = editBoisson();
				
				Boisson boisson = new Boisson(nom_boisson, infos[0], infos[1], infos[2], infos[3], infos[4]);
				
				this.boisson[i] = boisson;
				System.out.println("Votre boisson a bien �t� ajout�e.");
				return;
			}
		}
		
		System.out.println("La machine contient deja 3 boissons, ajout impossible !");
		retourMenu();
		
	}
	
	public void modifierBoisson(){
		int[] infos;
		
		listeBoisson("Veuillez saisir le num�ro de la boisson que vous souhaitez modifier");
		
		infos = editBoisson();

		this.boisson[action].setPrix(infos[0]);
		this.boisson[action].setUnit_cafe(infos[1]);
		this.boisson[action].setUnit_lait(infos[2]);
		this.boisson[action].setUnit_sucre(infos[3]);
		this.boisson[action].setUnit_chocolat(infos[4]);
		
		System.out.println("Votre boisson a �t� correctement modifi�e.");
		
		retourMenu();
	}
	
	public void supprimerBoisson(){
		
		listeBoisson("Veuillez saisir le num�ro de la boisson que vous souhaiter supprimer");
		
		this.boisson[action] = null;
		
		System.out.println("La boisson a �t� supprim�e");
		
		retourMenu();
	}
	
	public void ajouterIngredient(){
		int qte = 0;
		
		listeIngredient("Veuillez saisir le num�ro de l'ingr�dient que vous souhaitez ajouter");
		
		System.out.println("Veuillez indiquer la quantit� de " + this.ingredient[action].getNom() + " que vous souhaitez ajouter");
		
		qte = saisie();
		
		System.out.println(this.ingredient[action].getNom() + " - Quantit� avant ajout : " + this.ingredient[action].getUnit());
	
		this.ingredient[action].ajouterIngredient(qte);
		
		System.out.println(this.ingredient[action].getNom() + " - Quantit� apr�s ajout : " + this.ingredient[action].getUnit());
		retourMenu();
	}
	
	public void verifStockIngredients(){
		System.out.print("Voici le stock actuel des ingr�dients\n");
		for (int i = 0; i <= ingredient.length - 1; i++) {
			System.out.println("\t" + this.ingredient[i].getNom() + " - Quantit� : " + this.ingredient[i].getUnit());
		}
		userAction();
	}
	
	public void consulterRecette() {
		int i;

		for (i = 0; i < this.boisson.length - 1; i++) 
		{
			if(this.boisson[i] != null)
			{
				this.boisson[i].getInfos();
				nbBoisson++;
			}
		}
		
		userAction();
	}
	
	public void sortieMachine(){
		System.out.println("Merci de votre visite, � bientot !");
	}
	
	public void retourMenu(){
		System.out.println("Retour au menu");
		sc.reset();
		sc = new Scanner(System.in);
		menu();
	}

	public void listeBoisson(String txt){
		int i;
		if(this.boisson[0] != null) 
		{
			System.out.println(txt);
			do 
			{
				nbBoisson = -1;
				for (i = 0; i < this.boisson.length - 1; i++) 
				{
					if(this.boisson[i] != null)
					{
						System.out.println("\t" + i + " : " + this.boisson[i].getNom());
						nbBoisson++;
					}
				}
				
				action = saisie();
				
			} while (action < 0 || action > nbBoisson); // this.boisson.length - 1);
		}
		else 
		{
			System.out.println("Il n'y a aucune boisson dans la machine");
			retourMenu();
		}
	}
	
	public void listeIngredient(String txt) {
		int i;
		
		if(this.ingredient[0] != null) {
			System.out.println(txt);
			do {
				for (i = 0; i < this.ingredient.length; i++) {
					if(this.ingredient[i] != null){
						System.out.println("\t" + i + " : " + this.ingredient[i].getNom());
					}
				}
				
				action = saisie();
				
			} while (action < 0 || action > this.ingredient.length - 1);
		}
		else {
			System.out.println("Il n'y a aucun ingredient dans la machine");
			retourMenu();
		}
	}

	public int saisie() {
		int s = -1;
		try 
		{
			if (sc.hasNextInt()) 
			{
				s = sc.nextInt();
				return s;
			}
			else 
			{
				System.out.println("Vous devez saisir une valeur num�rique");
				retourMenu();
			}
		} 
		catch (InputMismatchException e) 
		{
			System.out.println("Votre valeur est sup�rieure � la capacit� max (2 147 483 647)");
			retourMenu();
		}
		return s;
	}

	public int[] editBoisson() {
		int[] infos = new int[5];
		
		System.out.println("Veuillez saisir le prix de votre boisson");
		infos[0] = saisie();
		System.out.println("Veuillez saisir la quantit� de caf� dans votre boisson");
		infos[1] = saisie();
		System.out.println("Veuillez saisir la quantit� de lait dans votre boisson");
		infos[2] = saisie();
		System.out.println("Veuillez saisir la quantit� de sucre dans votre boisson");
		infos[3] = saisie();
		System.out.println("Veuillez saisir la quantit� de chocolat dans votre boisson");
		infos[4] = saisie();
		
		return infos;
	}
	
	public void userAction() {
		System.out.println("Appuyez sur une touche pour quitter cet �cran");
		sc.next();
		retourMenu();
	}
	
}
