package com.railforge.trainservice.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.railforge.trainservice.model.enums.CoachType;

public class CoachRequestDTO {

	private String coachNumber;
    private CoachType coachType;
    private Integer totalBerths;
    private BigDecimal fareMultiplier;

    private List<BerthRequestDTO> berths;

	public String getCoachNumber() {
		return coachNumber;
	}

	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}

	public CoachType getCoachType() {
		return coachType;
	}

	public void setCoachType(CoachType coachType) {
		this.coachType = coachType;
	}

	public Integer getTotalBerths() {
		return totalBerths;
	}

	public void setTotalBerths(Integer totalBerths) {
		this.totalBerths = totalBerths;
	}

	public BigDecimal getFareMultiplier() {
		return fareMultiplier;
	}

	public void setFareMultiplier(BigDecimal fareMultiplier) {
		this.fareMultiplier = fareMultiplier;
	}

	public List<BerthRequestDTO> getBerths() {
		return berths;
	}

	public void setBerths(List<BerthRequestDTO> berths) {
		this.berths = berths;
	}
}
