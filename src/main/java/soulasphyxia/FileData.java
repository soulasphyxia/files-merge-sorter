package soulasphyxia;
import lombok.Getter;
import lombok.Setter;
import java.io.BufferedReader;

@Getter
@Setter
public class FileData {
    private Integer integerData;
    private String stringData;
    private BufferedReader reader;
    private boolean isValid;
    public FileData(String stringData, BufferedReader reader) {
        this.stringData = String.valueOf(stringData);
        this.reader = reader;
        isValid = true;
        try{
            this.integerData = Integer.parseInt(stringData);
        }catch (Exception e){
            this.integerData = 0;
            this.isValid = false;
        }
    }
}
