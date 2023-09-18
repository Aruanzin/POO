package ex4;

public class NaoPerecivel extends Produto{

	private int anosGarantia;
	
	public NaoPerecivel(String Codigo, float PrecoUni, String Descricao, int QuantEst) {
		super(Codigo, PrecoUni, Descricao, QuantEst);
		// TODO Auto-generated constructor stub
	}
	
	public int getAnosGarantia() {
		return anosGarantia;
	}

	
}
