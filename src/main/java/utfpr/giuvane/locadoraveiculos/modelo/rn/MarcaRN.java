/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;

/**
 *
 * @author Giuvane Conti
 */
public class MarcaRN {
    private GenericDAO<Marca> genericDao;
    
    public MarcaRN()
    {
        genericDao = new GenericDAO<>();
    }
    
    public void gravar(Marca marca) {
        genericDao.save(marca);
    }
    
    public void remover (Marca marca) {
        genericDao.delete(marca);
    }
    
    public List buscarTodos()
    {
        List<Marca> cargos = genericDao.listAll(Marca.class);
        
        return cargos;
    }
    
}
