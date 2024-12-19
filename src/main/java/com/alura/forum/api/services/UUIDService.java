package com.alura.forum.api.services;

import java.util.UUID;

public class UUIDService {
    public static UUID valideUUID(String strId){
        try{
            return UUID.fromString(strId);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("UUID invalido: " + strId);
        }
    }
}
