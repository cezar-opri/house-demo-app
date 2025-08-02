package org.opri.service;

import lombok.RequiredArgsConstructor;
import org.opri.dto.HouseRecordDto;
import org.opri.persistance.HouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;

    private final HouseMapper houseMapper;

    public Page<HouseRecordDto> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return houseRepository.findAll(pageable)
                .map(houseMapper::toDto);
    }

    public void addHouse(HouseRecordDto houseRecordDto) {
        houseRepository.save(houseMapper.toEntity(houseRecordDto));
    }

}
