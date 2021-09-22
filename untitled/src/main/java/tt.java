import org.junit.jupiter.api.Test;

public class tt {


    @Test
    public void mytest(){

        int h = 800;
        int w = 500;

        double r2 = h*h + w*w;
        System.out.println( "r2:" + r2 );

        double r = Math.sqrt( r2 = h*h + w*w );
        System.out.println( "R:" + r );

        double s = h/r;
        System.out.println( "S:" + s );

        double sin = Math.sin( s );
        System.out.println( "Sin:" + sin );

        double as = Math.asin( sin );
        System.out.println( "ArcSin:" +as );


        System.out.println( "DEG: " +Math.toDegrees( Math.asin( s )) );
        System.out.println( "DEG2: " +Math.toDegrees( Math.asin( h/r )) );
        System.out.println( "DEG3: " +Math.toDegrees( Math.asin( w/r )) );



        //System.out.println( arc );
        //System.out.println( arc2 );

    }



}



