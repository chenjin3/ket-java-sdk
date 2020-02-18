package com.ksyun.ket;

import com.ksyun.ket.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class KETDemo {
    //准备创建模板的数据
    private static String setPresetSet(String presetName, String codec) throws JSONException {
        String presetType = "avtrans";
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        JSONObject video = new JSONObject();
        JSONObject audio = new JSONObject();
        JSONArray logos = new JSONArray();
        data.put("Preset", presetName);
        data.put("PresetType", presetType);
        data.put("Description", "transcode to H.265 in HLS");
        video.put("vr", "24"); //帧率
        video.put("vb", "900k"); //码率（转出码率或最大码率）
        video.put("vcodec", codec);
        video.put("width", "1280");
        video.put("height","720");
        video.put("codecParams","rctype=3:crf=28");
        audio.put("acodec", "aac");
        audio.put("an", 0); //不去除音频
        param.put("f", "hls");
        param.put("VIDEO", video);
        param.put("AUDIO", audio);
        data.put("Param", param);
        return data.toString();
    }

    //准备创建任务的数据
    private static String setTask(String preset, String dst_bucket, String dst_object_key, String src_object_key)
            throws JSONException {
        JSONObject data = new JSONObject();
        data.put("Preset", preset);
        data.put("SrcInfo", KSCKETClient.TaskSrcInfo(dst_bucket, src_object_key));
        data.put("DstBucket", dst_bucket);
        data.put("DstObjectKey", dst_object_key);
        data.put("DstDir", "");
        data.put("DstAcl", "public-read");
        data.put("CbUrl", "");
        data.put("CbMethod", "POST");
        //data.put("ExtParam", "{\"passParams\":{\"hls_segment_type\":\"fmp4\",\"hls_flags\":\"single_file\",\"hls_playlist_type\":\"vod\"}}");
        data.put("ExtParam", "{\"passParams\":{\"hls_segment_type\":\"fmp4\",\"hls_playlist_type\":\"vod\"}}");
        return data.toString();
    }

    public static void main(String[] args) {
        KSCKETClient ksc = new KSCKETClient("73404025","http://10.69.67.113:8905/offline/");

        //创建模板
//        PresetRequest presetRequest = new PresetRequest();
//        String data = setPresetSet("hls_265_720p", "h265");
//        presetRequest.setData(data);
//        KvsErrResult presetResult = ksc.Preset(presetRequest);
//        System.out.println(presetResult);

        // 创建任务
//        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
//        String data2 = setTask("monitor_test", "qa-vod", "chenjin/video/test7/index.m3u8", "chenjin/video/8K/8k_test.mp4");//"liuhengxin/video/logo.mp4");
//        createTaskRequest.setData(data2);
//        CreateTasklResult createTasklResult = ksc.CreateTask(createTaskRequest);
//        System.out.println(createTasklResult);


        //删除任务
        DelTaskByTaskIDRequest delTaskByTaskIDRequest = new DelTaskByTaskIDRequest();
        delTaskByTaskIDRequest.setTaskID("88124659ee1daaa50b75c043dd9533z020200218");
        KvsErrResult delTaskByTaskIDResult = ksc.DelTaskByTaskID(delTaskByTaskIDRequest);
        System.out.println(delTaskByTaskIDResult);

    }
}
