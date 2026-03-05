package service;

import entity.DadosUsuario;

public class UsuarioService {


    public void alterarNome(DadosUsuario usuario, String novoNome) {
        usuario.setNome(novoNome);
    }

    public void excluir(DadosUsuario usuario) {
        usuario.excluirDados();
    }
}