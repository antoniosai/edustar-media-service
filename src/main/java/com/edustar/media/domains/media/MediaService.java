package com.edustar.media.domains.media;

import com.edustar.media.domains.media.dto.MediaDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    public void init();

    public String storeImage(MediaDTO mediaEntity, MultipartFile file);

    public Resource loadImage(String fileName);
}
