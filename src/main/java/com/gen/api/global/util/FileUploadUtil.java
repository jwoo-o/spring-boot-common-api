package com.gen.api.global.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-05-11
 * Time: 오후 6:11
 */
@Component
public class FileUploadUtil {


    public String fileUpload(MultipartFile file, String path) throws Exception {

        File dir = new File(path);

        if (!dir.isDirectory()) {

            dir.mkdirs();

        }
        UUID uid = UUID.randomUUID();
        String name = System.currentTimeMillis() + "_" + uid;


        file.transferTo(new File(path,name));
        return name;

    }

    public void fileDelete(String fileName, String path) throws Exception {

        File file = new File(path,fileName);
        if (!file.delete())
            throw new IllegalArgumentException();
    }

    public String fileUploadOverwrite(MultipartFile file, String path) throws Exception {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        file.transferTo(new File(path,fileName));
        return fileName;
    }

}
