package ru.itpurplehack.avito.decepichupachapaticon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Location;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;
import ru.itpurplehack.avito.decepichupachapaticon.repository.LocationRepository;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public void changeJumps(int locationId, int matrixID, int jumps){
        Location location = locationRepository.findById(locationId).get();
        ////todo доделать
    }
}
