package xiaoxuedi.api.service;

import xiaoxuedi.model.Output;
import xiaoxuedi.model.common.SendSmsInput;
import retrofit2.Call;
import retrofit2.http.*;

public interface CommonService
{
    @POST("/api/common/sendSms")
    Call<Output> sendSms(@Body SendSmsInput input);

    @GET("/api/common/school")
    Call<Output> school();

    @GET("/api/common/testLogin")
    Call<Output> testLogin();
}
