package com.asgeek.books.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase para la validación de campos DTO
 * @author Jrsaavedra
 *
 */
public class UtilFieldValidation {

    // Atributos
    private static UtilFieldValidation utilFieldValidation = null;
    private static final String DEFAULT_ERROR_CODE = "99999";
    private static final String DTO_DATA_TYPE = "dtoDatatype";
    private static final String DTO_FIELD = "dtoField";
    private static final String DTO_IS_OBJECT = "dtoIsObject";
    private static final String DTO_IS_REQUIRED = "dtoIsRequired";
    private static final String ERROR_CODE_REQUIRED_FIELD = "REQF01";

    /**¨
     * constructor
     */
    private UtilFieldValidation() {}

    /**
     * Singleton
     * @return UtilFieldValidation
     */
    public static UtilFieldValidation getInstance() {
        if(utilFieldValidation == null)
            utilFieldValidation = new UtilFieldValidation();

        return utilFieldValidation;
    }

    /**
     * Método para la validación de campos DTO
     * @param objDTO: object dto
     * @param dtoName: dto name
     * @return List
     * @throws UtilException: ex
     */
    public List<Object> inputFieldValidation(Object objDTO, String dtoName) throws UtilException {
        return dtoInputFieldValidation(objDTO, dtoName, null);
    }

    /**
     * Método para retornar la respuesta de error indicada para un campo
     * @param field: fieldname
     * @param property: property to get message
     * @return Object
     * @throws UtilException: ex
     */
    private Object errorResponse(String field, String property) throws UtilException {

        String message = DataMemoryApp.getInstance().getResponseCode(property);
        Map<String, String> error = new HashMap<>();
        error.put("field", field);
        error.put("message", message);

        return error;
    }

    /**
     * Método para la validación de campos de entrada de un DTO
     * @param objDTO: object dto
     * @param dtoName: dto name
     * @return List
     * @throws UtilException: ex
     */
    private List<Object> dtoInputFieldValidation(Object objDTO, String dtoName, String subDto) throws UtilException {
        try {
            List<Object> errors = new ArrayList<>();

            // Obtenemos la lista de campos DTO a llenar
            List<Map<String, String>> fields = getListFieldByDTO(dtoName);

            for(Map<String, String> field_w : fields) {
                if(field_w.get(DTO_IS_REQUIRED).equals("N"))
                    continue;

                boolean isBoolean = field_w.get(DTO_DATA_TYPE).equals("boolean");

                Object contentDTO = UtilClass.getInstance().getAttributeValue(objDTO, field_w.get(DTO_FIELD), isBoolean);
                if(field_w.get(DTO_IS_OBJECT).equals("N")) {
                    if(fieldIsNull(contentDTO, field_w.get(DTO_DATA_TYPE))) {
                        String field = field_w.get(DTO_FIELD).substring(0,1).toLowerCase() + field_w.get(DTO_FIELD).substring(1);
                        errors.add(errorResponse(((subDto == null) ? field : subDto + "." + field), ERROR_CODE_REQUIRED_FIELD));
                    }
                }else {
                    List<Object> subErrors = dtoInputFieldValidation(contentDTO, field_w.get(DTO_DATA_TYPE), field_w.get(DTO_FIELD));
                    errors.addAll(subErrors);
                }
            }

            return errors;
        }catch(UtilException ex){
            throw new UtilException(ex.getMessage(), DEFAULT_ERROR_CODE);
        } catch(Exception ex) {
            throw new UtilException("<<<<< Error al validar campos de entrada del DTO: " + dtoName, DEFAULT_ERROR_CODE);
        }
    }

    /**
     * Método de validación de campos Null
     * @param field: field
     * @return boolean
     */
    private boolean fieldIsNull(Object field, String dataType) {
        String value = null;
        // Validacion de campos de tipo string
        if(dataType.equals("String"))
            value = (String) field;

        // Validación de campos de tipo Integer
        if(dataType.equals("int") || dataType.equals("Integer") || dataType.equals("double")) {
            value = String.valueOf(field);
            if(value.equals("0"))
                return true;
        }
        // Validación de camspo de tipo fecha
        if(dataType.equals("LocalDateTime"))
            value = String.valueOf(field);

        // Validacion de campos boolean y uuid
        if(dataType.equals("boolean") || dataType.equals("UUID"))
            value = String.valueOf(field);

        if(value == null)
            return true;

        return value.length() == 0;
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
            throw new UtilException("<<<<< Error FieldValidation al obtener el listado del DTO: " + dtoName, DEFAULT_ERROR_CODE);
        }
    }
}
