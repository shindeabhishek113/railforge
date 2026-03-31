package com.railforge.trainservice.service;

import java.util.List;

import com.railforge.trainservice.dto.request.TrainRequestDTO;
import com.railforge.trainservice.dto.response.TrainSummaryDTO;

public interface TrainService {
	Long addTrain(TrainRequestDTO trainRequestDTO);
	List<TrainSummaryDTO> getTrainList();
}
