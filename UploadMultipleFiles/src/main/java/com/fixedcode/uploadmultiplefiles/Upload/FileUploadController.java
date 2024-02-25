package com.fixedcode.uploadmultiplefiles.Upload;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload-files")
    public ResponseEntity<FileMessageResponse> uploadFiles(@RequestParam("file") MultipartFile[] files)
    {
        //postman -> body -> form data with type file and file name wih value file
        String message = "";
        try
        {
            ArrayList<String> fileName = new ArrayList<>();
            Arrays.stream(files)
                    .forEach(file -> {fileUploadService.save(file);
                    fileName.add(file.getOriginalFilename());}
            );
            message = "File(s) uploaded successfully." + fileName;
            return ResponseEntity.status(OK).body(new FileMessageResponse(message));
        }
        catch(Exception e)
        {
             return ResponseEntity.status(EXPECTATION_FAILED).body(new FileMessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> getFileByName(@PathVariable String fileName) {
        Resource resource = fileUploadService.getFileByName(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filaName=\"" + resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/all-files")
    public ResponseEntity<List<FileResponse>> loadAllFiles()
    {
        List<FileResponse> files = fileUploadService.loadAllFiles()
                .map(
                        path -> {
                            String fileName = path.getFileName().toString();
                            String url = MvcUriComponentsBuilder
                                    .fromMethodName(FileUploadController.class, "getFileByName",
                                            path.getFileName().toString()).build().toString();
                            return new FileResponse(fileName, url);
                        }
                ).toList();
        return ResponseEntity.status(OK).body(files);
    }
    @DeleteMapping("/delete-all-files")
    public ResponseEntity<FileMessageResponse> deleteAllFiles()
    {
        try
        {
            fileUploadService.deleteAll();
            return ResponseEntity.status(OK).body(new FileMessageResponse("All Files deleted successfully"));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(EXPECTATION_FAILED).body(new FileMessageResponse(e.getMessage()));
        }
    }

}
