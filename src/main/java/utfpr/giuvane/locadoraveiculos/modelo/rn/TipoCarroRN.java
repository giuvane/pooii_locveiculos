/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;
import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;

/**
 *
 * @author Giuvane Conti
 */
public class TipoCarroRN {
    private GenericDAO<TipoCarro> genericDao;
    
    public TipoCarroRN()
    {
        genericDao = new GenericDAO<>();
    }
    
    public void gravar(TipoCarro tipoCarro) {
        genericDao.save(tipoCarro);
    }
    
    public void remover (TipoCarro tipoCarro) {
        genericDao.delete(tipoCarro);
    }
    
    public List buscarTodos()
    {
        List<TipoCarro> tipoCarros = genericDao.listAll(TipoCarro.class);
        
        return tipoCarros;
    }
}
