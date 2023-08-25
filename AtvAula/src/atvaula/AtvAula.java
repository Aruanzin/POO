/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atvaula;

/**
 *
 * @author 12609731
 */
public class AtvAula {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Pessoa p1 = new Pessoa("Jeca", "08574614294");
        Pessoa p2 = new Pessoa("Fly", "69696969694");
        //Moradia m1 = new Moradia("Tiradentes", 571, p1);
        Casa c1 = new Casa("Tiradentes", 571, p1);
        //Apartamento apto = new Apartamento("Aviação", 666, p2, 5, 23); 
        Apstudio apto = new Apstudio("Aviação", 666, p2, 5, 23); 
        System.out.println("Dados da Casa:");
        c1.imprimeEnd();
        c1.imprimeMorador();
        
        System.out.println("Dados do Apartamento:");
//        apto.imprimeEnd();
//        apto.imprimeApto();
//        apto.imprimeMorador();
        
        //m1.imprimeEnd();
        //m1.imprimeMorador();
    }
    
}
