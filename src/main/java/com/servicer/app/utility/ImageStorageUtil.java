package com.servicer.app.utility;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface ImageStorageUtil {


    public void init();

    public void store(MultipartFile file) ;


    public Resource loadFile(String filename) ;

    public void deleteAll() ;


    public Stream<Path> loadAll() ;

    public String renameFiles(String fileName,Long  id) throws RuntimeException;


}

