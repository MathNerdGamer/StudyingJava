// A matrix class. Non-generic.
package Matrix;

public class Matrix {
    // Instance variables.
    private long[][] matrix;
    private int rows;
    private int columns;

    // Default constructor, constructs a 2x2 matrix.
    public Matrix() {
        this(2, 2);
    }

    // Square matrix constructor, constructors an NxN matrix.
    public Matrix(int N) {
        this(N, N);
    }

    // Constructor with custom row and column count.
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Matrix of size " + rows + " x " + columns + " is invalid.");
        }

        matrix = new long[rows][columns];
    }

    public int rowCount() {
        return rows;
    }

    public int columnCount() {
        return columns;
    }

    public long get(int row, int column) {
        return matrix[row][column];
    }

    public void set(int row, int column, long value) {
        matrix[row][column] = value;
    }

    public Matrix add(Matrix B) {
        if (B == null) {
            throw new NullPointerException("Null pointer.\n");
        }

        if (rows != B.rowCount() || columns != B.columnCount()) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition.");
        }

        Matrix result = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, matrix[i][j] + B.get(i, j));
            }
        }

        return result;
    }

    public Matrix subtract(Matrix B) {
        if (B == null) {
            throw new NullPointerException("Null pointer.\n");
        }

        if (rows != B.rowCount() || columns != B.columnCount()) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for subtraction.");
        }

        Matrix result = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, matrix[i][j] - B.get(i, j));
            }
        }

        return result;
    }

    public Matrix multiply(Matrix B) {
        if (B == null) {
            throw new NullPointerException("Null pointer.\n");
        }

        if (columns != B.rowCount()) {
            throw new IllegalArgumentException("Matrices have incompatible dimensions.");
        }

        Matrix result = new Matrix(rows, B.columnCount());

        // ikj loop for locality.
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < B.rowCount(); k++) {
                for (int j = 0; j < columns; j++) {
                    result.set(i, j, result.get(i, j) + matrix[i][k] * B.get(k, j));
                }
            }
        }

        return result;
    }

    public Matrix negate() {
        Matrix result = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, -matrix[i][j]);
            }
        }

        return result;
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result += matrix[i][j];

                if (j < columns - 1) {
                    result += ", ";
                }
            }
            result += "\n";
        }

        return result;
    }

    public Matrix copy() {
        Matrix result = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, matrix[i][j]);
            }
        }

        return result;
    }

    public static Matrix identityMatrix(int N) {
        Matrix id = new Matrix(N);

        for (int i = 0; i < N; i++) {
            id.set(i, i, 1);
        }

        return id;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Matrix)) {
            return false;
        }

        Matrix B = (Matrix) o;

        if (rows != B.rowCount() || columns != B.columnCount()) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] != B.get(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Test method.
    public static void main(String[] args) {
        Matrix m1 = new Matrix(4);
        Matrix m2 = new Matrix(4);
        Matrix m3 = new Matrix(3, 4);

        try {
            System.out.println("Bad matrix 1 (rowCount = 0):");
            Matrix badMatrix1 = new Matrix(0, 1);
            System.out.println(badMatrix1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("\nBad matrix 2 (columnCount = 0):");
            Matrix badMatrix2 = new Matrix(1, 0);
            System.out.println(badMatrix2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < m1.rowCount(); i++) {
            for (int j = 0; j < m1.columnCount(); j++) {
                m1.set(i, j, i * j + 1);
                m2.set(i, j, 2 * i + 3 * j);
            }
        }

        System.out.println("\nMatrix m1:");
        System.out.println(m1);

        System.out.println("Matrix m2:");
        System.out.println(m2);

        System.out.println("m1 + m2:");
        Matrix sum = m1.add(m2);
        System.out.println(sum);

        System.out.println("m1 - m2:");
        sum = m1.subtract(m2);
        System.out.println(sum);

        try {
            System.out.println("m1 + m3 (wrong dimensions):");
            m1.add(m3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("\nm1 - m3 (wrong dimensions):");
            m1.subtract(m3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nm1 * m2:");
        Matrix product = m1.multiply(m2);
        System.out.println(product);

        try {
            System.out.println("m1 * m3 (wrong dimensions):");
            m1.multiply(m3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
