package com.br.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@Path("/riscos")
public class ControladorRisco {
    private static final Logger LOGGER = Logger.getLogger(ControladorRisco.class);

    @Channel("riscos")
    Emitter<Risco> emitter;

    @POST
    public Response mensagemRisco(Risco risco) {
        LOGGER.infof("Enviando a mensagem %s para o kafka", risco.getIdCadastro());
        emitter.send(risco);
        return Response.accepted().build();
    }
}
