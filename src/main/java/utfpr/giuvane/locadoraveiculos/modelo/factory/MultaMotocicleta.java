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
public class MultaMotocicleta extends CalculaPagamentoFactory {

    @Override
    public double valorMulta(Pagamento conta) {
        // Regra para Motocicleta
        
        return 0;
    }
    
}
