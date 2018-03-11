package br.com.robson.webservice.resources.filters;

import br.com.robson.webservice.domain.ErrorMessage;
import br.com.robson.webservice.domain.Usuario;
import br.com.robson.webservice.service.UsuarioService;
import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@Provider
@AcessoRestrito
public class AuthenticationFilter implements ContainerRequestFilter {

   private static final String AUTHORIZATION_HEADER = "authorization";
   private static final String BASIC_AUTHORIZATION_PREFIX = "Basic ";
   private UsuarioService usuarioService = new UsuarioService();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
       List<String> headersAutorizacao = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
       if((headersAutorizacao !=null) && (headersAutorizacao.size()>0)){
           String dadosAutorizacao = headersAutorizacao.get(0);
           Usuario usuario = obterUsuarioFromHeaders(dadosAutorizacao);

           if(usuarioService.validarUsuario(usuario)) {
               return;
           }
       }

        Response naoAutorizado = Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage("Usuário não autorizado", Response.Status.UNAUTHORIZED.getStatusCode()))
                .build();

        requestContext.abortWith(naoAutorizado);
    }

    private Usuario obterUsuarioFromHeaders(String dadosAutorizacao) {
        dadosAutorizacao = dadosAutorizacao.replaceFirst(BASIC_AUTHORIZATION_PREFIX, "");
        String dadosDecodificados = Base64.decodeAsString(dadosAutorizacao);
        StringTokenizer dadosTokenizer = new StringTokenizer(dadosDecodificados, ":");
        Usuario usuario = new Usuario();
        usuario.setUsername(dadosTokenizer.nextToken());
        usuario.setPassword(dadosTokenizer.nextToken());
        return usuario;
    }
}
