package com.edustar.media.domains.media.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDTO {

    @JsonProperty("mediaId")
    private Long id;

    @JsonProperty("mediaCategory")
    private String media_category;

    @JsonProperty("mediaUserRole")
    private String media_user_role;

    @JsonProperty("mediaUserIdentificationNumber")
    private String media_user_identification_number;

    @JsonProperty("mediaIsPublic")
    private Boolean media_is_public;

    @JsonProperty("mediaOriginFileName")
    private String media_origin_file_name;

    @JsonProperty("mediaMimeType")
    private String media_mime_type;

    @JsonProperty("mediaSize")
    private long media_size;

    @JsonProperty("mediaFileName")
    private String media_file_name;

    @JsonProperty("mediaFilePath")
    private String media_file_path;

    @JsonProperty("mediaCreatedAt")
    private Date media_created_at;

    @JsonProperty("mediaUpdatedAt")
    private Date media_updated_at;
}
