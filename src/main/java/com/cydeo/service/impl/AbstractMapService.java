package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService <T, ID>{

    //custom "fake" DB :)
    public Map<ID,T> map = new HashMap<>();


    T findById(ID id){
        return map.get(id);
    }

    List<T>findAll(){
        return new ArrayList<>(map.values());
    }

    T save(ID id, T object){
        map.put(id, object);
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }




}
