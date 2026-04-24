package br.gov.caixa.caixaverso.controller;

import br.gov.caixa.caixaverso.dto.EmprestimoRequestDTO;
import br.gov.caixa.caixaverso.service.EmprestimoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/emprestimos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmprestimosController {

    @Inject
    EmprestimoService emprestimoService;

    @POST
    public Response criarEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {
        return  Response
                .status(Response.Status.CREATED)
                .entity(emprestimoService.criarEmprestimo(emprestimoRequestDTO))
                .build();
    }
}
