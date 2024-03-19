package entities;

public class Tanque {
	
	public Tanque(Double litros, Double capacidadeMaxima, Combustivel combustivel) {
		this.litros = litros;
		this.capacidadeMaxima = capacidadeMaxima;
		this.combustivel = combustivel;
	}
	
	private Double litros;
	private Double capacidadeMaxima;
	private Combustivel combustivel;
	
	public Double getLitros() {
		return litros;
	}
	
	public void setLitros(Double litros) {
		this.litros = litros;
	}
	
	public Double getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
	
	public void setCapacidadeMaxima(Double capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}
	
	public Combustivel getCombustivel() {
		return combustivel;
	}
	
	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}
	
	public void abastecer(Double litros) {
		if(litros <= 0) {
			throw new IllegalArgumentException("Valor invalido");
		}
		
		if(this.litros + litros > this.capacidadeMaxima) {
			throw new IllegalArgumentException("Capacidade Maxima do Tanque excedida!");
		}
		
		this.litros+=litros;
	}
	
}
