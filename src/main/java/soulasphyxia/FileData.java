package soulasphyxia;
import lombok.Getter;
import lombok.Setter;
import java.io.BufferedReader;

@Getter
@Setter
public class FileData {
    private Long longData;
    private String stringData;
    private BufferedReader reader;
    private boolean isValid;
    public FileData(String stringData, BufferedReader reader) {
        this.stringData = String.valueOf(stringData);
        this.reader = reader;
        isValid = true;
        try{
            this.longData = Long.parseLong(stringData);
        }catch (Exception e){
            this.longData = 0L;
            this.isValid = false;
        }
    }
}
