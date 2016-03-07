package com.liefeng.property.exception;

import com.liefeng.core.exception.LiefengException;

/**
 *  工作台模块异常类
 * @author wuzhijing
 *
 */
public class WorkbenchException extends LiefengException {

	private static final long serialVersionUID = 3935006586458842095L;

	public WorkbenchException() {		
		super();
	}
	
	public WorkbenchException(String message) {
        super(message);
    }
	
	public WorkbenchException(String code, String message) {
        super(code, message);
    }

    public WorkbenchException(Throwable cause) {
        super(cause);
    }

    public WorkbenchException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public WorkbenchException(Enum<?> en) {
    	super(en);
    }
    
    public WorkbenchException(Enum<?> en, Object ... args) {
    	super(en, args);
    }
}
