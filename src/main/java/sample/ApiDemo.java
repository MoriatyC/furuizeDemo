package sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.EasyModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiDemo {
    /**
     * 根据部署环境分配的host，改变相应url
     */
    public static final String URL_SUCCESS = "http://127.0.0.1:8080/predict_success";
    public static final String URL_FAILED = "http://127.0.0.1:8080/predict_failed";
    public static final String URL_TEST = "http://127.0.0.1:8080/test";
    public static final String SUCCESS_KEY = "isSuccess";
    public static final String RESULT_KEY = "result";
    public static final String ERROR_KEY = "errorCode";
    private static final int OK = 200;
    public static void main(String[] args) {

        process(URL_SUCCESS, getPersonInfo());
        process(URL_FAILED, getPersonInfo());
    }

    public static void process(String url, EasyModel model) {
        try {
//            调用api
            String json = call(url, model);
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject.getBoolean(SUCCESS_KEY)) {
                /*
                 * 服务请求成功后，获得概率自定义操作
                 * */
                String probability = jsonObject.getString(RESULT_KEY);
                System.out.println("该样本为好人的概率" + probability);
            } else {
                /*
                 * 服务请求失败后，自定义容错机制
                 * */
                System.out.println("ErrorCode:" + jsonObject.getString(ERROR_KEY));
            }
        } catch (Exception e) {
            //custom log
            e.printStackTrace();
        }
    }

    /**
     * 获得需要判断的用户数据
     * @return 模型所需的用户数据
     */
    private static EasyModel getPersonInfo() {
        return new EasyModel();
    }

    public static String call(String url, EasyModel model) {
        //1.使用默认配置的httpclient(默认将自动执行跳转)
        CloseableHttpClient client = HttpClients.createDefault();


        //2.使用post方法进行请求
        HttpPost request = new HttpPost(url);


        // 3.将用户数据转为json通过post上传
        StringEntity entity = new StringEntity(JSON.toJSONString(model), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = null;
        try {
            //执行请求获得response
            response = client.execute(request);
            //4.获取response的实体内容,即api内容
            if (OK != response.getStatusLine().getStatusCode()) {
                //生产中此处改为日志记录服务请求异常
                System.out.println("request failed");
                return null;
            }
            HttpEntity httpEntity = response.getEntity();
//			//5.打印到控制台
            if (httpEntity != null) {
                return EntityUtils.toString(httpEntity, "UTF-8");
            }
            return null;

        } catch (IOException e) {
            // custom log
            return null;
        } catch (Exception e) {
            // custom log
            return null;
        }
    }
}
