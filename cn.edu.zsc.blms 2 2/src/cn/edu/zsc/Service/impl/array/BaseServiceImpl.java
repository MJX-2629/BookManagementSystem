package cn.edu.zsc.Service.impl.array;

import cn.edu.zsc.blms.App;
import cn.edu.zsc.blms.exception.EntityNotExistException;
import cn.edu.zsc.domain.BaseEntity;

public abstract class BaseServiceImpl<B extends BaseEntity> {
    protected final BaseEntity[] DATA;
    protected int idCounter;

    public BaseServiceImpl() {
        DATA = new BaseEntity[100];
    }

    protected BaseEntity getOne() throws EntityNotExistException {
        BaseEntity entity;
        try {
            int id = Integer.parseInt(App.input.nextLine());
            if (id >= idCounter || DATA[id] == null) {
                throw new EntityNotExistException();
            }
            entity = DATA[id];
        } catch (NumberFormatException e) {
            throw new EntityNotExistException("ID为整数，请输入整数值：");
        }
        return entity;
    }
}
