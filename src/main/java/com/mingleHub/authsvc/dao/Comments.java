package com.mingleHub.authsvc.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "comments")
public class Comments {

    @Id
    @Column(name = "service_details_id")
    private UUID serviceDetailsId;

    private Integer sequence;

    private String content;

    @Column(name = "like_count")
    private Integer likeCount;
}
