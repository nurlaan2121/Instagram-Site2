package java12.dtoes;


import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private List<LikeDTO> likes;
    private Long ownerId;
    private ImageDTO imageDTO;
    private Date createdAd;

    public PostDTO(Long id, String title, String description, Long ownerId, Date createdAd) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.createdAd = createdAd;
    }

}
