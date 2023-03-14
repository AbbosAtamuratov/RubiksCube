package Rubik;
import Rubik.Cell;

public class RubiksCube {
    private final int SIZE = 3;
    private Cell[][][] cells;

    public RubiksCube() {
        cells = new Cell[SIZE][SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    cells[i][j][k] = new Cell(' ');
                }
            }
        }
    }

    public void setCell(int i, int j, int k, char color) {
        cells[i][j][k].setColor(color);
    }

    public char getCell(int i, int j, int k) {
        return cells[i][j][k].getColor();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //печать верха
//        for (int j = 0; j < SIZE; j++) {
//            sb.append("   "); //отступ
//            for (int k = 0; k < SIZE; k++) {
//                sb.append(getCell(j, 0, k));
//            }
//            sb.append("\n");
//        }

        // печать развертки боков. левый-передний-правый-задний
        StringBuilder temp = new StringBuilder();

        //записываем левую грань
        for (int i = 0; i < SIZE; i++) {
            for (int k = 0; k < SIZE; k++) {
                temp.append(getCell(0, i, k));
            }
            temp.append('\n');
        }
        temp.append('\n');

        //записываем переднюю грань
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                temp.append(getCell(i, j, 0));
            }
            temp.append('\n');
        }
        temp.append('\n');

        //записываем правую грань
        for (int i = 0; i < SIZE; i++) {
            for (int k = 0; k < SIZE; k++) {
                temp.append(getCell(SIZE-1, i, k));
            }
            temp.append('\n');
        }
        temp.append('\n');

        //записываем заднюю грань
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                temp.append(getCell(i, j, SIZE-1));
            }
            temp.append('\n');
        }
        String crammedSides = temp.toString(); //здесь все 4 грани разделённые "\n\n"


        // теперь дробим каждую грань по \n и склеиваем развёртку
        sb.append(assembleSides(crammedSides));


        //печать низа
//        for (int j = 0; j < SIZE; j++) {
//            sb.append("   "); //отступ
//            for (int k = 0; k < SIZE; k++) {
//                sb.append(getCell(j, SIZE-1, k));
//            }
//            sb.append("\n");
//        }



        return sb.toString();
    }


    public void colorCubeToSolved(){

        // Set the front face to all blue
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                setCell(i, j, 0, 'B');
            }
        }

        // Set the back face to all green
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                setCell(i, j, SIZE-1, 'G');
            }
        }

        // Set the left face to all red
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                setCell(0, j, k, 'R');
            }
        }

        // Set the right face to all orange
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                setCell(SIZE-1, j, k, 'O');
            }
        }

        // Set the top face to all white
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                setCell(j, 0, k, 'W');
            }
        }

        // Set the bottom face to all yellow
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                setCell(j, SIZE-1, k, 'Y');
            }
        }
    }

    private String assembleSides(String allSides){
        String[] aS = allSides.split("\n\n");
        StringBuilder builder = new StringBuilder();
        String[] l = aS[0].split("\n");
        String[] f = aS[1].split("\n");
        String[] r = aS[2].split("\n");
        String[] b = aS[3].split("\n");
        builder.append(l[0]).append(f[0]).append(r[0]).append(b[0]).append('\n');
        builder.append(l[1]).append(f[1]).append(r[1]).append(b[1]).append('\n');
        builder.append(l[2]).append(f[2]).append(r[2]).append(b[2]).append('\n');
        return builder.toString();
    }
}
