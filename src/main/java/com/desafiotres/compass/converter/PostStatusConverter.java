package com.desafiotres.compass.converter;

import com.desafiotres.compass.enums.PostStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PostStatusConverter implements AttributeConverter<PostStatus, String> {

    @Override
    public String convertToDatabaseColumn(PostStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public PostStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return PostStatus.valueOf(dbData);
    }
}