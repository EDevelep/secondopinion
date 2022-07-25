
package org.secondopinion.diagnosticcenter.service.rest;

import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticcenterElasticSerchRestAPIService {

  @Autowired
  private CustomRestTemlpateConfig customRestTemlpateConfig;

  @Value("${elastic.diagnosticcenter.url}")
  private String diagnosticcenterElasticSerchSave;

  public void diagnosticcenterElasticSerchSave(String uri,
      DiagnosticcenterSearchDTO diagnosticcenter) {
    customRestTemlpateConfig.callRestAPI(diagnosticcenter, uri, HttpMethod.POST, String.class)
        .getData();
  }

  public void diagnosticcenterElasticSerchSave1(Diagnosticcenter diagnosticcenter) {
    DiagnosticcenterSearchDTO diagnosticcenterSearchDTO =
        DiagnosticcenterSearchDTO.build(diagnosticcenter);
    String uri = String.format(diagnosticcenterElasticSerchSave, diagnosticcenterSearchDTO);
    diagnosticcenterElasticSerchSave(uri, diagnosticcenterSearchDTO);
  }

  public void diagnosticcenterElasticSerchSave1(Diagnosticcenteraddress address) {
    DiagnosticcenterSearchDTO diagnosticcenterSearchDTO = DiagnosticcenterSearchDTO.build(address);
    String uri = String.format(diagnosticcenterElasticSerchSave, diagnosticcenterSearchDTO);
    diagnosticcenterElasticSerchSave(uri, diagnosticcenterSearchDTO);

  }


}
