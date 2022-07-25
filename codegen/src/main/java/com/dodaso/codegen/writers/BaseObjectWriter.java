package com.dodaso.codegen.writers;

import com.dodaso.codegen.ObjectWriter;

abstract class BaseObjectWriter implements ObjectWriter{
	private boolean usingAnnotations = true;
	public boolean isUsingAnnotations() {
		return usingAnnotations;
	}

	public void setUsingAnnotations(boolean usingAnnotations) {
		this.usingAnnotations = usingAnnotations;
	}
}
