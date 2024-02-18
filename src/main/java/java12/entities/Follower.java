package java12.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "followers")
@Getter
@Setter
@SequenceGenerator(name = "id_gen2", allocationSize = 1, sequenceName = "follower_id_seq")
public class Follower extends GeneratedId {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> subscribers = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> subscriptions = new ArrayList<>();
}
