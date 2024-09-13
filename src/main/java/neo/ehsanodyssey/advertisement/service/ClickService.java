package neo.ehsanodyssey.advertisement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.ehsanodyssey.advertisement.exception.FetchClicksException;
import neo.ehsanodyssey.advertisement.exception.ImpressionNotFoundException;
import neo.ehsanodyssey.advertisement.exception.ServiceException;
import neo.ehsanodyssey.advertisement.exception.StoreClickException;
import neo.ehsanodyssey.advertisement.repository.ClickRepository;
import neo.ehsanodyssey.advertisement.repository.ImpressionRepository;
import neo.ehsanodyssey.advertisement.service.dto.ClickDTO;
import neo.ehsanodyssey.advertisement.service.dto.ImpressionDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;
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
            var id = clickDTO.id() == null || clickDTO.id().trim().isEmpty() ?
                    UUID.randomUUID().toString() : clickDTO.id();
            var updatedClickDTO =
                    clickDTO.toBuilder()
                            .id(id)
                            .impression(impressionDTO)
                            .build();
            var storedClick = this.clickRepository.save(updatedClickDTO.toEntity());
            return ClickDTO.fromEntity(storedClick);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            }
            throw new StoreClickException("unable_store_click", e);
        }
    }

    public Stream<ClickDTO> getAllStream() {
        try {
            return this.clickRepository
                    .findAll()
                    .stream()
                    .map(ClickDTO::fromEntity);
        } catch (Exception e) {
            throw new FetchClicksException("unable_fetch_clicks", e);
        }
    }

    public Stream<ClickDTO> getByAppIdAndCountryCodeStream(int appId, String countryCode) {
        try {
            return this.clickRepository
                    .findAllByImpression_AppIdAndImpression_CountryCode(appId, countryCode)
                    .stream()
                    .map(ClickDTO::fromEntity);
        } catch (Exception e) {
            throw new FetchClicksException("unable_fetch_clicks", e);
        }
    }
}
