package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.AddressEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.address.AddInput;
import com.xiaoxuedi.model.address.DeleteInput;
import com.xiaoxuedi.model.address.ListOutput;
import com.xiaoxuedi.model.address.UpdateInput;
import com.xiaoxuedi.model.address.wx.WxAddInput;
import com.xiaoxuedi.model.address.wx.WxDeleteInput;
import com.xiaoxuedi.model.address.wx.WxListInput;
import com.xiaoxuedi.model.address.wx.WxUpdateInput;
import com.xiaoxuedi.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xiaoxuedi.model.Output.*;

@Service
@Transactional
public class AddressService {
    @Resource
    private AddressRepository addressRepository;

    public Output add(AddInput input) {
        int count = addressRepository.countByUser(UsersEntity.getUser());
        if (count >= 5) {
            return outputMaxCount();
        }
        AddressEntity address = addressRepository.save(input.toEntity());
        if (address == null) {
            return outputParameterError();
        }

        return outputOk();
    }

    public Output wxAdd(WxAddInput input) {
        int count = addressRepository.countByUser(UsersEntity.getUser(input.getUserid()));
        if (count >= 5) {
            return outputMaxCount();
        }
        AddressEntity address = addressRepository.save(input.toEntity());
        if (address == null) {
            return outputParameterError();
        }

        return outputOk();
    }

    public Output delete(DeleteInput input) {
        AddressEntity address = addressRepository.findOne(input.getId());
        if (address == null) {
            return outputParameterError();
        }
        if (!address.isBelong()) {
            return outputNotBelong();
        }
        addressRepository.delete(address);
        return outputOk();
    }
    public Output wxDelete(WxDeleteInput input) {
        AddressEntity address = addressRepository.findOne(input.getId());
        if (address == null) {
            return outputParameterError();
        }
//        if (!address.isBelong()) {
//            return outputNotBelong();
//        }
        addressRepository.delete(address);
        return outputOk();
    }

    public Output update(UpdateInput input) {
        AddressEntity address = addressRepository.findOne(input.getId());
        if (address == null) {
            return outputParameterError();
        }
        if (!address.isBelong()) {
            return outputNotBelong();
        }
        input.update(address);
        addressRepository.save(address);
        return outputOk();
    }

    public Output wxUpdate(WxUpdateInput input) {
        AddressEntity address = addressRepository.findOne(input.getId());
        if (address == null) {
            return outputParameterError();
        }
//        if (!address.isBelong()) {
//            return outputNotBelong();
//        }
        input.update(address);
        addressRepository.save(address);
        return outputOk();
    }
    public Output<List<ListOutput>> list() {
        List<AddressEntity> addresses = addressRepository.findAllByUser(UsersEntity.getUser());
        List<ListOutput> outputs = new ListOutput().fromEntityList(addresses);
        return output(outputs);
    }

    public Output<List<ListOutput>> Wxlist(WxListInput wxListInput) {
        List<AddressEntity> addresses = addressRepository.findAllByUser(UsersEntity.getUser(wxListInput.getUserid()));
        List<ListOutput> outputs = new ListOutput().fromEntityList(addresses);
        return output(outputs);
    }

}
