package java12.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "likes")
@Getter
@Setter
@SequenceGenerator(name = "id_gen5", allocationSize = 1, sequenceName = "like_id_seq")
public class Like extends GeneratedId {
    @Column(name = "is_like")
    private Boolean isLike;
    @OneToOne
    private User user;
    @ManyToOne
    private Post post;
}
