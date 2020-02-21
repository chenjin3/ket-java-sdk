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

KSCKETClient ksc = new KSCKETClient("[私有化用户ID]","[转码系统API地址]");
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
String data2 = setTask("ksc_souhu", "qa-vod", "chenjin/video/test9/index.m3u8", "chenjin/video/8K/8k_test.mp4");//"liuhengxin/video/logo.mp4");
createTaskRequest.setData(data2);
CreateTasklResult createTasklResult = ksc.CreateTask(createTaskRequest);
System.out.println(createTasklResult);
/**
 * 准备创建任务的数据
 * @param preset  模板名
 * @param dst_bucket  目的bucket
 * @param dst_object_key  目的文件路径
 * @param src_object_key 源文件路径
 * @return
 * @throws JSONException
 */
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
    //data.put("ExtParam", "{\"passParams\":{\"hls_segment_type\":\"fmp4\",\"hls_playlist_type\":\"vod\"}}");
    data.put("ExtParam", "{\"passParams\":{\"movflags\":\"frag_keyframe+empty_moov\"}}");
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