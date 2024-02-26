package java12.dtoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long userId;
    private String comment;
    private String userName;
    private Date createdAd;
    private int likesCount;

    public CommentDTO(Long id, String comment, String userName, Date createdAd,Long userId) {
        this.id = id;
        this.comment = comment;
        this.userName = userName;
        this.createdAd = createdAd;
        this.userId = userId;
    }
}
