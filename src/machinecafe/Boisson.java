package machinecafe;

public class Boisson {
	private String nom;
	private int prix;
	private int unit_cafe;
	private int unit_lait;
	private int unit_sucre;
	private int unit_chocolat;
	
	public Boisson(String nom, int prix, int unit_cafe, int unit_lait, int unit_sucre, int unit_chocolat) {
		this.nom = nom;
		this.prix = prix;
		this.unit_cafe = unit_cafe;
		this.unit_lait = unit_lait;
		this.unit_sucre = unit_sucre;
		this.unit_chocolat = unit_chocolat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getUnit_cafe() {
		return unit_cafe;
	}

	public void setUnit_cafe(int unit_cafe) {
		this.unit_cafe = unit_cafe;
	}

	public int getUnit_lait() {
		return unit_lait;
	}

	public void setUnit_lait(int unit_lait) {
		this.unit_lait = unit_lait;
	}

	public int getUnit_sucre() {
		return unit_sucre;
	}

	public void setUnit_sucre(int unit_sucre) {
		this.unit_sucre = unit_sucre;
	}

	public int getUnit_chocolat() {
		return unit_chocolat;
	}

	public void setUnit_chocolat(int unit_chocolat) {
		this.unit_chocolat = unit_chocolat;
	}
	
	public void getInfos(){
		System.out.println(this.nom + "\t\nPrix: " + this.prix + "\t\nQuantité de café: "+this.unit_cafe
				+"\t \nQuantité de lait: "+this.unit_lait+"\t\nQuantité de sucre: "+this.unit_sucre
				+"\t \nQuantité de chocolat: "+this.unit_chocolat);
	}
	
	
}
