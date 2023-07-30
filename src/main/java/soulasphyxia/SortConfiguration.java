package soulasphyxia;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter

public class SortConfiguration {
    private String sortOrder = "asc";
    private String dataType;
    private String outputFileName;
    private List<String> inputFileNames;


    @Override
    public String toString() {
        return "Порядок сортировки='" + sortOrder + '\'' +
                ", тип данных='" + dataType + '\'' +
                ", имя выходного файла ='" + outputFileName + '\'' +
                ", имена входных файлов=" + inputFileNames;
    }
}
