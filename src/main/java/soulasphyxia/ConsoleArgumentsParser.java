package soulasphyxia;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ConsoleArgumentsParser {

    private final String[] args;

    private static final List<String> availableParams = new ArrayList<>(List.of("-a","-d","-s","-i"));

    private static final Predicate<String> filePredicate = (x) -> !x.equals(".txt") && x.endsWith(".txt");
    private static final Predicate<String> paramsPredicate = availableParams::contains;

    public List<String> getFiles() {
        return Arrays.stream(this.args)
                .filter(filePredicate)
                .collect(Collectors.toList());
    }

    public String getOutputFile() {
        String outputFileName = null;
        try{
            outputFileName = getFiles().get(0);
        }catch (Exception e) {
            System.out.println("Ошибка! Не указано имя выходного файла!");
        }
        return outputFileName;
    }

    public String getSortOrder() {
        String sortOrderParam;
        try {
            sortOrderParam = getParameters()
                    .stream()
                    .filter(param -> param.equals("-a") || param.equals("-d"))
                    .collect(Collectors.toList())
                    .get(0);
        }catch (Exception e) {
            sortOrderParam = "-a";
        }
        return sortOrderParam.equals("-a") ? "asc" : "desc";
    }

    public List<String> getParameters() {
        return Arrays.stream(this.args)
                .filter(paramsPredicate)
                .collect(Collectors.toList());
    }

    public String getDataType() {
        String dataType = null;
        try{
            dataType = getParameters()
                    .stream()
                    .filter(param -> param.equals("-i") || param.equals("-s"))
                    .collect(Collectors.toList())
                    .get(0);
        }catch (Exception e) {
            System.out.println("Ошибка! Не указан тип данных");
        }
        if(dataType != null) {
            dataType = dataType.equals("-i") ? "integer" : "string";
        }
        return dataType;
    }

    public List<String> getInputFiles() {
        int indexOfOutputFile = List.of(args).indexOf(getOutputFile());
        List<String> inputFiles = List.of(args).subList(indexOfOutputFile+1, args.length);
        if(inputFiles.size() < 1) {
            System.out.println("Ошибка! Указано менее 1 входного файла!");
        }
        return inputFiles;
    }

    public boolean checkUndefinedParams() {
        List<String> undefinedParams = Arrays.stream(this.args).filter(x -> !filePredicate.test(x) && !paramsPredicate.test(x))
                           .collect(Collectors.toList());
        if(undefinedParams.size() == 0){
            return true;
        }else{
            StringBuilder stringBuilder = new StringBuilder("Ошибка! Неизвестные параметр(ы): ");

            undefinedParams.forEach(param -> {
                stringBuilder
                        .append("\"")
                        .append(param)
                        .append("\", ");
            });

            stringBuilder.setCharAt(stringBuilder.length() - 2, '.');
            return false;
        }
    }


    public SortConfiguration getSortConfiguration() {
        SortConfiguration config = new SortConfiguration();
        if(validation()) {
            config.setSortOrder(getSortOrder());
            config.setDataType(getDataType());
            config.setOutputFileName(getOutputFile());
            config.setInputFileNames(getInputFiles());
        }else{
            config = null;
        }
        return config;
    }


    public boolean validation() {
        return getDataType() != null && getOutputFile() != null && getInputFiles().size() >= 1;
    }

}
