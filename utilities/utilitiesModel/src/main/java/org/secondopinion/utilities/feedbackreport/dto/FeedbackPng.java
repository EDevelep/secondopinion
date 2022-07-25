package org.secondopinion.utilities.feedbackreport.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.utilities.feedbackreport.domain.BaseFeedbackPng;

@Entity
@Table(name = "feedbackpng")
public class FeedbackPng extends BaseFeedbackPng {
}