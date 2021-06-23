package br.com.restwith.services;

import br.com.restwith.config.FileStorageConfig;
import br.com.restwith.exception.FileStoregeException;

import br.com.restwith.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new FileStoregeException("Could not create directory", e);
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains(".."))throw new FileStoregeException("Invalid path"+fileName);

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch (Exception e){
            throw new FileStoregeException("Could not store file"+fileName+" Please try again ", e);
        }
    }

    public Resource loadFileResource(String fileName){
        try {
            Path filPath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filPath.toUri());

            if(resource.exists()){
                return resource;
            }else throw new MyFileNotFoundException("file not found");
        }catch (Exception e){
            throw new MyFileNotFoundException("file not found", e);
        }
    }
}
