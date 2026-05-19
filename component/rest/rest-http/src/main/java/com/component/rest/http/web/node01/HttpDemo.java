package com.component.rest.http.web.node01;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;

/**
 * @author shenlx
 * @description
 * @date 2024/12/12 16:29
 */
public class HttpDemo {
    /**
     * 使用 Httpclient 需要经过如下步骤
     * 1.创建 HttpClient
     * 2.创建 http 请求，如 HttpGet、HttpPost
     * 3.添加请求参数
     * 4.添加请求设置，如超时等
     * 5.使用 HttpClient 执行 http 请求
     * 6.读取返回内容并释放连接
     */

    /**
     * 创建HttpClient
     * 创建默认客户端：
     * CloseableHttpClient httpclient = HttpClients.createDefault();
     *
     * 一些重要的默认配置：
     * 默认连接池大小10，每域名最大连接5
     * 连接池中连接存活时间 connTimeToLive = -1 ，默认单位为毫秒，默认连接不失效
     * 域名验证器为 DefaultHostnameVerifier , 会验证域名
     * SSL 上下文为 SSLContext.getInstance("TLS")，没有使用密钥管理器(KeyManager)和信任管理器(TrustManager)
     */

    /**
     * 自定义客户端(失败不重试)
     *CloseableHttpClient client = HttpClients.custom().setRetryHandler((e, i, c) -> false).build();
     * 自定义连接池
     */
    public void customConnectionPool() throws Exception {
        //1.创建 https 需要的 SslContext 相关内容
        //1.1 创建 SslContext
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream("证书文件"), "密码".toCharArray());
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(TrustAllStrategy.INSTANCE)
                .loadKeyMaterial(ks, "证书密码".toCharArray()).build();
        //1.2 创建 SSLConnectionSocketFactory
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]{"SSLv3", "TLSv1.1", "TLSv1.2"}, null,
                NoopHostnameVerifier.INSTANCE);

        //2.创建连接池
        //2.1 构建协议 registry
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        //3.连接池针对所有连接、每域名的连接的数量设置
        poolingHttpClientConnectionManager.setMaxTotal(100);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);

        //4.创建client
        CloseableHttpClient client =
                HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();

        //CloseableHttpClient client = HttpClients.custom().setRetryHandler((e, i, c) -> false).build();
    }

    /**
     * 创建 HttpGet、HttpPost 请求
     */
    public void getAndPost(){
        //1.创建get请求
        HttpGet get = new HttpGet("https://www.baidu.com");

        //2.创建post请求
        HttpPost post = new HttpPost("https://www.baidu.com");

        //3.其他如 HttpPut、HttpOptions、HttpTrace、HttpDelete、HttpPatch
    }

    /**
     * 添加请求参数
     */
    public void addParams() throws IOException {
        HttpPost post = new HttpPost("https://www.baidu.com");
        //1.底层流,基础参数
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        //1.1添加参数内容
        InputStream bis = new ByteArrayInputStream("参数".getBytes());
        basicHttpEntity.setContent(bis);
        //1.2设置内容长度
        basicHttpEntity.setContentLength(bis.available());
        //1.3取消分块发送
        basicHttpEntity.setChunked(false);
        post.setEntity(basicHttpEntity);

        //2.字节码类型参数
        HttpEntity entity = new ByteArrayEntity("name=zhangsan&age=100".getBytes());
        post.setEntity(entity);

        //3.字符串类型参数
        entity = new StringEntity("name=zhangsan&age=100");
        post.setEntity(entity);

        //4.流式参数，用法与BasicHttpEntity类似，内容和长度严格匹配
        entity = new InputStreamEntity(bis,bis.available());
        post.setEntity(entity);

        //5.文件类型参数
        File file = new File("上传文件");
        entity = new FileEntity(file);
        post.setEntity(entity);

        //6.添加请求头
        post.addHeader("Content-Type","text/html;charset=UTF-8");

        Header contentType = new BasicHeader("Content-Type","text/html;charset=UTF-8");
        post.addHeader(contentType);

        Header host = new BasicHeader("Host","www.baidu.com");
        post.setHeaders(new Header[]{contentType,host});
    }

    /**
     * 添加请求设置
     * 超时时间说明
     * 超时类型                                    说明
     * connectionTimeout                         连接建立时间，即3次握手时间，默认值-1
     * socketTimeout                             连接后，数据传输过程中数据包之间间隔的最大时间，默认值-1
     * connectionRequestTimeout                  从连接池获取连接的超时时间，默认值-1
     *
     * socketTimeout 和 connectionRequestTimeout 如果不设置，请求会阻塞。
     * 但是 connectionTimeout 的情况有所不同，它依赖于各平台的 socket 超时时间设置。
     */
    public void requestConfig(){
        //1.配置RequestConfig
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000) //从连接池获取可用连接的超时时间，单位毫秒
                .setSocketTimeout(5000) //请求获取数据的超时时间
                .setConnectTimeout(4000) //连接超时时间
                .build();
        HttpPost post = new HttpPost("https://www.baidu.com");
        //2.设置到post请求当中
        post.setConfig(requestConfig);

        //也可以当作默认值，设置到client当中,此client都会按这个超时处理
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        //CloseableHttpClient client = HttpClients.custom().setRetryHandler((e, i, c) -> false).build();
    }




}
