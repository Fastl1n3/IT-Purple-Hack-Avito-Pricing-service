package ru.itpurplehack.avito.decepichupachapaticon.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.*;
import ru.itpurplehack.avito.decepichupachapaticon.repository.MicrocategoryRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MicrocategoryController {

    private final MicrocategoryRepository microcategoryRepository;

    @Autowired
    public MicrocategoryController(MicrocategoryRepository microcategoryRepository) {
        this.microcategoryRepository = microcategoryRepository;
    }

    @GetMapping("/microcategories")
    public RootMicrocategoryResponse getRootMicrocategory() {
        Microcategory microcategory = microcategoryRepository.findRootNode();
        return new RootMicrocategoryResponse(microcategory.getId(), microcategory.getName());
    }

    @GetMapping("/microcategories/{mc_id}")
    public NextMicrocategoryResponse getNextLocations(@PathVariable("mc_id") int locationId) {
        Optional<Microcategory> microcategory = microcategoryRepository.findById(locationId);
        if (microcategory.isPresent()) {
            return new NextMicrocategoryResponse(locationId, microcategory.get().getName(),
                    microcategory.get().getChildMicrocategories().stream().map(o -> new MicrocategoryResponse(o.getId(), o.getName())).collect(Collectors.toList()));
        }
        return new NextMicrocategoryResponse(locationId, "", null);
    }
}
