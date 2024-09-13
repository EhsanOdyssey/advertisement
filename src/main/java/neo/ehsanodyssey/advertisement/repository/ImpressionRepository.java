package neo.ehsanodyssey.advertisement.repository;

import neo.ehsanodyssey.advertisement.model.Impression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
public interface ImpressionRepository extends JpaRepository<Impression, String> {
    List<Impression> findAllByAppIdAndCountryCode(int appId, String countryCode);
}
