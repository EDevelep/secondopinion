package org.secondopinion.common.dto;

import java.math.BigInteger;

public class RatingsDTO {
	private Double totalRating;
	private BigInteger numberOfRatings;

	public Double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(Double totalRating) {
		this.totalRating = totalRating;
	}

	public BigInteger getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(BigInteger numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	public Double getAverage() {
		if(numberOfRatings == null || numberOfRatings.intValue() == 0) {
			return new Double(0);
		}
		return totalRating / numberOfRatings.intValue();
	}
}
