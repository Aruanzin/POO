package ex4;

public abstract class Produto {
	private String Codigo;
	private float PrecoUni;
	private String Descricao;
	private int QuantEst;
	
	public Produto(String Codigo, float PrecoUni, String Descricao, int QuantEst) {
		this.Codigo = Codigo;
		this.PrecoUni = PrecoUni;
		this.Descricao = Descricao;
		this.QuantEst = QuantEst;
	}
	
	
	public float getPreco() {
		return PrecoUni;
	}
	
	public String getDescricao() {
		return Descricao;
	}
	
	public String getCodigo() {
		return Codigo;
	}
	
	public int getQtddDisponivel() {
		return QuantEst;
	}
	
	public void retira(int Retirado) {
		if(QuantEst < Retirado) {
			System.out.println("Falha na Operação Quantidade Indisponível");
		}else {
			QuantEst -= Retirado;
		}
		return;
	}
	
	public boolean acrescenta(int Acrescentado) {
		QuantEst += Acrescentado;
		return true;
	}
	
	public void DescTotal() {
		System.out.println("Produto " + Codigo + ", " + Descricao + ", custo de " + PrecoUni + ", quantidade " + QuantEst);
		return;
	}
	
	

}