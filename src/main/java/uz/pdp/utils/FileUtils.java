package uz.pdp.utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileUtils {

    private static final String PATH = "src/main/java/uz/pdp/data/";

    public static final ObjectMapper objectMapper;
    public static final XmlMapper xmlMapper;

    static {
        objectMapper = JsonMapper.builder()
                .enable(MapperFeature.PROPAGATE_TRANSIENT_MARKER)
                .build();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        xmlMapper = XmlMapper.builder()
                .enable(MapperFeature.PROPAGATE_TRANSIENT_MARKER)
                .build();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static <T> void writeToJson(String fileName, T object) throws IOException {
        objectMapper.writeValue(new File(PATH + fileName), object);
    }

    public static <T> List<T> readFromJson(String fileName, Class<T> clazz) throws IOException {
        try {
            return objectMapper.readValue(new File(PATH + fileName),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            if (e.getMessage().contains("No content to map due to end-of-input")) {
                writeToJson(fileName, new ArrayList<T>());
                return new ArrayList<>();
            }
            throw e;
        }
    }

    public static <T> void writeToXml(String fileName, T object) throws IOException {
        xmlMapper.writeValue(new File(PATH + fileName), object);
    }

    public static <T> List<T> readFromXml(String fileName, Class<T> clazz) throws IOException {
        try {
            return xmlMapper.readValue(new File(PATH + fileName),
                    xmlMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            if (e.getMessage().contains("No containt to map due to end-of-input")) {
                writeToXml(fileName, new ArrayList<>());
                return new ArrayList<>();
            }
            throw e;
        }
    }

}
