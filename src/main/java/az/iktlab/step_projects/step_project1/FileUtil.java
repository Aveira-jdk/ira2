package az.iktlab.step_projects.step_project1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileUtil {

    public static String parseData(List<Person> people) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(people);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(String data, String path) {
        File file = new File(path);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Person> readFromFile(String path) {
        File file = new File(path);

        try (InputStream is = new FileInputStream(file)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new InputStreamReader(is, StandardCharsets.UTF_8), new TypeReference<>() {
            });
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}