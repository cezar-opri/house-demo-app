package org.opri.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.opri.dto.HouseRecordDto;
import org.opri.service.HouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;


    @GetMapping
    public ResponseEntity<List<HouseRecordDto>> getHouses(){
        return ResponseEntity.ok().body(houseService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createHouse(@RequestBody @Valid HouseRecordDto houseDto){
        System.out.println("Received: " + houseDto);
        houseService.addHouse(houseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(houseDto.toString());
    }
}
