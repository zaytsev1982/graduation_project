package json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NbuJson {

    @JsonProperty("r030")
    private String code;
    @JsonProperty("txt")
    private String text;
    @JsonProperty("rate")
    private String rate;
    @JsonProperty("cc")
    private String cc;
    @JsonProperty("exchangedate")
    private String exchangeDate;

}
