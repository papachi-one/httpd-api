package one.papachi.httpd.api.http;

public interface HttpOption<T> {

    String name();

    Class<T> type();

    T defaultValue();

}
