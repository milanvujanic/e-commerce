package com.twognation.ecommerce.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
public class OrderProductPK implements Serializable {

    private static final long serialVersionUID = 476151177562655457L;

    @JoinColumn(name = "orders_id")
    private Long ordersId;

    @JoinColumn(name = "product_id")
    private Long productId;

    public OrderProductPK() {
    }

    public OrderProductPK(Long ordersId, Long productId) {
        this.ordersId = ordersId;
        this.productId = productId;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((ordersId == null)
          ? 0
          : ordersId.hashCode());
        result = prime * result + ((productId == null)
          ? 0
          : productId.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderProductPK other = (OrderProductPK) obj;
        if (ordersId == null) {
            if (other.ordersId != null) {
                return false;
            }
        } else if (!(ordersId.longValue() == other.ordersId.longValue())) {
            return false;
        }

        if (productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!(productId.longValue() == other.productId.longValue())) {
            return false;
        }

        return true;
    }
}
