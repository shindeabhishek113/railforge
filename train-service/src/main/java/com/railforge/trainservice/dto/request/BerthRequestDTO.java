package com.railforge.trainservice.dto.request;

import com.railforge.trainservice.model.enums.BerthType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request body for creating a new berth")
public class BerthRequestDTO {
	
    @NotBlank(message = "Berth number is required")
    @Schema(description = "Berth number", example = "LB-1")
	private String  berthNumber;
    
    @NotNull(message = "Berth type is required")
    @Schema(description = "Type of berth", example = "LOWER")
    private BerthType berthType;

	public String getBerthNumber() {
		return berthNumber;
	}

	public void setBerthNumber(String berthNumber) {
		this.berthNumber = berthNumber;
	}

	public BerthType getBerthType() {
		return berthType;
	}

	public void setBerthType(BerthType berthType) {
		this.berthType = berthType;
	}
}
