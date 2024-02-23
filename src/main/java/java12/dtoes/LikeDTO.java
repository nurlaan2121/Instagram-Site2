package java12.dtoes;

import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private Long userId;
    private Long postId;

    public LikeDTO(Long id, Long userId, Long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }
}
