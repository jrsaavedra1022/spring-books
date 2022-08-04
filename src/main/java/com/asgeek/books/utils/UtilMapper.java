package com.asgeek.books.utils;

import java.util.List;
import java.util.Map;

/**
 * Clase custom para el mapeo de entidades con DTO's
 * @author Jrsaavedra
 *
 */
public class UtilMapper {

    // Atributos
    private static UtilMapper utilMapper = null;
    private static final String DEFAULT_ERROR_CODE = "99999";
    private static final String IS_MAPPER = "isMapper";
    private static final String DTO_DATATYPE = "dtoDatatype";
    private static final String ENTITY_FIELD = "entityField";
    private static final String DTO_IS_OBJECT = "dtoIsObject";
    private static final String DTO_FIELD = "dtoField";
    private static final String DTO_CLASS_NAME = "dtoClassName";

    /**¨
     * constructor
     */
    private UtilMapper() {}

    /**
     * Singleton
     * @return UtilMapper
     */
    public static UtilMapper getInstance() {
        if(utilMapper == null)
            utilMapper = new UtilMapper();

        return utilMapper;
    }
    /**
     * Método para mappear una entidad a un DTO
     * @param objEntity: entity
     * @param dtoName: dto name
     * @return Object
     * @throws UtilException: ex
     */
    public Object mapperDTOfromEntity(Object objEntity, String dtoName) throws UtilException {
        String dtoClass = DataMemoryApp.getInstance().getClassName(dtoName);
        return mapperToDtoFromEntity(objEntity, dtoClass, dtoName);
    }

    /**
     * Método para pasar información de un Entity a un DTO
     * @param objEntity: entity
     * @param dtoClass: dto class name
     * @param dtoName: dto name
     * @return Object
     * @throws UtilException: ex
     */
    private Object mapperToDtoFromEntity(Object objEntity, String dtoClass, String dtoName) throws UtilException {
        try {
            // Obtenemos el objeto DTO a Operar
            Object dto = UtilClass.getInstance().getObject(dtoClass);

            // Obtenemos la lista de campos DTO a llenar
            List<Map<String, String>> fields = getListFieldByDTO(dtoName);

            for (Map<String, String> field_w : fields) {
                if (field_w.get(IS_MAPPER).equals("N"))
                    continue;

                boolean isBoolean = field_w.get(DTO_DATATYPE).equals("boolean");

                Object contentEntity = UtilClass.getInstance().getAttributeValue(objEntity, field_w.get(ENTITY_FIELD), isBoolean);
                if (field_w.get(DTO_IS_OBJECT).equals("N"))
                    dto = UtilClass.getInstance().setAttributeValue(dto, contentEntity, field_w.get(DTO_FIELD));
                else {
                    Object subDTO = mapperToDtoFromEntity(contentEntity, field_w.get(DTO_CLASS_NAME), field_w.get(DTO_DATATYPE));
                    dto = UtilClass.getInstance().setAttributeValue(dto, subDTO, field_w.get(DTO_FIELD));
                }
            }

            return dto;
        }catch (UtilException ex){
            throw new UtilException(ex.getMessage(), DEFAULT_ERROR_CODE);
        }catch(Exception ex) {
            throw new UtilException("<<<<< Error al mappear el DTO: " + dtoName, DEFAULT_ERROR_CODE);
        }
    }

    /**
     * Método para obtener el listado de campos DTO, a partir de un archivo de propiedades
     * @param dtoName: dto name
     * @return List
     * @throws UtilException: ex
     */
    private List<Map<String, String>> getListFieldByDTO(String dtoName) throws UtilException {
        try {
            return DataMemoryApp.getInstance().getListDto(dtoName);
        }catch(Exception ex) {
            throw new UtilException("<<<<< Error mapper al obtener el listado del DTO: " + dtoName, DEFAULT_ERROR_CODE);
        }
    }
}
