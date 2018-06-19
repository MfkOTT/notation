package com.ski.notation.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by itw_yuekui on 2017/5/9.
 */
public class OkHttpUtils {
    public static final OkHttpClient client = new OkHttpClient();
    public static String doPost(String url,Map<String,String> bodyParams) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (bodyParams != null && !bodyParams.isEmpty()){
            for (Map.Entry<String,String> entity:bodyParams.entrySet()){
                formBuilder.add(entity.getKey(),entity.getValue());
            }
        }
        RequestBody body = formBuilder.build();
        Request request =  new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return "";
    }

    /**
     * get请求 参数已经预编码了
     * @param url baseUrl
     * @param queryParams queryParams
     * @return response content
     * @throws IOException
     */
    public static String doGetPreEncodeQueryParam(String url, Map<String,String> queryParams) throws IOException{
        HttpUrl.Builder urlBuilder  = HttpUrl.parse(url).newBuilder();

        if (queryParams != null && !queryParams.isEmpty()){
            for (Map.Entry<String,String> entity:queryParams.entrySet()){
                urlBuilder.addEncodedQueryParameter(entity.getKey(),entity.getValue());
            }
        }
        Request request =  new Request.Builder().url(urlBuilder.build()).get().build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return "";
    }

    /**
     * get请求 参数没有编码由okhttp3自己编码
     * @param url
     * @param queryParams
     * @return
     * @throws IOException
     */
    public static String doGetDefaultEncodeQueryParam(String url, Map<String,String> queryParams) throws IOException{
        HttpUrl.Builder urlBuilder  = HttpUrl.parse(url).newBuilder();

        if (queryParams != null && !queryParams.isEmpty()){
            for (Map.Entry<String,String> entity:queryParams.entrySet()){
                urlBuilder.addQueryParameter(entity.getKey(),entity.getValue());
            }
        }
        Request request =  new Request.Builder().url(urlBuilder.build()).get().build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return "";
    }

    public static String doPut(String url,Map<String,String> bodyParams) throws Exception{
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (bodyParams != null && !bodyParams.isEmpty()){
            for (Map.Entry<String,String> entity:bodyParams.entrySet()){
                formBuilder.add(entity.getKey(),entity.getValue());
            }
        }
        RequestBody body = formBuilder.build();
        Request request =  new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.message());
        if (response.isSuccessful()){
            return response.body().string();
        }
        return  null;
    }
    public static String doGetAndMediaTypeXml(String url ,Map<String,String> queryParams) throws Exception{
        HttpUrl.Builder urlBuilder  = HttpUrl.parse(url).newBuilder();

        if (queryParams != null && !queryParams.isEmpty()){
            for (Map.Entry<String,String> entity:queryParams.entrySet()){
                urlBuilder.addQueryParameter(entity.getKey(),entity.getValue());
            }
        }
        String query = urlBuilder.build().encodedQuery();
        Request request = new Request.Builder()
                .addHeader("ContentType","application/xml;charset=utf-8")
                .url(urlBuilder.build())
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }
        return "";
    }

    public static String doPostDataByte(String url,byte[] data) throws Exception{
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),data);
        Request request = new  Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.message());
        if (response.isSuccessful()){
            return response.body().string();
        }
        return  null;
    }
}
