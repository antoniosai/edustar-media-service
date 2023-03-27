package com.edustar.media.domains.media;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "media", schema = "public"
//    uniqueConstraints = {
//            @UniqueConstraint(columnNames = "media_origin_file_name"),
//    }
)
public class MediaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String media_category;

    private String media_user_role;

    private String media_user_identification_number;

    private Boolean media_is_public;

    private String media_origin_file_name;

    private String media_mime_type;

    private long media_size;

    private String media_file_name;

    private String media_file_path;

    @CreationTimestamp
    private Date media_created_at;

    @UpdateTimestamp
    private Date media_updated_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MediaEntity that = (MediaEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
