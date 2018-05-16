package com.xiaoxuedi.api.service;

import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.address.AddInput;
import com.xiaoxuedi.model.address.DeleteInput;
import com.xiaoxuedi.model.address.ListOutput;
import com.xiaoxuedi.model.address.UpdateInput;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface AddressService
{
    @POST("/api/address/add")
    Call<Output> add(@Body AddInput input);

    @POST("/api/address/delete")
    Call<Output> delete(@Body DeleteInput input);

    @POST("/api/address/update")
    Call<Output> update(@Body UpdateInput input);

    @GET("/api/address/list")
    Call<Output<List<ListOutput>>> list();
}
