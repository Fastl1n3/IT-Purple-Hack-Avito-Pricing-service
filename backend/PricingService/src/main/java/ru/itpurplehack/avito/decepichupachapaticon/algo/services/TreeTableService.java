package ru.itpurplehack.avito.decepichupachapaticon.algo.services;

import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.algo.IdType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TreeTableService {
    //dao для бд
    public int getRootId(IdType treeType){
        return 1;
    }
    public List<Integer> getNodeChildren(int nodeId, IdType treeType){
        return new ArrayList<>();
    }
    public Optional<Integer> getParent(int nodeId, IdType treeType){
        return null;
    }
}
