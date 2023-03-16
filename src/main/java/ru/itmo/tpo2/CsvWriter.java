package ru.itmo.tpo2;

import com.opencsv.CSVWriter;
import ru.itmo.tpo2.logarithm.*;
import ru.itmo.tpo2.trigometry.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvWriter {

    private static final String path = "src/main/resources/";

    private final static Map<String, String> funcFiles = new HashMap<>() {{
        put("sin", "sin.csv");
        put("cos", "cos.csv");
        put("tan", "tan.csv");
        put("cot", "cot.csv");
        put("trig", "trig.csv");
        put("ln", "ln.csv");
        put("log3", "log3.csv");
        put("log5", "log5.csv");
        put("log10", "log10.csv");
        put("log", "log.csv");
        put("system", "system.csv");
    }};

    public static void write(String funcName, double start, double end, double step) {
        List<String[]> data = new ArrayList<>();
        if (funcName.equals("sin")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Sin.calcSin(i))});
        }
        if (funcName.equals("cos")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Cos.calcCos(i))});
        }
        if (funcName.equals("tan")){
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Tan.calcTan(i))});
        }
        if (funcName.equals("cot")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Cot.calcCot(i))});
        }
        if (funcName.equals("trig")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(TrigonometryFunc.calc(i))});
        }
        if (funcName.equals("ln")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Ln.calcLn(i))});
        }
        if (funcName.equals("log3")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Log3.calcLog3(i))});
        }
        if (funcName.equals("log5")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Log5.calcLog5(i))});
        }
        if (funcName.equals("log10")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(Log10.calcLog10(i))});
        }
        if (funcName.equals("log")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(LogarithmFunc.calc(i))});
        }
        if (funcName.equals("system")) {
            for (double i = start; i <= end; i += step)
                data.add(new String[]{String.valueOf(i), String.valueOf(SystemFunc.calc(i))});
        }
        write(data, funcFiles.get(funcName));
    }

    private static void write(List<String[]> data, String fileName) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(path + fileName));
            data.forEach(writer::writeNext);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
