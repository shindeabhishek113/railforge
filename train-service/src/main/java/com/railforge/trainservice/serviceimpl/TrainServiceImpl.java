package com.railforge.trainservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railforge.trainservice.dto.request.CoachRequestDTO;
import com.railforge.trainservice.dto.request.TrainRequestDTO;
import com.railforge.trainservice.dto.response.TrainSummaryDTO;
import com.railforge.trainservice.entity.model.entities.Coach;
import com.railforge.trainservice.entity.model.entities.Train;
import com.railforge.trainservice.exception.handler.AppException;
import com.railforge.trainservice.repository.TrainJpaRepository;
import com.railforge.trainservice.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private TrainJpaRepository trainJpaRepository;

	@Override
	public Long addTrain(TrainRequestDTO trainRequestDTO) {
		Train train = new Train();
		train.setTrainName(trainRequestDTO.getTrainName());
		train.setTrainType(trainRequestDTO.getTrainType());
		train.setStatus(trainRequestDTO.getStatus());
		train.setOriginStation(trainRequestDTO.getOriginStation());
		train.setDestinationStation(trainRequestDTO.getDestinationStation());
		train.setRunningDays(trainRequestDTO.getRunningDays());
		
		List<Coach> coachs = new ArrayList<>();
		for (CoachRequestDTO coachRequestDTO : trainRequestDTO.getCoaches()) {
			Coach coach = new Coach();
			coach.setCoachType(coachRequestDTO.getCoachType());
			coach.setCoachNumber(coachRequestDTO.getCoachNumber());
			coach.setFareMultiplier(coachRequestDTO.getFareMultiplier());
			coach.setTotalBerths(coachRequestDTO.getTotalBerths());
			coach.setTrain(train);
			coachs.add(coach);
		}
		
		train.setCoaches(coachs);
				
		trainJpaRepository.save(train);
		return train.getId();
	}

	@Override
	public List<TrainSummaryDTO> getTrainList() {
		List<Train> trainList = trainJpaRepository.findAll();
		List<TrainSummaryDTO> trainSummaryDTOList = new ArrayList<>();
		for(Train train : trainList) {
			TrainSummaryDTO trainSummaryDTO = new TrainSummaryDTO();
			trainSummaryDTO.setTrainName(train.getTrainName());
			trainSummaryDTO.setOriginStation(train.getOriginStation());
			trainSummaryDTO.setDestinationStation(train.getDestinationStation());
			trainSummaryDTOList.add(trainSummaryDTO);
		}
		return trainSummaryDTOList;
	}

	@Override
	public TrainSummaryDTO getTrainObject(long id) throws Exception {
		Optional<Train> trainOptional = trainJpaRepository.findById(id);
		if(trainOptional.isEmpty()) {
			throw AppException.notFound("Invalid trainId: " + id);
		}
		Train train = trainOptional.get();
		TrainSummaryDTO trainSummaryDTO = new TrainSummaryDTO();
		trainSummaryDTO.setId(train.getId());
		trainSummaryDTO.setTrainName(train.getTrainName());
		trainSummaryDTO.setOriginStation(train.getOriginStation());
		trainSummaryDTO.setDestinationStation(train.getDestinationStation());
		return trainSummaryDTO;
	}
}
