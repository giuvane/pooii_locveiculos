/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.factory;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Pagamento;

/**
 *
 * @author Giuvane
 */
public abstract class CalculaPagamentoFactory {
     public abstract double valorMulta(Pagamento conta);
    
    public static CalculaPagamentoFactory criarMultaVeiculo(String nome)
    {
        if (nome.equals("Passeio")) {
            return new MultaPasseio();
        } else if (nome.equals("Carga")) {
            return new MultaCarga();
        }
        else if (nome.equals("Motocicleta")) {
            return new MultaMotocicleta();
        }
        
        return null;
    }
}
