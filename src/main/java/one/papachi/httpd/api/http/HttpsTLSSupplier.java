package one.papachi.httpd.api.http;

import javax.net.ssl.SSLEngine;
import java.util.function.Supplier;

public interface HttpsTLSSupplier extends Supplier<SSLEngine> {
}
