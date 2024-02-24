package DataPackage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//        read json to string, StandardCharsets.UTF_8 is encoding format
        String JsonContent = FileUtils.readFileToString
                (new File(filePath),
                StandardCharsets.UTF_8);

//        Converting String to HashMap to feed to the DataProvider Method
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;

    }
}
