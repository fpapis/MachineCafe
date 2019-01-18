package machinecafe;

public class Ingredient {
	private String nom;
	private int unit;
	
	public Ingredient(String nom, int unit){
		this.nom = nom;
		this.unit = unit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}
	
	public void ajouterIngredient(int unit){
		this.unit = this.unit + unit;
	}

}
