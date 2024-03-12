package ru.itpurplehack.avito.decepichupachapaticon.Leontev.services;

import org.springframework.stereotype.Component;
import ru.itpurplehack.avito.decepichupachapaticon.Leontev.IdType;

import java.util.ArrayList;
import java.util.List;

@Component
public class TreeTableService {
    public int getRootId(IdType treeType){
        return 1;
    }
    public List<Integer> getNodeChildren(int nodeId, IdType treeType){
        return new ArrayList<>();
    }
}
