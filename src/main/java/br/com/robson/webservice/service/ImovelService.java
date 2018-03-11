package br.com.robson.webservice.service;

import br.com.robson.webservice.dao.ImovelDAO;
import br.com.robson.webservice.domain.Imovel;

import java.util.List;

public class ImovelService {

    private final ImovelDAO imovelDAO = new ImovelDAO();

    public void cadastrarImovel(Imovel imovel) {
        imovelDAO.salvarImovel(imovel);
    }

    public void descadastrarImovel(long id) {
        imovelDAO.excluirImovel(id);
    }

    public void atualizarImovel(long id, Imovel imovel) {
        imovelDAO.atualizarImovel(id, imovel);
    }

    public List<Imovel> listarImoveis() {
        return imovelDAO.selecionarImoveis();
    }

    public Imovel obterImovel(long id) { return imovelDAO.recuperarImovelPorId(id); }

}
