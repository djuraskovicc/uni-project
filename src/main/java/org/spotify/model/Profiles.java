package org.spotify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@NamedQuery(name = Profiles.GET_USER_PROFILE, query = "Select p from Profiles p where p.id = :id")
public class Profiles {
    public static final String GET_USER_PROFILE = "GetUserProfile";

    @Id
    public Long id;
    public String displayName, bio, profilePicUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Users user;

    public void setUser(Users user) {
        this.user = user;
        if (user != null) this.id = user.id;
    }
}
