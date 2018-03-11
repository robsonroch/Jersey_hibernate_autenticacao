package br.com.robson.webservice.service;

import br.com.robson.webservice.dao.UsuarioDAO;
import br.com.robson.webservice.domain.Usuario;

import javax.persistence.NoResultException;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean validarUsuario(Usuario usuario){
        try{
            usuarioDAO.obterUsuario(usuario);
        }catch (NoResultException e){
            return false;
        }
        return true;
    }
}
