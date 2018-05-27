package com.xiaoxuedi.api;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.xiaoxuedi.api.service.*;
import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.address.AddInput;
import com.xiaoxuedi.model.address.DeleteInput;
import com.xiaoxuedi.model.address.ListOutput;
import com.xiaoxuedi.model.address.UpdateInput;
import com.xiaoxuedi.model.common.SendSmsInput;
import com.xiaoxuedi.model.mission.*;
import com.xiaoxuedi.model.order.ChargeInput;
import com.xiaoxuedi.model.order.TransferInput;
import com.xiaoxuedi.util.QiniuUtil;
import okhttp3.*;
import org.junit.Assert;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ApiTests extends Assert
{
    private final String mobile = "17864154930";

    private final Retrofit retrofit;

    public ApiTests() throws Exception
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(600, TimeUnit.SECONDS)
                .cookieJar(new CookieJar()
                {
                    private HashSet<Cookie> cookies = new HashSet<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
                    {
                        this.cookies.addAll(cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url)
                    {
                        return new LinkedList<>(cookies);
                    }
                }).build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));

        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        CommonService commonService = retrofit.create(CommonService.class);

        Response<Output> response = commonService.testLogin().execute();
        Output output = response.body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
    }

    @Test
    public void testCommon() throws Exception
    {
        CommonService service = retrofit.create(CommonService.class);

        Output output = service.school().execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);

        SendSmsInput input = new SendSmsInput();
        input.setMobile(mobile);
        output = service.sendSms(input).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
    }

    @Test
    public void testAddress() throws Exception
    {
        AddressService service = retrofit.create(AddressService.class);

        Output<List<ListOutput>> list = service.list().execute().body();
        if (list.getData().size() == 5)
        {
            DeleteInput deleteInput = new DeleteInput();
            deleteInput.setId(list.getData().get(0).getId());
            Output output = service.delete(deleteInput).execute().body();
            assertEquals(output.getCodeInfo(), Output.Code.OK);
            list = service.list().execute().body();
        }

        AddInput addInput = new AddInput();
        addInput.setAddress("地址");
        Output output = service.add(addInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);

        UpdateInput updateInput = new UpdateInput();
        updateInput.setId(list.getData().get(0).getId());
        updateInput.setAddress("修改地址");
        output = service.update(updateInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
    }

    @Test
    public void testMission() throws Exception
    {
        MissionService service = retrofit.create(MissionService.class);

        // 添加
        com.xiaoxuedi.model.mission.AddInput addInput = new com.xiaoxuedi.model.mission.AddInput();
        addInput.setSchool("学校");
        addInput.setDelivery("快递公司");
        addInput.setAddress("地址");
        addInput.setPrice(new BigDecimal("100"));
        Output output = service.add(addInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
        output = service.add(addInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);

        // 查询
        Output<List<com.xiaoxuedi.model.mission.ListOutput>> listOutput =
                service.acceptList(new MissionEntity.Status[]{MissionEntity.Status.WAIT}, 0).execute().body();
        assertEquals(listOutput.getCodeInfo(), Output.Code.OK);
        listOutput = service.nearby("0", 0).execute().body();
        assertEquals(listOutput.getCodeInfo(), Output.Code.OK);
        listOutput = service.myList(new MissionEntity.Status[]{MissionEntity.Status.WAIT}, 0).execute().body();
        assertEquals(listOutput.getCodeInfo(), Output.Code.OK);

        // 删除
        com.xiaoxuedi.model.mission.DeleteInput deleteInput = new com.xiaoxuedi.model.mission.DeleteInput();
        deleteInput.setId(listOutput.getData().get(0).getId());
        output = service.delete(deleteInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);


        listOutput = service.myList(new MissionEntity.Status[]{MissionEntity.Status.WAIT}, 0).execute().body();
        assertEquals(listOutput.getCodeInfo(), Output.Code.OK);
        com.xiaoxuedi.model.mission.ListOutput mission = listOutput.getData().get(0);

        // 修改
        com.xiaoxuedi.model.mission.UpdateInput updateInput = new com.xiaoxuedi.model.mission.UpdateInput();
        updateInput.setAddress("地址二");
        updateInput.setDescription(mission.getDescription());
        updateInput.setId(mission.getId());
        updateInput.setPrice(new BigDecimal("100"));
        updateInput.setTitle(mission.getTitle());
        output = service.update(updateInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);

        // 接收任务
        AcceptInput acceptInput = new AcceptInput();
        acceptInput.setId(mission.getId());
        output = service.accept(acceptInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);

        // 取消任务
        CancelInput cancelInput = new CancelInput();
        cancelInput.setId(mission.getId());
        output = service.cancel(cancelInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);

        // 确认取消
        AcceptCancelInput acceptCancelInput = new AcceptCancelInput();
        acceptCancelInput.setId(mission.getId());
        output = service.acceptCancel(acceptCancelInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);


        acceptInput = new AcceptInput();
        acceptInput.setId(mission.getId());
        output = service.accept(acceptInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);

        // 完成任务
        FinishInput finishInput = new FinishInput();
        finishInput.setId(mission.getId());
        output = service.finish(finishInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
    }

    @Test
    public void testOrder() throws Exception
    {
        OrderService service = retrofit.create(OrderService.class);

        // 查询余额
        Output output = service.balance().execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
        System.out.println(output.getData());

        // 充值
        ChargeInput chargeInput = new ChargeInput();
        chargeInput.setAmount(10000);
        chargeInput.setChannel("alipay");
        output = service.charge(chargeInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
        System.out.println(output.getData());

        // 提现
        TransferInput transferInput = new TransferInput();
        transferInput.setName("苏庆发");
        transferInput.setChannel("alipay");
        transferInput.setRecipient(mobile);
        transferInput.setAmount(1000);
        output = service.transfer(transferInput).execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.ParameterError);

        // 账单
        output = service.list().execute().body();
        assertEquals(output.getCodeInfo(), Output.Code.OK);
        System.out.println(output.getData());
    }

    @Test
    public void testQiniu() throws Exception{
        File file = new File("C:\\Users\\Administrator\\Desktop\\1525670186(1).jpg");
        QiniuUtil qiniuUtil = new QiniuUtil();
        String name = qiniuUtil.UploadFile(file);
        System.out.println("上传成功"+name);
    }
}
