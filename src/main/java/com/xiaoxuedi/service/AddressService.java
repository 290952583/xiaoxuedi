package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.Address;
import com.xiaoxuedi.entity.User;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.address.AddInput;
import com.xiaoxuedi.model.address.DeleteInput;
import com.xiaoxuedi.model.address.ListOutput;
import com.xiaoxuedi.model.address.UpdateInput;
import com.xiaoxuedi.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xiaoxuedi.model.Output.*;

@Service
@Transactional
public class AddressService
{
    @Resource
    private AddressRepository addressRepository;

    public Output add(AddInput input)
    {
        int count = addressRepository.countByUser(User.getUser());
        if (count >= 5)
        {
            return outputMaxCount();
        }
        Address address = addressRepository.save(input.toEntity());
        if (address == null)
        {
            return outputParameterError();
        }

        return outputOk();
    }

    public Output delete(DeleteInput input)
    {
        Address address = addressRepository.findOne(input.getId());
        if (address == null)
        {
            return outputParameterError();
        }
        if (!address.isBelong())
        {
            return outputNotBelong();
        }
        addressRepository.delete(address);
        return outputOk();
    }

    public Output update(UpdateInput input)
    {
        Address address = addressRepository.findOne(input.getId());
        if (address == null)
        {
            return outputParameterError();
        }
        if (!address.isBelong())
        {
            return outputNotBelong();
        }
        input.update(address);
        addressRepository.save(address);
        return outputOk();
    }

    public Output<List<ListOutput>> list()
    {
        List<Address> addresses = addressRepository.findAllByUser(User.getUser());
        List<ListOutput> outputs = new ListOutput().fromEntityList(addresses);
        return output(outputs);
    }
}
