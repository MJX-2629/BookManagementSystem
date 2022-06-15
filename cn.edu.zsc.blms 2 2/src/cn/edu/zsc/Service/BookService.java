package cn.edu.zsc.Service;

import cn.edu.zsc.blms.exception.EntityNotExistException;

import java.text.ParseException;

public interface BookService {
    void create() throws ParseException;

    void disable() throws EntityNotExistException;

    void update();

    void query();
}


