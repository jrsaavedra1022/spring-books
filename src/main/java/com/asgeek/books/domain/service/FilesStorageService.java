package com.asgeek.books.domain.service;

import com.asgeek.books.utils.UtilException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FilesStorageService {
    private final Path root = Paths.get("uploads");

    public void init() throws UtilException{
        try{
            Files.createDirectory(root);
        }catch (IOException ex){
            throw new UtilException("<<<<<<<< Error al inicializar carpeta uploads", "ERF001");
        }
    }

    public void save(MultipartFile file, String filename) throws UtilException{
        try{
            // Eliminar archivo si existe
            Path path = this.root.resolve(filename);
            Files.deleteIfExists(path);
            // Cargar nuevo archivo
            Files.copy(file.getInputStream(), path);
        }catch(Exception ex){
            throw new UtilException("<<<<<<<< No se pudo cargar el archivo. Error: " + ex.getMessage(), "ERF002");
        }
    }

    public Resource load(String filename) throws UtilException{
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if( resource.exists() || resource.isReadable() ){
                return resource;
            }else
                throw new UtilException("No se puede leer el archivo!", "ERF004");

        }catch (MalformedURLException ex){
            throw new UtilException("<<<<<<< Error cargando imagen: " + ex.getMessage(), "ERF003");
        }
    }
}
