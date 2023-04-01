package com.edustar.media.domains.media;

import com.edustar.core.utils.EduStarApiEntity;
import com.edustar.media.core.dto.HttpResponseDTO;
import com.edustar.media.domains.media.dto.MediaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@Validated
@Slf4j
@RequestMapping("/media")
public class MediaControllerImpl implements MediaController {

    @Autowired
    private MediaService mediaService;

    @Override
    @PostMapping(value = "/upload", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public EduStarApiEntity<String> handleFileUpload(@ModelAttribute @Validated MediaDTO mediaDTO, @RequestParam("media_file") MultipartFile file ) {

        System.out.println("mediaDTO => " + mediaDTO);
        System.out.println("file => " + file);

        String fileName = this.mediaService.storeImage(mediaDTO, file);

        return new EduStarApiEntity<>("Success", fileName);
    }

    @Override
    @GetMapping(value = "/preview/{filename:.+}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = mediaService.loadImage(filename);

        System.out.println(file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(Objects.requireNonNull(file.getFilename()), Objects.requireNonNull(file.getFilename()));
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok()
                .headers(headers).body(file);
    }

    @Override
    @GetMapping(value = "/delete/{filename:.+}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> deleteFile(@PathVariable String filename) {
        Resource file = mediaService.loadImage(filename);

        System.out.println(file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(Objects.requireNonNull(file.getFilename()), Objects.requireNonNull(file.getFilename()));
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok()
                .headers(headers).body(file);
    }
}
