/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.vo;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author Giuvane
 */
@Entity
public class Passeio extends Carro {
    @Transient
    private String passeio;
}
