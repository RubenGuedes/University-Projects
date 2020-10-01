/*
    Chapter 2 Sample Program: Displays the Monogram
    File: Step2/Ch2Monogram.java
*/

import java.util.*;

class Ch2Monogram {

    public static void main( String[] args ) {

        String  name, first, middle, last,
                space, monogram;

        space = " ";

        //Input the full name
        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter(System.getProperty("line.separator"));
        
        System.out.print("Enter your full name (first, middle, last):");
        
        name = scanner.next( );

        //Extract first, middle, and last names
        first  = name.substring(0, name.indexOf(space));
        name   = name.substring(name.indexOf(space)+1, name.length());

        middle = name.substring(0, name.indexOf(space));
        last   = name.substring(name.indexOf(space)+1, name.length());

        //Verify the substring operations
        System.out.println("First:"  + first);
        System.out.println("Middle:" + middle);
        System.out.println("Last:"   + last);

        //Compute the monogram
        monogram = first.substring(0, 1)
                    + middle.substring(0, 1)
                        + last.substring(0, 1);

        //Output the result
        System.out.println("Your monogram is " + monogram);
    }
}