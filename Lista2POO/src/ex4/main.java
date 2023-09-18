package ex4;

import java.time.LocalDate;

public class main {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		LocalDate date = new LocalDate(1, 1, 1);
		Estoque e = new Estoque();
		Produto p1 = new PerecivelEspecial("1234", (float)24.60, "Arroz Integral - 5kg", 5, LocalDate.of(2023, 12, 15));
		Produto p2 = new NaoPerecivel("5678", (float)14.80, "Copo de Vidro", 8);
		
		e.cadastrarProd(p1);
		e.cadastrarProd(p2);
		
		e.imprimeTudo();
		
		System.out.println("R$" + e.custoTotalEstoque());
		

		e.retirarProd(p1);
		e.retirarProd(p2);
	}

}
