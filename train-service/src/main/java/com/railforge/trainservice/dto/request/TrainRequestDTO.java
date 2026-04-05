package com.railforge.trainservice.dto.request;

import java.util.List;

import com.railforge.trainservice.model.enums.DayOfWeek;
import com.railforge.trainservice.model.enums.TrainStatus;
import com.railforge.trainservice.model.enums.TrainType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for creating a new train")
public class TrainRequestDTO {

	@Schema(description = "Train name", example = "Rajdhani Express")
    private String trainName;

    @Schema(description = "Type of train", example = "EXPRESS")
    private TrainType trainType;

    @Schema(description = "Current status of the train", example = "ACTIVE")
    private TrainStatus status;

    @Schema(description = "Origin station", example = "Mumbai Central")
    private String originStation;

    @Schema(description = "Destination station", example = "New Delhi")
    private String destinationStation;

    @Schema(description = "Days on which train runs", example = "[MONDAY, WEDNESDAY]")
    private List<DayOfWeek> runningDays;

    @Schema(description = "List of coaches in the train")
    private List<CoachRequestDTO> coaches;

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public TrainType getTrainType() {
		return trainType;
	}

	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}

	public TrainStatus getStatus() {
		return status;
	}

	public void setStatus(TrainStatus status) {
		this.status = status;
	}

	public String getOriginStation() {
		return originStation;
	}

	public void setOriginStation(String originStation) {
		this.originStation = originStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public List<DayOfWeek> getRunningDays() {
		return runningDays;
	}

	public void setRunningDays(List<DayOfWeek> runningDays) {
		this.runningDays = runningDays;
	}

	public List<CoachRequestDTO> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<CoachRequestDTO> coaches) {
		this.coaches = coaches;
	}
}
