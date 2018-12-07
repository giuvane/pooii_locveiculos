/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Locacao;

/**
 *
 * @author Giuvane Conti
 */
public class LocacaoRN {
    //private PersistenciaPagamento persistencia;
    private GenericDAO<Locacao> genericDao;
    
    public LocacaoRN()
    {
        genericDao = new GenericDAO<>();
    }
    
    public void gravar(Locacao cargo) {
        genericDao.save(cargo);
    }
    
    public void remover (Locacao cargo) {
        genericDao.delete(cargo);
    }
    
    public List buscarTodos()
    {
        List<Locacao> locacoes = genericDao.listAll(Locacao.class);
        
        return locacoes;
    }
}
