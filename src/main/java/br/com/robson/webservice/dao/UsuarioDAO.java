package br.com.robson.webservice.dao;

import br.com.robson.webservice.domain.Usuario;

import javax.persistence.EntityManager;

public class UsuarioDAO {

    public Usuario obterUsuario(Usuario usuario){
        EntityManager em = JPAUtil.getEntityManager("imoveis");

        return em.createQuery( "SELECT u From Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class)
                .setParameter( "username", usuario.getUsername())
                .setParameter( "password", usuario.getPassword())
                .getSingleResult();
    }
}
