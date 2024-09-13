package neo.ehsanodyssey.advertisement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
@Entity
@Table(name = "CLICK")
@Data
@EqualsAndHashCode(exclude = {"impression"})
@ToString(exclude = {"impression"})
public class Click {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMPRESSION_ID", referencedColumnName = "ID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Impression impression;
    @Basic
    private double revenue;
}
