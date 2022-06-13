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
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    private String reference;
    @ManyToOne
    private Merchant merchant;
    @ManyToOne
    private CodeOrder codeOrder;

    private String code; //todo name, encrypt
    private String pin; //todo encrypt
    private Long denomination;
    private String providerReference;
    private OffsetDateTime exportedAt;
    @OneToMany
    @JoinColumn(name = "code_id")
    private List<Revokal> revokals;
    private OffsetDateTime expiresAt;

    @Column(updatable = false)
    private OffsetDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

}
