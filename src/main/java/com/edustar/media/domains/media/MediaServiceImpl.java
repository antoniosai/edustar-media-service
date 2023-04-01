package com.edustar.media.domains.media;

import com.edustar.core.utils.ObjectMapperUtil;
import com.edustar.media.domains.media.dto.MediaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaMainRespository mediaMainRespository;

    private final String storagePath = "/home/antoniosai/Code/edustar-app/storage/media";

    private final Path root = Paths.get(storagePath);

    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(storagePath));
            Files.createDirectories(Paths.get(storagePath + "/assessment"));
            Files.createDirectories(Paths.get(storagePath + "/assignment"));
            Files.createDirectories(Paths.get(storagePath + "/master-data"));
            Files.createDirectories(Paths.get(storagePath + "/evaluation"));
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String storeImage(MediaDTO mediaDTO, MultipartFile file) {
        UUID uuid = UUID.randomUUID();

        try {
            Path root = Paths.get(storagePath);

            String extension = file.getOriginalFilename().split("\\.")[1];

            String newFileName = uuid + "." + extension;

            Files.copy(file.getInputStream(), root.resolve(Objects.requireNonNull(newFileName)));

            MediaEntity mediaData = ObjectMapperUtil.map(mediaDTO, MediaEntity.class);
//            mediaData.setMedia_id(null);
            mediaData.setMedia_file_name(newFileName);
            mediaData.setMedia_file_path(storagePath);
            mediaData.setMedia_mime_type(file.getContentType());
            mediaData.setMedia_size(file.getSize());
            mediaData.setMedia_origin_file_name(file.getOriginalFilename());
            mediaMainRespository.saveAndFlush(mediaData);

            return newFileName;
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource loadImage(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
