/*
 * package org.secondopinions.elasticsearch.serviceImpl;
 * 
 * import java.util.List; import java.util.UUID;
 * 
 * import org.elasticsearch.index.query.MatchQueryBuilder; import
 * org.elasticsearch.index.query.QueryBuilders; import
 * org.secondopinions.elasticsearch.model.Caretaker;
 * 
 * import org.secondopinions.elasticsearch.repository.CaretakerRepository;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * @Service public class CaretakerService {
 * 
 * @Autowired private CaretakerRepository caretakerRepository;
 * 
 * public List<Caretaker> searchCaretakerByName(String keywords) {
 * MatchQueryBuilder searchdoctor = QueryBuilders.matchQuery("firstName",
 * keywords); List<Caretaker> doctors =
 * this.caretakerRepository.search(searchdoctor); return doctors; }
 * 
 * public void save(Caretaker caretaker) {
 * caretaker.setId(UUID.randomUUID().toString());
 * caretakerRepository.save(caretaker);
 * 
 * } }
 */