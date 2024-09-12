package neo.ehsanodyssey.advertisement.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import neo.ehsanodyssey.advertisement.service.dto.ClickDTO;
import neo.ehsanodyssey.advertisement.service.dto.ImpressionDTO;

import java.io.IOException;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-13 Sep/Fri
 **/
public class ClickDeserializer extends StdDeserializer<ClickDTO> {

    public ClickDeserializer() {
        this(null);
    }

    public ClickDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public ClickDTO deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode node = jp.getCodec().readTree(jp);
        final var impressionId = node.get("impression_id").asText();
        var impressionDTO = new ImpressionDTO(impressionId, null, null, null);
        final var revenue = (Double) ((DoubleNode) node.get("revenue")).numberValue();
        return new ClickDTO(null, impressionDTO, revenue);
    }

}
