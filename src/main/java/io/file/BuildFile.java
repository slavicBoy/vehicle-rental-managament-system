package io.file;

import io.DataReader;
import io.InfoPrinter;

import javax.print.attribute.standard.PrinterInfo;
import java.util.InputMismatchException;

public class BuildFile {
    private InfoPrinter printer;
    private DataReader reader;

    public BuildFile(InfoPrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public SerializableManager build() {
        return new SerializableManager();
    }


}
