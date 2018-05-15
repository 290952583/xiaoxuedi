package xiaoxuedi.model.address;

import xiaoxuedi.entity.Address;
import xiaoxuedi.model.ModelUpdateEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class UpdateInput implements ModelUpdateEntity<Address>
{
	@NotNull
	private String id;

	@NotNull
	@Length(min = 2)
	private String address;

	@Override
	public void update(Address address)
	{
		address.setAddress(this.address);
	}
}
