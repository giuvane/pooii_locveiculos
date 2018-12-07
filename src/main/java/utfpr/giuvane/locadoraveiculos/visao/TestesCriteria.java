/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import utfpr.giuvane.locadoraveiculos.modelo.dao.ConexaoHibernate;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo_;

/**
 *
 * @author Giuvane
 */
public class TestesCriteria {
    public static void main(String[] args) 
    {

        // Utilizando Criteria
        EntityManager manager = ConexaoHibernate.getInstance();
        CriteriaBuilder cb = manager.getCriteriaBuilder();


        CriteriaQuery<Cargo> c = cb.createQuery(Cargo.class);
        Root<Cargo> a = c.from(Cargo.class);
        c.select(a);

        //Predicate p = cb.equal(a.get(Cargo_.descricaoCargo), "Cargo 02");
        Predicate p = cb.like(a.get(Cargo_.descricaoCargo), "Cargo%");
        c.where(p);

        TypedQuery<Cargo> query = manager.createQuery(c);
        List<Cargo> cargos = query.getResultList();

        for (Cargo cargo : cargos)
        {
            System.out.println("Cargo descrição: " + cargo.getDescricaoCargo());
        }

        // Criteria - Lista de objetos comuns
        //EntityManager manager = ConexaoHibernate.getInstance();
        //CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<String> cString = cb.createQuery(String.class);
        Root<Cargo> a2 = cString.from(Cargo.class);
        cString.select(a2.<String>get(Cargo_.descricaoCargo));

        TypedQuery<String> query2 = manager.createQuery(cString);
        List<String> descricoesCargos = query2.getResultList();

        for (String desc : descricoesCargos)
        {
             System.out.println("Cargo descrição: " + desc);
        }


        /*
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
        //GenericDAO<Cargo> cargoDao = new GenericDAO<>();

        // Listar um Object

        //Cargo c = cargoDao.listOne("codCargo", 1, Cargo.class);
        //System.out.println(c.getDescricaoCargo());


        // Listar Vários Objetos
        /*
        List<Cargo> cargos = cargoDao.listAll(Cargo.class);
        for (Cargo cargo : cargos )
        {
            System.err.println(cargo.getDescricaoCargo());
        }
        */

        // Gravando Objeto novo
        //Cargo c = new Cargo();
        //c.setDescricaoCargo("Cargo 02");

        //cargoDao.save(c);
        //cargoDao.commit();
    }
    
}
