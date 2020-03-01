package com.ksyun.ket;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import org.apache.commons.io.IOUtils;

//打印任务回调请求body
public class CallbackServer {

    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        server.createContext("/cb", new TestHandler());
        server.start();
    }

    static  class TestHandler implements HttpHandler{

        public void handle(HttpExchange exchange) throws IOException {
            String response = "Ok";
            //获得查询字符串(get)
            //String queryString =  exchange.getRequestURI().getQuery();
            //Map<String,String> queryStringInfo = formData2Dic(queryString);
            //获得表单提交数据(post)
            String postString = IOUtils.toString(exchange.getRequestBody());
            System.out.print(postString);

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


}