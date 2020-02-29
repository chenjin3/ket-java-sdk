package com.ksyun.ket;

import com.ksyun.ket.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class KETDemo {
    /**
     * 准备创建模板的数据
     * @param presetName 模板名
     * @param codec  编码格式
     * @return
     * @throws JSONException
     */
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
        video.put("width", 1280);
        video.put("height",720);
        video.put("codecParams","rctype=3:crf=28");
        audio.put("acodec", "aac");
        audio.put("an", 0); //不去除音频
        param.put("f", "hls");
        param.put("VIDEO", video);
        param.put("AUDIO", audio);
        data.put("Param", param);
        return data.toString();
    }


    /**
     * 准备更新模板数据
     * @param preset  模板名
     * @param description 模板描述
     * @return
     * @throws JSONException
     */
    private static String setPresetSet4Update(String preset, String description) throws JSONException {
        String presettype = "avtrans";
        JSONObject data = new JSONObject();
        JSONObject param = new JSONObject();
        JSONObject video = new JSONObject();
        data.put("Preset", preset);
        data.put("PresetType", presettype);
        data.put("Description", description);

        video.put("height", 360);
        video.put("as", 1);

        param.put("f", "flv");
        param.put("VIDEO", video);
        data.put("Param", param);
        return data.toString();
    }

    /**
     * 准备创建kss下载上传ks3的转码任务的数据
     * @param preset  模板名
     * @param ref_id  用于跟踪转码任务的业务ID，在转码结果回调请求中会带上
     * @return
     * @throws JSONException
     */
    private static String setTask4kss(String preset, String ref_id)
            throws JSONException {
        JSONObject data = new JSONObject();
        data.put("Preset", preset);
        data.put("RefID", ref_id);
        data.put("StoreType", StoreType.KSS.getType());

        //prepare SrcInfo structure
        JSONArray srcInfo = new JSONArray();
        SrcInfo insrcInfo = new SrcInfo();
        insrcInfo.setType("video");
        insrcInfo.setPath("mfn.VID_20200217_171842.mp4");
        Auth auth1 = new Auth("a3e2f00ef4e60ba681c3546907b040ec78075a248bffb31915a98067e519935f","1ec27b2e975173df59d05ec695f3fd29"); //第一个4M分块的加密秘钥和初始向量
        Auth auth2 = new Auth("abebc10c8fc7b34df0c9a3c187dc34e68eab93986ea7f097104f9b17387b4c68","b13a56f43907ffd68a7f1b4d3eb53737");
        Auth auth3 = new Auth("913ece6e8d6a1794ecc5ae486024c8dda45b86a529e77fe26a01f6302f496317","91c1162ae27cfa1374359aa756e95057");
        insrcInfo.addAuthList(auth1, auth2, auth3);
        com.alibaba.fastjson.JSONObject jsonObject  =  (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(insrcInfo);
        srcInfo.put(jsonObject);
        data.put("SrcInfo", srcInfo);

        data.put("DstBucket", "qa-vod");
        data.put("DstObjectKey", "chenjin/test1/chenjin.m3u8");
        data.put("DstKey", "60518cb5328262a1be2ed3c2001705afeb336066686a96759217c91ee31c789d"); //目标文件加密秘钥
        data.put("DstIv", "da7753e7d2cd3d3bc77ac67515d7679e"); //目标文件AES初始化向量
        data.put("DstAcl", "public-read");
        data.put("CbUrl", "http://111.196.244.30:8001/cb");
        data.put("CbMethod", "POST");
        data.put("ExtParam", "{\"passParams\":{\"hls_segment_type\":\"fmp4\",\"hls_flags\":\"single_file\",\"hls_playlist_type\":\"vod\"}}");
        return data.toString();
    }

    /**
     * 准备创建ks3下载上传ks3的转码任务的数据
     * @param preset  模板名
     * @param ref_id  用于跟踪转码任务的业务ID，在转码结果回调请求中会带上
     * @return
     * @throws JSONException
     */
    private static String setTask4ks3(String preset, String ref_id)
            throws JSONException {
        JSONObject data = new JSONObject();
        data.put("Preset", preset);
        data.put("RefID", ref_id);
        data.put("StoreType", StoreType.KS3.getType());

        //prepare SrcInfo structure
        JSONArray srcInfo = new JSONArray();
        SrcInfo insrcInfo = new SrcInfo();
        insrcInfo.setType("video");
        insrcInfo.setPath("");
        Block block1 = new Block("qa-vod/yaoml/xiaomi_private/VID_20200217_1718420.mp4","01208b4d9c046d87125066f5fe801c12df86de533d6567b5dbc56a2274d9a89f", "a4103b5f679bf6359579b9343e6d28ff","831c1d0a32e6c9564bbc3a3975184af061e52b95",4194304);
        Block block2 = new Block("qa-vod/yaoml/xiaomi_private/VID_20200217_1718421.mp4","e976df718ffc137e8236914a624a917f294adfe39d671ac9b4823cf6036f2fb2", "04c568cf0dd71e7b472612f13b1fa6f2","333d9928c7f574eb650731ac677dc5c633f5fbf9",4194304);
        Block block3 = new Block("qa-vod/yaoml/xiaomi_private/VID_20200217_1718422.mp4","59e3bf3962f0dac56915b51e7e6a460616b7ce6b6d316600cab5f72338423c1c", "d51e014f40169787f49988af3b3f6332","7b8f0518fd8bcabceea9b06df0a1d932da82f158",1449883);
        insrcInfo.addBlockList(block1, block2, block3);
        com.alibaba.fastjson.JSONObject jsonObject  =  (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(insrcInfo);
        srcInfo.put(jsonObject);
        data.put("SrcInfo", srcInfo);

        data.put("DstBucket", "qa-vod");
        data.put("DstObjectKey", "chenjin/test2/chenjin.m3u8");
        data.put("DstKey", "60518cb5328262a1be2ed3c2001705afeb336066686a96759217c91ee31c789d"); //目标文件加密秘钥
        data.put("DstIv", "da7753e7d2cd3d3bc77ac67515d7679e"); //目标文件AES初始化向量
        data.put("DstAcl", "public-read");
        data.put("CbUrl", "http://111.196.244.30:8001/cb");
        data.put("CbMethod", "POST");
        data.put("ExtParam", "{\"passParams\":{\"hls_segment_type\":\"fmp4\",\"hls_flags\":\"single_file\",\"hls_playlist_type\":\"vod\"}}");
        return data.toString();
    }

    //设置转码源文件地址
    private static String setMetaInfo(String srcPath) {
        JSONObject data = new JSONObject();
        data.put("SrcPath", srcPath);
        return data.toString();
    }

    /**
     * 设置更新任务队列请求参数
     * @param PipelineName 队列名
     * @return
     */
    private static String setPipeline(String PipelineName) {
        JSONObject data = new JSONObject();
        data.put("PipelineName", PipelineName);
        data.put("State", "Active");//开启定制激活
        data.put("RegularStart", "00:00:00"); //每天0点开始转码
        data.put("RegularDuration", "3600"); //每日定时执行3600s
        return data.toString();
    }

    public static void main(String[] args) {
        KSCKETClient ksc = new KSCKETClient("73404025","http://10.69.67.113/offline/");
        //KSCKETClient ksc = new KSCKETClient("73404025","http://inner-offlinetran-bj.ks-live.com:8091/offline/");
        //KSCKETClient ksc = new KSCKETClient("73404025","http://120.92.213.72:8091/offline/");

//        /**
//         * 模板管理
//         */
//        //创建模板
//        PresetRequest presetRequest = new PresetRequest();
//        String data = setPresetSet("hls_265", "h265");
//        presetRequest.setData(data);
//        KvsErrResult presetResult = ksc.Preset(presetRequest);
//        System.out.println(presetResult);
//
//        //查询模板列表
//        GetPresetListRequest getPresetListRequest = new GetPresetListRequest();
//        getPresetListRequest.setPresetType("avtrans");
//        getPresetListRequest.setWithDetail(1);
//        GetPresetListResult getPresetListResult = ksc.GetPresetList(getPresetListRequest);
//        System.out.println(getPresetListResult);
//
//        //更新模板
//        UpdatePresetRequest updatePresetRequest = new UpdatePresetRequest();
//        String data1 = setPresetSet4Update("hls_265_720p", "scale to width of 360px and transcode to flv format");
//        updatePresetRequest.setData(data1);
//        KvsErrResult updatePresetresetResult = ksc.UpdatePreset(updatePresetRequest);
//        System.out.println(updatePresetresetResult);
//
//        //删除模板
//        DeletePresetRequest deletePresetRequest = new DeletePresetRequest();
//        deletePresetRequest.setPreset("hls_265");
//        KvsErrResult deletePresetResult = ksc.DelPreset(deletePresetRequest);
//        System.out.println(deletePresetResult);
//
//
//        //查询模板详情
//        GetPresetDetailRequest getPresetDetailRequest = new GetPresetDetailRequest();
//        getPresetDetailRequest.setPreset("hls_265_720p");
//        GetPresetDetailResult getPresetDetailResult = ksc.GetPresetDetail(getPresetDetailRequest);
//        System.out.println(getPresetDetailResult);


        /**
         * 任务管理
         */
        // 创建任务
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        //String data2 = setTask4kss("test_hls_265_720", "chenjin_test_ref_id");//"liuhengxin/video/logo.mp4");
        String data2 = setTask4ks3("test_hls_265_720", "chenjin_test_ref_id");

        createTaskRequest.setData(data2);
        CreateTasklResult createTasklResult = ksc.CreateTask(createTaskRequest);
        System.out.println(createTasklResult);

//        //删除任务
//        DelTaskByTaskIDRequest delTaskByTaskIDRequest = new DelTaskByTaskIDRequest();
//        delTaskByTaskIDRequest.setTaskID("88124659ee1daaa50b75c043dd9533z020200218");
//        KvsErrResult delTaskByTaskIDResult = ksc.DelTaskByTaskID(delTaskByTaskIDRequest);
//        System.out.println(delTaskByTaskIDResult);
//
//        //置顶任务
//        TopTaskByTaskIDRequest topTaskByTaskIDRequest = new TopTaskByTaskIDRequest();
//        topTaskByTaskIDRequest.setTaskID("62c309bb40abe44b2814f68313d416z020200219");
//        KvsErrResult TopTaskByTaskIDResult = ksc.TopTaskByTaskID(topTaskByTaskIDRequest);
//        System.out.println(TopTaskByTaskIDResult);
//
//
//        //查询任务列表
//        GetTaskListRequest getTaskListRequest = new GetTaskListRequest();
//        getTaskListRequest.setStartDate(20200202);
//        getTaskListRequest.setEndDate(20200220);
//        getTaskListRequest.setTaskStatus("succ");
//        //getTaskListRequest.setErrorCode("3255");
//        GetTaskListResult getTaskListResult = ksc.GetTaskList(getTaskListRequest);
//        System.out.println(getTaskListResult);
//
//        //查询任务详情
//        GetTaskByTaskIDRequest getTaskByTaskIDRequest = new GetTaskByTaskIDRequest();
//        getTaskByTaskIDRequest.setTaskID("88124659ee1daaa50b75c043dd9533z020200218");
//        GetTaskByTaskIDResult getTaskByTaskIDResult = ksc.GetTaskByTaskID(getTaskByTaskIDRequest);
//        System.out.println(getTaskByTaskIDResult);
//
//        //查询任务META列表
//        GetTaskMetaRequest getTaskMetaInfoRequest = new GetTaskMetaRequest();
//        getTaskMetaInfoRequest.setTaskID("020256be6ab02c9351b037d9c7b7e3z020200219"); //需要是avinfo类型模板的任务
//        getTaskMetaInfoRequest.setStartDate(20200202);
//        GetTaskMetaResult getTaskMetaResult = ksc.GetTaskMetaInfo(getTaskMetaInfoRequest);
//        System.out.println(getTaskMetaResult);
//
//        //同步获取文件的META信息接口
//        FetchMetaInfoRequest fetchMetaInfoRequestRequest = new FetchMetaInfoRequest();
//        String data3 = setMetaInfo("/qa-vod/chenjin/video/KSHD/chenjin_vod2.m3u8");
//        fetchMetaInfoRequestRequest.setData(data3);
//        FetchMetaInfoResult fetchMetaInfoResult = ksc.FetchMetaInfo(fetchMetaInfoRequestRequest);
//        System.out.println(fetchMetaInfoResult);
//
//        //查询任务队列信息
//        QueryPipelineRequest queryPipelineRequest = new QueryPipelineRequest();
//        queryPipelineRequest.setPipelineName("usual");
//        QueryPipelineResult queryPipelineResult = ksc.QueryPipeline(queryPipelineRequest);
//        System.out.println(queryPipelineResult);
//
//        //更新任务队列信息
//        UpdatePipelineRequest updatePipelineRequest = new UpdatePipelineRequest();
//        updatePipelineRequest.setData(setPipeline("usual"));
//        KvsErrResult updatePipelineResult = ksc.UpdatePipeline(updatePipelineRequest);
//        System.out.println(updatePipelineResult);

    }
}
