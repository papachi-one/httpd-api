package one.papachi.httpd.api.http;

public interface HttpHeader {

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
