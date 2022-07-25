package org.secondopinion.dao.hibernate.transformer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

public class ResultToOrderedMapTransformer implements ResultTransformer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7384390809235253045L;

	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
        Map<String, Object> result = new LinkedHashMap<String, Object>(tuple.length);
        for (int i = 0; i < tuple.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }
        return result;
    }
}