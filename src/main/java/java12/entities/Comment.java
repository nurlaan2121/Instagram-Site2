package java12.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "comments")
@Getter
@Setter
@SequenceGenerator(name = "id_gen1", allocationSize = 1, sequenceName = "comment_id_seq")
public class Comment extends GeneratedId {
    private String comment;
    @Column(name = "created_ad")
    private Date createdAd;
    @ManyToOne
    private Post post;
    @OneToMany(cascade = {CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Like> likes = new ArrayList<>();
    @ManyToOne
    private User user;
}
