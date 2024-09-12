package neo.ehsanodyssey.advertisement.repository;

import neo.ehsanodyssey.advertisement.model.Click;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
public interface ClickRepository extends CrudRepository<Click, String> {
    Set<Click> findAllByImpression_CountryCodeAndImpression_AppId(String countryCode, int appId);
}
