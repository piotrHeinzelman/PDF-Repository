package com.heinzelman.pdf_repo.model;

public enum PdfType {
    A, AO, T, TO, C, CO;

    public String getDescription(){
        switch ( this ) {
            case A: return "Architektura:";
            case AO: return "Architektura okładka:";
            case T: return "Techniczny:";
            case TO: return "Techniczny okładka:";
            case C: return "Koszty:";
            case CO: return "Koszty okładka:";
            default: return "?";
        }
    }
}


