package org.secondopinions.elasticsearch.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.secondopinions.elasticsearch.dto.CSVTOJSON;
import org.secondopinions.elasticsearch.dto.MedicineDTO;
import org.secondopinions.elasticsearch.model.Medicine;
import org.secondopinions.elasticsearch.repository.ElasticsearchMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

	@Autowired
	private ElasticsearchMedicineRepository medicineRepository;

	public Medicine saveMedicine(Medicine medicine) {
		List<Medicine> medicines = CSVTOJSON.convertCsvTOJson();
		medicine.setId(UUID.randomUUID().toString());
		medicineRepository.save(medicine);
		return medicine;
	}

	public List<Medicine> search(String keywords) {
		MatchQueryBuilder searchmedicine = QueryBuilders.matchQuery("name", keywords);
		List<Medicine> medicine = medicineRepository.search(searchmedicine);
		return medicine;
	}

	public Medicine findMedicineByName(String medicineName) {
		List<Medicine> medicines = medicineRepository.findByName(medicineName);
		if(medicines.isEmpty()) {
			return  null;
		}
		return medicines.get(0);
	}
}
