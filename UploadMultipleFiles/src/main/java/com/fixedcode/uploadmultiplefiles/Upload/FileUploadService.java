package com.fixedcode.uploadmultiplefiles.Upload;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileUploadService implements IFileUploadService{
    private final Path rootDir = Paths.get("uploads");
    @Override
    public void init() {
        try
        {
            File tempDir = new File(rootDir.toUri());
            boolean dirExists = tempDir.exists();
            if(!dirExists)
            {
                Files.createDirectory(rootDir);
            }
        }
        catch(IOException e)
        {
            throw new RuntimeException("Error creating directory");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try
        {
            Files.copy(file.getInputStream(), this.rootDir.resolve(
                    Objects.requireNonNull(file.getOriginalFilename())
            ));
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file" + e.getMessage());
        }
    }

    @Override
    public Resource getFileByName(String fileName) {
        try
        {
            Path filePath = rootDir.resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() && resource.isReadable())
            {
                return resource;
            }
            else {
                throw new RuntimeException("Could not read file.");
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Error: "+
                    e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootDir.toFile());
    }

    @Override
    public Stream<Path> loadAllFiles() {
        try
        {
           return Files.walk(this.rootDir, 1)
                    .filter(path -> !path.equals(this.rootDir))
                    .map(this.rootDir :: relativize);
        }
        catch(IOException e)
        {
            throw new RuntimeException("could not load files");
        }
    }
}
