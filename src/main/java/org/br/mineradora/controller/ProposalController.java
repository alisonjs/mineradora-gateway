package org.br.mineradora.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;

@ApplicationScoped
@Path("/api/trade")
public class ProposalController {

    @Inject
    ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    public Response getProposalDetailsById(@PathParam("id") Long id) {
        try {
            return Response.ok(proposalService.findFullProposal(id), MediaType.APPLICATION_JSON).build();
        } catch (ServerErrorException e) {
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        int responseStatus = proposalService.createNewProposal(proposalDetailsDTO).getStatus();
        if (responseStatus >= 200 && responseStatus < 300) {
            return Response.ok().build();
        } else {
            return Response.status(responseStatus).build();
        }
    }


    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed("manager")
    public Response removeProposalById(@PathParam("id") Long id) {
        int responseStatus = proposalService.removeProposal(id).getStatus();
        if (responseStatus >= 200 && responseStatus < 300) {
            return Response.ok().build();
        } else {
            return Response.status(responseStatus).build();
        }
    }

}
