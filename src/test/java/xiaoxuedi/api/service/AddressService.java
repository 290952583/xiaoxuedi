package xiaoxuedi.api.service;

import xiaoxuedi.model.Output;
import xiaoxuedi.model.address.*;
import retrofit2.Call;
import retrofit2.http.*;

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
