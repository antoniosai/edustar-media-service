package com.edustar.media.domains.media;

import com.edustar.core.utils.EduStarApiEntity;
import com.edustar.media.domains.media.dto.MediaDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MediaController {
    EduStarApiEntity<String> handleFileUpload(MediaDTO mediaDTO, MultipartFile file );
    ResponseEntity<Resource> getImage(String filename);
    ResponseEntity<Resource> deleteFile(String filename);
}
