package ru.itpurplehack.avito.decepichupachapaticon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.*;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Location;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.LocationJump;
import ru.itpurplehack.avito.decepichupachapaticon.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void saveJumps(int nodeId, List<LocationJump> locationJumps) {
        Location location = locationRepository.findById(nodeId).get();
        location.setLocationJumps(locationJumps);
        locationRepository.save(location);
    }

    public void changeJump(int locationId, int matrixID, int height) {
        Location location = locationRepository.findById(locationId).get();
        for (LocationJump jump : location.getLocationJumps()) {
            if (jump.getDiscountMatrixId() == matrixID) {
                jump.setDistanceToNearest(height);
                locationRepository.save(location);
                return;
            }
        }
        location.getLocationJumps().add(new LocationJump(matrixID, height));
        locationRepository.save(location);
    }

    public Optional<LocationJump> findJumpByMatrix(Location location, int matrixId) {
        for (LocationJump jump : location.getLocationJumps()) {
            if (jump.getDiscountMatrixId() == matrixId) {
                return Optional.of(jump);
            }
        }
        return Optional.empty();
    }

    public void deleteJump(int locationId, int matrixId){
        Location location = locationRepository.findById(locationId).get();
        for(LocationJump jump: location.getLocationJumps()){
            if (jump.getDiscountMatrixId() == matrixId){
                location.getLocationJumps().remove(jump);
            }
        }
        locationRepository.save(location);
    }
}
