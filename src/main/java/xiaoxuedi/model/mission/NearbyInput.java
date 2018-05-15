package xiaoxuedi.model.mission;

import xiaoxuedi.model.PageInput;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class NearbyInput extends PageInput
{
    private String schoolId;
}
