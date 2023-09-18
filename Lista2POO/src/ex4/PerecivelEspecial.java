package ex4;

import java.time.LocalDate;

public class PerecivelEspecial extends ProdutoPerecivel{

	public PerecivelEspecial(String Codigo, float PrecoUni, String Descricao, int QuantEst, LocalDate dataValidade) {
		super(Codigo, PrecoUni, Descricao, QuantEst, dataValidade);
		// TODO Auto-generated constructor stub
	}

	public void DescTotal() {
		super.DescTotal();
		System.out.println("Data de Validade: " + super.getDataValidade());
	}

}
