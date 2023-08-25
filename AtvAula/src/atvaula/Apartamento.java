/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atvaula;

/**
 *
 * @author 12609731
 */
public abstract class Apartamento extends Moradia {
    private int bloco;
    private int apartamento;
    
    public Apartamento(String rua, int num, Pessoa p1, int bloco, int apartamento) {
        super(rua, num, p1);
        this.bloco = bloco;
        this.apartamento = apartamento;
    }
    
    public void imprimeApto(){
        System.out.println("bloco: " + bloco + "apto: " + apartamento);
    }
}
