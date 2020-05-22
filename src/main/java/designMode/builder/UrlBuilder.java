package designMode.builder;


import com.sun.javafx.collections.MappingChange;
import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: url Builder
 * @author: kuroneko
 * @create: 2020-05-12 16:23
 **/
public class UrlBuilder {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("a","b");
        map.put("c","d");
        String https = UrlBuilder.builder()
                .setScheme("https")
                .setDomain("www.baid.com")
                .setPath("xxxx/xxxx/yy")
                .setQuery(map)
                .build();
        System.out.println(https);

    }

    private String domain;
    private String scheme;
    private String path;
    private Map<String,String> query;

    public static UrlBuilder builder(){
        UrlBuilder urlBuilder = new UrlBuilder();
        urlBuilder.setScheme("http");
        return urlBuilder;
    }

    public UrlBuilder setDomain(String domain) {
        if(stringIsBlank(domain)){
            throw new RuntimeException("domain为空");
        }
        this.domain = domain;
        return this;
    }

    public UrlBuilder setScheme(String scheme) {
        if(stringIsBlank(domain)){
            scheme = "http";
        }
        this.scheme = scheme;
        return this;
    }

    public UrlBuilder setPath(String path) {
        if(stringIsBlank(domain)){
            path="";
        }
        this.path = path;
        return this;
    }

    public UrlBuilder setQuery(Map<String,String> query) {
        this.query = query;
        return this;
    }

    public String build(){
        if(stringIsBlank(this.domain)){
            throw new RuntimeException("domain为空");
        }
        StringBuilder url = new StringBuilder();
        url.append(this.scheme).append("://")
                .append(this.domain).append("/");
        if(!stringIsBlank(this.path)){
            url.append(this.path);
        }
        if(!this.query.isEmpty()){
            url.append("?");
            this.query.forEach((k,v)->{
                url.append(k).append("=").append(v).append("&");
            });
            int i = url.lastIndexOf("&");
            url.delete(i,i+1);
        }

        return url.toString();

    }

    private boolean stringIsBlank(String s){
        return Objects.isNull(s) || "".equals(s);
    }

}
