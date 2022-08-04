package com.asgeek.books;

import com.asgeek.books.domain.config.Dto;
import com.asgeek.books.domain.service.ConfDtoService;
import com.asgeek.books.utils.DataMemoryApp;
import com.asgeek.books.utils.UtilException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BookMarketTApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookMarketTApplication.class, args);
	}

	private static final Logger logger = LoggerFactory.getLogger(BookMarketTApplication.class);
	@Autowired
	private ConfDtoService confDtoService;

	@Override
	public void run(String... args) {
		loadDataInMemory();
	}

	/**
	 * Método para guardart datos en Memoria
	 */
	private void loadDataInMemory(){
		try{
			logger.info("<<<<<<<<<<<<<<<<<<< Inicia proceso de cargar datos en Memoria");

			List<Dto> dtoNames = confDtoService.getDistinctDtoNames();
			Map<String, List<Map<String, String>>> infoDtos = new HashMap<>();
			Map<String, String> classDtos_w = new HashMap<>();

			for (Dto dto: dtoNames){
				List<Map<String, String>> infoDto = new ArrayList<>();

				// Obtener campos del DTO
				List<Dto> confDtos = confDtoService.getLisByDtoName(dto.getDtoName());
				for(Dto confDto: confDtos){
					Map<String, String> fieldDto = new HashMap<>();
					fieldDto.put("dtoName", confDto.getDtoName());
					fieldDto.put("dtoClassName", confDto.getDtoClasName());
					fieldDto.put("dtoField", confDto.getDtoField());
					fieldDto.put("dtoIsRequired", isTrue(confDto.getDtoIsRequired()));
					fieldDto.put("dtoIsObject", isTrue(confDto.getDtoIsObject()));
					fieldDto.put("dtoDatatype", confDto.getDtoDatatype());
					fieldDto.put("entityField", confDto.getEntityField());
					fieldDto.put("isMapper", isTrue(confDto.getIsMapper()));

					infoDto.add(fieldDto);
				}

				// Almacenar mapa de datos
				infoDtos.put(dto.getDtoName(), infoDto);
				classDtos_w.put(dto.getDtoName(), dto.getDtoClasName());
			}

			// Guardar datos en Memoria
			DataMemoryApp.getInstance().setDtos(infoDtos);
			DataMemoryApp.getInstance().setClassDtos(classDtos_w);

			logger.info("<<<<<<<<<<<<<<<<<<< Finaliza proceso de cargar datos en Memoria");
		}catch(UtilException ex){
			logger.error(ex.getMessage());
		}
	}

	/**
	 * Método para devolver S/N de acuerdo a un valor Booleano
	 * @param is: true or false
	 * @return String
	 */
	private String isTrue(Boolean is){
		if(is == null)
			return null;

		if (is.equals(true))
			return "S";

		return "N";
	}
}
