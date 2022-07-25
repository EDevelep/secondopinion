package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Userreference;

public interface UserreferenceDAO extends IDAO<Userreference,Long >{

	List<Userreference> getallUserreference(Long userid,String referencetype);

	List<Userreference> getallUserreferenceDiagnosticcenter(Long diagnosticcenterid,String referencetype);
}