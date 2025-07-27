package org.opri.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record HouseRecordDto(Long id, @NotEmpty String name, @NotNull Long noOfRooms, String city, String description,  Long surface) {
}
