package org.secondopinion.diagnosticcenter.dto;

public class DiagnosticcenterSearchDTO {
  private String diagnosticcentername;
  private String lastName;
  private Double latitude;
  private Double longitude;
  private Long diagnosticcenterId;
  private Long diagnosticcenteraddresId;
  private Long diagnosticcenrUserId;

  public String getDiagnosticcentername() {
    return diagnosticcentername;
  }

  public void setDiagnosticcentername(String diagnosticcentername) {
    this.diagnosticcentername = diagnosticcentername;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Long getDiagnosticcenterId() {
    return diagnosticcenterId;
  }

  public void setDiagnosticcenterId(Long diagnosticcenterId) {
    this.diagnosticcenterId = diagnosticcenterId;
  }

  public Long getDiagnosticcenteraddresId() {
    return diagnosticcenteraddresId;
  }

  public void setDiagnosticcenteraddresId(Long diagnosticcenteraddresId) {
    this.diagnosticcenteraddresId = diagnosticcenteraddresId;
  }

  public Long getDiagnosticcenrUserId() {
    return diagnosticcenrUserId;
  }

  public void setDiagnosticcenrUserId(Long diagnosticcenrUserId) {
    this.diagnosticcenrUserId = diagnosticcenrUserId;
  }

  public static DiagnosticcenterSearchDTO build(Diagnosticcenter diagnosticcenter) {
    DiagnosticcenterSearchDTO diagnosticcenterSearchDTO = new DiagnosticcenterSearchDTO();

    diagnosticcenterSearchDTO.setDiagnosticcenrUserId(diagnosticcenter.getPrimaryUser());
    diagnosticcenterSearchDTO
        .setDiagnosticcenteraddresId(diagnosticcenter.getPrimaryDataCenterAddressId());
    diagnosticcenterSearchDTO.setDiagnosticcentername(diagnosticcenter.getName());
    diagnosticcenterSearchDTO.setDiagnosticcenterId(diagnosticcenter.getDiagnosticcenterId());
    return diagnosticcenterSearchDTO;
  }

  public static DiagnosticcenterSearchDTO build(Diagnosticcenteraddress address) {
    DiagnosticcenterSearchDTO diagnosticcenterSearchDTO = new DiagnosticcenterSearchDTO();

    diagnosticcenterSearchDTO.setDiagnosticcenteraddresId(address.getDiagnosticCenterAddressId());
    diagnosticcenterSearchDTO.setLatitude(address.getLatitude());
    diagnosticcenterSearchDTO.setLongitude(address.getLongitude());
    diagnosticcenterSearchDTO.setDiagnosticcenterId(address.getDiagnosticcenterId());
    return diagnosticcenterSearchDTO;
  }


}
