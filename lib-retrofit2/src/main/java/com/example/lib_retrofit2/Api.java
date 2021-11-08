package com.example.lib_retrofit2;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {
    void listRepos(String user);
    /**
     * 无参数
     */
//    @GET("user")
//    Call<Response> getData;

    /**
     * dec:  添加参数在方法括号内添加@Query,后面是参数类型和参数字段，表示后面idValue的取值作为"id"的值，
     * nameValue的取值作为"name"的值，其实就是键值对，Retrofit会把两个字段拼接到接口中，追加到"/user"后面.
     * 比如：baseUrl为https://api.github.com/，那么拼接网络接口注解中的地址后变为：https://api.github.com/user，
     * 我们需要传入的id=10006，name="刘亦菲"，那么拼接参数后就是完整的请求地址：https://api.github.com/user?id=10006&name=刘亦菲。
     *
     * @return
     */
    @GET("user")
    Call<ResponseBody> getData2(@Query("id")long idValue,@Query("name")String nameValue);

    /**
     * 如果有不确定的把表单参数我们可以使用@QueryMap注解来添加参数，通过Map将不确定的参数存入
     *  Map<String, Object> map = new HashMap<>();
     *   map.put("id", 10006);
     *   map.put("name", "刘亦菲");
     *
     *   Call<ResponseBody> call = retrofit.create(Api.class).getData3(map);
     * @param map
     * @return
     */
    @GET("user")
    Call<ResponseBody> getData3(@QueryMap Map<String,Object> map);


    //=======================================POST==================================================

    @POST("user/emails")
    Call<ResponseBody> post();

    /**
     * 注解：@FormUrlEncoded 请求格式注解，请求实体是一个From表单，每个键值对需要使用@Field注解
     * 注解：@Field  请求参数注解，提交请求的表单字段，必须要添加，而且需要配合@FormUrlEncoded使用
     *
     * @param nameValue  "name"/"sex"  参数字段，与后台给的字段需要一致
     * @param sexValue
     * @return
     */
    @FormUrlEncoded
    @POST("user/emails")
    Call<ResponseBody> post2(@Field("name")String nameValue,@Field("sex") String sexValue);


    /**
     * Map<String, Object> fieldMap = new HashMap<>();
     *  map.put("name", "刘亦菲");
     *  map.put("sex", "女");
     *
     *  Call<ResponseBody> psotData3 = retrofit.create(Api.class).getPostData3(map);
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/emails")
    Call<Object> post3(@FieldMap Map<String ,Object> map);

    /**
     * 特别注意：@Body注解不能用于表单或者支持文件上传的表单的编码，即不能与@FormUrlEncoded和@Multipart注解同时使用
     * @param body
     * @return
     */
    @POST("user/emails")
    Call<ResponseBody> post4(@Body RequestBody body);

    /**
     * 注解：@Path注解用于Url中的占位符{}，所有在网址中的参数，如上面 @GET("orgs/{id}")的id，通过{}占位符来标记id，使用
     * @param map
     * @param idValue
     * @return
     */
    @FormUrlEncoded
    @POST("user/{id}")
    Call<ResponseBody> post5(@FieldMap Map<String ,Object> map, @Path("id")String idValue);
}
