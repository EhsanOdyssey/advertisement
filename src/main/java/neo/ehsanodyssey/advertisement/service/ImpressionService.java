package neo.ehsanodyssey.advertisement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.ehsanodyssey.advertisement.exception.FetchImpressionsException;
import neo.ehsanodyssey.advertisement.exception.StoreImpressionException;
import neo.ehsanodyssey.advertisement.repository.ImpressionRepository;
import neo.ehsanodyssey.advertisement.service.dto.ImpressionDTO;
import org.springframework.stereotype.Service;

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
public class ImpressionService {

    private final ImpressionRepository impressionRepository;

    @Transactional
    public ImpressionDTO save(ImpressionDTO impressionDTO) {
        try {
            var storedImpression = this.impressionRepository.save(impressionDTO.toEntity());
            return ImpressionDTO.fromEntity(storedImpression);
        } catch (Exception e) {
            throw new StoreImpressionException("unable_store_impression", e);
        }
    }

    public Set<ImpressionDTO> getByCountryCodeAndAppId(String countryCode, int appId) {
        try {
            return this.impressionRepository
                    .findAllByCountryCodeAndAppId(countryCode, appId)
                    .stream()
                    .map(ImpressionDTO::fromEntity)
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new FetchImpressionsException("unable_fetch_impressions", e);
        }
    }
}
