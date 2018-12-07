/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Funcionario;

/**
 *
 * @author Giuvane Conti
 */
public class FuncionarioRN {
    private GenericDAO<Funcionario> genericDao;
    
    public FuncionarioRN()
    {
        genericDao = new GenericDAO<>();
    }
    
    public void gravar(Funcionario funcionario) {
        genericDao.save(funcionario);
    }
    
    public void remover (Funcionario funcionario) {
        genericDao.delete(funcionario);
    }
    
    public List buscarTodos()
    {
        List<Funcionario> funcionarios = genericDao.listAll(Funcionario.class);
        
        return funcionarios;
    }
}
