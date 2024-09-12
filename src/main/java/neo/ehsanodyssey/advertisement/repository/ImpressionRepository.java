package neo.ehsanodyssey.advertisement.repository;

import neo.ehsanodyssey.advertisement.model.Impression;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
public interface ImpressionRepository extends CrudRepository<Impression, String> {
    Set<Impression> findAllByCountryCodeAndAppId(String countryCode, int appId);
}
