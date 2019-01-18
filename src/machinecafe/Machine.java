package machinecafe;

import java.util.Scanner;

public class Machine {
	
	private Ingredient[] ingredient;

	Scanner sc = new Scanner(System.in);
	
	public Machine(Ingredient[] ingredient) {
		super();
		this.ingredient = ingredient;
	}
	
	public Ingredient[] getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient[] ingredient) {
		this.ingredient = ingredient;
	}
	
	public Machine(){
		
	}

	public static void main(String[] args) {
		Ingredient cafe = new Ingredient("Caf�", 0);
		Ingredient lait = new Ingredient("Lait", 0);
		Ingredient chocolat = new Ingredient("Chocolat", 0);
		Ingredient sucre = new Ingredient("Sucre", 0);
		
		Machine m = new Machine();
		m.setIngredient(new Ingredient[] {cafe, lait, chocolat, sucre});
		
		m.demarrerMachine();
	}
	
	public void demarrerMachine(){
		System.out.println("Bienvenue dans notre Machine � caf�");
		menu();
	}
	
	public void menu(){
		
		int i;
		do {
			System.out.println("Veuillez �crire votre action");
			System.out.println("1 : Acheter une boisson");
			System.out.println("2 : Ajouter une boisson");
			System.out.println("3 : Modifier une boisson");
			System.out.println("4 : Supprimer une boisson");
			System.out.println("5 : Ajouter un ingr�dient");
			System.out.println("6 : V�rifier le stock des ingr�dients");
			System.out.println("7 : Quitter");
			
			i = sc.nextInt();
			switch(i)
			{
				case 1: this.ajouterBoisson();
					break;
				case 2: this.ajouterBoisson();
					break;
				case 5: this.ajouterIngredient();
					break;
				case 6: this.verifIngredients();
					break;
				case 7: break;
			}
		} while (i != 7);
			
	}
	
	public void acheterBoisson(){
		
	}
	
	public void ajouterBoisson(){
		System.out.println("Vous voulez ajouter une boisson \n");
	}
	
	public void ajouterIngredient(){
		int i;
		
		do {
			System.out.println("Voici la liste des ingr�dients : s�lectionner celui que vous souhaitez ajouter");
			System.out.println("1 : Caf�");
			System.out.println("2 : Lait");
			System.out.println("3 : Chocolat");
			System.out.println("4 : Sucre");

			i = sc.nextInt();
			
		} while (i < 0 || i > 4);
		
		System.out.println("Veuillez indiquer la quantit� que vous souhaitez ajouter");
		int j = sc.nextInt();
		
		//nom ing - avant ajout : x
		System.out.println(this.ingredient[i-1].getNom() + " - Quantit� avant ajout : " + this.ingredient[i-1].getUnit());
		this.ingredient[i-1].setUnit(j);
		System.out.println(this.ingredient[i-1].getNom() + " - Quantit� apr�s ajout : " + this.ingredient[i-1].getUnit());
		//nom ing - apr�s ajout : x
	}
	
	public static void verifIngredients(){
		
	}

}
