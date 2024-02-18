package java12.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "images")
@Getter
@Setter
@SequenceGenerator(name = "id_gen4", allocationSize = 1, sequenceName = "image_id_seq")
public class Image extends GeneratedId {
    @Column(name = "image_ulr", length = 1000)
    private String imageUrl;
    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "user_list")
    private List<User> userList = new ArrayList<>();
}
