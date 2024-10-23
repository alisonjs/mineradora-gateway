package org.br.mineradora.service;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.dto.ProposalDetailsDTO;

public interface ProposalService {
    ProposalDetailsDTO findFullProposal(@PathParam("id") long id);

    Response createNewProposal(ProposalDetailsDTO proposalDetailsDTO);

    Response removeProposal(long id);
}
