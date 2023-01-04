package com.AthenaML.NeuralNetworkJava;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import tech.tablesaw.api.BooleanColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.io.File;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @author Wayne Sidney
 * Created on {22/12/2022}
 */
public class DataPreprocessing {
    public static void dataProcessing(File file) {
        Table data = Table.read().csv(file);

        //input variables
        Table inputs = data.create(data.columns());
        inputs.removeColumns("Country", "League", "Season", "Date", "Time");

        //output variables
        Table outputs = data.selectColumns("Res");

        //one-hot encoding the results column
        StringColumn results = (StringColumn) outputs.column("Res");

        List<BooleanColumn> dummies = results.getDummies();
        outputs.removeColumns("Res");
        outputs.addColumns(DoubleColumn.create("Home", dummies.get(0).asDoubleArray()),
                DoubleColumn.create("Draw", dummies.get(1).asDoubleArray()),
                DoubleColumn.create("Away", dummies.get(2).asDoubleArray())
        );

        NDManager ndManager = NDManager.newBaseManager();
        NDArray X  = ndManager.create(inputs.as().doubleMatrix());
        NDArray Y = ndManager.create(outputs.as().doubleMatrix());
        System.out.println(X.getShape());
        System.out.println(Y.getShape());

    }
}
