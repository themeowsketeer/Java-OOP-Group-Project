package org.modernclient.javafx_test.model;

public class Rain {
    private float date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rain rain)) return false;

        return Float.compare(rain.getDate(), getDate()) == 0;
    }

    @Override
    public int hashCode() {
        return (getDate() != +0.0f ? Float.floatToIntBits(getDate()) : 0);
    }

    public float getDate() {
        return date;
    }

    public void setDate(float date) {
        this.date = date;
    }

    public Rain() {
    }
}
