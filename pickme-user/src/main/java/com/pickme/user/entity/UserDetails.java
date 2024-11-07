package com.pickme.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "User_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserDetails {

    private final Random random = new Random();

    @Id
    @Column(length = 10, updatable = false, nullable = false)
    private String userUid;

    private String userFirstName;
    private String userLastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date lastUpdate;

    private double userRatting;
    private boolean isUserAccountBlocked;
    private String accountStatus;

    private int otp;
    private String verificationToken;
    private boolean isAccountVerify;

    private String userProfileUrl;

    private String userAadhaarCardNumber;
    private boolean governmentApproved;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCarDetails> carDetails;

    @PrePersist
    private void ensureId() {
        if (this.userUid == null) {
            this.userUid = generateUniqueId();
        }
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
