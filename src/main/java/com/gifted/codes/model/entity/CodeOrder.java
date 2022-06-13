package com.gifted.codes.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CodeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    private String reference;
    @OneToMany
    @JoinColumn(name = "code_order_id")
    private List<Code> codes;

    @Column(updatable = false)
    private OffsetDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

}
