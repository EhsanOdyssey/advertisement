package neo.ehsanodyssey.advertisement.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import neo.ehsanodyssey.advertisement.model.Impression;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
public record ImpressionDTO(
        String id,
        @JsonProperty("advertiser_id") Integer advertiserId,
        @JsonProperty("country_code") String countryCode,
        @JsonProperty("app_id") Integer appId
) {
    public Impression toEntity() {
        var entity = new Impression();
        entity.setId(this.id);
        entity.setAdvertiserId(this.advertiserId);
        entity.setCountryCode(this.countryCode);
        entity.setAppId(this.appId);
        return entity;
    }

    public static ImpressionDTO fromEntity(Impression impression) {
        return new ImpressionDTO(
                impression.getId(),
                impression.getAdvertiserId(),
                impression.getCountryCode(),
                impression.getAppId()
        );
    }
}
