package one.papachi.httpd.api.http;

import one.papachi.httpd.api.spi.HttpClientProvider;
import one.papachi.httpd.api.spi.HttpDataProvider;

import java.util.ServiceLoader;

public interface HttpHeader {

    static HttpHeader.Builder getBuilder() {
        return ServiceLoader.load(HttpDataProvider.class).findFirst().map(HttpDataProvider::getHttpHeaderBuilder).orElse(null);
    }

    interface Builder {

        default Builder headerLine(String line) {
            String[] split = line.split(":\\s+", 2);
            String name = split[0].trim();
            String value = split.length == 2 ? split[1].trim() : "";
            name(name);
            value(value);
            return this;
        }

        Builder name(String name);

        Builder value(String value);

        HttpHeader build();

    }

    String getHeaderLine();

    String getName();

    String getValue();

}
