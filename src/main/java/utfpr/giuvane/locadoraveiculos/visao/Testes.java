/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import utfpr.giuvane.locadoraveiculos.modelo.dao.ConexaoHibernate;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;

/**
 *
 * @author Giuvane
 */
public class Testes {
    public static void main(String[] args) 
	{
            // Utilizando SQL Dinâmica
            /*
            EntityManager manager = ConexaoHibernate.getInstance();
            
            manager.getTransaction().begin();
            String jpql = " SELECT c FROM Cargo c where c.codCargo = ?1";
            Query query = manager.createQuery(jpql);
            query.setParameter(1, 1);
            Cargo c = (Cargo) query.getSingleResult();
            
            System.out.println(c.getDescricaoCargo());
            
            //manager.persist(c);
            //manager.getTransaction().commit();
            */
            
            
            // Utilizando GenericDAO
            GenericDAO<Cargo> cargoDao = new GenericDAO<>();
            
            // Listar um Object
            
            Cargo c = cargoDao.listOne("codCargo", 1, Cargo.class);
            System.out.println(c.getDescricaoCargo());
            
            
            // Listar Vários Objetos
            
            List<Cargo> cargos = cargoDao.listAll(Cargo.class);
            for (Cargo cargo : cargos )
            {
                System.out.println(cargo.getDescricaoCargo());
            }
            
            
            // Gravando Objeto novo
            //Cargo c = new Cargo();
            //c.setDescricaoCargo("Cargo 02");
            
            //cargoDao.save(c);
            //cargoDao.commit();
	}
    
}
