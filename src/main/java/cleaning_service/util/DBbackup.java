package cleaning_service.util;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DBbackup {
    public static boolean writeToCSVFile(String filename, TableModel tableModel) throws IOException {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter(filename);
            printWriter = new PrintWriter(fileWriter);

            // Write column headers
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                printWriter.print(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    printWriter.print(",");
                }
            }
            printWriter.println();

            // Write rows
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    printWriter.print(tableModel.getValueAt(i, j));
                    if (j < tableModel.getColumnCount() - 1) {
                        printWriter.print(",");
                    }
                }
                printWriter.println();
            }

            return true;
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

}
