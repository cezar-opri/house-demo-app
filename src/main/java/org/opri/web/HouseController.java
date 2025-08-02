package org.opri.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.opri.dto.HouseRecordDto;
import org.opri.service.HouseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;


    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    // Allow access to users with USER or ADMIN roles
    public ResponseEntity<Page<HouseRecordDto>> getHouses(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "10") int size,
                                                          @RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
        return ResponseEntity.ok().body(houseService.findAll(page, size, sortBy));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create a house
    public ResponseEntity<String> createHouse(@RequestBody @Valid HouseRecordDto houseDto){
        System.out.println("Received: " + houseDto);
        houseService.addHouse(houseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(houseDto.toString());
    }

    @GetMapping("/super-secret")
    @PreAuthorize("hasRole('SUPER_ADMIN')") // Only SUPER_ADMIN can access
    public ResponseEntity<String> onlyForSuperAdmin() {
        return ResponseEntity.ok("Top secret");

    }
}

