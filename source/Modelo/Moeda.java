package Modelo;

public class Moeda extends PersonagemMovel  {
	private static final long serialVersionUID = 5725735988597693215L;

	public Moeda(int lado) {
		super(0, 11);
		this.ladoVirado = lado;
		System.out.println("MOEDA CIRADA");
		this.bTransponivel = false;
	}

	public void quandoBater() {
		podeMoverAposBater = false;
	}

}
