package one.papachi.httpd.api.http;

public enum HttpStatus {
    STATUS_100_CONTINUE(100, "Continue"),
    STATUS_101_SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    STATUS_200_OK(200, "OK"),
    STATUS_201_CREATED(201, "Created"),
    STATUS_202_ACCEPTED(202, "Accepted"),
    STATUS_203_NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    STATUS_204_NO_CONTENT(204, "No Content"),
    STATUS_205_RESET_CONTENT(205, "Reset Content"),
    STATUS_206_PARTIAL_CONTENT(206, "Partial Content"),
    STATUS_300_MULTIPLE_CHOICES(300, "Multiple Choices"),
    STATUS_301_MOVED_PERMANENTLY(301, "Moved Permanently"),
    STATUS_302_FOUND(302, "Found"),
    STATUS_303_SEE_OTHER(303, "See Other"),
    STATUS_304_NOT_MODIFIED(304, "Not Modified"),
    STATUS_305_USE_PROXY(305, "Use Proxy"),
    STATUS_306_SWITCH_PROXY(306, "Switch Proxy"),
    STATUS_307_TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    STATUS_308_PERMANENT_REDIRECT(308, "Permanent Redirect"),
    STATUS_400_BAD_REQUEST(400, "Bad Request"),
    STATUS_401_UNAUTHORIZED(401, "Unauthorized"),
    STATUS_402_PAYMENT_REQUIRED(402, "Payment Required"),
    STATUS_403_FORBIDDEN(403, "Forbidden"),
    STATUS_404_NOT_FOUND(404, "Not Found"),
    STATUS_405_METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    STATUS_406_NOT_ACCEPTABLE(406, "Not Acceptable"),
    STATUS_407_PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    STATUS_408_REQUEST_TIMEOUT(408, "Request Timeout"),
    STATUS_409_CONFLICT(409, "Conflict"),
    STATUS_410_GONE(410, "Gone"),
    STATUS_411_LENGTH_REQUIRED(411, "Length Required"),
    STATUS_412_PRECONDITION_FAILED(412, "Precondition Failed"),
    STATUS_413_PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    STATUS_414_URI_TOO_LONG(414, "URI Too Long"),
    STATUS_415_UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    STATUS_416_RANGE_NOT_SATISFIABLE(416, "Range Not Satisfiable"),
    STATUS_417_EXPECTATION_FAILED(417, "Expectation Failed"),
    STATUS_418_I_AM_A_TEAPOT(418, "I'm a teapot"),
    STATUS_421_MISDIRECTED_REQUEST(421, "Misdirected Request"),
    STATUS_422_UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    STATUS_425_TOO_EARLY(425, "Too Early"),
    STATUS_426_UPGRADE_REQUIRED(426, "Upgrade Required"),
    STATUS_428_PRECONDITION_REQUIRED(428, "Precondition Required"),
    STATUS_429_TOO_MANY_REQUESTS(429, "Too Many Requests"),
    STATUS_431_REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    STATUS_451_UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),
    STATUS_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    STATUS_501_NOT_IMPLEMENTED(501, "Not Implemented"),
    STATUS_502_BAD_GATEWAY(502, "Bad Gateway"),
    STATUS_503_SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    STATUS_504_GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    STATUS_505_HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),
    STATUS_506_VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    STATUS_510_NOT_EXTENDED(510, "Not Extended"),
    STATUS_511_NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required"),
    ;

    private int statusCode;

    private String reasonPhrase;

    HttpStatus(int statusCode, String reasonPhrase) {
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

}
