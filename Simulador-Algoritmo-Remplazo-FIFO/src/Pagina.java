
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP15DA0023LA
 */
public class Pagina {
    private Integer marco=0;
    private Integer valor=0;

    public Pagina() {
        Random random = new Random();
        this.valor = random.nextInt(10 + 1) + 1;
    }
    
    public Integer getMarco() {
        return marco;
    }

    public void setMarco(Integer marco) {
        this.marco = marco;
    }

    public Integer getValor() {
        return valor;
    }
    
}
