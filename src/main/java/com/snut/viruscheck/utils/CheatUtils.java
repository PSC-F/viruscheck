package com.snut.viruscheck.utils;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CheatUtils {
    /**
     * 获取完美校园的身份认证
     *
     * @return
     */
    public static String getAuthor() throws URISyntaxException, IOException {
        String url = "https://job.17wanxiao.com/operation/pub/iface/userInfo?token=e8b71310-9374-4784-bbb6-761e0ac2f8e9";
//        String url = "https://reportedh5.17wanxiao.com/nCovReport/index.html?token=e8b71310-9374-4784-bbb6-761e0ac2f8e9&customerId=2250#";
        //构建请求实体,utf-8字符集
        String body = "userId=9630303&name=%E5%BC%A0%E9%B9%8F%E9%A3%9E&stuNo=1818042004&whereabouts=%E9%99%95%E8%A5%BF%E7%9C%81%2F%E5%92%B8%E9%98%B3%E5%B8%82&beenToWuhan=false&contactWithPatients=false&symptom=false&fever=false&cough=false&soreThroat=false&debilitation=false&diarrhea=false&cold=false&staySchool=false&contacts=18628579058&emergencyPhone=15991356818&address=%E6%B8%AD%E5%9F%8E%E5%8C%BA%E8%81%94%E7%9B%9F%E4%B8%80%E8%B7%AF%E8%A5%BF%E5%8C%97%E5%8C%BB%E7%96%97%E5%99%A8%E6%A2%B0%28%E9%9B%86%E5%9B%A2%29%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E7%94%9F%E6%B4%BB%E5%8C%BA-1%E5%8F%B7%E6%A5%BC&collegeId=33609&majorId=33624&classId=33625&classDescribe=%E6%95%B0%E5%AD%A6%E4%B8%8E%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6%E5%AD%A6%E9%99%A2-%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6%E4%B8%8E%E6%8A%80%E6%9C%AF%28%E4%B8%93%E5%8D%87%E6%9C%AC%29-%E8%AE%A1%E7%AE%97%E6%9C%BA%E4%B8%93%E5%8D%87%E6%9C%AC1801&temperature=36.5&confirmed=false&isolated=2&passingWuhan=false&passingHubei=false&patientSide=false&patientContact=false&mentalHealth=1&wayToSchool=false&backToSchool=false&haveBroadband=true&emergencyContactName=%E5%BC%A0%E5%BE%B7%E8%8D%A3&helpInfo=&passingCity=&token=e8b71310-9374-4784-bbb6-761e0ac2f8e9";
        StringEntity entity = new StringEntity(body, "utf-8");
        //创建HTTPCLIENT对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建访问地址
        URIBuilder uriBuilder = new URIBuilder(url);
        //创建Http对象
        HttpGet httpPost = new HttpGet(uriBuilder.build());
        //设置连接超时
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        //设置请求头
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        Set<Map.Entry<String, String>> entrySet = headers.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            // 设置到请求头到HttpRequestBase对象中
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String content = "";
        if (httpResponse.getEntity() != null) {
            content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        }
        return content;
    }

    public static String dopost() throws URISyntaxException, IOException {
//        String url = "https://job.17wanxiao.com/operation/pub/iface/userInfo?token=e8b71310-9374-4784-bbb6-761e0ac2f8e9";
        String url = "https://reportedh5.17wanxiao.com/api/reported/receive";
        //构建请求实体,utf-8字符集
        String body = "userId=9630303&name=%E5%BC%A0%E9%B9%8F%E9%A3%9E&stuNo=1818042004&whereabouts=%E9%99%95%E8%A5%BF%E7%9C%81%2F%E5%92%B8%E9%98%B3%E5%B8%82&beenToWuhan=false&contactWithPatients=false&symptom=false&fever=false&cough=false&soreThroat=false&debilitation=false&diarrhea=false&cold=false&staySchool=false&contacts=18628579058&emergencyPhone=15991356818&address=%E6%B8%AD%E5%9F%8E%E5%8C%BA%E8%81%94%E7%9B%9F%E4%B8%80%E8%B7%AF%E8%A5%BF%E5%8C%97%E5%8C%BB%E7%96%97%E5%99%A8%E6%A2%B0%28%E9%9B%86%E5%9B%A2%29%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E7%94%9F%E6%B4%BB%E5%8C%BA-1%E5%8F%B7%E6%A5%BC&collegeId=33609&majorId=33624&classId=33625&classDescribe=%E6%95%B0%E5%AD%A6%E4%B8%8E%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6%E5%AD%A6%E9%99%A2-%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6%E4%B8%8E%E6%8A%80%E6%9C%AF%28%E4%B8%93%E5%8D%87%E6%9C%AC%29-%E8%AE%A1%E7%AE%97%E6%9C%BA%E4%B8%93%E5%8D%87%E6%9C%AC1801&temperature=36.5&confirmed=false&isolated=2&passingWuhan=false&passingHubei=false&patientSide=false&patientContact=false&mentalHealth=1&wayToSchool=false&backToSchool=false&haveBroadband=true&emergencyContactName=%E5%BC%A0%E5%BE%B7%E8%8D%A3&helpInfo=&passingCity=&token=e8b71310-9374-4784-bbb6-761e0ac2f8e9";
        StringEntity entity = new StringEntity(body, "utf-8");
        //创建HTTPCLIENT对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建访问地址
        URIBuilder uriBuilder = new URIBuilder(url);
        //创建Http对象
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        //设置连接超时
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        //设置请求头
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        Set<Map.Entry<String, String>> entrySet = headers.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            // 设置到请求头到HttpRequestBase对象中
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }

        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String content = "";
        if (httpResponse.getEntity() != null) {
            content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        }
        return content;
    }
    public static void main(String[] args) throws IOException, URISyntaxException {

        System.out.println(getAuthor());
    }
}
