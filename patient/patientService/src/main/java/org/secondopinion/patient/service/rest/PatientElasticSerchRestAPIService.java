
package org.secondopinion.patient.service.rest;

import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.patient.dto.Address;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PatientElasticSerchRestAPIService {

  @Autowired
  private CustomRestTemlpateConfig customRestTemlpateConfig;

  @Value("${elastic.patient.url}")
  private String patientElasticSerchSave;

  public void patientElasticSerchSave(String uri, UserSearchDTO userSearchDTO) {

    customRestTemlpateConfig
        .callRestAPI(userSearchDTO, patientElasticSerchSave, HttpMethod.POST, String.class)
        .getData();

  }

  public void patientElasticSerchSave(User user) {
    UserSearchDTO userSearchDTO = UserSearchDTO.buildUserObject(user);
    String uri = String.format(patientElasticSerchSave, userSearchDTO);
    patientElasticSerchSave(uri, userSearchDTO);

  }

  public void patientElasticSerchSave(Address address) {
    UserSearchDTO userSearchDTO = UserSearchDTO.buildUserObject(address);
    String uri = String.format(patientElasticSerchSave, userSearchDTO);
    patientElasticSerchSave(uri, userSearchDTO);

  }
}
