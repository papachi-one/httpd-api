package one.papachi.httpd.api.http;

import java.util.List;

public interface HttpMultiPartFormDataBody extends HttpBody {

    interface Builder {

        Builder addPart(HttpPart part);

        HttpMultiPartFormDataBody build();

    }

    List<HttpPart> getParts();

}
