package com.ksyun.ket;

import com.ksyun.ket.model.*;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.nio.charset.Charset;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class KSCKETClient {
    protected volatile String endpoint;  //memory barrier ensure visibility & orderliness
    protected HttpClient httpClient;
    protected volatile String customID;

    public KSCKETClient(String customID, String endpoint) {
        this.setCustomID(customID);
        this.setEndpoint(endpoint);
        this.httpClient = new DefaultHttpClient();
    }

    public void setEndpoint(String endpoint) {
        synchronized(this) {
            this.endpoint = endpoint;
        }
    }

    public void setCustomID(String id){
        synchronized(this) {
            this.customID = id;
        }
    }

    public static JSONArray TaskSrcInfo(String dst_bucket, String dst_object_key) throws JSONException {
        JSONArray srcInfo = new JSONArray();
        JSONObject insrcInfo = new JSONObject();
        insrcInfo.put("path", "/" + dst_bucket + "/" + dst_object_key);
        insrcInfo.put("index", 0);
        insrcInfo.put("type", "video");

        srcInfo.put(insrcInfo);

        return srcInfo;
    }

    //创建任务
    public CreateTasklResult CreateTask(CreateTaskRequest createTaskRequest) {
        Map<String, String> specialHeader = null;
        String resStr = this.post(this.endpoint + "CreateTask", specialHeader, createTaskRequest.getData());
        CreateTasklResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, CreateTasklResult.class);
        return result;
    }

    //删除任务
    public KvsErrResult DelTaskByTaskID(DelTaskByTaskIDRequest delTaskByTaskIDRequest) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("TaskID", delTaskByTaskIDRequest.getTaskID()));
        String resStr = this.get(this.endpoint + "DelTaskByTaskID", null, params);
        KvsErrResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, KvsErrResult.class);
        return result;
    }

    //创建模板
    public KvsErrResult Preset( PresetRequest presetRequest) {
        String resStr = this.post(this.endpoint + "Preset", null, presetRequest.getData());
        KvsErrResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, KvsErrResult.class);
        return result;
    }

    private String post(String url, Map<String, String> headers, String body) {
        System.out.println("POST " + url);

        HttpPost request = new HttpPost(url);
        String result = "";

        setHeaders(request, headers);

        StringEntity entity = new StringEntity(body, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        HttpResponse response = null;
        request.setEntity(entity);

        try {
            response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.SC_OK){
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
                //System.out.println(result);
                return result;
            } else {
                System.out.println("status: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    private String get(String url, Map<String, String> headers,List<NameValuePair> params) {
        HttpResponse response = null;
        String result = "";
        HttpGet request = new HttpGet(url);

        try {
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));
            request.setURI(new URI(request.getURI().toString() + "?" + str));

            setHeaders(request, headers);

            response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.SC_OK){
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
                return result;
            } else {
                System.out.println("status: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    private void setHeaders(HttpRequestBase request, Map<String, String> headers){
        request.setHeader("Content-type", "application/json; charset=utf-8");
        request.setHeader("X-KSC-VERSION","2017-01-01");
        request.setHeader("X-KSC-ACCOUNT-ID",this.customID);
        if (headers != null) {
            for (String key: headers.keySet()) {
                request.addHeader(key, headers.get(key));
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("hi");
    }
}
