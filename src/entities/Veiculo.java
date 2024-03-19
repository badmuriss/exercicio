package entities;

public abstract class Veiculo {

	public Veiculo(String placa, String marca, String modelo, Integer ano, Integer velocidadeMax, Tanque tanque, Cambio cambio) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.velocidadeMax = velocidadeMax;
		this.tanque = tanque;
		this.cambio = cambio;
	}

	private String placa;
	private String marca;
	private String modelo;
	private Integer ano;
	private Integer velocidadeAtual = 0;
	private Integer velocidadeMax;
	private Tanque tanque;
	private Cambio cambio;
	private Boolean ligado = false;
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getVelocidadeAtual() {
		return velocidadeAtual;
	}

	public void setVelocidadeAtual(Integer velocidadeAtual) {
		this.velocidadeAtual = velocidadeAtual;
	}

	public Integer getVelocidadeMax() {
		return velocidadeMax;
	}

	public void setVelocidadeMax(Integer velocidadeMax) {
		this.velocidadeMax = velocidadeMax;
	}

	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
	
	public Cambio getCambio() {
		return cambio;
	}

	public void setCambio(Cambio cambio) {
		this.cambio = cambio;
	}

	public Boolean getLigado() {
		return ligado;
	}

	public void setLigado(Boolean ligado) {
		this.ligado = ligado;
	}
	
	public void ligar() {
		this.setLigado(true);
	}
	
	public void desligar() {
		this.setLigado(false);
	}
	
	public void acelerar(int quantidade) {
		if(!ligado) {
			ligar();
		}
		
		if(quantidade <= 0) {
			throw new IllegalArgumentException("Valor invalido");
		} 
		
		//se a quantidade extrapolar a velocidade maxima travar na velocidade maxima
		if(velocidadeAtual + quantidade > velocidadeMax) {
			this.velocidadeAtual = velocidadeMax;
		} else {
			this.velocidadeAtual += quantidade;
			
			//logica de alteracao de marchas = passar uma marcha a cada (velocidade maxima / 2) / num de marchas, ex carro com 200km/h de velocidade max e 5 marchas, passar uma marcha a cada 20km/h e travar na ultima apos os 80km/h
			int marchaMaxima = this.cambio.getQtdMarchas();
			double coefAlteracaoMarcha = (this.velocidadeMax/2) / marchaMaxima;
			int marchaAlterada = (int)(Math.ceil(velocidadeAtual/coefAlteracaoMarcha));
			
			if(marchaAlterada <= marchaMaxima) {
				cambio.setMarchaAtual(marchaAlterada);
			} else {
				cambio.setMarchaAtual(marchaMaxima);
			}
		}
	
	}
	
	public void frear(int quantidade) {
		if(quantidade <= 0) {
			throw new IllegalArgumentException("Valor invalido");
		} 
		if(velocidadeAtual - quantidade < 0) {
			this.velocidadeAtual = 0;
		} else {
			this.velocidadeAtual -= quantidade;
			int marchaAlterada = (int)(Math.ceil(velocidadeAtual/20.0));
			
			if(marchaAlterada <= 5) {
				cambio.setMarchaAtual(marchaAlterada);
			} else {
				cambio.setMarchaAtual(5);
			}
		}
		
	}
	
	public void abastecer(double litros) {
		this.tanque.abastecer(litros);
	}
	
	//cada veiculo vai render o combustivel de uma maneira
	public abstract Double getRendimentoCombustivel();

	public abstract void exibirDados();
	
	//uma forma de testar e brincar com o codigo
	public abstract void jogoPrincipal();
	
	
}
	

