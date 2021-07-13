package com.lucaspq;

public class Letter {

    private Character c;
    private Boolean chosen;

    public Letter(Character c) {
        this.c = c;
        this.chosen = false;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public Boolean getChosen() {
        return chosen;
    }

    public void setChosen(Boolean chosen) {
        this.chosen = chosen;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((c == null) ? 0 : c.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Letter other = (Letter) obj;
        if (c == null) {
            if (other.c != null)
                return false;
        } else if (Character.toUpperCase(c) != Character.toUpperCase(other.c))
            return false;
        return true;
    }

    

}