package neo.ehsanodyssey.advertisement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
@Entity
@Table(name = "IMPRESSION")
@Data
public class Impression {
    @Id
    private String id;
    private int advertiserId;
    @Column(length = 2)
    private String countryCode;
    private int appId;
}
