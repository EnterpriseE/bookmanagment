package com.book.book.service.ex;

/** 用户数据不存在的异常 */
public class FoundException extends ServiceException {
    public FoundException() {
        super();
    }

    public FoundException(String message) {
        super(message);
    }

    public FoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoundException(Throwable cause) {
        super(cause);
    }

    protected FoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
