package db.operation;

import db.Column;
import db.Type;

/**
 * Implementation of the subtraction arithmetic operator '-'.
 */
public class Subtraction extends AbstractOperation {

    @Override
    protected Column<Double> operationFloat(String columnName, Column c1, Column c2) {
        Column<Double> result = new Column<>(columnName, c1.getType());
        if (c1.size() > c2.size()) {
            for (int i = 0; i < c1.size(); i++) {
                if (c1.isNaN(i) || c2.isNaN(i)) {
                    result.add(Type.NAN);
                } else if (c1.isNOVALUE(i) && c2.isNOVALUE(i)) {
                    result.add(Type.NOVALUE);
                } else if(c2.isNOVALUE(i)) { // c1 - 0
                    result.add((double) c1.get(i));
                } else if (i < c2.size()) {
                    if (c1.isNOVALUE(i)) { // 0 - c2
                        result.add(- (double) c2.get(i));
                    } else {
                        result.add((double) c1.get(i) - (double) c2.get(i));
                    }
                } else {
                    if (c1.isNOVALUE(i)) {
                        result.add(Type.NOVALUE);
                    } else {
                        result.add((double) c1.get(i));
                    }
                }
            }
        } else {
            for (int i = 0; i < c2.size(); i++) {
                if (c2.isNaN(i) || c1.isNaN(i)) {
                    result.add(Type.NAN);
                } else if (c2.isNOVALUE(i) && c1.isNOVALUE(i)) {
                    result.add(Type.NOVALUE);
                } else if(c1.isNOVALUE(i)) { // 0 - c2
                    result.add(- (double) c2.get(i));
                } else if (i < c1.size()) {
                    if (c2.isNOVALUE(i)) {
                        result.add((double) c1.get(i)); // c1 - 0
                    } else {
                        result.add((double) c1.get(i) - (double) c2.get(i));
                    }
                } else {
                    if (c2.isNOVALUE(i)) {
                        result.add(Type.NOVALUE);
                    } else {
                        result.add((double) c2.get(i));
                    }
                }
            }

        }

        return result;
    }

    @Override
    protected Column<Double> operationFloat(String columnName, Column column, double literal) {
        Column<Double> result = new Column<>(columnName, Type.FLOAT);
        for (int i = 0; i < column.size(); i++) {
            if (column.isNaN(i)) {
                result.add(Type.NAN);
            } else if (column.isNOVALUE(i)) {
                result.add(- literal);
            } else {
                result.add((double) column.get(i) - literal);
            }
        }

        return result;
    }

    protected Column<Integer> operationInt(String columnName, Column<Integer> c1,
                                           Column<Integer> c2) {
        Column<Integer> result = new Column<>(columnName, c1.getType());
        if (c1.size() > c2.size()) {
            for (int i = 0; i < c1.size(); i++) {
                if (c1.isNaN(i) || c2.isNaN(i)) {
                    result.add(Type.NAN);
                } else if (c1.isNOVALUE(i) && c2.isNOVALUE(i)) {
                    result.add(Type.NOVALUE);
                } else if (c2.isNOVALUE(i)) { // c1 - 0
                    result.add(c1.get(i));
                } else if (i < c2.size()) {
                    if (c1.isNOVALUE(i)) { // 0 - c2
                        result.add(- c2.get(i));
                    } else {
                        result.add(c1.get(i) - c2.get(i));
                    }
                } else {
                    if (c1.isNOVALUE(i)) {
                        result.add(Type.NOVALUE);
                    } else {
                        result.add(c1.get(i));
                    }
                }
            }
        } else {
            for (int i = 0; i < c2.size(); i++) {
                if (c2.isNaN(i) || c1.isNaN(i)) {
                    result.add(Type.NAN);
                } else if (c2.isNOVALUE(i) && c1.isNOVALUE(i)) {
                    result.add(Type.NOVALUE);
                } else if(c1.isNOVALUE(i)) { // 0 - c2
                    result.add(- c2.get(i));
                } else if (i < c1.size()) {
                    if (c2.isNOVALUE(i)) { // c1 - 0
                        result.add(c1.get(i));
                    } else {
                        result.add(c1.get(i) - c2.get(i));
                    }
                } else {
                    if (c2.isNOVALUE(i)) {
                        result.add(Type.NOVALUE);
                    } else {
                        result.add(c2.get(i));
                    }
                }
            }

        }

        return result;
    }

    @Override
    protected Column<Integer> operationInt(String columnName, Column<Integer> column, int literal) {
        Column<Integer> result = new Column<>(columnName, Type.INT);
        for (int i = 0; i < column.size(); i++) {
            if (column.isNaN(i)) {
                result.add(Type.NAN);
            } else if (column.isNOVALUE(i)) {
                result.add(- literal);
            } else {
                result.add(column.get(i) - literal);
            }
        }

        return result;
    }

}