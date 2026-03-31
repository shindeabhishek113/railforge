package com.railforge.trainservice.dto.request;

import com.railforge.trainservice.model.enums.BerthType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BerthRequestDTO {
	
    @NotBlank(message = "Berth number is required")
	private String  berthNumber;
    
    @NotNull(message = "Berth type is required")
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
