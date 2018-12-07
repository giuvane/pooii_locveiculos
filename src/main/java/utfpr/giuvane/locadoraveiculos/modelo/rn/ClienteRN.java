/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;

/**
 *
 * @author Giuvane Conti
 */
public class ClienteRN {
    //private PersistenciaPagamento persistencia;
    private GenericDAO<Cliente> genericDao;
    
    public ClienteRN()
    {
        genericDao = new GenericDAO<>();
    }
    
    public void gravar(Cliente cliente) {
        genericDao.save(cliente);
    }
    
    public void remover (Cliente cliente) {
        genericDao.delete(cliente);
    }
    
    public List buscarTodos()
    {
        List<Cliente> clientes = genericDao.listAll(Cliente.class);
        
        return clientes;
    }
}
