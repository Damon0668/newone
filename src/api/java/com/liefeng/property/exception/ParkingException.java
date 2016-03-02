package com.liefeng.property.exception;

import com.liefeng.core.exception.LiefengException;

/**
 *  车位模块异常类
 * @author wuzhijing
 *
 */
public class ParkingException extends LiefengException {

	private static final long serialVersionUID = 3935006586458842095L;

	public ParkingException() {		
		super();
	}
	
	public ParkingException(String message) {
        super(message);
    }
	
	public ParkingException(String code, String message) {
        super(code, message);
    }

    public ParkingException(Throwable cause) {
        super(cause);
    }

    public ParkingException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ParkingException(Enum<?> en) {
    	super(en);
    }
    
    public ParkingException(Enum<?> en, Object ... args) {
    	super(en, args);
    }
}
