package com.servicer.app.utility.Impl;

import com.servicer.app.user.User;
import com.servicer.app.user.UserService;
import com.servicer.app.utility.ImageStorageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class ImageStorageImpl implements ImageStorageUtil {

    @Autowired
    private UserService userService;

    private final Path rootLocation = Paths.get("upload-dir");


    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void init() {
        try {

            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    @Override
    @Transactional
    public void store(MultipartFile file) {

        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String s = renameFiles(file.getOriginalFilename(), user.getId());
        try {

            Files.copy(file.getInputStream(), this.rootLocation.resolve(
                    s), StandardCopyOption.REPLACE_EXISTING);

            user.setImageUrl("http://localhost:8080/api/images/files/" + s);



        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }


    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public String renameFiles(String fileName, Long id) throws RuntimeException {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
            String s = StringUtils.getFilenameExtension(fileName);
            return id + "." + s;
        } else {

            throw new
                    RuntimeException("Invalid file format");
        }


    }
}
