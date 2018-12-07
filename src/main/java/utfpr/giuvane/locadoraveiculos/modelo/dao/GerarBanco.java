/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.dao;

import javax.persistence.EntityManager;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Passeio;
import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;

/**
 *
 * @author Giuvane
 */
public class GerarBanco {
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManager manager = ConexaoHibernate.getInstance();
        
        Marca m = new Marca();
        m.setDescricaoMarca("Marca 01");
        
        manager.getTransaction().begin();
        manager.persist(m);
        
        TipoCarro tc = new TipoCarro();
        tc.setDescricaoTipoCarro("Tipo Carro 01");
        manager.persist(tc);
        
        Passeio p = new Passeio();
        p.setMarca(m);
        p.setDescricaoCarro("Carro 01");
        p.setPlaca("1234");
        p.setTipoCarro(tc);
        manager.persist(p);
        
        manager.getTransaction().commit();
    }
}
