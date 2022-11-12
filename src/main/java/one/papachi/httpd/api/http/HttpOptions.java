package one.papachi.httpd.api.http;

import java.util.Set;

public interface HttpOptions {

    Set<HttpOption<?>> supportedOptions();

    <T> T getOption(HttpOption<T> name);

    <T> HttpOptions setOption(HttpOption<T> name, T value);

}
