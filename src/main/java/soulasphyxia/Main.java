package soulasphyxia;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length >= 3){
            ConsoleArgumentsParser parser = new ConsoleArgumentsParser(args);

            AtomicBoolean readersFlag = new AtomicBoolean(true);

            if(parser.checkUndefinedParams()) {
                SortConfiguration config = parser.getSortConfiguration();
                if(config != null){
                    List<BufferedReader> readers = new ArrayList<>();
                    config.getInputFileNames().forEach(fileName -> {
                        try{
                            BufferedReader reader = new BufferedReader(new FileReader(fileName));
                            readers.add(reader);
                        } catch (FileNotFoundException e) {
                            readersFlag.set(false);
                            System.out.printf("Не удалось найти файл с именем '%s'%n",fileName);
                        }
                    });
                    if(readersFlag.get()){

                        BufferedWriter writer = new BufferedWriter(new FileWriter(config.getOutputFileName()));

                        Comparator<FileData> cmp = config.getDataType().equals("integer")
                                ? Comparator.comparing(FileData::getIntegerData)
                                : Comparator.comparing(FileData::getStringData);

                        String sortOrder = config.getSortOrder();
                        if(sortOrder.equals("asc")) {
                            MergeSort.mergeFiles(readers,writer,cmp);
                        }else {
                            MergeSort.mergeFiles(readers,writer,cmp.reversed());
                        }

                        System.out.println("Файлы отсортированы!");
                    }
                }
            }
        }else {
            System.out.println("Ошибка! Указаны не все параметры для сортировки");
        }
    }

}