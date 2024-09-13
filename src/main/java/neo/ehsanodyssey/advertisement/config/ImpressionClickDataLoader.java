package neo.ehsanodyssey.advertisement.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.ehsanodyssey.advertisement.service.ClickService;
import neo.ehsanodyssey.advertisement.service.ImpressionService;
import neo.ehsanodyssey.advertisement.service.dto.ClickDTO;
import neo.ehsanodyssey.advertisement.service.dto.ImpressionDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-13 Sep/Fri
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class ImpressionClickDataLoader implements CommandLineRunner {

    private static final String IMPRESSIONS_JSON = "/data/impressions.json";
    private static final String CLICK_JSON = "/data/clicks.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final ImpressionService impressionService;
    private final ClickService clickService;

    @Override
    public void run(String... args) {
        log.info("storing the input data started");
        this.storeInputImpressions();
        this.storeInputClicks();
    }

    private void storeInputImpressions() {
        log.debug("storing the input impressions data started");
        var impressionsTypeReference = new TypeReference<List<ImpressionDTO>>(){};
        try(var impressionsInputStream = TypeReference.class.getResourceAsStream(IMPRESSIONS_JSON)) {
            var impressionDTOs = MAPPER.readValue(impressionsInputStream, impressionsTypeReference);
            log.debug("storing '{}' input impressions", impressionDTOs.size());
            impressionDTOs.forEach(this.impressionService::save);
            log.debug("storing the input impressions data done successfully");
        } catch (Exception e) {
            log.warn("storing the input impressions data failed with exception: {}", e.getMessage(), e);
        }
    }

    private void storeInputClicks() {
        log.debug("storing the input clicks data started");
        var module = new SimpleModule();
        module.addDeserializer(ClickDTO.class, new ClickDeserializer());
        MAPPER.registerModule(module);
        var clicksTypeReference = new TypeReference<List<ClickDTO>>(){};
        try(var clicksInputStream = TypeReference.class.getResourceAsStream(CLICK_JSON)) {
            var clicksDTOs = MAPPER.readValue(clicksInputStream, clicksTypeReference);
            log.debug("storing '{}' input clicks", clicksDTOs.size());
            clicksDTOs.forEach(this.clickService::save);
            log.debug("storing the input clicks data done successfully");
        } catch (Exception e) {
            log.warn("storing the input clicks data failed with exception: {}", e.getMessage(), e);
        }
    }
}
