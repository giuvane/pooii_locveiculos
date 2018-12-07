/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.rn;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;

/**
 *
 * @author Giuvane
 */
public class CargoRN {
    //private PersistenciaPagamento persistencia;
    private GenericDAO<Cargo> genericDao;
    
    public CargoRN()
    {
        genericDao = new GenericDAO<>();
    }
    
    public void gravar(Cargo cargo) {
        genericDao.save(cargo);
    }
    
    public void remover (Cargo cargo) {
        genericDao.delete(cargo);
    }
    
    public List buscarTodos()
    {
        List<Cargo> cargos = genericDao.listAll(Cargo.class);
        
        return cargos;
    }
 }
