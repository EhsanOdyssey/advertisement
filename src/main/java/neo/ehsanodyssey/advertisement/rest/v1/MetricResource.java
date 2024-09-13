package neo.ehsanodyssey.advertisement.rest.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.ehsanodyssey.advertisement.rest.v1.response.PerformanceResponse;
import neo.ehsanodyssey.advertisement.rest.v1.response.TopFiveRecommendedAdvertisersResponse;
import neo.ehsanodyssey.advertisement.service.MetricsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-13 Sep/Fri
 **/
@RestController
@RequestMapping("/v1/metrics")
@RequiredArgsConstructor
@Slf4j
public class MetricResource {

    private final MetricsService metricsService;

    @Operation(summary = "Check application performance depending on the country")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Check application performance depending on the country",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PerformanceResponse.class))
                            )
                    })
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("performance")
    public List<PerformanceResponse> checkPerformance() {
        return this.metricsService.checkPerformance();
    }

    @Operation(summary = "Get top 5 recommended advertisers depending on the appId and country")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Get top 5 recommended advertisers depending on the appId and country",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopFiveRecommendedAdvertisersResponse.class)
                            )
                    })
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("recommended-advertisers")
    public List<TopFiveRecommendedAdvertisersResponse> getTopFiveRecommendedAdvertisers() {
        return this.metricsService.getTopFiveRecommendedAdvertisers();
    }
}
