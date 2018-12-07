/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Pagamento;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaPagamento;
import utfpr.giuvane.locadoraveiculos.modelo.factory.CalculaPagamentoFactory;

/**
 *
 * @author Giuvane
 */
public class PagamentoRN {
    
    //private PersistenciaPagamento persistencia;
    private GenericDAO<Pagamento> genericDao = new GenericDAO<>();
    
    public void gravar(Object obj)
    {
        genericDao.save((Pagamento)obj);
        
    }
    
    public Pagamento recuperar(int id)
    {
        Pagamento pag = genericDao.listOne("codPagamento", id, Pagamento.class);
        return pag;
    }
    
    public List<Pagamento> buscarTodos()
    {
        List<Pagamento> pagamentos = genericDao.listAll(Pagamento.class);
        return pagamentos;
    }
    
    public void alterar(Object p)
    {
        genericDao.update((Pagamento) p);
    }
    
    public void excluir(Object p)
    {
        genericDao.delete((Pagamento) p);
    }
    
}
