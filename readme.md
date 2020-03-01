# 金山云离线转码Java SDK使用说明

## 1. 集成
### 下载并安装到到本地Maven仓库
```bash
# 从github下载sdk
git clone https://github.com/chenjin3/ket-java-sdk.git
# 安装sdk到本地Maven仓库
mvn clean install
```

说明：如果采用IntelliJ IDEA集成开发环境，也可以通过右侧Maven菜单中
ket>Lifecycle> install来安装sdk到本地Maven仓库

### 在pom文件中引入sdk依赖

在pom文件的`<dependencies></dependencies>`中增加如下内容：

```xml
<dependency>
    <groupId>com.ksyun</groupId>
    <artifactId>ket</artifactId>
    <version>1.0.1</version>
</dependency>
```

## 2. 初始化客户端
在调用接口前，先初始化 KSCKVSJsonClient，如下：
```java
import com.ksyun.ket.KSCKETClient;

KSCKETClient ksc = new KSCKETClient("[私有化客户ID]","[转码系统API地址]");
```        

## 3. 接口说明
### 模板管理接口
#### 1）创建模板
```java
PresetRequest presetRequest = new PresetRequest();
String data = setPresetSet("hls_265", "h265");
presetRequest.setData(data);
KvsErrResult presetResult = ksc.Preset(presetRequest);
System.out.println(presetResult);

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
```
对应API接口： https://docs.ksyun.com/documents/2389

#### 2）更新模板
```java
UpdatePresetRequest updatePresetRequest = new UpdatePresetRequest();
String data1 = setPresetSet4Update("hls_265_720p", "scale to width of 360px and transcode to flv format");
updatePresetRequest.setData(data1);
KvsErrResult updatePresetresetResult = ksc.UpdatePreset(updatePresetRequest);
System.out.println(updatePresetresetResult);

//准备更新模板数据
private static String setPresetSet4Update(String preset, String description) throws JSONException {
    String presettype = "avtrans";
    JSONObject data = new JSONObject();
    JSONObject param = new JSONObject();
    JSONObject video = new JSONObject();
    data.put("Preset", preset); //模板名
    data.put("PresetType", presettype); //模板类型
    data.put("Description", description); //模板描述

    video.put("height", 360);
    video.put("as", 1);

    param.put("f", "flv");
    param.put("VIDEO", video);
    data.put("Param", param);
    return data.toString();
}
```
对应API接口：https://docs.ksyun.com/documents/2390

#### 3）删除模板
```java
//删除模板
DeletePresetRequest deletePresetRequest = new DeletePresetRequest();
deletePresetRequest.setPreset("hls_265"); //设置模板名
KvsErrResult deletePresetResult = ksc.DelPreset(deletePresetRequest);
System.out.println(deletePresetResult);
```
对应API接口：https://docs.ksyun.com/documents/2391

#### 4）查询模板列表
```java
GetPresetListRequest getPresetListRequest = new GetPresetListRequest();
getPresetListRequest.setPresetType("avtrans");//设置模板类型
getPresetListRequest.setWithDetail(1); //返回详情
GetPresetListResult getPresetListResult = ksc.GetPresetList(getPresetListRequest);
System.out.println(getPresetListResult);
```
对应API接口：https://docs.ksyun.com/documents/2392

#### 5）查询模板详情
```java
GetPresetDetailRequest getPresetDetailRequest = new GetPresetDetailRequest();
getPresetDetailRequest.setPreset("hls_265_720p"); // 设置模板名
GetPresetDetailResult getPresetDetailResult = ksc.GetPresetDetail(getPresetDetailRequest);
System.out.println(getPresetDetailResult);
```
对应API接口：https://docs.ksyun.com/documents/2393

### 任务管理接口
#### 6）创建任务
```java
    CreateTaskRequest createTaskRequest = new CreateTaskRequest();
    String data2 = setTask4kss("test_hls_265_720", "chenjin_test_ref_id");
    createTaskRequest.setData(data2);
    CreateTasklResult createTasklResult = ksc.CreateTask(createTaskRequest);
    System.out.println(createTasklResult);
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
        insrcInfo.setPath("mfn.chenjin_test_kss.mp4"); // storeId, 格式一般是 mfn.${fileName}
        Auth auth1 = new Auth("25b2a7ee0527e7e920a3542f622a69f94a65f7b9cfbc805c9d21ec2a169dffd8","67383fd6223035b4b93acc2bf4c27c39"); //第一个4M分块的加密秘钥和初始向量
        Auth auth2 = new Auth("3dd540e1cb01567f3582d995af63855bbece9115b13d40763807f050cbae9b4b","c72ba64b71f9ee27efd067e4f1dfa112");//第二个4M分块的加密秘钥和初始向量
        Auth auth3 = new Auth("0bea802f10421852d4ec34fa71ab0686c9b8b822728be6113da2e01d55e16953","808816383f9edb245a832d781c1b0ef8");
        insrcInfo.addAuthList(auth1, auth2, auth3);
        com.alibaba.fastjson.JSONObject jsonObject  =  (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(insrcInfo);
        srcInfo.put(jsonObject);
        data.put("SrcInfo", srcInfo);

        data.put("DstBucket", "qa-vod");//转出文件ks3目标存储桶
        data.put("DstObjectKey", "chenjin/test5/chenjin.m3u8"); //转出文件ks3目的文件对象路径
        data.put("DstKey", "60518cb5328262a1be2ed3c2001705afeb336066686a96759217c91ee31c789d"); //目标文件加密秘钥
        data.put("DstIv", "da7753e7d2cd3d3bc77ac67515d7679e"); //目标文件AES初始化向量
        data.put("DstAcl", "public-read");
        data.put("CbUrl", "http://111.196.244.30:8001/cb");
        data.put("CbMethod", "POST");
        data.put("ExtParam", "{\"passParams\":{\"hls_segment_type\":\"fmp4\",\"hls_flags\":\"single_file\",\"hls_playlist_type\":\"vod\"}}");
        return data.toString();
    }
```
对应API接口：https://docs.ksyun.com/documents/2397

#### 7）删除任务
```java
 DelTaskByTaskIDRequest delTaskByTaskIDRequest = new DelTaskByTaskIDRequest();
delTaskByTaskIDRequest.setTaskID("88124659ee1daaa50b75c043dd9533z020200218");
KvsErrResult delTaskByTaskIDResult = ksc.DelTaskByTaskID(delTaskByTaskIDRequest);
System.out.println(delTaskByTaskIDResult);
```
对应API接口：https://docs.ksyun.com/documents/2399

#### 8）置顶任务
```java
TopTaskByTaskIDRequest topTaskByTaskIDRequest = new TopTaskByTaskIDRequest();
topTaskByTaskIDRequest.setTaskID("62c309bb40abe44b2814f68313d416z020200219");
KvsErrResult TopTaskByTaskIDResult = ksc.TopTaskByTaskID(topTaskByTaskIDRequest);
System.out.println(TopTaskByTaskIDResult);
```
对应API接口：https://docs.ksyun.com/documents/2400

#### 9）查询任务列表
```java
GetTaskListRequest getTaskListRequest = new GetTaskListRequest();
getTaskListRequest.setStartDate(20200202);
getTaskListRequest.setEndDate(20200220);
getTaskListRequest.setTaskStatus("succ");
//getTaskListRequest.setErrorCode("3255");
GetTaskListResult getTaskListResult = ksc.GetTaskList(getTaskListRequest);
System.out.println(getTaskListResult);

```
对应API接口：https://docs.ksyun.com/documents/2401

#### 10）查询任务详情
```java
GetTaskByTaskIDRequest getTaskByTaskIDRequest = new GetTaskByTaskIDRequest();
getTaskByTaskIDRequest.setTaskID("88124659ee1daaa50b75c043dd9533z020200218");
GetTaskByTaskIDResult getTaskByTaskIDResult = ksc.GetTaskByTaskID(getTaskByTaskIDRequest);
System.out.println(getTaskByTaskIDResult);

```
对应API接口：https://docs.ksyun.com/documents/2402

#### 11）查询任务META列表
````java
GetTaskMetaRequest getTaskMetaInfoRequest = new GetTaskMetaRequest();
getTaskMetaInfoRequest.setTaskID("020256be6ab02c9351b037d9c7b7e3z020200219"); //需要是avinfo类型模板的任务
getTaskMetaInfoRequest.setStartDate(20200202);
GetTaskMetaResult getTaskMetaResult = ksc.GetTaskMetaInfo(getTaskMetaInfoRequest);
System.out.println(getTaskMetaResult);

````
对应API接口：https://docs.ksyun.com/documents/2403

#### 12）同步获取文件的META信息接口
````java
FetchMetaInfoRequest fetchMetaInfoRequestRequest = new FetchMetaInfoRequest();
String data3 = setMetaInfo("/qa-vod/chenjin/video/KSHD/chenjin_vod2.m3u8");
fetchMetaInfoRequestRequest.setData(data3);
FetchMetaInfoResult fetchMetaInfoResult = ksc.FetchMetaInfo(fetchMetaInfoRequestRequest);
System.out.println(fetchMetaInfoResult);

````
对应API接口：https://docs.ksyun.com/documents/5707

## 任务队列信息管理接口
#### 13）查询任务队列信息
```java
QueryPipelineRequest queryPipelineRequest = new QueryPipelineRequest();
queryPipelineRequest.setPipelineName("usual");
QueryPipelineResult queryPipelineResult = ksc.QueryPipeline(queryPipelineRequest);
System.out.println(queryPipelineResult);

```
对应API接口：https://docs.ksyun.com/documents/2396


#### 14）更新任务队列信息
```java
UpdatePipelineRequest updatePipelineRequest = new UpdatePipelineRequest();
updatePipelineRequest.setData(setPipeline("usual"));
KvsErrResult updatePipelineResult = ksc.UpdatePipeline(updatePipelineRequest);
System.out.println(updatePipelineResult);

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
```
对应API接口：https://docs.ksyun.com/documents/2395

#### 15）接收任务结果回调格式
GET或POST，根据创建任务时指定的CbMethod而定，建议采用POST。回调请求body格式如下：

```json
{
    "TaskID": "39898b95f4577382ccc8f2d0c925a9z020200229",
    "RefID": "2cb9a3ee0a62bb1aac743c6f1fa41eba",
    "M3u8Index": "XXX",
    "Status": 3,
    "Type": "avtrans",
    "Details": {
        "errnum": 0,
        "bucket": "qa-vod",
        "items": [{
            	"dstpath": "chenjin/xiaomi_private_out/VID_20200218_16405286ea1080b7a792c3.mp4.aes",
            	"outMediainfo": "{\"metadata\":{\"rotate\":\"90\"},\"format\":{\"format_name\":\"mp4\",\"size\":29616559,\"duration\":\"11\",\"bit_rate\":21539312},\"streams\":[{\"codec_name\":\"h264\",\"codec_type\":\"video\",\"bit_rate\":128000,\"r_frame_rate\":\"30/1\"},{\"index\":1,\"codec_name\":\"aac\",\"codec_type\":\"audio\",\"bit_rate\":128000}]}",
            	"ETag": "4cd930ae30ab73ec35c5c94b63ed0a5f"
        }],
        "status": "",
        "progress": 0
    }
}
其中，TaskID为任务ID，RefID为业务相关跟踪ID，M3u8Index字段保存m3u8文件内容（如果是hls协议）。Status表示
任务执行状态，3为成功，4为失败，0为处理中（只在扩展参数中设置时才会在未完成时周期性回调）。Type为任务类型，转码任务都是"avtrans"。
dstpath是输出文件的bucket下的相对路径，metainfo是媒体的元数据信息。

其他字段说明详见： https://docs.ksyun.com/documents/2408


```
