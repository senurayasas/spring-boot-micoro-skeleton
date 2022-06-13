package com.gifted.codes.model.entity;

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
public class MerchantConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    @ManyToOne
    private Merchant merchant;
    private String currency; //todo datatype?
    private int bufferCount;
    private String externalProductId;
    @OneToMany
    @JoinColumn(name = "merchant_config_id")
    private List<DenominationConfig> denominations;

    @Column(updatable = false)
    private OffsetDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

}
