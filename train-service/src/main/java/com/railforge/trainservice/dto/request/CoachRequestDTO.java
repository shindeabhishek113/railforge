package com.railforge.trainservice.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.railforge.trainservice.model.enums.CoachType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for creating a new coach")
public class CoachRequestDTO {

	@Schema(description = "Coach number", example = "S1")
    private String coachNumber;

    @Schema(description = "Type of coach", example = "SLEEPER")
    private CoachType coachType;

    @Schema(description = "Total number of berths in the coach", example = "72")
    private Integer totalBerths;

    @Schema(description = "Fare multiplier for this coach type", example = "1.5")
    private BigDecimal fareMultiplier;

    @Schema(description = "List of berths in the coach")
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
