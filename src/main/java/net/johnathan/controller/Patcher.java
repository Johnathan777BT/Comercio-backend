package net.johnathan.controller;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import net.johnathan.model.Comerciantes;

@Component
public class Patcher {
    public static void internPatcher(Comerciantes existingIntern, Comerciantes incompleteIntern) throws IllegalAccessException {

        //GET THE COMPILED VERSION OF THE CLASS
        Class<?> internClass= Comerciantes.class;
        Field[] internFields=internClass.getDeclaredFields();
        System.out.println(internFields.length);
        for(Field field : internFields){
            System.out.println(field.getName());
            //CANT ACCESS IF THE FIELD IS PRIVATE
            field.setAccessible(true);

            //CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING INTERN
            Object value=field.get(incompleteIntern);
            if(value!=null){
                field.set(existingIntern,value);
            }
            //MAKE THE FIELD PRIVATE AGAIN
            field.setAccessible(false);
        }

    }

}