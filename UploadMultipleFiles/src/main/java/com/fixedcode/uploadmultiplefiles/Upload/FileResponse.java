package com.fixedcode.uploadmultiplefiles.Upload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {
    String fileName;
    String url;
}
