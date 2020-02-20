package com.ksyun.ket;

import com.alibaba.fastjson.JSON;
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
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
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

    //置顶任务
    public KvsErrResult TopTaskByTaskID(TopTaskByTaskIDRequest topTaskByTaskIDRequest) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("TaskID", topTaskByTaskIDRequest.getTaskID()));
        String resStr = this.get(this.endpoint + "TopTaskByTaskID", null, params);
        KvsErrResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, KvsErrResult.class);
        return result;
    }

    //查询任务列表
    public GetTaskListResult GetTaskList(GetTaskListRequest getTaskListRequest) {
        Map<String, Object> params = new HashMap<String, Object>();
        if((getTaskListRequest.getStartDate() != -1)) params.put("StartDate", getTaskListRequest.getStartDate());
        if((getTaskListRequest.getEndDate() != -1)) params.put("EndDate", getTaskListRequest.getEndDate());
        if((getTaskListRequest.getStartTime() != -1)) params.put("StartTime", getTaskListRequest.getStartTime());
        if((getTaskListRequest.getEndTime() != -1)) params.put("EndTime", getTaskListRequest.getEndTime());
        if((getTaskListRequest.getMarker() != -1)) params.put("Marker", getTaskListRequest.getMarker());
        if((getTaskListRequest.getLimit() != -1)) params.put("Limit", getTaskListRequest.getLimit());
        if((!getTaskListRequest.getErrorCode().equals("") )) params.put("ErrorCode", getTaskListRequest.getErrorCode());
        if((!getTaskListRequest.getTaskStatus().equals(""))) params.put("TaskStatus", getTaskListRequest.getTaskStatus());
        String resStr = this.get(this.endpoint + "GetTaskList", null, params);
        GetTaskListResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, GetTaskListResult.class);
        return result;
    }

    //查询任务详情
    public GetTaskByTaskIDResult GetTaskByTaskID(GetTaskByTaskIDRequest getTaskByTaskIDRequest) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("TaskID", getTaskByTaskIDRequest.getTaskID()));
        String resStr = this.get(this.endpoint + "GetTaskByTaskID", null, params);
        GetTaskByTaskIDResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, GetTaskByTaskIDResult.class);
        return result;
    }

    //查询任务META列表
    public GetTaskMetaResult GetTaskMetaInfo(GetTaskMetaRequest getTaskMetaInfoRequest) {
        Map<String, Object> params = new HashMap<String, Object>();
        if((getTaskMetaInfoRequest.getStartDate() != -1)) params.put("StartDate", getTaskMetaInfoRequest.getStartDate());
        if((getTaskMetaInfoRequest.getEndDate() != -1)) params.put("EndDate", getTaskMetaInfoRequest.getEndDate());
        if((getTaskMetaInfoRequest.getMarker() != -1)) params.put("Marker", getTaskMetaInfoRequest.getMarker());
        if((getTaskMetaInfoRequest.getLimit() != -1)) params.put("Limit", getTaskMetaInfoRequest.getLimit());
        if((!getTaskMetaInfoRequest.getTaskID().equals("") )) params.put("ErrorCode", getTaskMetaInfoRequest.getTaskID());
        String resStr = this.get(this.endpoint + "GetTaskMetaInfo", null, params);
        GetTaskMetaResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, GetTaskMetaResult.class);
        return result;
    }

    //同步获取文件的META信息接口
    public FetchMetaInfoResult FetchMetaInfo(FetchMetaInfoRequest fetchMetaInfoRequest) {
        String resStr = this.post(this.endpoint + "FetchMetaInfo", null, fetchMetaInfoRequest.getData());
        FetchMetaInfoResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, FetchMetaInfoResult.class);
        return result;
    }


    //创建模板
    public KvsErrResult Preset( PresetRequest presetRequest) {
        String resStr = this.post(this.endpoint + "Preset", null, presetRequest.getData());
        KvsErrResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, KvsErrResult.class);
        return result;
    }

    //查询模板列表
    public GetPresetListResult GetPresetList(GetPresetListRequest getPresetListRequest) {
        Map<String, Object> params = new HashMap<String, Object>();
        if((getPresetListRequest.getWithDetail() != -1)) params.put("WithDetail", getPresetListRequest.getWithDetail());

        if(getPresetListRequest.getPresetType() != null) {
            params.put("PresetType", getPresetListRequest.getPresetType());
        }
        if(getPresetListRequest.getPresets() != null) {
            params.put("Presets", getPresetListRequest.getPreset());
        }
        String resStr = this.get(this.endpoint + "GetPresetList", null, params);
        GetPresetListResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, GetPresetListResult.class);
        return result;
    }

    //更新模板
    public KvsErrResult UpdatePreset(UpdatePresetRequest updatePersetRequest) {
        String resStr = this.post(this.endpoint + "Preset", null, updatePersetRequest.getData());
        KvsErrResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, KvsErrResult.class);
        return result;
    }

    //删除模板
    public KvsErrResult DelPreset(DeletePresetRequest deletePresetRequest) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Preset", deletePresetRequest.getPreset()));
        String resStr = this.get(this.endpoint + "DelPreset", null, params);
        KvsErrResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, KvsErrResult.class);
        return result;
    }

    //查询模板详情
    public GetPresetDetailResult GetPresetDetail(GetPresetDetailRequest getPresetDetailRequest) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Preset", getPresetDetailRequest.getPreset()));
        String resStr = this.get(this.endpoint + "GetPresetDetail", null, params);
        GetPresetDetailResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, GetPresetDetailResult.class);
        return result;
    }

    //查询任务队列信息
    public QueryPipelineResult QueryPipeline(QueryPipelineRequest queryPipelineRequest) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("PipelineName", queryPipelineRequest.getPipelineName()));
        String resStr = this.get(this.endpoint + "QueryPipeline", null,params);
        QueryPipelineResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, QueryPipelineResult.class);
        return result;
    }

    //更新任务队列
    public KvsErrResult UpdatePipeline(UpdatePipelineRequest updatePipelineRequest) {
        String resStr = this.post(this.endpoint + "UpdatePipeline", null, updatePipelineRequest.getData());
        KvsErrResult result = com.alibaba.fastjson.JSONObject.parseObject(resStr, KvsErrResult.class);
        return result;
    }

    private String post(String url, Map<String, String> headers, String body) {
        //System.out.println("POST " + url);

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

    private String get(String url, Map<String, String> headers,Object params) {
        HttpResponse response = null;
        String result = "";
        HttpGet request = new HttpGet(url);

        try {
            if (params instanceof List) {
                String str = EntityUtils.toString(new UrlEncodedFormEntity((List<NameValuePair>)params, Charset.forName("UTF-8")));
                request.setURI(new URI(request.getURI().toString() + "?" + str));
            }
            if(params instanceof Map) {
                StringBuilder sb = new StringBuilder();
                for(String key: ((Map<String, ?>) params).keySet()) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    Object value = ((Map<String, ?>)params).get(key);
                    sb.append(key + "=" + value);
                }
                String str = sb.toString();
                request.setURI(new URI(request.getURI().toString() + "?" + str));
            }

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

    }
}
