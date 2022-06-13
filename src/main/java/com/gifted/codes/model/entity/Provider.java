package com.gifted.codes.model.entity;

import com.gifted.codes.model.ProviderImplementation;
import com.gifted.codes.model.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private ProviderType type;
    @Enumerated(EnumType.STRING)
    private ProviderImplementation implementation;
    @OneToMany
    @JoinColumn(name = "provider_id")
    private List<Merchant> merchants;
    private String billingGroup;

    @Column(updatable = false)
    private OffsetDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

}
