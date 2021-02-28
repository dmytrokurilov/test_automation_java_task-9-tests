package com.epam.test.automation.java.practice9;

import java.text.DecimalFormat;

public class Matrix {

    private int rows;
    private int columns;
    private double[][] array;
    private static final String ACTION_2 = "Incompatible matrix sizes";

    private int getRows() { return rows;}
    public void setRows(int rows) { this.rows = rows;}

    private int getColumns() { return columns;}
    public void setColumns(int columns) { this.columns = columns;}

    public void setArray(double[][] array) { this.array = array;}

    public Matrix(int row, int column) throws MatrixException {

        if (row <= 0 || column <= 0) { throw new MatrixException (ACTION_2); }

        rows = row;
        columns = column;
        array = new double[rows][columns];

    }

    public Matrix(double[][] twoDimensionalArray) throws MatrixException {
        if (twoDimensionalArray != null) {
            rows = twoDimensionalArray.length;
            columns = twoDimensionalArray[0].length;
            this.array = new double[rows][columns];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++)
                    this.array[i][j] = twoDimensionalArray[i][j];
        }
        else throw new MatrixException(ACTION_2);
    }

    public final int rows() {
        return rows;
    }

    public final int columns() {
        return columns;
    }

    public double[][] twoDimensionalArrayOutOfMatrix() throws MatrixException {
        if (rows <= 0 || columns <= 0) { throw new MatrixException(ACTION_2); }
        double[][] buf = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                buf[i][j] = array[i][j];

        return buf;
    }

    public double getValue(int row, int column) throws MatrixException {
        if (row <= 0 || column <= 0) { throw new MatrixException(ACTION_2); }
        if (array != null) { return array[row][column]; } else { throw new MatrixException(ACTION_2); }
    }
    public void setValue(int row, int column, double newValue) throws MatrixException {
        if (row <= 0 || column <= 0) { throw new MatrixException(ACTION_2); }
        if (array != null) { array[row][column] = newValue; } else { throw new MatrixException(ACTION_2); }
    }

    public Matrix addition(Matrix matrix) throws MatrixException {
        if (matrix == null)
        {
            throw new MatrixException(ACTION_2);
        }
        if (rows <= 0 || columns <= 0) { throw new MatrixException(ACTION_2); }
        Matrix z = new Matrix(rows, columns);
        double value;

        if(rows == matrix.getRows() && columns == matrix.getColumns()) {
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    value = array[i][j] + matrix.getValue(i, j);
                    z.setValue((int) value, i, j);
                }
            }
            return z;
        }
        else
        {
            throw new MatrixException(ACTION_2);
        }
    }

    public Matrix subtraction(final Matrix matrix) throws MatrixException {
        if (matrix == null)
        {
            throw new MatrixException(ACTION_2);
        }

        if (rows <= 0 || columns <= 0) { throw new MatrixException(ACTION_2); }

        Matrix z = new Matrix(rows, columns);
        double value;

        if(rows == matrix.getRows() && columns == matrix.getColumns())
        {
            for(int i = 0; i < matrix.getRows(); i++)
            {
                for(int j = 0; j < matrix.getColumns(); j++)
                {
                    value = array[i][j] - matrix.getValue(i,j);
                    z.setValue((int) value,i,j);

                }
            }
            return z;
        }
        else
        {
            throw new MatrixException(ACTION_2);
        }
    }


    public Matrix multiplication(final Matrix matrix) throws MatrixException {
        if (matrix == null)
        {
            throw new MatrixException(ACTION_2);
        }
        if (rows <= 0 || columns <= 0) { throw new MatrixException(ACTION_2); }

            Matrix z = new Matrix(rows, matrix.getColumns());
            double value;


            if(columns == matrix.getRows())
            {
                for(int i = 0; i < rows; i++)
                {
                    for(int j = 0; j < matrix.getColumns(); j++)
                    {
                        double sum = 0;
                        for(int k = 0; k < rows; k++)
                        {
                            sum += array[i][k] * matrix.getValue(k,j);
                        }
                        value = sum;
                        z.setValue((int) value,i,j);
                    }

                }
                return z;
        }
        else
        {
            throw new MatrixException(ACTION_2);
        }
    }


    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                try {
                    if (j != this.columns() - 1) {
                        builder.append(decimalFormat.format(getValue(i, j)) + " ");
                    } else {
                        builder.append(decimalFormat.format(getValue(i, j)));
                    }
                } catch (MatrixException e) {
                    e.getMessage();
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}