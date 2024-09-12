package neo.ehsanodyssey.advertisement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.ehsanodyssey.advertisement.exception.FetchClicksException;
import neo.ehsanodyssey.advertisement.exception.ImpressionNotFoundException;
import neo.ehsanodyssey.advertisement.exception.ServiceException;
import neo.ehsanodyssey.advertisement.exception.StoreClickException;
import neo.ehsanodyssey.advertisement.model.Impression;
import neo.ehsanodyssey.advertisement.repository.ClickRepository;
import neo.ehsanodyssey.advertisement.repository.ImpressionRepository;
import neo.ehsanodyssey.advertisement.service.dto.ClickDTO;
import neo.ehsanodyssey.advertisement.service.dto.ImpressionDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class ClickService {

    private final ClickRepository clickRepository;
    private final ImpressionRepository impressionRepository;

    @Transactional
    public ClickDTO save(ClickDTO clickDTO) {
        try {
            var impressionDTO = this.impressionRepository
                    .findById(clickDTO.impression().id())
                    .map(ImpressionDTO::fromEntity)
                    .orElseThrow(() -> new ImpressionNotFoundException("invalid_impression_id_" + clickDTO.impression().id()));
            var updatedImpressionDTO =
                    clickDTO.toBuilder()
                            .impression(impressionDTO)
                            .build();
            var storedClick = this.clickRepository.save(updatedImpressionDTO.toEntity());
            return ClickDTO.fromEntity(storedClick);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            }
            throw new StoreClickException("unable_store_click", e);
        }
    }

    public Set<ClickDTO> getByCountryCodeAndAppId(String countryCode, int appId) {
        try {
            return this.clickRepository
                    .findAllByImpression_CountryCodeAndImpression_AppId(countryCode, appId)
                    .stream()
                    .map(ClickDTO::fromEntity)
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new FetchClicksException("unable_fetch_clicks", e);
        }
    }
}
