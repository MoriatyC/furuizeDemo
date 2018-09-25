package sample;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
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
    public static final String SUCCESS_KEY = "isSuccess";
    public static final String RESULT_KEY = "result";
    public static final String ERROR_KEY = "errorCode";
    private static final int OK = 200;

    public static void main(String[] args) {
        process(URL_SUCCESS);
        process(URL_FAILED);
    }
    public static void process(String url) {
        String json = call(url);
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (jsonObject.getBoolean(SUCCESS_KEY)) {
            /*
            * 服务请求成功后，获得概率自定义操作
            * */
            String probability = jsonObject.getString(RESULT_KEY);
            System.out.println("该样本为好人的概率" + probability);
        } else {
            //custom log
            System.out.println("ErrorCode:" + jsonObject.getString(ERROR_KEY));
        }
    }
    public static String call(String url) {
        //1.使用默认配置的httpclient(默认将自动执行跳转)
        CloseableHttpClient client = HttpClients.createDefault();


        //2.使用get方法进行请求
        HttpPost request = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            //执行请求获得response
            response = client.execute(request);
//	        response = client.execute(httpPost);
            //4.获取response的实体内容,即api内容
            if (OK != response.getStatusLine().getStatusCode()) {
                //生产中此处改为日志记录服务请求异常
                System.out.println("request failed");
                return null;
            }
            HttpEntity entity = response.getEntity();
//			//5.打印到控制台
            if(entity != null)
            {
                return EntityUtils.toString(entity,"UTF-8");
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
