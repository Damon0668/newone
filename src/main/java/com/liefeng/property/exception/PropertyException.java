package com.liefeng.property.exception;

import com.liefeng.core.exception.LiefengException;

/**
 * 物业模块异常类
 * @author Huangama
 * @date 2015-12-22
 */
public class PropertyException extends LiefengException {

	private static final long serialVersionUID = 3935006586458842095L;

	public PropertyException() {		
		super();
	}
	
	public PropertyException(String message) {
        super(message);
    }
	
	public PropertyException(String code, String message) {
        super(code, message);
    }

    public PropertyException(Throwable cause) {
        super(cause);
    }

    public PropertyException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PropertyException(Enum<?> en) {
    	super(en);
    }
    
    public PropertyException(Enum<?> en, Object ... args) {
    	super(en, args);
    }
}
