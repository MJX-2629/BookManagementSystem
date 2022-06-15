package cn.edu.zsc.blms.service;

import cn.edu.zsc.blms.exception.EntityNotExistException;

public interface ReaderService {
    void create();

    void disable() throws EntityNotExistException;

    void update() throws EntityNotExistException;

    void query();
}
