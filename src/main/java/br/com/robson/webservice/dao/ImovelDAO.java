package br.com.robson.webservice.dao;

import br.com.robson.webservice.domain.Imovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelDAO {

    public Imovel salvarImovel(Imovel imovel) {
        EntityManager em = JPAUtil.getEntityManager("imoveis");

        try {
            em.getTransaction().begin();
            em.persist(imovel);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return imovel;
    }

    public List<Imovel> selecionarImoveis() {
        EntityManager em = JPAUtil.getEntityManager("imoveis");

        return em.createQuery("select i from Imovel i", Imovel.class).getResultList();
    }

    public Imovel recuperarImovelPorId(long id) {
        EntityManager em = JPAUtil.getEntityManager("imoveis");

        return em.find(Imovel.class, id);
    }

    public void atualizarImovel(long imovelId, Imovel imovel) {
        EntityManager em = JPAUtil.getEntityManager("imoveis");
        Imovel imovelManaged;

        try {
            em.getTransaction().begin();
            imovelManaged = em.find(Imovel.class, imovelId);
            imovelManaged.setNome(imovel.getNome());
            imovelManaged.setDirecionamento(imovel.getDirecionamento());
            imovelManaged.setEndereco(imovel.getEndereco());
            imovelManaged.setValor(imovel.getValor());
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void excluirImovel(long id) {
        EntityManager em = JPAUtil.getEntityManager("imoveis");
        Imovel imovel;

        try {
            em.getTransaction().begin();
            imovel = em.find(Imovel.class, id);
            em.remove(imovel);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
