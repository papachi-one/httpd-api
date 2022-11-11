package one.papachi.httpd.api.http;

public enum HttpVersion {
    HTTP_1_0("HTTP/1.0"), HTTP_1_1("HTTP/1.1"), HTTP_2("HTTP/2"), AUTO("HTTP/?");

    private String version;

    private HttpVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return version;
    }
}
