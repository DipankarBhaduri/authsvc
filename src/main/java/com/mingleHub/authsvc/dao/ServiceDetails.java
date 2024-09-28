package com.mingleHub.authsvc.dao;

import com.mingleHub.authsvc.constants.ServiceCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "service_details")
public class ServiceDetails {

    @Id
    private UUID id;

    private String userId;

    private String location;

    private String about;

    private Long latitude;

    private Long longitude;

    private Integer rangeInKm;

    @Enumerated(EnumType.STRING)
    private ServiceCategory serviceCategory;

    private Double rating;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String image5;

    private String image6;

    private String image7;

    private String image8;

    private String image9;

    private String image10;
}
