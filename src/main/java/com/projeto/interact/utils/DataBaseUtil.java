package com.projeto.interact.utils;

import com.projeto.interact.exceptions.RegisterException;

import java.util.List;

public class DataBaseUtil {
    public static void insertCheck(List<?> obj, String errorMessage)throws RegisterException {
        if(obj.size() != 0){
            throw new RegisterException(errorMessage);
        }
    }

}
