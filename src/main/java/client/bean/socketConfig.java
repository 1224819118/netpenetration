package client.bean;

/**
 * 代表了与客户端连接的一个socket和他的配置信息
 */
public class socketConfig {
    private String host;
    private Integer port;

    public socketConfig(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
