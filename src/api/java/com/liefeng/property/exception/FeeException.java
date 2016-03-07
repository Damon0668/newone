package com.liefeng.property.exception;

import com.liefeng.core.exception.LiefengException;

/**
 *  抄表费用模块异常类
 * @author wuzhijing
 *
 */
public class FeeException extends LiefengException {

	private static final long serialVersionUID = 3935006586458842095L;

	public FeeException() {		
		super();
	}
	
	public FeeException(String message) {
        super(message);
    }
	
	public FeeException(String code, String message) {
        super(code, message);
    }

    public FeeException(Throwable cause) {
        super(cause);
    }

    public FeeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FeeException(Enum<?> en) {
    	super(en);
    }
    
    public FeeException(Enum<?> en, Object ... args) {
    	super(en, args);
    }
}
