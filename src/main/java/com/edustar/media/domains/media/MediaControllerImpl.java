package com.edustar.media.domains.media;

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

@RestController
@Validated
@Slf4j
@RequestMapping("/media")
public class MediaControllerImpl implements MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping(value = "/upload", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpResponseDTO> handleFileUpload(@ModelAttribute @Validated MediaDTO mediaDTO, @RequestParam("media_file") MultipartFile file ) {

        System.out.println("mediaDTO => " + mediaDTO);
        System.out.println("file => " + file);

        String fileName = this.mediaService.storeImage(mediaDTO, file);

        HttpResponseDTO httpResponseDTO = new HttpResponseDTO("File uploaded Successfully", fileName);

        return new ResponseEntity<HttpResponseDTO>(httpResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = mediaService.loadImage(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
