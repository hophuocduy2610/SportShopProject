package com.project.orderservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1130177367196006416L;
	
	private Integer id;
    private int ordId;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailPK that = (OrderDetailPK) o;
        return getId() == that.getId() && Objects.equals(getOrdId(), that.getOrdId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrdId());
    }
}
