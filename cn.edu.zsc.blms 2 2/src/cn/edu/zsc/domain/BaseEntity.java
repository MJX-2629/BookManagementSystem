package cn.edu.zsc.domain;

import java.util.Objects;

public class BaseEntity implements Comparable<BaseEntity>{
    public Integer id;

    @Override
    public int compareTo(BaseEntity o) {
        return this.id-o.id;
    }

    public BaseEntity() {

    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
