package com.microsoft.netconfig;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * @author Administrator
 * @date 2016/6/17
 * @email liu594545591@126.com
 * @introduce
 */
public interface NetInterface {
    /**
     * @GET 代表发送的GET请求
     * article/list/text?page=1 请求的路径(并且加上了基地址)
     * Call<T> 代表返回值的类型，ResponseBody 是Retrofit的原生的返回值
     */
    @GET("article/list/text?page=1")
    Call<ResponseBody> getNetData();

    /**
     * 可以通过Retrofit 直接去解析获取到的json 得到的数据可以直接开始使用
     * TestModle 是gson生成的实体类
     *
     * @return
     */
    @GET("article/list/text?page=1")
    Call<ResponseBody> getNetModle();

    /**
     * 如果是get请求的参数 必须是加了@Field("page") 这个注解 括号里面试服务器接收的参数名字
     * 必须和服务器保持统一
     *
     * @param page
     * @return
     */
    @GET("article/list/text?")
    Call<ResponseBody> getNetModleWithParams(@Field("page") String page);

    /**
     * 动态替换地址
     * 需要替换的地址必须要用"{}"包裹起来，然后通过@path 来声明需要替换的参数
     *
     * @param contents
     * @return
     */
    @GET("article/list/{content}?page=1")
    Call<ResponseBody> getNetModleWithPath(@Path("content") String contents);

    /**
     * @param list
     * @param path
     * @return
     */
    @GET("article/{name}/text?")
    Call<ResponseBody> getNetModlePathWithParams(@Path("name") String list,
                                                 @Field("page") String path);

    /**
     * 通过map传递多个参数
     *
     * @param map
     * @return
     */
    @GET("article/list/text?")
    Call<ResponseBody> getNetModleWithMap(@FieldMap Map<String, String> map);

//-----------------------------------------------------------------------------------------------


    /**
     * 接任务
     *
     * @param userId
     * @param tskId
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/answerTask")
    Call<String> takeTask(@Field("userId") String userId,
                          @Field("tskId") String tskId);

    /**
     * 获取抖音任务列表
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/dyList")
    Call<String> takeDyTask(@Field("id") String id);

    /**
     * 获取快手任务列表
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/ksList")
    Call<String> takeKsTask(@Field("id") String id);

    /**
     * 获取用户元宝
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/earning")
    Call<String> takeEarning(@Field("id") String id);

    /**
     * 获取验证码
     *
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/getCode")
    Call<String> takeMobileCode(@Field("mobile") String mobile);

    /**
     * 登陆
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/login")
    Call<String> takeLogin(@Field("mobile") String mobile,
                           @Field("password") String password);

    /**
     * 注册
     *
     * @param mobile
     * @param password
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/register")
    Call<String> takeRegister(@Field("mobile") String mobile,
                              @Field("password") String password,
                              @Field("code") String code);

    //TODO  图片 与 参数 混传

    /**
     * 提交任务
     *
     * @param id
     * @return
     */
    @Multipart
    @POST("iwapb/task/commit")
    Call<String> takeCommiteTask(@Part("id") String id,
                                 @Part("taskId") String taskId,
                                 @PartMap Map<String, RequestBody> image1);

    /**
     * 提交任务
     *
     * @param id
     * @return
     */
    @Multipart
    @POST("iwapb/task/commit")
    Call<String> takeCommiteTask1(@Part("id") String id,
                                  @Part("taskId") String taskId,
                                  @Part() List<MultipartBody.Part> parts);

    /**
     * 任务详情
     *
     * @param taskId
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/task/detail")
    Call<String> takeTaskDetail(@Field("taskId") String taskId,
                                @Field("userId") String userId);

    /**
     * 我的任务列表
     *
     * @param id
     * @param type
     * @param page
     * @param size
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/task/myTask")
    Call<String> takeMyTask(@Field("id") String id,
                            @Field("type") String type,
                            @Field("page") String page,
                            @Field("size") String size);

    /**
     * app版本号
     *
     * @param versionCode
     * @return
     */
    @FormUrlEncoded
    @POST("iwapb/upgradeApp")
    Call<String> checkVersion(@Field("versionCode") String versionCode);

}