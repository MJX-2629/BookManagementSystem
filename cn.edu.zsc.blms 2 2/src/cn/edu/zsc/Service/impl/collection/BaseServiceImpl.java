package cn.edu.zsc.Service.impl.collection;

import cn.edu.zsc.blms.App;
import cn.edu.zsc.blms.exception.EntityNotExistException;
import cn.edu.zsc.domain.BaseEntity;

import java.util.Map;
import java.util.TreeMap;

public abstract class BaseServiceImpl<T extends BaseEntity> {
    protected final Map<Integer, T> DATA;

    protected int idCounter;

    public BaseServiceImpl() {
        DATA = new TreeMap<>();
    }

    protected T getOne() throws EntityNotExistException {
        T entity;
        try {
            int id = Integer.parseInt(App.input.nextLine());
            if (!DATA.containsKey(id)) {
                throw new EntityNotExistException();
            }
            entity = DATA.get(id);
        } catch (NumberFormatException e) {
            throw new EntityNotExistException("ID为整数，请输入整数值：");
        }
        return entity;
    }
}
