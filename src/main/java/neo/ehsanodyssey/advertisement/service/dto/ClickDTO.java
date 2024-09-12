package neo.ehsanodyssey.advertisement.service.dto;

import lombok.Builder;
import neo.ehsanodyssey.advertisement.model.Click;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
@Builder(toBuilder = true)
public record ClickDTO(
        String id,
        ImpressionDTO impression,
        Double revenue
) {
    public Click toEntity() {
        var entity = new Click();
        entity.setId(this.id);
        entity.setImpression(this.impression.toEntity());
        entity.setRevenue(this.revenue);
        return entity;
    }

    public static ClickDTO fromEntity(Click click) {
        return new ClickDTO(
                click.getId(),
                ImpressionDTO.fromEntity(click.getImpression()),
                click.getRevenue()
        );
    }
}
