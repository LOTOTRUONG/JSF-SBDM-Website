package vn.loto.jsf04.METIER.SubMetier;

import java.util.Objects;

public class Stock {
    private Integer stock;

    public Stock() {
        stock = 0;
    }

    public Stock(Integer stock) {
        this.stock = stock;
    }


    public Integer getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.valueOf(stock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock1 = (Stock) o;
        return stock == stock1.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock);
    }
}
