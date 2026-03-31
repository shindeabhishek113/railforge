package com.railforge.trainservice.dto.request;

import java.util.List;

import com.railforge.trainservice.model.enums.DayOfWeek;
import com.railforge.trainservice.model.enums.TrainStatus;
import com.railforge.trainservice.model.enums.TrainType;

public class TrainRequestDTO {

	private String trainNumber;
    private String trainName;
    private TrainType trainType;
    private TrainStatus status;

    private String originStation;
    private String destinationStation;

    private List<DayOfWeek> runningDays;

    private List<CoachRequestDTO> coaches;

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

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
