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
public class MultaCarga extends CalculaPagamentoFactory {

    @Override
    public double valorMulta(Pagamento conta) {
        // Regra para Carga
        
        return 0;
    }
    
}
