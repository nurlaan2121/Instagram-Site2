package java12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_infoes")
@Getter
@Setter
@Data
@SequenceGenerator(name = "id_gen8", allocationSize = 1, sequenceName = "userInfo_id_seq")
public class UserInfo extends GeneratedId {
    @Column(name = "full_name")
    private String fullName;
    private String biography;
    private Gender gender;
    @Column(name = "image_profile", length = 1000)
    private String imageProfile;
}
