package analisisespacial;
/*
Hector marzo 2020 
*/

public class Mascaras {
    public static final int[][] PrewittGX= new int[][]{
            {1,0,-1},
            {1,0,-1},
            {1,0,-1}
    };
    public static final int[][] PrewittGY = new int[][]{
            {1,  1, 1},
            {0,  0, 0},
            {-1,-1,-1}
    };
    public static final int[][] SobelGX= new int[][]{
            {-1,0,1},
            {-2,0,2},
            {-1,0,1}
    };
    public static final int[][] SobelGY = new int[][]{
            {-1, -2 , -1},
            {0,   0,   0},
            {1,   2,   1}
    };
    public static final int[][] KirschGX= new int[][]{
            {-3,-3, 5},
            {-3, 0, 5},
            {-3,-3, 5}
    };
    public static final int[][] KirschGY = new int[][]{
            {5,  5 , 5},
            {-3, 0, -3},
            {-3,-3, -3}
    };
    public static final int[][] RobertsGX= new int[][]{
            {-1, 1, 0},
            { 0, 0, 0},
            { 0, 0, 0}
    };
    public static final int[][] RobertsGY = new int[][]{
            {-1, 0, 0},
            { 0, 1, 0},
            { 0, 0, 0}
    };



}
