package java12.dtoes;

import lombok.Data;

@Data
public class ImageDTO {
    private String url;

    public ImageDTO(String url) {
        this.url = url;
    }

    public ImageDTO() {
    }
}
