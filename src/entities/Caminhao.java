package entities;

import java.util.Scanner;

public class Caminhao extends Veiculo {

	
	
	public Caminhao(String placa, String marca, String modelo, Integer ano, Integer velocidadeMax, Tanque tanque,
			Cambio cambio, Integer numEixos, Double capacidadeCarga) {
		super(placa, marca, modelo, ano, velocidadeMax, tanque, cambio);
		this.numEixos = numEixos;
		this.capacidadeCarga = capacidadeCarga;
		this.cargaAtualKg = 0.0;
	}

	private Integer numEixos;
	private Double capacidadeCarga;
	private Double cargaAtualKg;
	
	public Integer getNumEixos() {
		return numEixos;
	}

	public void setNumEixos(Integer numEixos) {
		this.numEixos = numEixos;
	}

	public Double getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public void setCapacidadeCarga(Double capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
	}

	public Double getCargaAtualKg() {
		return cargaAtualKg;
	}

	public void setCargaAtualKg(Double cargaAtual) {
		this.cargaAtualKg = cargaAtual;
	}

	public void carregar(double carga) {
		if(carga <= 0) {
			throw new IllegalArgumentException("Valor invalido");
		}
		
		if(this.cargaAtualKg + carga > this.capacidadeCarga) {
			this.cargaAtualKg = capacidadeCarga;
		} else {
			this.cargaAtualKg += carga;
		}
	}
	
	public void descarregar(double carga) {
		if(carga <= 0) {
			throw new IllegalArgumentException("Valor invalido");
		}
		
		if(this.cargaAtualKg - carga < 0) {
			this.cargaAtualKg = 0.0;
		} else {
			this.cargaAtualKg -= carga;
		}
	}
	
	@Override
	public Double getRendimentoCombustivel() {
		return this.getTanque().getCombustivel().getFatorRendimento() / (1 + 0.1*(this.cargaAtualKg/35));  //segundo a GM, a cada 35 kg adicionais, o consumo aumenta 1%
	}
	
	@Override
	public void exibirDados() {
		System.out.println("Velocidade atual: " + this.getVelocidadeAtual());
		System.out.println("Marcha atual: " + this.getCambio().getMarchaAtual());
		System.out.println("Carga atual: " + this.getCargaAtualKg() + " quilos");		
	}
	
	@Override
	public void jogoPrincipal() {

		
		try(Scanner sc = new Scanner(System.in)){
				
				int contagemHora = 0;
				int contagemKm = 0;
				this.getTanque().setLitros(this.getTanque().getCapacidadeMaxima()/2); //da metade do tanque de combustivel para comecar o jogo
				String nomeCombustivel = this.getTanque().getCombustivel().getClass().getSimpleName();
				
				while(true){
					
					//imprime as informações do carro atualmente
					System.out.println("\nHora " + contagemHora + ", " + contagemKm + "kms rodados");
					
					System.out.println("\n ⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢸⡿⠖⠒⠲⢦⣄⠀⠀\r\n"
							+ "⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢸⡇⠀⠀⠀⠀⠹⣧⠀\r\n"
							+ "⠀⠙⢿⣿⠿⠿⣿⣿⠿⠿⣿⡿⠿⢿⣿⡿⠿⠇⠀⢸⣷⣶⣶⣶⣶⣶⠿⠀\r\n"
							+ "⠀⠇⠈⠋⠠⠆⠘⠃⠰⠀⠙⠁⠴⠀⠛⠀⠶⠀⠀⢸⣿⣿⠿⠿⠿⣿⠀⣶\r\n"
							+ "⠂⣴⠖⠲⢶⡀⠂⣰⠶⠒⢶⡄⠂⢠⣤⣤⣤⣤⣤⠀⠋⣰⡶⠒⢶⡄⠀⠛\r\n"
							+ "⠀⢿⡀⢀⣼⠇⠀⢿⣄⠀⣸⠇⠀⠘⠛⠛⠛⠛⠛⠀⠀⢿⣄⠀⣨⡇⠀⠀\r\n"
							+ "⠀⠈⠙⠛⠉⠀⠀⠈⠙⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠈⠙⠛⠋⠀⠀⠀\n");
					
					System.out.println("Velocidade atual: " + this.getVelocidadeAtual());
					System.out.println("Marcha atual: " + this.getCambio().getMarchaAtual());
					System.out.println(nomeCombustivel + " no tanque: " + Math.ceil(this.getTanque().getLitros()) + " litros");
					System.out.println("Carga Atual: " + this.getCargaAtualKg() + " quilos");
					
					//se nao tem mais combustivel significa que voce perdeu
					if(this.getTanque().getLitros() == 0 && getVelocidadeAtual() > 0) {
						System.out.println("\nAcabou o combustivel, você perdeu :(");
						break; //fim de jogo
					}
					
					System.out.println("\nInsira o comando (A - abastecer, F - frear, W - acelerar, C - carregar, D - descarregar) seguido do valor separado por espaço (ex: A 40)\n");
					
					//recebe o comando e divide em duas partes
					String[] comandoFields = sc.nextLine().split(" ");
					char comando = comandoFields[0].toUpperCase().charAt(0);
					int valor = Integer.parseInt(comandoFields[1]);
					
					//faz um switch case e realiza a acao do comando
					switch (comando) {
					
					case 'A': 
						this.abastecer(valor);
						break;
				
					case 'F': 
						this.frear(valor);
						break;
					case 'W':
						this.acelerar(valor);
						break;
					case 'C':
						this.carregar(valor);
						break;
					case 'D':
						this.descarregar(valor);
						break;
				
					default:
						throw new IllegalArgumentException("Comando invalido");
					}
					
					//atualiza os dados apos a ação
					contagemHora++;
					double litrosRestantes = this.getTanque().getLitros();
					double litrosGastos = getVelocidadeAtual()/getRendimentoCombustivel();
					int kmsRodados = getVelocidadeAtual();
					

					if(litrosGastos > litrosRestantes) {
						litrosGastos = litrosRestantes;
						kmsRodados = (int)Math.floor(litrosGastos*getRendimentoCombustivel());
					}
					
					this.getTanque().setLitros(litrosRestantes - litrosGastos);
					contagemKm += kmsRodados;
					
				};
				
			} catch(RuntimeException e) {
				System.out.println();
				System.out.println(e.getMessage());
			}
	}

}
