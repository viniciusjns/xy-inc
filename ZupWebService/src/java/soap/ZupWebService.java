/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.PontoInteresse;

/**
 *
 * @author Vinicius
 */
@WebService(serviceName = "ZupWebService")
public class ZupWebService {
    
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZupWebServicePU");
    protected EntityManager em = emf.createEntityManager();
    protected Query query;

    /**
     * This is a sample web service operation
     */
    /*@WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }*/
    @WebMethod(operationName = "salvar")
    public void salva(PontoInteresse ponto) {
        em.getTransaction().begin();
        em.persist(ponto);
        em.getTransaction().commit();
        
    }
    
    @WebMethod(operationName = "alterar")
    public void alterar(PontoInteresse ponto) {
        em.getTransaction().begin();
        em.merge(ponto);
        em.getTransaction().commit();
        
    }
    
    @WebMethod(operationName = "buscarTodos")
    public List<PontoInteresse> buscarTodos() {
        em.getTransaction().begin();
        query =em.createQuery("SELECT p FROM PontoInteresse p");
        em.getTransaction().commit();
        return query.getResultList();
    }
    
    @WebMethod(operationName = "excluir")
    public void excluir(PontoInteresse ponto) {
        em.getTransaction().begin();
        em.remove(em.merge(ponto));
        em.getTransaction().commit();
    }
}
