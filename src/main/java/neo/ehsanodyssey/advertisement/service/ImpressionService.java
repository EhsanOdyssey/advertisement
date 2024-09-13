package neo.ehsanodyssey.advertisement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.ehsanodyssey.advertisement.exception.FetchImpressionException;
import neo.ehsanodyssey.advertisement.exception.FetchImpressionsException;
import neo.ehsanodyssey.advertisement.exception.StoreImpressionException;
import neo.ehsanodyssey.advertisement.repository.ImpressionRepository;
import neo.ehsanodyssey.advertisement.service.dto.ImpressionDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

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

    public Optional<ImpressionDTO> getImpressionById(String id) {
        try {
            return this.impressionRepository
                    .findById(id)
                    .map(ImpressionDTO::fromEntity);
        } catch (Exception e) {
            throw new FetchImpressionException("unable_fetch_impression", e);
        }
    }

    public Stream<ImpressionDTO> getAllStream() {
        try {
            return this.impressionRepository
                    .findAll()
                    .stream()
                    .map(ImpressionDTO::fromEntity);
        } catch (Exception e) {
            throw new FetchImpressionsException("unable_fetch_impressions", e);
        }
    }

    public Stream<ImpressionDTO> getByAppIdAndCountryCodeStream(int appId, String countryCode) {
        try {
            return this.impressionRepository
                    .findAllByAppIdAndCountryCode(appId, countryCode)
                    .stream()
                    .map(ImpressionDTO::fromEntity);
        } catch (Exception e) {
            throw new FetchImpressionsException("unable_fetch_impressions", e);
        }
    }
}
