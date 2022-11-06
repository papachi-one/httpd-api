package one.papachi.httpd.api.http;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface HttpXWwwFormUrlEncodedBody extends HttpBody {

    interface Builder {

        Builder addParameter(String name, String value);

        default Builder addParameter(String name, List<String> values) {
            values.forEach(value -> addParameter(name, value));
            return this;
        }

        default Builder addParameter(String name, String[] values) {
            addParameter(name, Arrays.asList(values));
            return this;
        }

        HttpXWwwFormUrlEncodedBody build();

    }

    Map<String, List<String>> getParameters();

    String getParameterValue(String name);

    List<String> getParameterValues(String name);

}
