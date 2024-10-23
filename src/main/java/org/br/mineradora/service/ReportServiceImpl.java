package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.br.mineradora.client.ReportRestClient;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.utils.CSVHelper;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public class ReportServiceImpl implements ReportService {

    @Inject
    @RestClient
    ReportRestClient reportRestClient;

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return reportRestClient.requestOpportunitiesData();
    }

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {
        return CSVHelper.opportunitiesToCSV(reportRestClient.requestOpportunitiesData());
    }
}
