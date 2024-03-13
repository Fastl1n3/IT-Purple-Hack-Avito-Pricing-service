package ru.itpurplehack.avito.decepichupachapaticon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Location;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.LocationResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.NextLocationResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.RootLocationResponse;
import ru.itpurplehack.avito.decepichupachapaticon.repository.LocationRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LocationsController {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationsController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/locations")
    public RootLocationResponse getRootLocation() {
        Location location = locationRepository.findLocationByParentLocationIsNull();
        return new RootLocationResponse(location.getId(), location.getName());
    }

    @GetMapping("/locations/{lc_id}")
    public NextLocationResponse getNextLocations(@PathVariable("lc_id") int locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isPresent()) {
            return new NextLocationResponse(locationId, location.get().getName(),
                    location.get().getChildLocations().stream().map(o -> new LocationResponse(o.getId(), o.getName())).collect(Collectors.toList()));
        }
        return new NextLocationResponse(locationId, "", null);
    }

}
