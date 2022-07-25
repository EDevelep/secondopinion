package org.secondopinion.batch;

import java.util.Collection;

public interface IBatchDequeCallBack<T> {

	public void processCollection(Collection<T> batchedData);
}