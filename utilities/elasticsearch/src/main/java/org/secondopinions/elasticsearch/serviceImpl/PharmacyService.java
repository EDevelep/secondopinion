package org.secondopinions.elasticsearch.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.secondopinions.elasticsearch.dto.GeoLocationDTO;
import org.secondopinions.elasticsearch.dto.PharmacyDTO;
import org.secondopinions.elasticsearch.model.Pharmacy;
import org.secondopinions.elasticsearch.repository.ElasticsearchPharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

	@Autowired
	private ElasticsearchPharmacyRepository pharmacyRepository;
	@Autowired
	public ElasticsearchOperations elasticsearchTemplate;

	public Iterable<Pharmacy> listAll() {
		return this.pharmacyRepository.findAll();
	}

	public Pharmacy save(Pharmacy pharmacy) {
		return this.pharmacyRepository.save(pharmacy);
	}

	public List<Pharmacy> search(String keywords) {
		MatchQueryBuilder searchpharmacy = QueryBuilders.matchQuery("pharmacyname", keywords);
		List<Pharmacy> pharmacy = this.pharmacyRepository.search(searchpharmacy);

		return pharmacy;
	}

//10 to 5 km radiues  if  Distance  is not comeing 
	public List<Pharmacy> getLocationMembers(GeoLocationDTO geoLocationDTO) {
		GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("geoPoint")
				.point(geoLocationDTO.getLatitude(), geoLocationDTO.getLongitude())
				.distance(geoLocationDTO.getDistance(), DistanceUnit.KILOMETERS);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(geoDistanceQueryBuilder).build();
		return elasticsearchTemplate.queryForList(searchQuery, Pharmacy.class);
	}

}
