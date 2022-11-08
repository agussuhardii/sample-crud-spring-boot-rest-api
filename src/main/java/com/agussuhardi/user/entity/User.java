package com.agussuhardi.user.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@ToString
@Setter// consider encapsulating changes
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_")
@FieldNameConstants
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "hobie", nullable = false)
    private String hobie;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = System.currentTimeMillis();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = System.currentTimeMillis();
    }
}
