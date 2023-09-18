package ex4;

import java.time.LocalDate;
import java.time.Period;

public abstract class ProdutoPerecivel extends Produto{
	private LocalDate dataValidade;
	
	public ProdutoPerecivel(String Codigo, float PrecoUni, String Descricao, int QuantEst, LocalDate dataValidade) {
		super(Codigo, PrecoUni, Descricao, QuantEst);
		this.dataValidade = dataValidade;
		// TODO Auto-generated constructor stub
	}
	
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	
	public void retira(int Retirado, LocalDate hoje) {
		
		Period diferenca = Period.between(dataValidade, hoje);
		
		if(diferenca.getDays()<30) {
			super.retira(super.getQtddDisponivel());
		}else {
			super.retira(Retirado);
		}
		
	}
	
	public boolean acrescenta(int Acrescentado) {
		if(super.getQtddDisponivel() != 0) {
			return false;
		}else {
			super.acrescenta(Acrescentado);
			return true;
		}
	}
	
}
