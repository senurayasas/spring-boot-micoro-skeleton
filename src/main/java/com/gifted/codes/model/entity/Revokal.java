package com.gifted.codes.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Revokal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    @ManyToOne
    private Code code;
    private String reason;


    @Column(updatable = false)
    private OffsetDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

}
