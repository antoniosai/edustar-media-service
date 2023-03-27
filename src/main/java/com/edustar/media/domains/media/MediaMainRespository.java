package com.edustar.media.domains.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaMainRespository extends JpaRepository<MediaEntity, Long>, MediaRespository {
}
