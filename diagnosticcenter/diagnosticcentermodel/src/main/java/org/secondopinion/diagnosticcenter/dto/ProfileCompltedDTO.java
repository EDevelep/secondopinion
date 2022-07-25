package org.secondopinion.diagnosticcenter.dto;

public class ProfileCompltedDTO {
  private char isAddressCompleted;
  private char isPersonalDetailComplted;

  public char getIsAddressCompleted() {
    return isAddressCompleted;
  }

  public void setIsAddressCompleted(char isAddressCompleted) {
    this.isAddressCompleted = isAddressCompleted;
  }

  public char getIsPersonalDetailComplted() {
    return isPersonalDetailComplted;
  }

  public void setIsPersonalDetailComplted(char isPersonalDetailComplted) {
    this.isPersonalDetailComplted = isPersonalDetailComplted;
  }

}
