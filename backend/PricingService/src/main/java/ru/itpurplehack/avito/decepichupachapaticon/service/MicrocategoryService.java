package ru.itpurplehack.avito.decepichupachapaticon.service;

import org.springframework.stereotype.Service;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.MicrocategoryJump;
import ru.itpurplehack.avito.decepichupachapaticon.repository.MicrocategoryRepository;

import java.util.List;

@Service
public class MicrocategoryService {
    private final MicrocategoryRepository microcategoryRepository;

    public MicrocategoryService(MicrocategoryRepository microcategoryRepository) {
        this.microcategoryRepository = microcategoryRepository;
    }

    public void saveJumps(int nodeId, List<MicrocategoryJump> microcategoryJumps){
        Microcategory microcategory = microcategoryRepository.findById(nodeId).get();
        microcategory.setMicrocategoryJumps(microcategoryJumps);
        microcategoryRepository.save(microcategory);
    }

    public void changeJumps(int categoryId, int matrixID, int jumps){
        Microcategory microcategory = microcategoryRepository.findById(categoryId).get();
        ////todo доделать
    }
}
