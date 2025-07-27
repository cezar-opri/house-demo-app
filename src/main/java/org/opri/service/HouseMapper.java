package org.opri.service;

import org.mapstruct.Mapper;
import org.opri.dto.HouseRecordDto;
import org.opri.persistance.entity.House;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    HouseRecordDto toDto(House house);

    List<HouseRecordDto> toDto(List<House> houses);

    House  toEntity(HouseRecordDto dto);

}
