package neo.ehsanodyssey.advertisement.rest.v1.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-13 Sep/Fri
 **/
@Builder(toBuilder = true)
public record PerformanceResponse(
        @JsonProperty("app_id") int appId,
        @JsonProperty("country_code") String countryCode,
        long impressions,
        long clicks,
        double revenue
) {}
