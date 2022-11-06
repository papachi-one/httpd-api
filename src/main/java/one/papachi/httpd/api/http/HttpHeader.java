package one.papachi.httpd.api.http;

public interface HttpHeader {

    interface Builder {

        default Builder setHeaderLine(String line) {
            String[] split = line.split(":\\s+", 2);
            String name = split[0].trim();
            String value = split.length == 2 ? split[1].trim() : "";
            setName(name);
            setValue(value);
            return this;
        }

        Builder setName(String name);

        Builder setValue(String value);

        HttpHeader build();

    }

    String getHeaderLine();

    String getName();

    String getValue();

}
