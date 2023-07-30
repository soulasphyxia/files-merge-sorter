package soulasphyxia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSort{

    public static void mergeFiles(List<BufferedReader> readers,
                                  BufferedWriter writer,
                                  Comparator<FileData> cmp) throws IOException
    {
        PriorityQueue<FileData> priorityQueue = new PriorityQueue<>(cmp);
        for(BufferedReader reader : readers) {
            String line = reader.readLine();
            if (line != null){
                line = parseLine(line);
                FileData data = new FileData(line,reader);
                priorityQueue.add(data);
            }
        }

        while (!priorityQueue.isEmpty()) {
            FileData minData = priorityQueue.poll();
            if (!minData.getStringData().equals("") && minData.isValid()) {
                writer.write(minData.getStringData());
                writer.newLine();
            }
            String nextLine = minData.getReader().readLine();
            if(nextLine != null) {
                nextLine = parseLine(nextLine);
                FileData data = new FileData(nextLine,minData.getReader());
                priorityQueue.add(data);
            }
        }
        writer.close();
    }

    private static String parseLine(String line) {
        line = line.trim();
        StringBuilder lineStringBuilder = new StringBuilder();
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ' ') {
                break;
            }
            lineStringBuilder.append(line.charAt(i));
        }
        return lineStringBuilder.toString().trim();
    }

}
