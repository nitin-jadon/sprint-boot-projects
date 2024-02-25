package com.fixedcode.uploadmultiplefiles.Upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileUploadService {
    public void init();
    public void save(MultipartFile file);
    public Resource getFileByName(String fileName);
    public void deleteAll();
    Stream<Path> loadAllFiles();
}
