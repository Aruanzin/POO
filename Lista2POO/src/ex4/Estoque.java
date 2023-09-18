package ex4;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
	private List<Produto> armazem = new ArrayList();
	
	public void cadastrarProd(Produto p) {
		armazem.add(p);
	}
	
	public void consultarProd(String codigo) {
		for(int i = 0; i < armazem.size(); i++) {
			if(armazem.get(i).getCodigo().equals(codigo)) {
				armazem.get(i).DescTotal();
			}
		}
	}
	
	public float custoTotalEstoque() {
		float custo = 0;
		for(int i = 0; i < armazem.size(); i++) {
			custo += armazem.get(i).getPreco();
		}
		return custo;
	}
	
	public void retirarProd(Produto p) {
		armazem.remove(p);
	}
	
	public void imprimeTudo() {
		for(int i = 0; i < armazem.size(); i++) {
			armazem.get(i).DescTotal();
		}
	}
}
