package ru.itpurplehack.avito.decepichupachapaticon.service;

import org.springframework.stereotype.Service;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.Microcategory;
import ru.itpurplehack.avito.decepichupachapaticon.entity.tree.MicrocategoryJump;
import ru.itpurplehack.avito.decepichupachapaticon.repository.MicrocategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MicrocategoryService {
    private final MicrocategoryRepository microcategoryRepository;

    public MicrocategoryService(MicrocategoryRepository microcategoryRepository) {
        this.microcategoryRepository = microcategoryRepository;
    }

    public void saveJumps(int nodeId, List<MicrocategoryJump> microcategoryJumps) {
        Microcategory microcategory = microcategoryRepository.findById(nodeId).get();
        microcategory.setMicrocategoryJumps(microcategoryJumps);
        microcategoryRepository.save(microcategory);
    }

    public void changeJump(int categoryId, int matrixID, int height) {
        Microcategory microcategory = microcategoryRepository.findById(categoryId).get();
        for (MicrocategoryJump jump : microcategory.getMicrocategoryJumps()) {
            if (jump.getDiscountMatrixId() == matrixID) {
                jump.setDistanceToNearest(height);
                microcategoryRepository.save(microcategory);
                return;
            }
        }
        microcategory.getMicrocategoryJumps().add(new MicrocategoryJump(matrixID, height));
        microcategoryRepository.save(microcategory);
    }

    public Optional<MicrocategoryJump> findJumpByMatrix(Microcategory microcategory, int matrixId) {
        for (MicrocategoryJump jump : microcategory.getMicrocategoryJumps()) {
            if (jump.getDiscountMatrixId() == matrixId) {
                return Optional.of(jump);
            }
        }
        return Optional.empty();
    }

    public void deleteJump(int categoryId, int matrixId){
        Microcategory microcategory = microcategoryRepository.findById(categoryId).get();
        for(MicrocategoryJump jump: microcategory.getMicrocategoryJumps()){
            if (jump.getDiscountMatrixId() == matrixId){
                microcategory.getMicrocategoryJumps().remove(jump);
            }
        }
        microcategoryRepository.save(microcategory);
    }
}
