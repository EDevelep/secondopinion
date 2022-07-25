package org.secondopinion.utilities.feedbackreport.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.secondopinion.utilities.feedbackreport.service.ILookupstaticService;
import org.secondopinion.utilities.jobs.dao.LookupstaticDAO;
import org.secondopinion.utilities.jobs.dto.LookupStaticCache;
import org.secondopinion.utilities.jobs.dto.LookupType;
import org.secondopinion.utilities.jobs.dto.Lookupstatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class LookupstaticServiceImpl implements ILookupstaticService {
	@Autowired
	private LookupstaticDAO lookupstaticdao;

	private LookupStaticCache lookupStaticCache;

	public LookupstaticServiceImpl() {
		this.lookupStaticCache = LookupStaticCache.getInstance();
	}

	private Map<Long, Lookupstatic> lookupstatic = new ConcurrentHashMap<>();

	@Override
	@Transactional(readOnly = true)
	public List<Lookupstatic> getAllLookupstatics() {

		if (lookupstatic.isEmpty()) {
			Collection<Lookupstatic> drugs = lookupstaticdao.findAll();
			lookupstatic = drugs.stream().collect(Collectors.toMap(Lookupstatic::getId, dl -> dl));
		}
		return lookupstatic.entrySet().stream().map(es -> es.getValue()).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public void getAllLookupstaticsMap() {
		LookupType[] lookupTypes = LookupType.values();
		for (LookupType lookupType : lookupTypes) {
			List<Lookupstatic> lookupstatics = lookupstaticdao.findByProperty(Lookupstatic.FIELD_lookupType,
					lookupType.name());
			if (!CollectionUtils.isEmpty(lookupstatics)) {
				Map<Long, Lookupstatic> lookupStaticMap = lookupstatics.stream()
						.collect(Collectors.toMap(Lookupstatic::getLookupId, ls -> ls));
				lookupStaticCache.putListOfLookupstaticCache(lookupType, lookupStaticMap);
			}
		}

	}

	@Override
	@Transactional
	public List<Lookupstatic> getDiagnosticcenterTest(String type, String module) {
		List<Lookupstatic> lookupstatics = lookupstaticdao.getDiagnosticcenterTest(type, module);
		return lookupstatics;
	}
}
