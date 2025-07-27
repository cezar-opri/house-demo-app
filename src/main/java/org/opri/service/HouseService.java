package org.opri.service;

import lombok.RequiredArgsConstructor;
import org.opri.dto.HouseRecordDto;
import org.opri.persistance.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;

    private final HouseMapper houseMapper;

    public List<HouseRecordDto> findAll() {
        return houseRepository.findAll().stream()
                .map(houseMapper::toDto)
                .toList();
    }

    public void addHouse(HouseRecordDto houseRecordDto) {
        houseRepository.save(houseMapper.toEntity(houseRecordDto));
    }

}
