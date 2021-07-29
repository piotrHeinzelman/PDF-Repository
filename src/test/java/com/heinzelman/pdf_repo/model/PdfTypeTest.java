package com.heinzelman.pdf_repo.model;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PdfTypeTest {

    @Test
    void values() {
        PdfType myType = PdfType.A;
        System.out.println( myType.name() + " : " + myType.ordinal() );

        PdfType myTypeao = PdfType.AO;
        System.out.println( myTypeao.name() + " : " + myTypeao.ordinal() );

        PdfType x = PdfType.valueOf("A");
        System.out.println( x.name() );

        PdfType y = PdfType.valueOf("A");
        System.out.println( x.name() );
        System.out.println( x.getDescription() );



        if(Set.of( PdfType.values()).contains("XX") );

    }

    @Test
    void valueOf() {
    }
}