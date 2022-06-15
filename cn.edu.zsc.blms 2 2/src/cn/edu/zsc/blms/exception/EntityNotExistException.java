package cn.edu.zsc.blms.exception;

public class EntityNotExistException extends Exception {
    public EntityNotExistException() {
        super("数据不存在！");
    }

    public EntityNotExistException(String message) {
        super(message);
    }
}
