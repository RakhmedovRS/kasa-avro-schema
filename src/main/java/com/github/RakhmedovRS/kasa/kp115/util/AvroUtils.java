package com.github.RakhmedovRS.kasa.kp115.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecord;

import java.io.*;

@Slf4j
public class AvroUtils {
    private AvroUtils() {
    }

    public static <T extends SpecificRecord> T readAvroJson(byte[] bytes, Class<T> clazz) {
        try {
            return readAvroJson(new ByteArrayInputStream(bytes), clazz);
        } catch (Exception e) {
            log.error("Error during translating json to avro", e);
            throw new RuntimeException(e);
        }
    }

    public static <T extends SpecificRecord> T readAvroJson(InputStream inputStream, Class<T> clazz) throws IOException {
        T result;
        try {
            result = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        readAvroJson(inputStream, result);
        return result;
    }

    public static void readAvroJson(InputStream inputStream, SpecificRecord specificRecord) throws IOException {
        DatumReader reader = new SpecificDatumReader(specificRecord.getClass());
        Decoder decoder = DecoderFactory.get().jsonDecoder(specificRecord.getSchema(), inputStream);
        reader.read(specificRecord, decoder);
    }

    public static String writeAvroJson(GenericRecord genericRecord) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            writeAvroJson(outputStream, genericRecord);
        } catch (IOException e){
            log.error("Error during transforming avro object to json");
            throw new RuntimeException(e);
        }
        return outputStream.toString();
    }

    public static void writeAvroJson(OutputStream outputStream, GenericRecord genericRecord) throws IOException {
        Schema schema = genericRecord.getSchema();
        DatumWriter writer = new GenericDatumWriter(schema);
        Encoder encoder = EncoderFactory.get().jsonEncoder(schema, outputStream);
        writer.write(genericRecord, encoder);
        encoder.flush();
    }
}
