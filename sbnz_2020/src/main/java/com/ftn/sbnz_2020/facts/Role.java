package com.ftn.sbnz_2020.facts;

public enum Role {
    VET,
    ADMIN;

    @Override
    public String toString() {
        switch(this) {
            case VET: return "VET";
            case ADMIN: return "ADMIN";
            default: throw new IllegalArgumentException();
        }
    }
}
