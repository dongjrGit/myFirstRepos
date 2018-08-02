package com.music.exception;

/**
 * 自定义异常类 MusicException
 * @author djr
 *
 */
public class MusicException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//异常信息
    public String message;

    public MusicException(String message) {
        super(message);  
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
