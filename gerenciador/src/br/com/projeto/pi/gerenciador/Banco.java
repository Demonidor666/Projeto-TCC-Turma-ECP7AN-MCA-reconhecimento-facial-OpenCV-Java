package br.com.projeto.pi.gerenciador;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	private static List<Cadastro> lista = new ArrayList<>();
	
	public void adiciona(Cadastro pessoa) {
		
		Banco.lista.add(pessoa);
		System.out.println("Pessoa adicinada no Banco: " + lista.get(0).getNome());
	}
	
	
	public List<Cadastro> getPessoa(){
		return Banco.lista;
	}
}
