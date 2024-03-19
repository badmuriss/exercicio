package entities;

public class Cambio {

	public Cambio(Integer qtdMarchas) {
		this.qtdMarchas = qtdMarchas;
	}

	private Integer qtdMarchas;
	private Integer marchaAtual = 0; //0 = ponto morto, -1 = ré;

	
	public Integer getQtdMarchas() {
		return qtdMarchas;
	}

	public void setQtdMarchas(Integer qtdMarchas) {
		this.qtdMarchas = qtdMarchas;
	}

	public Integer getMarchaAtual() {
		return marchaAtual;
	}

	public void setMarchaAtual(Integer marchaAtual) {
		this.marchaAtual = marchaAtual;
	}

	

	public void reduzirMarcha() {
		if(marchaAtual > -1) {
			this.marchaAtual--;
		} 

	}
	
	public void aumentarMarcha() {
		if(marchaAtual < qtdMarchas) {
			this.marchaAtual++;
		}
	}
}
