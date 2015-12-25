package com.liefeng.core.exception;

import java.text.MessageFormat;

/**
 * 异常父类，所有的异常类都继承该类
 * @author Huangama
 * @date 2015-12-04
 */
public class LiefengException extends RuntimeException {

	private static final long serialVersionUID = 6961926234532027069L;

	/**
	 * 异常码
	 */
	private String code;
	
	public LiefengException(String message) {
        super(message);
    }

    public LiefengException(Throwable cause) {
        super(cause);
    }

    public LiefengException(String message, Throwable cause) {
        super(message, cause);
    }
	
    public LiefengException() {		
		super();
	}

	public LiefengException(String code, String msg) {
		this(msg);
		this.code = code;
	}

	public LiefengException(Enum<?> en) {		
		this(en.name(), en.toString());
	}
	
	public LiefengException(Enum<?> en, Object ... args) {
    	this(en.name(), MessageFormat.format(en.toString(), args));
    }
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
