package java12.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@Getter
@Setter
@SequenceGenerator(name = "id_gen7", allocationSize = 1, sequenceName = "user_id_seq")
public class User extends GeneratedId {
    @Column(unique = true, name = "user_name")
    private String userName;
    private String password;
    private String email;
    @Column(unique = true, name = "phone_number")
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    private UserInfo userInfo;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();
    @ManyToOne
    private Image image;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Follower follower;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();
}
