/**
 * 
 */
package org.secondopinion.dao.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * @author JYKIM
 *
 */
public class DaoUtil {

	/**
	 * Breaks a collection into multiple lists based on the batchSize.  The last list
	 * might contain fewer elements than the batch size if the remainder of objects 
	 * size to batch size is greater than zero.
	 * 
	 * @param <C>
	 * @param objects
	 * @param batchSize
	 * @return
	 */
	public static <C extends Object> List<List<C>> createBatches(Collection<C> collection, int batchSize) {
		if (batchSize < 1)
			throw new IllegalArgumentException("Batch size cannot be zero or less");
		
		List<C> objects = new ArrayList<C>(collection);
		int noOfBatches = (int) Math.ceil ((double) objects.size() / batchSize);
		List<List<C>> batches = new ArrayList<List<C>>(noOfBatches);		
		int index = 0;
		for (int i = 0; i < noOfBatches; i++) {
			List<C> list = new ArrayList<C>(batchSize);			
			for (int j = 0; j < batchSize && index < objects.size(); j++, index++) {
				list.add(objects.get(index));
			}
			batches.add(list);
		}
		return batches;		
	}

	public static <C extends Object> String generateInQuery(
			Collection<C> ids, 
			String idFieldName) {
		return generateInQuery(ids, idFieldName, false);
	}
	
	public static <C extends Object> String generateInQuery(
			Collection<C> ids, 
			String idFieldName,
			boolean withQuotes) 
	{
		if (CollectionUtils.isEmpty(ids)) return "";
		
		String query = "";
		List<List<C>> batches = DaoUtil.createBatches(ids, 800);
		for (int i = 0; i < batches.size(); i++) {
			List<C> batch = batches.get(i);
			StringBuilder inQuery = new StringBuilder(idFieldName + " IN (");
			for (int j = 0; j < batch.size(); j++) {
				C id = batch.get(j);
				
				if (id.toString().trim().length() == 0) continue;
				
				inQuery.append(withQuotes? "'" + id.toString().trim() + "'" : id.toString());
				if (j + 1 == batch.size())
					inQuery.append(")");
				else
					inQuery.append(", ");
			}
			query += "(" + inQuery.toString() + ")";
			if (i + 1 != batches.size())
				query += " OR ";
		}
		return "(" + query + ")";
	}
	
}
