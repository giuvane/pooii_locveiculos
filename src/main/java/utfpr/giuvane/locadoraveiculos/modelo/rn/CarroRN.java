/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;

/**
 *
 * @author Giuvane Conti
 */
public class CarroRN {
    //private PersistenciaPagamento persistencia;
    private GenericDAO<Carro> genericDao;
    
    public CarroRN()
    {
        genericDao = new GenericDAO<>();
    }
    
    public void gravar(Carro carro) {
        genericDao.save(carro);
    }
    
    public void remover (Carro carro) {
        genericDao.delete(carro);
    }
    
    public List buscarTodos()
    {
        List<Carro> carros = genericDao.listAll(Carro.class);
        
        return carros;
    }
}
