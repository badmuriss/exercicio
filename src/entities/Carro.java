package entities;

import java.util.Scanner;

public class Carro extends Veiculo {

	public Carro(String placa, String marca, String modelo, Integer ano, Integer velocidadeMax, Tanque tanque, Cambio cambio) {
		super(placa, marca, modelo, ano, velocidadeMax, tanque, cambio);
	
		//se tentar colocar diesel lança uma exceção
		if(this.getTanque().getCombustivel() instanceof Diesel) {
			throw new IllegalArgumentException("Combustivel não suportado");
		}
	}
	
	//a escada turbina o carro, aumentando sua velocidade max em 2x
	private Boolean temEscada = false;
	
	public Boolean getTemEscada() {
		return temEscada;
	}

	public void setTemEscada(Boolean temEscada) {
		this.temEscada = temEscada;
	}
	
	public void tirarEscada() {
		if(temEscada) {
			this.setTemEscada(false);
			this.setVelocidadeMax(this.getVelocidadeMax()/2);
		}
		
	}
	
	public void colocarEscada() {
		
		if(!temEscada) {
			this.setTemEscada(true);
			this.setVelocidadeMax(this.getVelocidadeMax()*2);
		}
			
		
	}
	
	public Double getRendimentoCombustivel() {
		return this.getTanque().getCombustivel().getFatorRendimento();
	}
	
	@Override
	public void exibirDados() {
		System.out.println("Velocidade atual: " + this.getVelocidadeAtual());
		System.out.println("Marcha atual: " + this.getCambio().getMarchaAtual());
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
					
					System.out.println("\n                  _________________\r\n"
							+ "              _.-'_____  _________ _`.\r\n"
							+ "            .` ,'      ||         | `.`.\r\n"
							+ "          .` ,'        ||         |   `.`.\r\n"
							+ "        .`  /          ||         |  ,' ] `....___\r\n"
							+ "      _`__.'''''''''''''''''''''''`''''''''|..___ `-.._\r\n"
							+ "    .'                  [='                '     `'-.._`.\r\n"
							+ " ,:/.'''''''''''''''''''|''''''''''''''''''|'''''''''''\\'\\\r\n"
							+ "  //||    _..._         |                  '    _..._  |)|\r\n"
							+ " /|//   ,',---.`.       |                  |  .',---.`.\\>|\r\n"
							+ "(':/   //' .-. `\\\\      \\_________________/  '/' .-. `\\\\|_)\r\n"
							+ " `-...'||  '-'  ||________,,,,,,,,,,,,,,,__.'||  '-'  ||-'\r\n"
							+ "       '.'.___.','                           '.'.___.','\r\n"
							+ "         '-.m.-'                               '-.m.-'"
							+ "\n");
					
					System.out.println("Velocidade atual: " + this.getVelocidadeAtual());
					System.out.println("Marcha atual: " + this.getCambio().getMarchaAtual());
					System.out.println(nomeCombustivel + " no tanque: " + Math.ceil(this.getTanque().getLitros()) + " litros");
					
					//se nao tem mais combustivel significa que voce perdeu
					if(this.getTanque().getLitros() == 0) {
						System.out.println("\nAcabou o combustivel, você perdeu :(");
						break; //fim de jogo
					}
					
					System.out.println("\nInsira o comando (A - abastecer, F - frear, W - acelerar) seguido do valor separado por espaço (ex: A 40)\n");
					
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
