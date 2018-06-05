package com.xiaoxuedi.controller.wxapi;

import com.xiaoxuedi.controller.api.AbstractController;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.address.AddInput;
import com.xiaoxuedi.model.address.DeleteInput;
import com.xiaoxuedi.model.address.UpdateInput;
import com.xiaoxuedi.model.address.wx.WxAddInput;
import com.xiaoxuedi.model.address.wx.WxDeleteInput;
import com.xiaoxuedi.model.address.wx.WxListInput;
import com.xiaoxuedi.model.address.wx.WxUpdateInput;
import com.xiaoxuedi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("wxapi/address")
public class WxAddressController extends AbstractController
{
    @Autowired
    private AddressService addressService;

    @PostMapping("add")
    public Output add(@Valid @RequestBody WxAddInput input)
    {
        return addressService.wxAdd(input);
    }

    @PostMapping("delete")
    public Output delete(@Valid @RequestBody WxDeleteInput input)
    {
        return addressService.wxDelete(input);
    }

    @PostMapping("update")
    public Output update(@Valid @RequestBody WxUpdateInput input)
    {
        return addressService.wxUpdate(input);
    }

    @PostMapping("list" )
    public Output list( @RequestBody WxListInput wxListInput)
    {
        return addressService.Wxlist(wxListInput);
    }
}
