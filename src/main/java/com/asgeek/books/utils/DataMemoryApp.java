package com.asgeek.books.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase para guardar datos en memoria
 * @author Jrsaavedra
 *
 */
public class DataMemoryApp {

    /**
     * Atributos
     */
    private static DataMemoryApp dataMemoryApp = null;
    private Map<String, List<Map<String, String>>> dtos = null;
    private Map<String, String> classDtos = null;
    private final Map<String, String> responseCodes = new HashMap<>();

    /**
     * Constructor
     */
    private DataMemoryApp() throws UtilException {
        try {
            // Inicializar datos en memoria
            responseCodes.put("REQF01", "El campo es obligatorio");

        }catch(Exception ex) {
            throw new UtilException("<<<<<<<< Error al cargar datos en Memoria", "ERM999");
        }
    }

    /**
     * Singleton
     * @return DataMemoryApp
     * @throws UtilException: ex
     */
    public static DataMemoryApp getInstance() throws UtilException {
        if(dataMemoryApp == null)
            dataMemoryApp = new DataMemoryApp();

        return dataMemoryApp;
    }


    // Métodos de implementación
    public List<Map<String, String>> getListDto(String dtoName){
        List<Map<String, String>> list = new ArrayList<>();
        if(dtos.containsKey(dtoName))
            list = dtos.get(dtoName);
        return list;
    }

    public void setDtos(Map<String, List<Map<String, String>>> dtos) {
        this.dtos = dtos;
    }

    public String getResponseCode(String code) {
        return responseCodes.get(code);
    }
    public String getClassName(String dtoName) {
        return classDtos.get(dtoName);
    }

    public void setClassDtos(Map<String, String> classDtos) {
        this.classDtos = classDtos;
    }

}
