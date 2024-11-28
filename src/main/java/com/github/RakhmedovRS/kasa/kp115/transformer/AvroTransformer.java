package com.github.RakhmedovRS.kasa.kp115.transformer;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

public abstract class AvroTransformer<T extends SpecificRecordBase> {
    protected final DatumReader<T> reader;
    protected final DatumWriter<T> writer;

    public AvroTransformer(Schema schema) {
        reader = new GenericDatumReader<>(schema);
        writer = new GenericDatumWriter<>(schema);
    }

    public abstract T toAvro(String json);

    public abstract String toJson(T object);

    public abstract List<String> toJson(List<T> objects);
}
