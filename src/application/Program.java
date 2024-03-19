package application;

import entities.Cambio;
import entities.Caminhao;
import entities.Carro;
import entities.Diesel;
import entities.Gasolina;
import entities.Moto;
import entities.Tanque;

public class Program {

	public static void main(String[] args) {
		
		Carro carro = new Carro("CIR1455", "Fiat", "Uno", 1997, 200, new Tanque(0.0, 50.0, new Gasolina()), new Cambio(5));

		carro.colocarEscada();
		
		Moto moto = new Moto("JIE1B34", "Honda", "CG160", 2020, 140, new Tanque(0.0, 10.0, new Gasolina()), new Cambio(4), "Kit azul e branco chave");
				
		Caminhao caminhao = new Caminhao("GEF9B32", "Scania", "G500", 2015, 100, new Tanque(0.0, 300.0, new Diesel()), new Cambio(6), 3, 6000.0);
		
		carro.jogoPrincipal();
		
		//moto.jogoPrincipal();
		
		//caminhao.jogoPrincipal();
	}
	
}
